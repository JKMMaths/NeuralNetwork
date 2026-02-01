@echo off
echo ========================================
echo NN App Android - APK Builder
echo ========================================
echo.

echo Starting APK build process...
echo.

REM Check if in correct directory
if not exist "app\src\main\java\com\nnapp\MainActivity.java" (
    echo ❌ Not in Android Version directory!
    echo Please run this from: d:\NN App\Android Version\
    pause
    exit /b 1
)

echo Step 1: Cleaning previous builds...
if exist "app\build\outputs\apk\debug\app-debug.apk" (
    del "app\build\outputs\apk\debug\app-debug.apk"
    echo ✅ Old APK removed
)

echo.
echo Step 2: Building APK with Gradle...
echo This may take 3-5 minutes on first build...
echo.

REM Try to use system gradle if available
gradle assembleDebug --stacktrace --info 2>build_log.txt

if %errorlevel% equ 0 (
    echo.
    echo ✅ BUILD SUCCESSFUL!
    echo.
    
    REM Check if APK was created
    if exist "app\build\outputs\apk\debug\app-debug.apk" (
        echo 🎉 APK Created Successfully!
        echo.
        echo 📱 APK Details:
        echo Location: app\build\outputs\apk\debug\app-debug.apk
        echo Size:
        dir "app\build\outputs\apk\debug\app-debug.apk" | find "app-debug.apk"
        echo.
        echo ✅ You can now:
        echo 1. Install on Android device
        echo 2. Test the NN App features
        echo 3. Share the APK file
    ) else (
        echo ❌ APK file not found after build
        echo Check build_log.txt for details
    )
) else (
    echo.
    echo ❌ BUILD FAILED!
    echo.
    echo Common issues:
    echo 1. Gradle not installed
    echo 2. Java version compatibility
    echo 3. Android SDK missing
    echo 4. Network issues
    echo.
    echo Solutions:
    echo 1. Use Android Studio: Build → Build APK(s)
    echo 2. Install Gradle manually
    echo 3. Check Java version: java -version
    echo.
    echo Build log saved to: build_log.txt
    echo.
    echo 📱 Alternative: Use Android Studio to build
    echo File → Open → d:\NN App\Android Version
    echo Build → Build Bundle(s) / APK(s) → Build APK(s)
)

echo.
pause
