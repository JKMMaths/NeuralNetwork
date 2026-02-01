@echo off
echo ========================================
echo NN App - Upload to Your GitHub Repository
echo ========================================
echo.

echo Repository: https://github.com/JKMMaths/NeuralNetwork.git
echo.

REM Check if git is installed
git --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Git not found
    echo Please install Git from: https://git-scm.com/
    pause
    exit /b 1
)

echo ✅ Git found
git --version
echo.

echo Step 1: Initialize Git repository...
if not exist ".git" (
    git init
    echo ✅ Git repository initialized
) else (
    echo ✅ Git repository already exists
)

echo.
echo Step 2: Configure Git if not configured...
git config user.name >nul 2>&1
if %errorlevel% neq 0 (
    echo Please enter your Git configuration:
    set /p git_name="Enter your name: "
    set /p git_email="Enter your email: "
    git config --global user.name "%git_name%"
    git config --global user.email "%git_email%"
    echo ✅ Git configured
)

echo.
echo Step 3: Add remote repository...
git remote remove origin >nul 2>&1
git remote add origin https://github.com/JKMMaths/NeuralNetwork.git
echo ✅ Remote repository added

echo.
echo Step 4: Create .gitignore file...
echo # Gradle files > .gitignore
echo .gradle/ >> .gitignore
echo build/ >> .gitignore
echo app/build/ >> .gitignore
echo local.properties >> .gitignore
echo *.keystore >> .gitignore
echo *.jks >> .gitignore
echo ✅ .gitignore created

echo.
echo Step 5: Add all necessary files...
git add .
echo ✅ Files added to staging

echo.
echo Step 6: Commit changes...
git commit -m "Add NN App Android project with GitHub Actions for automatic APK building

Features:
- 8 Neural Network Architectures (MLP, LSTM, RBF, etc.)
- Material Design 3 UI
- REST API Integration
- Excel File Upload
- Real-time Training Progress
- Automatic APK Builds via GitHub Actions

Build Configuration:
- Android Gradle Plugin 8.13.2
- Gradle 9.0-milestone-1
- Compile SDK 34
- Min SDK 24
- Java 17 compatible"

if %errorlevel% neq 0 (
    echo ❌ Commit failed - No changes to commit
    echo This is normal if files are already up to date
)

echo.
echo Step 7: Push to GitHub...
git branch -M main
git push -u origin main --force
if %errorlevel% neq 0 (
    echo ❌ Push failed
    echo.
    echo Possible reasons:
    echo 1. Repository doesn't exist
    echo 2. Authentication required
    echo 3. Network issues
    echo.
    echo Please check:
    echo - Repository exists: https://github.com/JKMMaths/NeuralNetwork
    echo - You have push access
    echo - Git credentials are configured
) else (
    echo ✅ Successfully pushed to GitHub!
    echo.
    echo 🎉 Your NN App Android project is now on GitHub!
    echo.
    echo Next steps:
    echo 1. Go to: https://github.com/JKMMaths/NeuralNetwork
    echo 2. Click "Actions" tab
    echo 3. Wait for automatic build to start
    echo 4. Download APK from build artifacts
    echo.
    echo Build will start automatically in 1-2 minutes!
)

echo.
pause
