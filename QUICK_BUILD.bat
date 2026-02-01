@echo off
echo ========================================
echo NN App - Quick APK Builder
echo ========================================
echo.

echo This script will build APK using system tools
echo.

REM Check Java
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Java not found
    echo Please install Java from: https://adoptium.net/
    pause
    exit /b 1
)

echo ✅ Java found
java -version

REM Check if Android SDK tools are available
set ANDROID_HOME=C:\Users\%USERNAME%\AppData\Local\Android\Sdk
if exist "%ANDROID_HOME%\tools\bin\sdkmanager.bat" (
    echo ✅ Android SDK found
    set PATH=%PATH%;%ANDROID_HOME%\tools;%ANDROID_HOME%\platform-tools
) else (
    echo ❌ Android SDK not found
    echo Install Android Studio or SDK tools first
    pause
    exit /b 1
)

echo.
echo Building APK...
echo.

REM Try different build methods
echo Method 1: Using gradlew.bat...
if exist "gradlew.bat" (
    call gradlew.bat assembleDebug
    if %errorlevel% equ 0 goto :success
)

echo.
echo Method 2: Using gradle command...
gradle assembleDebug
if %errorlevel% equ 0 goto :success

echo.
echo Method 3: Using Android build tools...
if exist "%ANDROID_HOME%\tools\android.bat" (
    call "%ANDROID_HOME%\tools\android.bat" update project -p . -n NNApp
    ant debug
    if %errorlevel% equ 0 goto :success
)

echo.
echo ❌ All build methods failed
echo.
echo Recommended: Use Android Studio
echo Or try online builders mentioned in ONLINE_BUILDERS.md
pause
exit /b 1

:success
echo.
echo ✅ BUILD SUCCESSFUL!
echo.
if exist "app\build\outputs\apk\debug\app-debug.apk" (
    echo 🎉 APK Created: app\build\outputs\apk\debug\app-debug.apk
    dir "app\build\outputs\apk\debug\app-debug.apk"
) else if exist "bin\NNApp-debug.apk" (
    echo 🎉 APK Created: bin\NNApp-debug.apk
    dir "bin\NNApp-debug.apk"
) else (
    echo ✅ Build completed, check for APK file
)
echo.
pause
