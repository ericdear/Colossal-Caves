package adventure;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import java.awt.event.*;
import java.awt.*;

public class GameView extends JFrame {
    private static final long serialVersionUID = 7305436428373534270L;
    public static final int WIDTH = 700;
    public static final int HEIGHT = 450;

    Container contentPane;
    private Game game;
    private Adventure adventure;
    private String filename;

    JLabel adventureName = new JLabel("Adventure Name: Default_Adventure");
    JLabel playerName = new JLabel("Player Name: ");
    JTextField inputField = new JTextField("Type your commands here");
    JTextArea outputArea = new JTextArea();
    JTextArea inventory = new JTextArea();

    JScrollPane scrollPane;
    //JScrollBar scrollBar;
    

    public static void main(String[] args) {
        Game newGame = new Game();
        GameView gui = new GameView(newGame, args);
        gui.setVisible(true);
    }

    public GameView(Game newGame, String[] args) {
        super();
        game = newGame;
        adventure = game.gameIntro(args);
        filename = game.setFileName(args, adventure);
        setUpSize();
        setMainContainer();
        setAdventure(args);
    }

    private void setAdventure(String[] args) {
        checkNullAdventure();
        outputArea.append(filename + " has been loaded\n\n");
        game.possibleNewPlayer(adventure, "No name", filename);
        outputArea.append(game.displayStartingRoom(adventure.getPlayer().getCurrentRoom()) + "\n");
        playerName.setText("Player name: " + adventure.getPlayer().getName());
        adventureName.setText("Adventure name: " + adventure.getPlayer().getSaveGameName());
        setInventory();
    }

    private void checkNullAdventure() {
        outputArea.setText("");
        if(adventure == null) {
            adventure = game.gameIntro(new String[] {""});
            filename = "default_adventure.json";
            outputArea.append("File could not be opened.\n\n");
        } else {
            filename = adventure.getPlayer().getSaveGameName();
        }
    }

    private void setUpSize() {
        setSize(WIDTH, HEIGHT);
		setTitle("Adventure!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
    }

    private void setMainContainer() {
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(namePanel(), BorderLayout.PAGE_START);
        contentPane.add(playPanel(), BorderLayout.CENTER);
        contentPane.add(rightPanel(), BorderLayout.LINE_END);
        contentPane.add(bottomPanel(), BorderLayout.PAGE_END);
    }

    private JPanel bottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        //bottomPanel.setSize(800, 100);
        return(bottomPanel);
    }

    private JPanel namePanel() {
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.PAGE_AXIS));
        namePanel.add(adventureName);
        playerName.setBorder(new EmptyBorder(0, 0, 10, 0));
        namePanel.add(playerName);
        return(namePanel);
    }

    private JPanel playPanel() {
        JPanel playPanel = new JPanel();
        playPanel.add(inputField());
        playPanel.add(outputArea());
        return(playPanel);
    }

    private JTextField inputField() {
        inputField.addActionListener(enterPressed -> doCommand(inputField));
        inputField.setColumns(45);
        inputField.setMargin(new Insets(5,5,5,5));
        return(inputField);
    }

    private JScrollPane outputArea() {
        outputArea.setColumns(45);
        outputArea.setRows(20);
        outputArea.setMargin(new Insets(5,5,5,5));
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.add(new JScrollPane());
        scrollPane = new JScrollPane(outputArea);
        return(scrollPane);
    }

    private JPanel rightPanel() {
        JPanel rightPanel = new JPanel();
        GridLayout layout = new GridLayout(2,0);
        layout.setVgap(5);
        rightPanel.setLayout(layout);
        rightPanel.add(menuPanel());
        rightPanel.add(inventory());
        return(rightPanel);
    }

    private JPanel menuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5,0));
        menuPanel.add(menuLabel());
        menuPanel.add(changeNameButton());
        menuPanel.add(loadJsonButton());
        menuPanel.add(loadSavedGameButton());
        menuPanel.add(saveButton());
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        return(menuPanel);
    }

    private JLabel menuLabel() {
        JLabel menuLabel = new JLabel("MENU");
        //menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return(menuLabel);
    }

    private JButton changeNameButton() {
        JButton changeNameButton = new JButton("Change Name");
        changeNameButton.addActionListener(buttonPressed -> changeName());
        return(changeNameButton);
    }

    private JButton loadJsonButton() {
        JButton loadJsonButton = new JButton("Load JSON file");
        loadJsonButton.addActionListener(buttonPressed -> loadFile("-a"));
        return(loadJsonButton);
    }

    private JButton loadSavedGameButton() {
        JButton loadSavedGameButton = new JButton("Load saved game");
        loadSavedGameButton.addActionListener(buttonPressed -> loadFile("-l"));
        return(loadSavedGameButton);
    }

    private JButton saveButton() {
        JButton saveButton = new JButton("Save game");
        //saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return(saveButton);
    }

    private JTextArea inventory() {
        inventory.setColumns(15);
        inventory.setRows(10);
        inventory.setMargin(new Insets(5,5,5,5));
        inventory.setEditable(false);
        return(inventory);
    }

    /* These are action listener methods that call methods from game */
    private void changeName() {
        String name = JOptionPane.showInputDialog("Enter your player's new name");
        if(name == null) {
            return;
        }
        //set the Game player name to the new name
        game.changePlayerName(name,adventure);
        //set the player jlabel to the new name
        playerName.setText("Player Name: " + name);
        outputArea.append("Player name changed to " + name + "\n\n");
    }

    private void doCommand(JTextField textField) {
        outputArea.append(textField.getText() + "\n" + game.checkCommand(textField.getText(), adventure.getPlayer()) + "\n");
        setInventory();
        textField.setText("");

        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    private void setInventory() {
        inventory.setText("Inventory:\n" + game.checkCommand("inventory", adventure.getPlayer()));
    }

    private void loadFile(String flag) {
        String file = JOptionPane.showInputDialog("Enter the file path and name.");
        if(file == null || file.equals("")) {
            return;
        }
        String[] arguments = new String[] {flag, file};
        adventure = game.gameIntro(arguments);
        setAdventure(arguments);
    }
    
    
}