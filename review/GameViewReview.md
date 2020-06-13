## Class GameView

| method sig | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
|main(String[])|sets up the gui|none|GameView(Game, String[])|GameView gui|5|
|GameView(Game, String[])|sets up the GUI|Game game, Adventure adventure|setUpSize(), setMainContainer(), setAdventure(String[])|Game game|8|
|setAdventure(String[])|sets up the adventure|String filename, JTextArea outputArea, Jlabel playerName, JLabel adventureName|checkNullAdventure(), setInventory()|Game game, JTextArea outputArea, JLabel playerName, JLabel adventureName, Adventure adventure|9|
|checkNullAdventure()|checks if the adventure is null|Adventure adventure, String filename, JTextArea outputArea|loadDefaultAdventure()|JTextArea outputArea, Game game, Adventure adventure|10|
|loadDefaultAdventure()|loads the default adventure json|Adventure adventure, String filename, JTextArea outputArea|none|Game game, JTextArea outputArea|6|
|setUpSize()|sets up the size of the gui|none|none|none|5|
|setMainContainer()|sets the main container of the gui|Container contentPane|namePanel(), playPanel(), rightPanel(), bottomPanel()|Container contentPane|8|
|bottomPanel()|returns the bottom JPanel||none|none|JPanel bottomPanel|5|
|namePanel()|returns the name JPanel|JLabel playerName|none|JPanel namePanel, Jlabel playerName|8|
|playPanel()|returns the play JPanel|none|inputField(), outputArea()|JPanel playPanel|6|
|inputField()|returns the input field JTextArea|JTextField inputField|doCommand(JTextField)|JTextField inputField|6|
|outputArea()|returns the output area|JTextArea outputArea, JScrollPane scrollPane|none|JTextArea outputArea|11|
|rightPanel()|returns the right JPanel|none|menuPanel(), inventory()|JPanel rightPanel|8|
|menuPanel()|returns the menu panel|none|menuLabelPanel(), changeNameButton(), loadJsonButton(), loadSavedGameButton(), saveButton()|JPanel menuPanel|11|
|menuLabelPanel()|returns the menu label|none|none|none|4|
|changeNameButton()|returns the change name button|none|changeName()|JButton changeNameButton|5|
|loadJsonButton()|returns the load json button|none|loadFile(String)|JButton loadJsonButton|5|
|loadSavedGameButton()|returns the load saved game button|none|loadFile(String)|JButton loadSavedGameButton|5|
|saveButton()|returns the save button|none|save()|JButton saveButton|5|
|inventory()|returns the inventory text area scroll pane|JTextArea inventory|none|JTextArea inventory|10|
|changeName()|changes the players name|String name, Adventure adventure, Game game, JLabel playerName, JTextArea outputArea|none|JoptionPane, Game game, JLabel playerName, JTextArea outputArea|9|
|doCommand(JTextArea)|does the command the user enters|JTextArea outputArea, Adventure adventure|setInventory(), checkExit(String)|JTextArea outputArea, JTextField textField, Adventure adventure|8|
|setInventory()|sets the inventory of the player|JTextArea inventory, Adventure adventure, Game game|none|JTextArea inventory, Game game, Adventure adventure|3|
|loadFile(String)|load the file the user wants|Adventure adventure, Game game|askToSave(), setAdventure(String[])|JOptionPane, String file, Game game|10|
|save()|serializes the game|String filename, Adventure adventure, Game game|none|JOptionPane, Adventure adventure, Game game, String filename|8|
|checkExit(String)|check if the user wants to exit|none|exit()|String command|6|
|exit()|exit if the user wants to|none|askToSave()|JOptionPane, String saveAnswer|12|
|askToSave()|ask the user to save|none|save()|String saveAnswer, JOptionPane|11|
