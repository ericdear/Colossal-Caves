## Author Information
* Name: Eric Dearing
* Email: edearing@uoguelph.ca
* Student ID: 1087655


## How to operate your program
compile: mvn assembly:assembly
Run Terminal: java -cp target/2430_A2-1.0-jar-with-dependencies.jar adventure.Game <flag> <file>
Run GUI: java -jar target/2430_A2-1.0-jar-with-dependencies.jar <flag> <file>
flag/file options: -l <game save name>
                   -a <json file name>
                   or no flags to run with default json file

commands: "go x" where x is N, S, E, W, up, or down
          "look" to see a longer description of the room
          "look x" where x is an item in that room
          "exit" or "quit" to exit
          "take x" where x is an item in that room
          "inventory" to look what the player has
          "read x" where x is an item, to read an item that is readable
          "toss x" where x is an item, to toss/throw an item that is tossable
          "eat x" where x is an item, to eat an item that is edible
          "wear x" where x is an item, to wear an item of type wearable as clothing

## Documentation of RoomTest.java
getConnectedRoom() looks good. no errors in any of the test case
all there is is a setter to set the connected room and a getter so it doesnt take many test cases 
to know that my hashmap is working perfectly.

## Things TA should know!
line 128 of room.java is 13 lines because it is a bunch of if else statements calling other methods
Judi said this was okay because an if statement will count as 1 line anyways!

## Statement of Individual Work
By the action of submitting this work for grading, I certify that this assignment is my own work, based on my personal study. I also certify that no parts of this assignment have previously been submitted for assessment in any other course, except where specific permission has been granted.  Finally, I certify that I have not copied in part or whole or otherwise plagiarised the work of other persons.

