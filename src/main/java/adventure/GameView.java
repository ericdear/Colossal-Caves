package adventure;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Container;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;


public class GameView extends JFrame {
    private static final long serialVersionUID = 7305436428373534270L;
    public static final int WIDTH = 700;
    public static final int HEIGHT = 450;

    private static final int NONE = 0;
    private static final int SMALL = 5;
    private static final int MEDIUM = 10;

    private static final int SMALLCOL = 15;
    private static final int BIGCOL = 45;
    private static final int ROWS = 20;

    private Container contentPane;
    private Game game;
    private Adventure adventure;
    private String filename;

    private JLabel adventureName = new JLabel("Adventure Name: Default_Adventure");
    private JLabel playerName = new JLabel("Player Name: ");
    private JTextField inputField = new JTextField("Type your commands here");
    private JTextArea outputArea = new JTextArea();
    private JTextArea inventory = new JTextArea();

    private JScrollPane scrollPane;
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
        
        setUpSize();
        setMainContainer();
        setAdventure(args);
    }

    private void setAdventure(String[] args) {
        filename = game.setFileName(args, adventure);
        

        checkNullAdventure();
        outputArea.append(filename + " has been loaded\n\n");

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
            outputArea.append(game.getErrorMessage() + "\nFile could not be opened.\nPlease use the menu to load a file or use our default adventure.\n\n");
            game.possibleNewPlayer(adventure, "No name", filename);
        } else {
            game.possibleNewPlayer(adventure, "No name", filename);
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
        playerName.setBorder(new EmptyBorder(NONE, NONE, MEDIUM, NONE));
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
        inputField.setColumns(BIGCOL);
        inputField.setMargin(new Insets(SMALL,SMALL,SMALL,SMALL));
        return(inputField);
    }

    private JScrollPane outputArea() {
        outputArea.setColumns(BIGCOL);
        outputArea.setRows(ROWS);
        outputArea.setMargin(new Insets(SMALL,SMALL,SMALL,SMALL));
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
        layout.setVgap(SMALL);
        rightPanel.setLayout(layout);
        rightPanel.add(menuPanel());
        rightPanel.add(inventory());
        return(rightPanel);
    }

    private JPanel menuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(SMALL,0));
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
        saveButton.addActionListener(buttonPressed -> save());
        return(saveButton);
    }

    private JTextArea inventory() {
        inventory.setColumns(SMALLCOL);
        inventory.setRows(MEDIUM);
        inventory.setMargin(new Insets(SMALL,SMALL,SMALL,SMALL));
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
        String output = game.checkCommand(textField.getText(), adventure.getPlayer());
        outputArea.append(textField.getText() + "\n" + output + "\n");
        setInventory();
        checkExit(textField.getText());
        textField.setText("");

        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    private void setInventory() {
        inventory.setText("Inventory:\n" + game.checkCommand("inventory", adventure.getPlayer()));
    }

    private void loadFile(String flag) {
        askToSave();
        String file = JOptionPane.showInputDialog("Enter the file path and name.");
        if(file == null || file.equals("")) {
            return;
        }
        String[] arguments = new String[] {flag, file};
        adventure = game.gameIntro(arguments);
        setAdventure(arguments);
    }

    private void save() {
        filename = JOptionPane.showInputDialog("What would you like to name your saved game?");
        if(filename == null || filename.equals("")) {
            return;
        }
        adventure.getPlayer().setSaveGameName(filename);

        game.saveGame(adventure, adventure.getPlayer(), filename);
    }

    private void checkExit(String command) {
        command.toLowerCase();
        if(command.equals("quit") || command.equals("exit")) {
            exit();
        }
    }

    private void exit() {
        String saveAnswer = "";
        while(!saveAnswer.equals("yes") && !saveAnswer.equals("no")) {
            saveAnswer = JOptionPane.showInputDialog("Are you sure you want to exit? (yes/no)");
            if(saveAnswer == null || saveAnswer.equals("")) {
                return;
            } else if(saveAnswer.equals("yes")) {
                askToSave();
                System.exit(0);
            }
        }
    }

    private void askToSave() {
        String saveAnswer = "";
        while(!saveAnswer.equals("yes") && !saveAnswer.equals("no")) {
            saveAnswer = JOptionPane.showInputDialog("Do you want to save first? (yes/no)");
            if(saveAnswer == null || saveAnswer.equals("")) {
                return;
            } else if(saveAnswer.equals("yes")) {
                save();
            }
        }
    }
    
}
