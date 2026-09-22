@echo off
echo Compiling and Running Unit Test Suites...
if not exist bin mkdir bin
javac -d bin src/com/student/mgt/config/*.java src/com/student/mgt/exception/*.java src/com/student/mgt/model/*.java src/com/student/mgt/repository/*.java src/com/student/mgt/service/*.java src/com/student/mgt/util/*.java src/com/student/mgt/test/*.java
if %ERRORLEVEL% EQU 0 (
    echo Compilation successful! Running StudentServiceTest...
    java -ea -cp bin com.student.mgt.test.StudentServiceTest
    echo Running ValidationUtilTest...
    java -ea -cp bin com.student.mgt.test.ValidationUtilTest
    echo Running StudentServiceSearchTest...
    java -ea -cp bin com.student.mgt.test.StudentServiceSearchTest
    echo All Test Suites Completed!
) else (
    echo Compilation failed!
)
