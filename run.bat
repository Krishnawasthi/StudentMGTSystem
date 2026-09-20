@echo off
echo Compiling Core Java Student Management System...
if not exist bin mkdir bin
javac -d bin src/com/student/mgt/config/*.java src/com/student/mgt/exception/*.java src/com/student/mgt/model/*.java src/com/student/mgt/repository/*.java src/com/student/mgt/service/*.java src/com/student/mgt/util/*.java src/com/student/mgt/Main.java
if %ERRORLEVEL% EQU 0 (
    echo Compilation successful! Running application...
    java -cp bin com.student.mgt.Main
) else (
    echo Compilation failed!
)
