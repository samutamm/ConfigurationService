export PORT=4571
export ENCRYPTION_KEY="foobar"
mvn clean install exec:java -Dexec.mainClass=com.mycompany.configurationservice.Main