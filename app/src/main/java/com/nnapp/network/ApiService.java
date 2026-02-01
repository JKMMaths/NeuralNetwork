package com.nnapp.network;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface ApiService {

    // Get available neural network models
    @GET("/api/models")
    Call<ModelResponse> getModels();

    // Train a neural network model
    @POST("/api/train")
    Call<TrainResponse> trainModel(@Body TrainRequest request);

    // Make predictions with trained model
    @POST("/api/predict")
    Call<PredictResponse> predict(@Body PredictRequest request);

    // Upload data files
    @Multipart
    @POST("/api/upload")
    Call<UploadResponse> uploadFile(@Part MultipartBody.Part file);

    // Get training status
    @GET("/api/training/status/{training_id}")
    Call<TrainingStatusResponse> getTrainingStatus(@Path("training_id") String trainingId);

    // Stop training
    @POST("/api/training/stop/{training_id}")
    Call<ApiResponse> stopTraining(@Path("training_id") String trainingId);

    // Get training results
    @GET("/api/training/results/{training_id}")
    Call<TrainingResultsResponse> getTrainingResults(@Path("training_id") String trainingId);
}

// Request/Response Models
class ModelResponse {
    public boolean success;
    public List<String> models;
    public Map<String, String> info;
    public String error;
}

class TrainRequest {
    public String model_type;
    public String task;
    public int hidden_size;
    public int epochs;
    public double lr;
    public String optimizer;
    public int train_pct;
    public int val_pct;
    public int test_pct;
    public boolean use_two_files;
    public String xlsx_path;
    public String target_col;
    public String features_path;
    public String targets_path;
}

class TrainResponse {
    public boolean success;
    public String training_id;
    public String message;
    public String error;
}

class PredictRequest {
    public String model_id;
    public List<List<Double>> data;
}

class PredictResponse {
    public boolean success;
    public List<Double> predictions;
    public String error;
}

class UploadResponse {
    public boolean success;
    public String filename;
    public String file_path;
    public List<String> columns;
    public int[] shape;
    public List<Map<String, Object>> preview;
    public String error;
}

class TrainingStatusResponse {
    public boolean success;
    public String status;
    public int progress;
    public int current_epoch;
    public int total_epochs;
    public double train_loss;
    public double val_loss;
    public String message;
    public String error;
}

class TrainingResultsResponse {
    public boolean success;
    public Map<String, Object> metrics;
    public List<Map<String, Object>> viz_data;
    public String error;
}

class ApiResponse {
    public boolean success;
    public String message;
    public String error;
}
