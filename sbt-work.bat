set SCRIPT_DIR=%~dp0
del %TEMP%\jansi.dll
java -Dsbt.global.base=d:\home\.sbt -Dsbt.ivy.home=d:\home\.ivy2 -noverify -javaagent:"C:\Program Files (x86)\ZeroTurnaround\JRebel/jrebel.jar" -Drebel.lift_plugin=true -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=556m -Xmx1712M -Xss2M -jar "%SCRIPT_DIR%\sbt-launch.jar" %*
