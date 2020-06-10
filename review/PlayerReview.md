## Class Player

| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|Player()|initializes player|none|Player(String, Room, ArrayList\<Room>, String)|this|3|
|Player(String, Room, ArrayList\<Room>, String)|initializes player|ArrayList\<Item> inventory|setRoomList(ArrayList\<Room>), setName(String), setRoom(Room), setSaveGameName(String)|this|7|
|setRoomList(ArrayList<Room>)|sets the list of all rooms|ArrayList<Room> rooms|none|none|3|
|getRoomList()|returns the list of rooms|ArrayList<Room> rooms|none|none|3|
|setRoom(Room)|sets the room the player is in|Room room|none|none|3|
|setName(String)|sets the name of the player|String name|none|none|3|
|setInventory(ArrayList<Item>)|sets the players inventory|ArrayList<Item> inventory|none|none|3|
|setSaveGameName(String)|sets the name of the saved game|String saveGameName|none|none|3|
|getName()|returns the name of the player|String name|none|none|3|
|getInventory()|returns the ArrayList of items the player has|ArrayList<Item> inventory|none|none|3|
|getCurrentRoom()|returns the current room the player is in|Room room|none|none|3|
|getSavedGameName()|returns the name of the saved game|String savedGameName|none|none|3|
|look(Command)|returns the room long description or the item description|Room room|none|Command command, Room room|7|
|go(Command)|returns the room the command is looking for|Room room|none|Room room, Command command|9|
|take(Command)|picks up the item if it is there|ArrayList<Item> inventory, Room room|none|Room room, Command command, ArrayList<Item> inventory, Item item|10|
|inventory(Command)|returns the players inventory|ArrayList<Item> inventory|none|ArrayList<Item> inventory, Item item|10|
|toString()|returns the name of the player and the room the player is in|String name, Room room|none|none|3|