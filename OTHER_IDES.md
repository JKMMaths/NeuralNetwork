# 💻 Other IDEs for Android APK Building

## 🎯 **Option 1: Eclipse with ADT**

### **Setup:**
1. **Download Eclipse IDE for Enterprise Java**
2. **Install Android Development Tools (ADT) plugin**
3. **Import Android project**
4. **Build APK**

### **Steps:**
```
Help → Eclipse Marketplace → Search "Android" → Install ADT
File → Import → Android → Existing Android Code
Right-click project → Android Tools → Export Signed Application Package
```

## 🎯 **Option 2: IntelliJ IDEA Community**

### **Setup:**
1. **Download IntelliJ IDEA Community Edition**
2. **Install Android plugin**
3. **Import project**
4. **Build APK**

### **Steps:**
```
File → Open → Select Android Version folder
Wait for Gradle sync
Build → Build Artifacts → app-debug
```

## 🎯 **Option 3: VS Code with Android Extensions**

### **Setup:**
1. **Install VS Code**
2. **Install Android extensions**
3. **Configure build tasks**

### **Extensions:**
- Android iOS Emulator
- Flutter (for Android development)
- Gradle for Java

### **Build Command:**
```
Ctrl+Shift+P → Tasks: Run Task → gradle assembleDebug
```

## 🎯 **Option 4: NetBeans with Android Plugin**

### **Setup:**
1. **Download NetBeans**
2. **Install Android plugin**
3. **Import project**
4. **Build**

## 🎯 **Option 5: Command Line with AOSP**

### **Setup:**
1. **Download Android NDK**
2. **Setup build environment**
3. **Use build tools directly**

### **Commands:**
```bash
# Set Android SDK path
export ANDROID_HOME=/path/to/android-sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools

# Build APK
./gradlew assembleDebug
```

---

**🎯 Recommendation: IntelliJ IDEA Community Edition - it's free and powerful!**
