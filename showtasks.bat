call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openExplorer
echo.
echo executing runcrud.bat has errors - breaking work
goto fail

:openExplorer
start chrome http://www.google.com
if "%ERRORLEVEL%" == "0" goto openLocalHost
echo.
echo opening google in chrome has errors - breaking work
goto fail

:openLocalHost
start chrome http://localhost:8080/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo opening localhost has errors - breaking work
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished