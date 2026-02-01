@echo off
echo ========================================
echo NN App Android - Build APK
echo ========================================
echo.

echo Building NN App Android APK...
echo.

REM Check if gradlew exists
if not exist "gradlew.bat" (
    echo ❌ gradlew.bat not found!
    echo This script must be run from the Android Version folder.
    echo.
    echo Please run this script from:
    echo d:\NN App\Android Version\
    pause
    exit /b 1
)

echo Step 1: Cleaning previous builds...
call gradlew clean
if %errorlevel% neq 0 (
    echo ❌ Clean failed!
    pause
    exit /b 1
)

echo.
echo Step 2: Building debug APK...
call gradlew assembleDebug
if %errorlevel% neq 0 (
    echo ❌ Build failed!
    echo.
    echo Common issues:
    echo 1. Java version compatibility
    echo 2. Android SDK not installed
    echo 3. Gradle sync issues
    echo.
    echo Try opening in Android Studio first.
    pause
    exit /b 1
)

echo.
echo ✅ Build completed successfully!
echo.

REM Check if APK was created
if exist "app\build\outputs\apk\debug\app-debug.apk" (
    echo 🎉 APK created successfully!
    echo Location: app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo APK Size:
    dir "app\build\outputs\apk\debug\app-debug.apk" | find "app-debug.apk"
    echo.
    echo You can now:
    echo 1. Install APK on Android device
    echo 2. Share APK file
    echo 3. Upload to app store
) else (
    echo ❌ APK file not found!
    echo Check build logs for errors.
)

echo.
pause
