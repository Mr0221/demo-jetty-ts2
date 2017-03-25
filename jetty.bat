@echo off
echo Jetty start time: %DATE% %TIME%
setlocal 

REM for machine with more than 1 CPU:
REM set MAVEN_OPTS=-Xms512m -Xmx1024m -XX:PermSize=512m -XX:MaxPermSize=1024m -XX:+CMSPermGenSweepingEnabled -XX:+UseParallelGC

REM for machine with only 1 CPU:
set MAVEN_OPTS=-Xms512m -Xmx1024m -XX:PermSize=512m -XX:MaxPermSize=1024m -XX:+CMSPermGenSweepingEnabled -XX:+UseConcMarkSweepGC 

set LOG_FOLDER=logs

goto DEL_LOG
if exist %LOG_FOLDER% rmdir /s/q %LOG_FOLDER%
mkdir %LOG_FOLDER%

:DEL_LOG
echo Deleting log files
if exist %LOG_FOLDER% del /s/q %LOG_FOLDER%\*

echo Starting jetty
mvn jetty:run 2> error.log

endlocal
