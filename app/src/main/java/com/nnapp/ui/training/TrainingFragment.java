package com.nnapp.ui.training;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.nnapp.R;
import com.nnapp.databinding.FragmentTrainingBinding;
import com.nnapp.network.ApiService;
import com.nnapp.network.ModelResponse;
import com.nnapp.network.TrainRequest;
import com.nnapp.network.TrainResponse;
import com.nnapp.utils.FileUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class TrainingFragment extends Fragment {

    private FragmentTrainingBinding binding;
    private ApiService apiService;
    private String featuresFilePath;
    private String targetsFilePath;
    private List<String> modelTypes;
    private static final int PICK_FEATURES_FILE = 1001;
    private static final int PICK_TARGETS_FILE = 1002;

    public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTrainingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        apiService = com.nnapp.network.RetrofitClient.getApiService();

        // Initialize UI
        initUI();

        // Load available models
        loadAvailableModels();

        return root;
    }

    private void initUI() {
        // Set up click listeners
        binding.btnUploadFeatures.setOnClickListener(v -> selectFeaturesFile());
        binding.btnUploadTargets.setOnClickListener(v -> selectTargetsFile());
        binding.btnStartTraining.setOnClickListener(v -> startTraining());

        // Initialize model spinner
        modelTypes = Arrays.asList(
            "Multilayer Perceptron",
            "Adaptive LM Backpropagation",
            "Radial Basis Function",
            "LSTM",
            "Bidirectional RNN",
            "Wavelet Neural Network",
            "Multitask Neural Network",
            "Graph Attention Network"
        );

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            modelTypes
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerModelType.setAdapter(adapter);

        // Set default values
        binding.etHiddenSize.setText("64");
        binding.etEpochs.setText("100");
        binding.etLearningRate.setText("0.001");
    }

    private void loadAvailableModels() {
        apiService.getModels().enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ModelResponse modelResponse = response.body();
                    if (modelResponse.success) {
                        // Update UI with available models
                        updateModelsInfo(modelResponse.models, modelResponse.info);
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load models: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateModelsInfo(List<String> models, java.util.Map<String, String> info) {
        // Update UI with model information
        // This could populate a list or update descriptions
    }

    private void selectFeaturesFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select Features File"), PICK_FEATURES_FILE);
    }

    private void selectTargetsFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select Targets File"), PICK_TARGETS_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            String filePath = FileUtils.getPath(requireContext(), uri);

            if (requestCode == PICK_FEATURES_FILE) {
                featuresFilePath = filePath;
                binding.tvFeaturesPath.setText("Features: " + FileUtils.getFileName(requireContext(), uri));
                uploadFile(filePath, "features");
            } else if (requestCode == PICK_TARGETS_FILE) {
                targetsFilePath = filePath;
                binding.tvTargetsPath.setText("Targets: " + FileUtils.getFileName(requireContext(), uri));
                uploadFile(filePath, "targets");
            }
        }
    }

    private void uploadFile(String filePath, String fileType) {
        File file = new File(filePath);
        RequestBody requestFile = RequestBody.create(file, MediaType.parse("multipart/form-data"));
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        apiService.uploadFile(body).enqueue(new Callback<com.nnapp.network.UploadResponse>() {
            @Override
            public void onResponse(Call<com.nnapp.network.UploadResponse> call, Response<com.nnapp.network.UploadResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    com.nnapp.network.UploadResponse uploadResponse = response.body();
                    if (uploadResponse.success) {
                        Toast.makeText(getContext(), fileType + " file uploaded successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Upload failed: " + uploadResponse.error, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<com.nnapp.network.UploadResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Upload error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startTraining() {
        if (featuresFilePath == null) {
            Toast.makeText(getContext(), "Please select features file", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show progress
        binding.progressTraining.setVisibility(View.VISIBLE);
        binding.btnStartTraining.setEnabled(false);
        binding.tvStatus.setText("Starting training...");

        // Create training request
        TrainRequest request = new TrainRequest();
        request.model_type = getModelTypeCode(binding.spinnerModelType.getSelectedItemPosition());
        request.task = "regression"; // Could be configurable
        request.hidden_size = Integer.parseInt(binding.etHiddenSize.getText().toString());
        request.epochs = Integer.parseInt(binding.etEpochs.getText().toString());
        request.lr = Double.parseDouble(binding.etLearningRate.getText().toString());
        request.optimizer = "Adam";
        request.train_pct = 70;
        request.val_pct = 15;
        request.test_pct = 15;
        request.use_two_files = targetsFilePath != null;
        request.xlsx_path = featuresFilePath;
        request.features_path = featuresFilePath;
        request.targets_path = targetsFilePath;

        apiService.trainModel(request).enqueue(new Callback<TrainResponse>() {
            @Override
            public void onResponse(Call<TrainResponse> call, Response<TrainResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    TrainResponse trainResponse = response.body();
                    if (trainResponse.success) {
                        binding.tvStatus.setText("Training started successfully!");
                        // Could navigate to results or start monitoring
                    } else {
                        binding.tvStatus.setText("Training failed: " + trainResponse.error);
                    }
                } else {
                    binding.tvStatus.setText("Training request failed");
                }
                
                binding.progressTraining.setVisibility(View.GONE);
                binding.btnStartTraining.setEnabled(true);
            }

            @Override
            public void onFailure(Call<TrainResponse> call, Throwable t) {
                binding.tvStatus.setText("Training error: " + t.getMessage());
                binding.progressTraining.setVisibility(View.GONE);
                binding.btnStartTraining.setEnabled(true);
            }
        });
    }

    private String getModelTypeCode(int position) {
        String[] codes = {"mlp", "lm", "rbf", "lstm", "birnn", "wavelet", "multitask", "gat"};
        if (position >= 0 && position < codes.length) {
            return codes[position];
        }
        return "mlp";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
