## Class Parser

| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|Parser()|inizializes Parser|none|none|none|1|
|parseUserCommand(String)|creates a command from the input|none|createCommand(Scanner)|Scanner inputScanner|10|
|createCommand(Scanner)|creates the command and returns it|none|none|Scanner inputScanner, String noun|9|
|allCommands()|returns all the valid commands|none|none|Command command|12|
|toString()|returns a message about the parser|none|none|none|3|
