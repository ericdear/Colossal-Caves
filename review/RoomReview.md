## Class Room

| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|Room()| initialize room |roomItems, roomEntrances, allRooms|setId(Long), setName(String), setShortDescription(String), setLongDescription(String)|this|9|
|Room(JSONObject)|sets up room|roomItems, roomEntrances, allRooms|setId(long), setName(String), setShortDescription(String), setLongDescription(String)|this|9|
|setName(String)|sets the room name|name|none|String newName|3|
|setId(long)|sets the room id|id|none|long newId|3|
|setShortDescription(String)|sets the room short description|shortDescription|none|String newShort|3|
|setLongDescription(String)|sets the room long description|longDescription|none|String newLong|3|
|listItems()|lists the rooms items|roomItems|none|none|3|
|setRoomItems(ArrayList<Item>)|sets the ArrayList of items in the room|roomItems|none|ArrayList<Item> newRoomItems|3|
|setRoomItem(JSONObject,Room)|adds an item to the roomItems ArrayList|roomItems|none|JSONObject tempItem|9|
|setRoomEntrance(String, Room)|adds a room entrance to the HashMap|RoomEntrances|none|String direction, roomEntrances|4|
|setRoomEntrances(HashMap<String, Room>)|sets all the entrances for the room|roomEntrances|none|none|3|
|getId()|returns the room id|id|none|none|3|
|getName()|returns the room name|name|none|none|3|
|getShortDescription()|returns the rooms short description|shortDescription|none|none|3|
|getLongDescription()|returns the rooms long description|longDescription|none|none|3|
|searchItemDescription(String)|search for the item and returns its description|roomItems|getLongDescription(), getName()|roomItems, Item item, String itemSearched|9|
|getConnectedRoom(String)|find the room connected in the provided direction|roomEntrances|none|roomEntrances, String direction|4|
|seeAllRooms(ArrayList<Room>)|sets the ArrayList of all rooms|allRooms|none|none|3|
|getRoomArray()|returns the list of rooms|allRooms|none|none|3|
|removeItem(String)|removes the item that matches the name as the string and returns it too|roomItems|getName()|roomItems, String itemName|10|
|toString()|returns the name of the room|name|none|none|3|