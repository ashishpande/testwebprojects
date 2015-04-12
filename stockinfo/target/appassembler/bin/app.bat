@REM ----------------------------------------------------------------------------
@REM  Copyright 2001-2006 The Apache Software Foundation.
@REM
@REM  Licensed under the Apache License, Version 2.0 (the "License");
@REM  you may not use this file except in compliance with the License.
@REM  You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM  Unless required by applicable law or agreed to in writing, software
@REM  distributed under the License is distributed on an "AS IS" BASIS,
@REM  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM  See the License for the specific language governing permissions and
@REM  limitations under the License.
@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
@REM   reserved.

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup
set REPO=


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\org\hibernate\hibernate-entitymanager\3.6.1.Final\hibernate-entitymanager-3.6.1.Final.jar;"%REPO%"\org\hibernate\hibernate-core\3.6.1.Final\hibernate-core-3.6.1.Final.jar;"%REPO%"\antlr\antlr\2.7.6\antlr-2.7.6.jar;"%REPO%"\commons-collections\commons-collections\3.1\commons-collections-3.1.jar;"%REPO%"\dom4j\dom4j\1.6.1\dom4j-1.6.1.jar;"%REPO%"\org\hibernate\hibernate-commons-annotations\3.2.0.Final\hibernate-commons-annotations-3.2.0.Final.jar;"%REPO%"\javax\transaction\jta\1.1\jta-1.1.jar;"%REPO%"\cglib\cglib\2.2\cglib-2.2.jar;"%REPO%"\asm\asm\3.1\asm-3.1.jar;"%REPO%"\javassist\javassist\3.12.0.GA\javassist-3.12.0.GA.jar;"%REPO%"\org\hibernate\javax\persistence\hibernate-jpa-2.0-api\1.0.0.Final\hibernate-jpa-2.0-api-1.0.0.Final.jar;"%REPO%"\org\slf4j\slf4j-api\1.6.1\slf4j-api-1.6.1.jar;"%REPO%"\org\springframework\spring-orm\3.0.5.RELEASE\spring-orm-3.0.5.RELEASE.jar;"%REPO%"\org\springframework\spring-beans\3.0.5.RELEASE\spring-beans-3.0.5.RELEASE.jar;"%REPO%"\org\springframework\spring-core\3.0.5.RELEASE\spring-core-3.0.5.RELEASE.jar;"%REPO%"\org\springframework\spring-asm\3.0.5.RELEASE\spring-asm-3.0.5.RELEASE.jar;"%REPO%"\org\springframework\spring-jdbc\3.0.5.RELEASE\spring-jdbc-3.0.5.RELEASE.jar;"%REPO%"\org\springframework\spring-tx\3.0.5.RELEASE\spring-tx-3.0.5.RELEASE.jar;"%REPO%"\aopalliance\aopalliance\1.0\aopalliance-1.0.jar;"%REPO%"\org\springframework\spring-aop\3.0.5.RELEASE\spring-aop-3.0.5.RELEASE.jar;"%REPO%"\org\springframework\spring-aspects\3.0.5.RELEASE\spring-aspects-3.0.5.RELEASE.jar;"%REPO%"\org\springframework\spring-context\3.0.5.RELEASE\spring-context-3.0.5.RELEASE.jar;"%REPO%"\org\springframework\spring-expression\3.0.5.RELEASE\spring-expression-3.0.5.RELEASE.jar;"%REPO%"\org\springframework\spring-context-support\3.0.5.RELEASE\spring-context-support-3.0.5.RELEASE.jar;"%REPO%"\org\springframework\spring-test\3.0.5.RELEASE\spring-test-3.0.5.RELEASE.jar;"%REPO%"\mysql\mysql-connector-java\5.1.9\mysql-connector-java-5.1.9.jar;"%REPO%"\commons-logging\commons-logging\1.1.1\commons-logging-1.1.1.jar;"%REPO%"\com\hxtt\support\hibernate\hibernateSupport\1.0\hibernateSupport-1.0.jar;"%REPO%"\com\accessjdbc\Access_JDBC\1.0\Access_JDBC-1.0.jar;"%REPO%"\org\jsoup\jsoup\1.7.3\jsoup-1.7.3.jar;"%REPO%"\stockinfoapp\stockinfo\1.0\stockinfo-1.0.jar

set ENDORSED_DIR=
if NOT "%ENDORSED_DIR%" == "" set CLASSPATH="%BASEDIR%"\%ENDORSED_DIR%\*;%CLASSPATH%

if NOT "%CLASSPATH_PREFIX%" == "" set CLASSPATH=%CLASSPATH_PREFIX%;%CLASSPATH%

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS%  -classpath %CLASSPATH% -Dapp.name="app" -Dapp.repo="%REPO%" -Dapp.home="%BASEDIR%" -Dbasedir="%BASEDIR%" stockinfoapp.stockinfo.App %CMD_LINE_ARGS%
if %ERRORLEVEL% NEQ 0 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=%ERRORLEVEL%

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@REM If error code is set to 1 then the endlocal was done already in :error.
if %ERROR_CODE% EQU 0 @endlocal


:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
