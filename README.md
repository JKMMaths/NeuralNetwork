# 📱 NN App - Android Version

## 🎯 **Project Overview**

A native Android application that integrates with the NN App neural network system, allowing users to train and use neural networks on mobile devices.

## 🏗️ **Architecture**

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Android App   │◄──►│  REST API       │◄──►│   Python NN     │
│   (Frontend)    │    │  (Flask)        │    │   Backend       │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 📁 **Project Structure**

```
Android Version/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/nnapp/
│   │   │   │   ├── MainActivity.java
│   │   │   │   ├── network/
│   │   │   │   │   ├── ApiService.java
│   │   │   │   │   └── RetrofitClient.java
│   │   │   │   ├── ui/
│   │   │   │   │   ├── dashboard/
│   │   │   │   │   ├── training/
│   │   │   │   │   ├── results/
│   │   │   │   │   └── settings/
│   │   │   │   └── utils/
│   │   │   │       └── FileUtils.java
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── values/
│   │   │   │   ├── drawable/
│   │   │   │   ├── menu/
│   │   │   │   ├── navigation/
│   │   │   │   └── xml/
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── settings.gradle
└── gradle.properties
```

## 🚀 **Features**

### **📊 Dashboard**
- Welcome screen with app overview
- Quick stats cards (models, training, results)
- Navigation to all main features
- Modern Material Design 3 UI

### **🧠 Training**
- Upload Excel files (.xlsx) for features and targets
- Configure neural network parameters:
  - Model type (8 different architectures)
  - Hidden size, epochs, learning rate
  - Training/validation/test split
- Real-time training progress
- Training status monitoring

### **📈 Results**
- Display training results and metrics
- Performance visualizations
- Model accuracy and loss curves
- Export results functionality

### **⚙️ Settings**
- Server configuration
- App preferences
- Connection settings

## 🔧 **Technologies Used**

### **Android**
- **Language**: Java
- **UI Framework**: Material Design 3
- **Architecture**: MVVM with Fragments
- **Navigation**: Navigation Component
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

### **Networking**
- **HTTP Client**: Retrofit 2.9.0
- **JSON Parsing**: Gson
- **File Upload**: Multipart
- **Timeout Handling**: OkHttp

### **UI Components**
- **Charts**: MPAndroidChart
- **Images**: Glide
- **File Picker**: System Intent
- **Permissions**: EasyPermissions

### **Backend Integration**
- **API**: Flask REST API
- **Neural Networks**: PyTorch
- **Data Processing**: Pandas, NumPy
- **File Handling**: Apache POI

## 📋 **Setup Instructions**

### **Prerequisites**
1. **Android Studio** (installed at `D:\Android`)
2. **Java 8+**
3. **Android SDK** (API 24+)
4. **Python 3.8+** (for backend API)

### **1. Backend Setup**
```bash
# Navigate to NN App directory
cd "d:\NN App"

# Install required Python packages
pip install flask flask-cors pandas numpy torch torchvision

# Start the API server
python api_server.py
```

The API server will start at: `http://0.0.0.0:5000`

### **2. Android Setup**
1. **Open Android Studio** (`D:\Android`)
2. **Open Project**: Navigate to `d:\NN App\Android Version`
3. **Sync Project**: Let Gradle sync dependencies
4. **Connect Device**: Enable USB debugging on your Android device
5. **Run App**: Click the Run button or use `./gradlew installDebug`

### **3. Configuration**
1. **Update Server URL**: In Settings tab, configure the server IP address
2. **Test Connection**: Ensure Android device can reach the API server
3. **Upload Data**: Use Excel files in the Training tab

## 🌐 **API Endpoints**

### **Models**
- `GET /api/models` - Get available neural network models

### **Training**
- `POST /api/train` - Start model training
- `GET /api/training/status/<id>` - Get training status
- `POST /api/training/stop/<id>` - Stop training
- `GET /api/training/results/<id>` - Get training results

### **Files**
- `POST /api/upload` - Upload data files
- `POST /api/predict` - Make predictions

### **Health**
- `GET /` - API health check

## 📱 **App Screens**

### **1. Dashboard Screen**
- Welcome message
- Model count display
- Quick action cards
- Navigation menu

### **2. Training Screen**
- File upload buttons
- Model configuration
- Training progress
- Status updates

### **3. Results Screen**
- Training metrics
- Performance charts
- Export options

### **4. Settings Screen**
- Server configuration
- App preferences
- Connection test

## 🔐 **Permissions Required**

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

## 🐛 **Troubleshooting**

### **Common Issues**

1. **Network Connection**
   - Ensure server is running on `http://0.0.0.0:5000`
   - Check firewall settings
   - Verify same WiFi network for device and server

2. **File Upload**
   - Grant storage permissions
   - Use valid Excel files (.xlsx)
   - Check file size limits

3. **Training Issues**
   - Verify data format
   - Check model parameters
   - Monitor API server logs

### **Debug Mode**
Enable debug mode in `api_server.py`:
```python
app.run(host='0.0.0.0', port=5000, debug=True)
```

## 📊 **Supported Neural Networks**

1. **Multilayer Perceptron (MLP)**
2. **Adaptive Levenberg-Marquardt Backpropagation**
3. **Radial Basis Function (RBF) Network**
4. **Long Short-Term Memory (LSTM)**
5. **Bidirectional RNN**
6. **Wavelet Neural Network**
7. **Multitask Neural Network**
8. **Graph Attention Network**

## 🎯 **Next Steps**

1. **Enhanced UI**: Add more visualizations
2. **Offline Mode**: Cache models for offline predictions
3. **Real-time Updates**: WebSocket integration
4. **Model Management**: Save/load trained models
5. **Advanced Analytics**: Detailed performance metrics

## 📞 **Support**

For issues and questions:
1. Check the troubleshooting section
2. Review API server logs
3. Verify network connectivity
4. Test with sample data files

---

**🎉 Ready to use NN App on Android!**
