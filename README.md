mvn versions:use-latest-versions
mvn clean test
mvn -Dtest=BillingTest#cardIsAdded test
mvn -f src/test/resources/files/pom.xml clean
mvn -Dtest=ChatTest#messageIsSentByEnterTest -DParameter=SomeText test 