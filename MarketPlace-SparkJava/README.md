## Problem Statement:

Design a Marketplace for Internet plans. This marketplace will read in plan data from a json file. A plan might have a name, tier (residential, SMB, commercial), price per month, and total data usage. A User of the Marketplace should be able to sign up for notifications for plans below a certain price threshold.Implement the marketplace and any unit/integration tests necessary to show your code is correct. Be prepared to talk about your overall design, any patterns used, and your reasoning behind the decisions that you made in your design.

## Goal of problem:
1. Learn Spark Java web framework
2. Practice some junit

## Solution:

### How to Run Server
(Requires JRE/JDK 1.8 and maven installed on the machine)  

1. Open a command prompt and go to folder `marketplace-app`. This is the folder where `pom.xml` resides
2. Run `mvn compile`
4. Run `mvn test`  (**Optional** - required only run unit tests)
5. Run `mvn package`
6. To start server: Run mvn exec:java -Dexec.mainClass="Main"

Wait for server to start. When the server gets started, REST APIs can be accessed at http://localhost:4567/ (default location for **sparkjava** application).

When server starts it reads **Internet Plans** from a file named **marketplacedata.txt** (is is available in **test-app/input** folder and can be modified) containing plan data in jason format. Application assumes to read this file from this location. Each time the server starts it logs the basic actions and API operations in a log file prefixed with current time stamp inside the folder **test-app/output**.

### How to make requests
A very basic unintelligent html/javascript file has been provided to make REST requests to the server running locally. File assumes that server is API calls are accessible at http://localhost:4567/

![](readmeimages/addUser.JPG?raw=true)