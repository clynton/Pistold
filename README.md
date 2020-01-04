# Pistold
Platform for Internet Site Testing Over Long Distances
- A simple automation test project - using Selenium
- The sample connects to GitHub and clicks a link

# PreReq
* Requires git, maven, selenium, JDK (1.8 or greater)
* For the IDE, we can use Eclipse, VS Studio Code, etc

# Install

* Clone/download this repo

```shell
git clone https://github.com/clynton/Pistold.git MyAutomationTestr
```

# Setup
	
1. Rename the Core folder if desired (ex: if loading into eclipse and there are others already there)
2. If using eclipse: Import Project > Maven > Existing Maven Project
3. Rename 'groupId' in pom.xml to a desired company name
4. Update the project after changing the POM: Right-Click project > Maven > Update Project
5. Right-Click project > Properties > Libraries
6. Edit the JDK/JRE setting if required: (or Add Library > JRE System Library > select one)
7. Change the paths to the log files in log4j.properties
8. Change the paths for SeleniumDriverPaths in configSettings.java

# Try It
1. Right-Click loadHomepagesInDiffBrowsers.java > Run as TestNG Test
2. Run our test using maven (can be done in a bat file - using 'call mvn test' if so)

```dos
cd MyAutomationTestr\Core
mvn test
```

# Next Steps

1. Double check that there's stuff in app.log after the test run
2. Change the url and title page in testData.java
3. Add test case classes similar to loadHomepageInDiffBrowsers.java
4. Use the batch in jenkins, windows task manager, etc


# TO DO

1. Next I'll proly add some BDD stuff - outside of Core


<br/><br/><br/>



