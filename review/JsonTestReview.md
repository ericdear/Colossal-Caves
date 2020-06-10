## Class JsonTest

| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|JsonTest()|initializes jsonTest|boolean missingItem, boolean equalExits, boolean missingRoom, boolean noExits|none|none|6|
|setMissingItem(boolean)|sets missingItem to true if true is passed in|boolean missingItem|none|none|5|
|setEqualExits(boolean)|sets equalExits to true if true is passed in|boolean equalExits|none|none|5|
|setMissingRoom(boolean)|sets missingRoom to true if true is passed in|boolean missingRoom|none|none|5|
|setNoExits(boolean)|sets noExits to true if true is passed in|boolean noExits|none|none|5|
|missingItem()|returns the error message|boolean missingItem|none|none|6|
|equalExits()|returns the error message|boolean equalExits|none|none|6|
|missingRoom()|returns the error message|boolean missingRoom|none|none|6|
|noExits()|returns the error message|boolean noExits|none|none|6|
|test()|returns the string of all errors|none|missingItem(), equalExits(), missingRoom(), noExits()|none|5|
|testEntrances(ArrayList<Room>)|tests if the entrances of rooms match|HashMap<String, String> DIRECTIONMAP|setEqualExits(boolean)|HashMap<String, String> DIRECTIONMAP, HashMap.Entry entry, Room room|12|
|toString()|returns the error message|none|test()|none|3|
