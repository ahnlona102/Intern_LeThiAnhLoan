# Railway Selenium Java Project

This project is a Selenium automation framework using Java for testing the Railway application. It outlines the setup
for IntelliJ, JDK 11, and Selenium version 4, and provides instructions for creating a Maven project.

## Prerequisites

- Java Development Kit (JDK) 11
- IntelliJ IDEA
- Maven
- Git
- Selenium Grid JAR file downloaded
- Terminal or Command Prompt
- Docker installed

### Run on browsers

- Firefox: mvn test -Dbrowser=firefox
- Chrome: mvn test -Dbrowser=chrome

## Setting Up Selenium Grid with two ways:

- Hub & Node
- Docker

To set up a Selenium Grid, follow these steps:

## Using hub & node

### Steps to Setup Selenium Grid

1. **Download Selenium Grid JAR:**
    - Download the Selenium Grid JAR file from https://www.selenium.dev/downloads/.
2. **Start the Hub:**
    - Open a terminal or command prompt and navigate to the directory where `selenium-server-4.21.0.jar` is located.
    - Start the hub using the command:
      ```
      java -jar selenium-server-4.21.0.jar hub
      ```
3. **Start Nodes:**
    - For each node, open a new terminal or command prompt and navigate to the same directory as above.
    - Start the node using the command:
      ```
       java -jar selenium-server-4.21.0.jar node
      ```
4. **Verify Hub and Nodes:**
    - Open a web browser and go to `http://localhost:4444`. You should see the Selenium Grid console displaying details
      of the hub and registered nodes.
5. **Configure WebDriver in Your Tests:**
    - Modify your Selenium test scripts to use the hub URL (`http://localhost:4444`) when creating WebDriver instances.

## Using docker

### Steps to Setup Selenium Grid

1. **Create a docker-compose.yml file in project**
2. **Run docker compose**
- Navigate to your project director
- Open a terminal or command prompt
- Run command line:
  With file name: docker-compose.yml
```
docker-compose up
```
or with another name:
```
docker-compose -f anothername.yml up
```
4. **Verify Hub and Nodes:**
    - Open a web browser and go to `http://localhost:4444`. You should see the Selenium Grid console displaying details
      of the hub and registered nodes.
5. **Configure WebDriver in Your Tests:**
    - Modify your Selenium test scripts to use the hub URL (`http://localhost:4444`) when creating WebDriver instances.

### Notes

- Ensure that your network allows communication between the hub and nodes, and ports `4444` (default for Selenium Grid)
  are open if you encounter connectivity issues.

6. **Parallel Test Execution:**

- Parallel by classes
  In testNG.xml
```
   <suite name="Suite" parallel="classes" thread-count="3">
   ...
   </suite>
```

- Parallel by methods:
  In testNG.xml
```
   <!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
   <suite name="Suite" parallel="methods" thread-count="3">
   ...
   </suite>
```

- Parallel by tests:
  In testNG.xml
```
   <!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
   <suite name="Suite" parallel="tests" thread-count="3">
   ...
   </suite>
```

- Parallel by instances:
  In testNG.xml
```
   <!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
   <suite name="Suite" parallel="instances" thread-count="3">
   ...
   </suite>
```

- Parallel by data providers:
  In TestDataProvider.java
```
    @DataProvider(name = "chapter10Testcase1", parallel = true)
    public Object[][] getLoginChapter10Testcase1() {
        return getTestData("testChapter10", "testCase1");
    }
```
In testNG.xml
```
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
<suite name="Suite" parallel="tests" thread-count="3">
...
</suite>
```