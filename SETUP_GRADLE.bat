@echo off
echo ========================================
echo NN App - Gradle Setup for APK Build
echo ========================================
echo.

echo Checking for Gradle installation...
gradle --version >nul 2>&1
if %errorlevel% equ 0 (
    echo ✅ Gradle is installed
    echo Version:
    gradle --version
    echo.
    echo You can build APK with:
    echo gradle assembleDebug
    goto :build
) else (
    echo ❌ Gradle not found in system PATH
    echo.
    echo Installing Gradle wrapper...
    
    REM Download Gradle wrapper
    echo Downloading Gradle wrapper files...
    
    REM Create gradle wrapper jar using PowerShell
    powershell -Command "& {Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-9.0-milestone-1-bin.zip' -OutFile 'gradle.zip'; Expand-Archive -Path 'gradle.zip' -DestinationPath '.'; Remove-Item 'gradle.zip';}"
    
    if exist "gradle-9.0-milestone-1" (
        echo ✅ Gradle downloaded
        set GRADLE_HOME=%CD%\gradle-9.0-milestone-1
        set PATH=%PATH%;%GRADLE_HOME%\bin
        echo Gradle setup complete!
    ) else (
        echo ❌ Gradle download failed
        echo.
        echo Manual installation required:
        echo 1. Download Gradle from: https://gradle.org/install/
        echo 2. Extract to: C:\gradle
        echo 3. Add to PATH: C:\gradle\bin
        pause
        exit /b 1
    )
)

:build
echo.
echo Building APK...
gradle assembleDebug

if %errorlevel% equ 0 (
    echo ✅ APK Build Successful!
    echo Location: app\build\outputs\apk\debug\app-debug.apk
) else (
    echo ❌ Build failed
    echo Try Android Studio method instead
)

pause
