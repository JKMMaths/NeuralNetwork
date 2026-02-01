@echo off
echo ========================================
echo NN App Android - Java 17 Setup
echo ========================================
echo.

echo Checking for Java 17 installation...
if exist "C:\Program Files\Java\jdk-17" (
    echo ✅ Java 17 found at C:\Program Files\Java\jdk-17
    echo.
    echo Configuration is ready!
    echo You can now import the project in Android Studio.
    pause
    exit /b 0
) else (
    echo ❌ Java 17 not found at C:\Program Files\Java\jdk-17
    echo.
    echo Please install Java 17 first:
    echo 1. Download from: https://adoptium.net/temurin/releases/?version=17
    echo 2. Choose "Windows x64 Installer"
    echo 3. Install to default location
    echo 4. Run this script again
    echo.
    echo Alternative Java 17 downloads:
    echo - Oracle JDK 17: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
    echo - OpenJDK 17: https://jdk.java.net/17/
    echo.
    pause
    exit /b 1
)
