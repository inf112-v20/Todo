[![Codacy Badge](https://api.codacy.com/project/badge/Grade/799bcad926c04c4f91c35ab2bc034c18)](https://www.codacy.com/gh/inf112-v20/Todo?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=inf112-v20/Todo&amp;utm_campaign=Badge_Grade) [![Build Status](https://travis-ci.com/inf112-v20/Todo.svg?branch=master)](https://travis-ci.com/inf112-v20/Todo)

## Project setup

### With IDE: Windows, Linux, MacOS

  First you need to install a Java IDE, such as IntelliJ or Eclipse.

  Then you need to import the project from version control, as a Maven project. <br/> 
  Specify pom.xml as the configuration file.

  To run the project you need to run the main class located at: inf112.desktop.Main.



### With commandline: Linux, MacOs

**Before you try to run the program, make sure Maven is installed**<br/>
    mvn --version    
  Maven can downloaded for here: https://maven.apache.org/download.cgi
  
**When you are sure Maven is installed correctly, you can clone the repository**  
    git clone https://github.com/inf112-v20/Todo

**Build the game with Maven**  
    cd Todo && mvn clean verify assembly:single
    

**You can now run the game**   
    java -jar target/mvn-app-1.0-SNAPSHOT-jar-with-dependencies.jar
    
  
  
## How to play
(Many of the current features are temporary and used for debugging)
-   Move forwards or backwards using up or down arrow keys
-   Rotate left or right using left or rigth arrow keys
-   Activate all lasers by pressing "L"
-   Pressing "T" will activate all conveyors, gears, wrenches and pushers
-   Pressing "C" will set a new backup point for the active player, and pressing "SPACE" will retur a player to its
    previous backup point.
    
     
     
## Known bugs
Currently throws "WARNING: An illegal reflective access operation has occurred", 
when the java version used is >8. This has no effect on function or performance, and is just a warning.

