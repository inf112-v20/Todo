[![Codacy Badge](https://api.codacy.com/project/badge/Grade/799bcad926c04c4f91c35ab2bc034c18)](https://www.codacy.com/gh/inf112-v20/Todo?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=inf112-v20/Todo&amp;utm_campaign=Badge_Grade) [![Build Status](https://travis-ci.com/inf112-v20/Todo.svg?branch=master)](https://travis-ci.com/inf112-v20/Todo)

# INF112 TODO

## Project setup

### With IDE: Windows, Linux, MacOS

  First you need to install a Java IDE, such as IntelliJ or Eclipse.<br/>

  Then you need to import the project from version control, as a Maven project. <br/> 
  Specify pom.xml as the configuration file.<br/>

  To run the project you need to run the main class located at: inf112.desktop.Main.<br/>
  If you encounter an error its probably because Maven is not correctly importet. Usally you will get a popup to add Maven. If not try to close the IDE and reopen. If that doesn't fix try going to maven setting in you're Java IDE, and try to download sources and reimport "all Maven projects"
  PS: you might need to set the working directory to the "assets" folder.

  
  
## How to play
To start the application run the Main-class, this opens the application. You now have the option to either
exit the application or to "play". Since the game is yet to be implemented fully the game is not playable just yet.
When pressing play you gain control over a robot on a large test map.

Controls:
1. Pick cards by clicking on them, you can remove a picked card by clicking on the card again. Press "confirm" to confrim cards.
2. Pressing P activates powerdown
3. Pressing Z activates debugging controls

Debug controls:
1. Move the robot using the arrowkeys (Up for forward, Down for backwards, Left and Right for turning Left and Right)
2. Press the C key to change the robots checkpoint. (This happens automatically when the robot walks over a flag)
3. Press SPACE to move the robot back to the checkpoint
4. Press T for all mapbased movement. Conveyorbelts, Gears, Pushers ect..
5. Press L for all the lasers to fire  


### Controls for respawning  
When your robot dies you must choose how you would like to respawn. This includes the direction you want to face when
respawning. This is done using the arrowkeys, and pressing enter once you are facing the direction you wish to spawn in.  
If you try to respawn on a location where there is another robot you must choose one of the locations around the location.
This is done using the num-keys. And is locked in by pressing enter.
 
     
## How to run tests  
The J-Unit tests that are included can be run by simply rightclicking the (/src/test/java/inf112.core/) inf112.core
 folder and clicking "Run tests in 'inf112.core'". Alternatively by pressing "ctrl + shift + f10"  
 
 Manual tests are run according to the instructions in the ObligatoriskOppgave files found in deliverables. 

