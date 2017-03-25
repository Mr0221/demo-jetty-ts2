set MAVEN_OPTS=-Xms512m -Xmx512m -XX:PermSize=512m -XX:MaxPermSize=512m -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=y
mvn jetty:run 2> ..\error.log
