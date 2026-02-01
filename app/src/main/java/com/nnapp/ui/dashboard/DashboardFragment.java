package com.nnapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.nnapp.R;
import com.nnapp.databinding.FragmentDashboardBinding;
import com.nnapp.network.ApiService;
import com.nnapp.network.ModelResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ApiService apiService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        apiService = com.nnapp.network.RetrofitClient.getApiService();

        // Initialize dashboard
        initDashboard();

        // Load data
        loadModelsInfo();

        return root;
    }

    private void initDashboard() {
        // Set up welcome message
        binding.tvWelcome.setText("Welcome to NN App Mobile");
        
        // Set up stats cards
        binding.cardModels.setOnClickListener(v -> {
            // Navigate to models info
        });

        binding.cardTraining.setOnClickListener(v -> {
            // Navigate to training
        });

        binding.cardResults.setOnClickListener(v -> {
            // Navigate to results
        });
    }

    private void loadModelsInfo() {
        apiService.getModels().enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ModelResponse modelResponse = response.body();
                    if (modelResponse.success) {
                        updateModelsCount(modelResponse.models.size());
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                // Handle error
                updateModelsCount(0);
            }
        });
    }

    private void updateModelsCount(int count) {
        binding.tvModelsCount.setText(String.valueOf(count));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
