# Sky Bet Trading Engine Tests


## Pre-test-Setup
* LTS version of NodeJS, and NPM: https://nodejs.org/

### Usage
Start the mock API server:
`npm install && npm run start`

It runs by default on `http://localhost:3000`

## API

## Endpoints
1.findFixtureById
2.fixtureIdDelete
3.getFixtures
4.storeNewFixture

## Framework

### Structure
This project is a standard Maven Java TestNG project. Used Rest-Assured in the framework. 
See Rest-Assured at : https://code.google.com/p/rest-assured/

# Tools / libraries used :

1. Java
2. Rest Assured
3. TestNG
4. Maven

### Properties
`src/test/resources/config.properties` is a simple properties file to store various configurations

### Tests
`src/main/java/base.TestBase.class` is the tests superclass for configuration and common code
`src/test/java/` holds test classes (TestNG) 


### Run the Tests
# Steps to start :

1. Clone / Download the project into your local
2. Open the Command prompt and navigat to project location
3. Execute the following Maven command's
    - mvn clean :- To clean the maven repo
    - mvn install :- To install the maven requirments 
    - mvn test :- To execute the test scenarios


References : 

1. https://github.com/rest-assured/rest-assured/wiki/usage
2. https://github.com/angiejones/restassured-with-cucumber-demo
3. https://github.com/swtestacademy/RestAssuredExample
4. http://www.groupkt.com/post/f2129b88/free-restful-web-services-to-consume-and-test.htm 
5. http://angiejones.tech/rest-assured-with-cucumber-using-bdd-for-web-services-automation

Example:
 `apple@apple-MacBook-Pro  ~/assessment/trading-tribe-automation  mvn clean test `                                                                                                        1 ↵  2752  20:24:01


### Test Results

Test Results can be found in /target/surefire-reports directory

