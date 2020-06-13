## Class Command

| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|Command()|sets action and noun to null|none|Command(String, String)|this|3|
|Command(String)|creates a command only given the action word|none|Command(String, String)|this|3|
|Command(String, String)|creates a command|none|validateCommand(String, String)|none|3|
|validateCommand(String, String)|checks if the command is valid|none|validateCommand2(String, String), validateActionWord(String), validateDirection(String)|String command|12|
|validateCommand2(String, String)|check if command is valid|none|setCommands(String, String)|String command|12|
|setCommands(String, String)|sets the first and last word of command|String action, String noun|none|this|4|
|getActionWord()|returns the actionWord|String action|none|this|3|
|getNoun()|returns the noun|String noun|none|this|3|
|setAction(String)|sets the first word of the command|String noun|validateCommand(String, String)|none|3|
|setNoun(String)|sets the second word of the command|String action|validateCommand(String, String)|none|3|
|hasSecondWord()|returns true of the command has a second word|String noun|none|none|3|
|validateActionWord(String)|returns true if the first word is valid|none|none|String command|11|
|validateDirection(String)|returns true if direction is valid|none|none|String direction|8|
|getCommands()|returns the list of valid commands|none|none|none|3|
|getDirections()|returns the list of valid directions|none|none|none|3|
|toString()|returns the actions and noun as a string|String action, String noun|none|none|3|
