## Class Game

| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|main(String[])|creates the game|none|gameIntro(String[]), setPlayer(Adventure, Scanner), running(Scanner, Adventure, Player)|Game theGame, Scanner scnr|10|
|checkJsonErrors(Adventure)|checks json errors|none|getErrorMessage()|Adventure adventure|11|
|setErrorMessage(String)|sets the error message|String errorMessage|none|none|3|
|getErrorMessage()|gets the error message|String errorMessage|none|none|3|
|running(Scanner, Adventure, Player)|the loop that runs while playing|none|checkCommand(String, Player, Adventure), exit(Scanner, String, Adventure, Player)|Scanner scnr, String inputLine|10|
|exit(Scanner, String, Adventure)|determines if user wants to quit|none|confirmAnswer(Scanner), doAnswer(String, Scanner, Adventure, Player)|String inputLine|7|
|doAnswer(String, Scanner, Adventure, Player)|ask user to save or put user back in the game|none|askUserToSave(Scanner, Adventure, Player)|none|9|
|confirmAnswer(Scanner)|confirm the user wants to exit|none|none|Scanner scnr, String answer|8|
|askUserToSave(Scanner, Adventure, Player)|asks user to save the game|none|saveAnswer(Scanner, Adventure, Player, String)|String answer, Scanner scnr|9|
|saveAnswer(Scanner, Adventure, Player)|save or exit|none|saveGame(Adventure, Player, String)|Scanner scnr, Player player, String answer|10|
|saveGame(Adventure, Player, String)|serializes Adventure object|none|none|Adventure adventure, ObjectOutputStream outPutDest|12|
|tryLoadGame(String)|checks if file can be loaded|none|loadGame(String)|none|10|
|loadGame(String)|loads the game|none|displayStartingRoom(Room)|ObjectInputStream in, Adventure adventure|10|
|setPlayer(Adventure, Scanner)|sets up the player|none|displayStartingRoom(Room)|Adventure adventure, Scanner scnr|12|
|possibleNewPlayer(Adventure, String, String)|makes new player if there is not one already|none|none|Adventure adventure|5|
|setFileName(String[], Adventure)|sets the file name|none|none|String[] args|7|
|displayStartingRoom(Room)|displays the current room|none|getItems(Room)|Room room|5|
|loadAdventureJson(String)|creates json object from the .json file|none|none|JSONParser parser, JSONObject jsonObject, Reader reader|12|
|loadAdventureJson(InputStream)|creates a json object from the default adventure .json file|none|none|JSONParser parser, JSONObject jsonObject, InputStreamReader reader|12|
|generateAdventure(JSONObject)|returns the adventure created from the json object|none|setRoomList(JSONObject), setItemList(JSONObject)|Adventure adventure|11|
|setRoomList(JSOBObject)|sets the arrayList of rooms|none|none|JSONObject obj, ArrayList\<JSONObject> rooms|8|
|setItemList(JSOBObject)|sets the arrayList of items|none|none|JSONObject obj, ArrayList\<JSONObject> items|8|
|gameInro(String[])|returns the adventure object the user loaded|none|tryLoadGame(String), getAdventureJson(String[]), checkJsonErrors(Adventure), generateAdventure(JSONObject)|String[] args|9|
|getAdventureJson(String[])|loads the json object from the file|none|loadAdventureJson(String), loadAdventureJson(InputStream)|String[] args|10|
|checkCommand(String, Player, Adventure)|creates a command|none|doCommand1(Command, Player, Adventure), movedRooms(Room, Player)|Parser parser, InvalidCommandException e|12|
|movedRooms(Room, Player)|if the rooms changed, print the room and its items|none|getItems(Room)|Player player|6|
|doCommand1(Command, Player, Adventure)|tells player what command to execute|none|doCommand2(Command, Player, Adventure)|Command command, Player player|11|
|doCommand2(Command, Player, Adventure)|tells player what command to execute|none|doCommand3(Command, Player)|Command command, Player player|11|
|doCommand3(Command, Player)|tells player what command to execute|none|none|Command command, Player player|9|
|getItems(Room)|gets the list of items from the current room|none|none|Room room, Item tempItem|8|
|changePlayerName(String, Adventure)|changes the players name|none|none|Adventure adventure|7|
|toString()|returns "The Game Class"|none|none|none|3|
