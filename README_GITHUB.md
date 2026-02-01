# 📱 NN App - Android Version

## 🎯 **About**

Neural Network App for Android - Train and use neural networks on mobile devices!

## 🚀 **Features**

- 🧠 **8 Neural Network Architectures** (MLP, LSTM, RBF, etc.)
- 📊 **Real-time Training Progress**
- 📈 **Results Visualization**
- 📁 **Excel File Upload**
- 🌐 **REST API Integration**
- 🎨 **Material Design 3 UI**

## 📱 **Automatic APK Builds**

This repository uses **GitHub Actions** to automatically build APK files!

### **🔄 When Builds Trigger:**
- ✅ **Push to main/master branch**
- ✅ **Pull requests**
- ✅ **Manual trigger** (Actions tab → Run workflow)

### 📦 **Download APK**

1. **Go to Actions tab** in this repository
2. **Select "Build NN App APK" workflow**
3. **Click on the latest run**
4. **Download artifacts**:
   - `nn-app-debug-apk` (Debug version)
   - `nn-app-release-apk` (Release version - manual trigger)

## 🛠️ **Setup Instructions**

### **For Users:**
1. **Download APK** from Actions tab
2. **Install on Android device**
3. **Launch NN App**
4. **Configure server** in Settings tab

### **For Developers:**
1. **Clone this repository**
2. **Open in Android Studio**
3. **Run on device/emulator**
4. **Push changes** to trigger new builds

## 🔧 **Technical Details**

- **Minimum Android**: 7.0 (API 24)
- **Target Android**: 14 (API 34)
- **Language**: Java
- **UI Framework**: Material Design 3
- **Network**: Retrofit + REST API
- **Build Tool**: Gradle 9.0

## 📊 **Neural Networks Supported**

| Architecture | Type | Use Case |
|-------------|------|----------|
| MLP | Multilayer Perceptron | General purpose |
| LSTM | Long Short-Term Memory | Sequential data |
| RBF | Radial Basis Function | Function approximation |
| BiRNN | Bidirectional RNN | Sequential (bidirectional) |
| Wavelet | Wavelet Neural Network | Signal processing |
| Multitask | Multitask Learning | Multiple outputs |
| GAT | Graph Attention Network | Relational data |
| LM | Levenberg-Marquardt BP | Fast convergence |

## 🌐 **API Integration**

The app connects to a Python Flask API server:
- **Training endpoints**: `/api/train`
- **Model endpoints**: `/api/models`
- **File upload**: `/api/upload`
- **Predictions**: `/api/predict`

## 📞 **Support**

- 🐛 **Report issues**: Use GitHub Issues
- 💡 **Feature requests**: Use GitHub Discussions
- 📧 **Contact**: Check repository details

---

**🎉 Download your APK from the Actions tab above!**
