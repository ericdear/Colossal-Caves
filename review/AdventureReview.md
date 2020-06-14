## Class Adventure

| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|Adventure()|initializes Adventure|ArrayList\<Room> roomList, ArrayList\<Item> allItems, JsonTest jsonTest, Player player|none|none|6|
|setJsonTest(JsonTest)|sets the jsonText object|JsonTest jsonTest|none|none|3|
|getJsonTest()|returns the json test object|JsonTest jsonTest|none|none|3|
|setAdventure(ArrayList\<JSONObject> newRooms, ArrayList\<JSONObject> items)|sets up each room in the adventure|ArrayList\<Room> roomList|setRoomArrayList(ArrayList\<JSONObject>), checkForStart(JSONObject, int), setLoot(JSONObject, Room, ArrayList\<JSONObject>), setEntrance(JSONObject, Room)|ArrayList\<Room> roomList, ArrayList\<JSONObject> newRooms|11|
|checkForStart(JSONObject, int)|checks each rooms for the start key|Room currentRoom, ArrayList\<Room> roomList|JSONObject roomObject, ArrayList\<Room> roomList|5|
|setPlayer(Player)|sets the player of the game|Player player|none|none|3|
|getPlayer()|gets the player of the game|Player player|none|none|3|
setRoomArray(ArrayList<JSONObject>)|sets up the arraylist of rooms|ArrayList\<Room> roomList|none|ArrayList\<Room> roomList|7|
|setAllItems(ArrayList<Item>)|sets the arraylist of all items|ArrayList\<Item> allItems|none|none|3|
|setRoomList(ArrayList\<Room>)|sets the arrayList of rooms|ArrayList\<Room> roomsList|none|none|3|
|setItemArrayList(ArrayList\<Item>)|sets up the arraylist of all items|ArrayList\<Item> allItems|none|none|3|
setLoot(JSONObject, Room, ArrayList\<JSONObject>)|sets the loot for each room|none|addItem(ArrayList\<JSONObject>, ArrayList\<JSONObject>, Room)|JSONObject roomObject, ArrayList\<JSONObject> roomItems|10|
|addItem(ArrayList\<JSONObject>, ArrayList\<JSONObject>, Room)|adds the item to the room|JsonTest jsonTest|checkItem(JSONObject, JSONObject, Room)|JsonTest jsonTest|12|
|checkItem(JSONObject, JSONObject, Room)|checks if the loot matches an item|ArrayList\<Item> allItems|none|JSONObject item, JSONObject loot, ArrayList\<Item> allItems|11|
|setEntrance(JSONObject, Room)|set entrances of current room|JsonTest jsonTest|noExitCheck(JSONArray), addEntrance(Room, Object)|JSONObject roomObject|11|
|noExitCheck(JSONArray)|checks if room has no exits|JsonTest jsonTest|none|JSONArray entrances, JsonTest jsonTest|5|
|addEntrance(Room, Object)|adds an entrance to the room|JsonTest, jsonTest|none|JSONObject entrance, Room connectedRoom, Room tempRoom, JsonTest jsonTest|11|
|listAllRooms()|returns the list of all rooms|ArrayList\<Room> roomList|none|none|3|
|listAllItems()|returns the list of all items|ArrayList\<Item> allItems|none|none|3|
|getCurrentRoom()|returns the current room|Room currentRoom|none|none|3|
|setCurrentRoom(Room)|sets the current room|Room currentRoom|none|none|3|
|removeItem(Item)|removes the item from the list of all items|ArrayList\<Item> allItems|none|Item tempItem, Item item, ArrayList\<Item> allItems|9|
|getCurrentRoomDescription()|returns the current room description|Room currentRoom|none|Room currentRoom|3|
|toString()|returns the name of the current room|Room currentRoom|none|none|3|
