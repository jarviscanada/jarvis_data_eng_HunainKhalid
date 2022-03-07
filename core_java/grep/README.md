# Introduction
This app is intended to mimic the `egrep` command from linux allowing users to search for matching, in Java. 
This app was built to help develop Java skills and future JAva apps such as the stock trading app.
- **Development Method**: Agile _(Scrum)_
- **Technologies Used**:
  _Docker, Docker Hub, PostGreSQL, Git, 
Github, IntelliJ IDE, VNC, GCP, Java 8_

# Quick Start
1. Run using JAR file
```java, bash
mvn install
mvn clean package
java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp [searchRegex] [rootDirectory] [outputFile]
```
2. Alternatively use a Docker Image
```java, bash, Docker, docker
   docker pull hunaink/grep
   docker run --rm -v `pwd`/data:/data -v `pwd`/log:/log hunaink/grep [searchRegex] [rootDirectory] [outputFile]
```

# Implementation
## Pseudocode
The main method requires help via method `process` and follows
the following pseudocode:
```bash, java
   matchedLines = []
   
   for file in listFilesRecursively(rootDir)
     for line in readLines(file)
       if containsPattern(line)
         matchedLines.add(line)
   
   writeToFile(matchedLines)
```
## Performance Issues
Because this app prints each line of a document using simple for-loops, larger files face the issue of 
inefficient runtime. Mitigation can be done using stream and lambda features or specifying 
size of heaps prior to java execution using additional JVM options, reducing the number of runtime 
garbage collection operations, thus improving performance.
- `-Xms` specifies initial java heap size.
- `-Xmx` specifies max java heap size.

Example w/ Heap Size:
```bash, java
   java -Xms5m -Xmx20m -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp .*Romeo.*Juliet.* ./data ./log/grep.out
```
# Testing
All testing was done manually. Entering `egrep -r {regex} {rootPath} > {outFile}`, allowed for comparison
of my app versus the built-in `egrep` function. Logging software `self4j` was used for debugging and logging.

# Deployment
This app is deployed to GitHub. A Dockerized version of the same app can also be found on DockerHub.

# Improvements
- Creation of JUnit tests with development. Constant manual re-checking is tedious and can contain human error.
- Usage of different logging program `SELF4J`, which is dependent on older versions of `LOG4J` known for severe vulnerabilities.
- Conversion of remaining code from for-loops to lambda and stream expressions also may help with runtime performance.
