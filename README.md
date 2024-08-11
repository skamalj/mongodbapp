# mongodbapp
Springboot app created with mongo atlas as backend. 

This is part of blog - https://www.kamalsblog.com/2021/08/RBAC%20-on%20-Azure-API%20-App-Service-using-API-Gateway-and-App-Roles.html

# Update and Run

Create a mongo instance on mongo atlas cloud or have local instance. Below settings are needed to package the app as it runs couple of tests as well during package.

* Set mongo environment
```
export SPRING_DATA_MONGODB_DATABASE=<DBName>

export SPRING_DATA_MONGODB_URI=<MONGO_URI>
```

* Update Java and library version in the POM.xml as required, then build and run (local).
```
mvn clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
```

# Deploy to azure

* Create webapp (Java and Linux)
    * Create environment variables in webapp as in local environment for mongodb
    * Additional environment variable SPRING_PROFILES_ACTIVE=prod is also required for azure app deployment
* Set the azure plugin parameters in POM.xml
* set environment variable for azure subscription AZ_SUBSCRIPTION
```
mvn azure-webapp:deploy -Djava.awt.headless=true
```

# Open API url
```
http://localhost:8080/swagger-ui/index.html
```

