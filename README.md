# Pistold
Platform for Internet Site Testing Over Long Distances
- A simple automation test project - using Selenium
- The sample connects to GitHub and clicks a link

# PreReq
* Requires git, maven, selenium (Jar files and webdriver EXEs), JDK (1.8 or greater)
* For the IDE, we can use Eclipse, VS Studio Code, etc

# Install

* Clone/download this repo

```shell
git clone https://github.com/clynton/Pistold.git MyAutomationTestr
```

# Setup
	
1. If using eclipse: Import Project > Maven > Existing Maven Project
2. Change the values of 'groupId' and 'artifactId' in pom.xml
3. Update the project after changing the POM: Right-Click project > Maven > Update Project
4. Right-Click project > Properties > Libraries
5. Edit the JDK/JRE setting if required: (or Add Library > JRE System Library > select one)
6. Change the paths to the log files in log4j.properties
7. Change the paths for SeleniumDriverPaths in configSettings.java

# Try It
1. Right-Click loadHomepageInDiffBrowsers.java > Run as TestNG Test
2. Right-Click bddRunnerTest.java > Run as JUnit Test
3. Run our test using maven (can be done in a bat file - using 'call mvn test' if so)

```dos
cd MyAutomationTestr
mvn test
```

# Validation

1. The Console/Output might show some warnings, but there shouldn't be any major errors
2. Errors might result from missing/outdated Jar/Lib files, bad path or config values, etc
3. Check that there's stuff in app.log after the test run
4. Check files under target like htmlreports/index.html and cucumber-reports/report.html

# Next Steps

1. Change the url and title page in testData.java
2. Add test case classes similar to loadHomepageInDiffBrowsers.java
3. Use the batch in jenkins, windows task manager, etc


# TO DO


<br/><br/><br/>



