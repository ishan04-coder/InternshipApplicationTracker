@echo off
echo Compiling Internship Application Tracker...
if not exist out mkdir out
javac -d out src\*.java

if errorlevel 1 (
    echo.
    echo Compilation failed.
    pause
    exit /b 1
)

echo.
echo Starting application...
echo.
java -cp out Main
pause
