package adventure;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.graalvm.compiler.lir.CompositeValue.Component;

//import org.graalvm.compiler.lir.CompositeValue.Component;

//import jdk.internal.platform.Container;

import java.awt.event.*;
import java.awt.*;

public class GameView extends JFrame {
    private static final long serialVersionUID = 7305436428373534270L;
    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;
    Dimension dimension = new Dimension(200,50);
    Container contentPane;
    private Game game;
    private Adventure adventure;
    JLabel adventureName = new JLabel("Adventure Name: Default_Adventure");
    JLabel playerName = new JLabel("Player Name: Eric");
    JTextField inputField = new JTextField("Type your commands here");
    JTextArea outputArea = new JTextArea();
    JTextArea inventory = new JTextArea();
    

    public static void main(String[] args) {
        Game newGame = new Game();
        GameView gui = new GameView(newGame);
        gui.setVisible(true);
    }

    public GameView(Game newGame) {
        super();
        game = newGame;
        adventure = newGame.gameIntro(new String[] {""});
        setUpSize();
        setMainContainer();
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
        //inputField.addActionListener(enterPressed -> changeName(inputField));
        inputField.setColumns(45);
        inputField.setMargin(new Insets(5,5,5,5));
        return(inputField);
    }

    private JTextArea outputArea() {
        outputArea.setColumns(45);
        outputArea.setRows(20);
        outputArea.setMargin(new Insets(5,5,5,5));
        outputArea.setEditable(false);
        return(outputArea);
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
        //changeNameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        changeNameButton.addActionListener(buttonPressed -> changeName());
        return(changeNameButton);
    }

    private JButton loadJsonButton() {
        JButton loadJsonButton = new JButton("Load JSON file");
        //loadJsonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return(loadJsonButton);
    }

    private JButton loadSavedGameButton() {
        JButton loadSavedGameButton = new JButton("Load saved game");
        //loadSavedGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return(loadSavedGameButton);
    }

    private JButton saveButton() {
        JButton saveButton = new JButton("Save game");
        //saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return(saveButton);
    }

    private JTextArea inventory() {
        inventory.setColumns(2);
        inventory.setRows(2);
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
        changePlayerName(name);
        
        //set the player jlabel to the new name
        playerName.setText("Player Name: " + name);
        outputArea.setText("Player name changed to " + name);
    }

    private void changePlayerName(String name) {
        if(adventure.getPlayer() == null) {
            adventure.setPlayer(new Player(name, adventure.getCurrentRoom(), adventure.listAllRooms()));
        } else {
            adventure.getPlayer().setName(name);
        }
    }
    
}