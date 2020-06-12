## Class Room

| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|Room()| initialize room |ArrayList\<Item> roomItems, HashMap<String, Room> roomEntrances, ArrayList\<Room> allRooms|setId(Long), setName(String), setShortDescription(String), setLongDescription(String)|this|9|
|Room(JSONObject)|sets up room|ArrayList\<Item> roomItems, HashMap<String, Room> roomEntrances, ArrayList\<Room> allRooms|setId(long), setName(String), setShortDescription(String), setLongDescription(String)|this|9|
|setName(String)|sets the room name|String name|none|String newName|3|
|setId(long)|sets the room id|long id|none|long newId|3|
|setShortDescription(String)|sets the room short description|String shortDescription|none|String newShort|3|
|setLongDescription(String)|sets the room long description|String longDescription|none|String newLong|3|
|listItems()|lists the rooms items|ArrayList\<Item> roomItems|none|none|3|
|setRoomItems(ArrayList\<Item>)|sets the ArrayList of items in the room|ArrayList\<Item> roomItems|none|ArrayList\<Item> newRoomItems|3|
|setRoomItem(JSONObject,Room)|adds an item to the roomItems ArrayList|ArrayList\<Item> roomItems|none|JSONObject tempItem|9|
|addItem(Item)|ads the item to the list of items in the room|ArrayList\<Item> roomItems|none|ArrayList\<Item> roomItems|3|
|checkItemKind(long, String, String, Room, JSONObject)|checks what kind the item is and returns it|none|checkFood(long, String, String, Room, JSONObject), checkClothing(long, String, String, Room, JSONObject), checkWeapon(long, String, String, Room, JSONObject), checkSpell(long, String, String, Room, JSONObject)|none|13(reason in README.md)|
|checkClothing(long, String, String, Room, JSONObject)|checks if the item is clothing|none|none|Item item|10|
|checkWeapon(long, String, String, Room, JSONObject)|check if item is a weapon|none|none|Item item|7|
|checkFood(long, String, String, Room, JSONObject)|checks if the item is food|none|none|Item item|10|
|checkSpell(long, String, String, Room, JSONObject)|checks if the item is a spell|none|none|Item item|7|
|setRoomEntrance(String, Room)|adds a room entrance to the HashMap|RoomEntrances|none|String direction, HashMap<String, Room> roomEntrances|4|
|setRoomEntrances(HashMap<String, Room>)|sets all the entrances for the room|HashMap<String, Room> roomEntrances|none|none|3|
|getId()|returns the room id|long id|none|none|3|
|getName()|returns the room name|String name|none|none|3|
|getShortDescription()|returns the rooms short description|String shortDescription|none|none|3|
|getLongDescription()|returns the rooms long description|String longDescription|none|none|3|
|searchItemDescription(String)|search for the item and returns its description|ArrayList\<Item> roomItems|getLongDescription(), getName()|ArrayList\<Item> roomItems, Item item, String itemSearched|9|
|getConnectedRoom(String)|find the room connected in the provided direction|HashMap<String, Room> roomEntrances|none|HashMap<String, Room> roomEntrances, String direction|4|
|setAllRooms(ArrayList\<Room>)|sets the ArrayList of all rooms|ArrayList\<Room> allRooms|none|none|3|
|getRoomArray()|returns the list of rooms|ArrayList\<Room> allRooms|none|none|3|
|removeItem(String)|removes the item that matches the name as the string and returns it too|roomItems|getName()|ArrayList\<Item> roomItems, String itemName|10|
|toString()|returns the name of the room|String name|none|none|3|