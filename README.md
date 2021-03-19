## Author Information
* Name: Eric Dearing
* Email: eric@dearing.ca


## How to operate your program
compile: mvn assembly:assembly  
Run Terminal: java -cp target/2430_A2-1.0-jar-with-dependencies.jar adventure.Game "flag" "file"  
Run GUI: java -jar target/2430_A2-1.0-jar-with-dependencies.jar "flag" "file"  
flag/file options: -l (game save name)  
                   -a (json file name)  
                   or no flags to run with default json file  

commands:
* "go x" where x is N, S, E, W, up, or down
* "look" to see a longer description of the room
* "look x" where x is an item in that room
* "exit" or "quit" to exit
* "take x" where x is an item in that room
* "inventory" to look what the player has
* "read x" where x is an item, to read an item that is readable
* "toss x" where x is an item, to toss/throw an item that is tossable
* "eat x" where x is an item, to eat an item that is edible
* "wear x" where x is an item, to wear an item of type wearable as clothing

