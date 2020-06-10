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
|setRoomItem(JSONObject,Room)|adds an item to the roomItems ArrayList|roomItems|none|9|