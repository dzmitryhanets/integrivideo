mvn versions:use-latest-versions
mvn clean test
mvn -Dtest=TestCircle#multiplyingIsCalculated test
mvn -f src/test/resources/files/pom.xml