@echo off
echo ========================================
echo NN App - Upload to GitHub
echo ========================================
echo.

echo This script will help you upload your project to GitHub
echo for automatic APK building.
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
echo Step 2: Add all files...
git add .
echo ✅ Files added

echo.
echo Step 3: Create initial commit...
git commit -m "Initial commit: NN App Android project with GitHub Actions"
if %errorlevel% neq 0 (
    echo.
    echo ⚠️  Please configure Git first:
    echo git config --global user.name "Your Name"
    echo git config --global user.email "your.email@example.com"
    echo.
    echo Then run this script again.
    pause
    exit /b 1
)
echo ✅ Initial commit created

echo.
echo Step 4: Manual GitHub setup required...
echo.
echo Please follow these steps:
echo.
echo 1. Go to https://github.com
echo 2. Create a new repository named "NN-App-Android"
echo 3. Copy the repository URL (HTTPS)
echo 4. Run this script again and paste the URL
echo.
echo Or run these commands manually:
echo git remote add origin https://github.com/YOUR_USERNAME/NN-App-Android.git
echo git push -u origin main
echo.

set /p remote_url="Enter your GitHub repository URL (or press Enter to skip): "
if not "%remote_url%"=="" (
    echo.
    echo Step 5: Connect to GitHub repository...
    git remote add origin %remote_url%
    echo ✅ Remote added
    
    echo.
    echo Step 6: Push to GitHub...
    git push -u origin main
    echo ✅ Pushed to GitHub
    
    echo.
    echo 🎉 Your project is now on GitHub!
    echo.
    echo Next steps:
    echo 1. Go to your repository on GitHub
    echo 2. Click "Actions" tab
    echo 3. Wait for the build to complete
    echo 4. Download the APK from the build artifacts
) else (
    echo.
    echo ⚠️  Repository not connected to GitHub
    echo Please complete the manual setup steps above.
)

echo.
pause
