## Class Item

| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|Item()|initializes Item|none|Item(long, String, String, Room)|this|3|
|Item(long, String, String, Room)|initializes Item|none|setId(long), setName(String), setLongDescription(String), setContainingRoom(Room)|this|6|
|getId()|returns the item id|id|none|none|3|
|setId(long)|sets the item id|id|none|none|3|
|getName()|returns the item name|String name|none|none|3|
|setName(String)|sets the item name|String name|none|none|3|
|getLongDescription()|returns the items description|String longDescription|none|none|3|
|setLongDescription(String)|sets the items description|String longDescription|none|none|3|
|getContainingRoom()|returns the room the item is in|Room containingRoom|none|none|3|
|setContainingRoom(Room)|sets the room the item is in|Room containingRoom|none|none|3|
|toString()|returns the name of the item|String name|none|none|3|