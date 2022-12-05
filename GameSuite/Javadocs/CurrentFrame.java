import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class CurrentFrame extends JFrame implements ActionListener {
    
    private JFrame blackjackFrame;
    private JLabel backgroundLabel;
    private ImageIcon blackjackLabelBackground;
    private ImageIcon sudokuLabelBackground;
    private ImageIcon euchreLabelBackground;
    private ImageIcon playBackground = new ImageIcon("Images" + File.separator + "play-button.png");
    private ImageIcon quitBackground = new ImageIcon("Images" + File.separator + "quit-button.png");
    private ImageIcon hitBackground = new ImageIcon("Images" + File.separator + "hit-button.png");
    private ImageIcon stayBackground = new ImageIcon("Images" + File.separator + "stay-button.png");
    private ImageIcon splitBackground = new ImageIcon("Images" + File.separator + "split-button.png");
    private JButton playButton;
    private JButton quitButton;
    private JButton hitButton;
    private JButton stayButton;
    private JButton splitButton;
    private JLabel dealerCard1;
    private JLabel dealerCard2;
    private ArrayList<JLabel> playerCardImageLabels = new ArrayList<JLabel>();
    private ArrayList<JLabel> dealerCardImageLabels = new ArrayList<JLabel>();
    private JLabel playerCard1;
    private JLabel playerCard2;

    private JFrame menuFrame;
    private JPanel menuBackgroundPanel;
    private JButton blackjackButton;
    private JButton sudokuButton;
    private JButton euchreButton;

    private JFrame playerResultFrame;
    private JLabel playerResultText;
    private JButton playerResultExitButton;
    private JPanel playerResultPanel;

    private JFrame sudokuFrame;
    private JLabel sudokuLabel;
    private JPanel mainGridPanel;
    private JButton[][] gridPanels;
    private JLabel[][] gridPanelLabels;
    private JPanel numberPadPanel;
    private JButton[][] numberPad;
    private JLabel[] numbers;
    private int currentPanelRow = 0;
    private int currentPanelColumn = 0;
    private JButton sudokuPlayButton;
    private JButton sudokuQuitButton;
    private String inputText;

    private JFrame euchreFrame;
    private JLabel euchreLabel;
    private JButton euchrePlayButton;
    private JButton euchreQuitButton;
    private JButton[][] playerCardButtons = new JButton[4][5];
    private Card[] playedCard = new Card[4];
    private JLabel trumpLabel;
    private JButton finalizeButton;

    private String currentGame = "";

    private Blackjack blackjack = new Blackjack();
    private Sudoku sudoku = new Sudoku();
    private Euchre euchre = new Euchre();

    CurrentFrame() {
        this.createMenuFrame();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
            blackjack.startGame();
            dealerCard1 = new JLabel(blackjack.getDealerCards().get(0).getImage());
            dealerCardImageLabels.add(dealerCard1);
            dealerCard1.setBounds(68, 84, 66, 96);
            dealerCard1.setVisible(true);
            dealerCard2 = new JLabel(blackjack.getDealerCards().get(1).getImage());
            dealerCardImageLabels.add(dealerCard2);
            dealerCard2.setBounds(144, 84, 66, 96);
            dealerCard2.setVisible(true);
            backgroundLabel.add(dealerCard1);
            backgroundLabel.add(dealerCard2);



            playerCard1 = new JLabel(blackjack.getPlayerCards().get(0).getImage());
            playerCardImageLabels.add(playerCard1);
            playerCard1.setBounds(68, 202, 66, 96);
            playerCard1.setVisible(true);
            playerCard2 = new JLabel(blackjack.getPlayerCards().get(1).getImage());
            playerCardImageLabels.add(playerCard2);
            playerCard2.setBounds(144, 202, 66, 96);
            playerCard2.setVisible(true);
            backgroundLabel.add(playerCard1);
            backgroundLabel.add(playerCard2);
            
            hitButton.setEnabled(true);
            stayButton.setEnabled(true);
            splitButton.setEnabled(true);
            playButton.setEnabled(false);

            SwingUtilities.updateComponentTreeUI(blackjackFrame);
        }
        if(e.getSource() == quitButton) {
            blackjackFrame.dispose();
            this.createMenuFrame();
        }
        if(e.getSource() == sudokuQuitButton) {
            sudokuFrame.dispose();
            this.createMenuFrame();
        }
        if(e.getSource() == euchreQuitButton) {
            euchreFrame.dispose();
            this.createMenuFrame();
        }
        if(e.getSource() == hitButton) {
            blackjack.playerHit();

            System.out.println("Number of player cards: " + playerCardImageLabels.size());

            System.out.println(blackjack.getPlayerCards().get(blackjack.getPlayerCards().size() - 1).getImage());

            this.playerCardImageLabels.add(new JLabel(blackjack.getPlayerCards().get(blackjack.getPlayerCards().size() - 1).getImage()));

            this.playerCardImageLabels.get(this.playerCardImageLabels.size() - 1).setBounds(68 + (76 * (blackjack.getPlayerCards().size() - 1)), 202, 66, 96);

            for(int i = 0; i < blackjack.getPlayerCards().size(); i++) {
                backgroundLabel.add(this.playerCardImageLabels.get(i));
            }
            if(blackjack.getPlayerCards().size() >= 8) {
                hitButton.setEnabled(false);
            }
            else if(blackjack.getPlayerSum() > 21) {

                this.createResultFrame(blackjack.getPlayerSum() + " ! You have gone bust! You Lose!");
            }

            SwingUtilities.updateComponentTreeUI(blackjackFrame);
        }
        if(e.getSource() == playerResultExitButton) {
            playerResultFrame.dispose();
            for(int i = playerCardImageLabels.size() - 1; i >= 0 ; i--) {
                backgroundLabel.remove(this.playerCardImageLabels.get(i));
            }
            for(int i = dealerCardImageLabels.size() - 1; i >= 0 ; i--) {
                backgroundLabel.remove(this.dealerCardImageLabels.get(i));
            }
            blackjack.getBlackjackDeck().clear();
            blackjack.getPlayerCards().clear();
            blackjack.getDealerCards().clear();
            this.dealerCardImageLabels.clear();
            this.playerCardImageLabels.clear();
            blackjack.setDealerSum(0);
            blackjack.setPlayerSum(0);
            playButton.setEnabled(true);
            hitButton.setEnabled(false);
            stayButton.setEnabled(false);
            splitButton.setEnabled(false);
            backgroundLabel.remove(dealerCard1);
            backgroundLabel.remove(dealerCard2);
            backgroundLabel.remove(playerCard1);
            backgroundLabel.remove(playerCard2);

            SwingUtilities.updateComponentTreeUI(blackjackFrame);
            SwingUtilities.updateComponentTreeUI(sudokuFrame);
            SwingUtilities.updateComponentTreeUI(euchreFrame);
        }
        if(e.getSource() == stayButton) {
            while(blackjack.getDealerSum() < 17) {
                blackjack.dealerHit();
            }
            if(blackjack.getDealerSum() > 21) {
                this.createResultFrame("Dealer has gone bust! YOU WIN!");
            }
            else if(blackjack.compareCards() == 1) {
                this.createResultFrame("You had: " + blackjack.getPlayerSum() + ", Dealer had: " + blackjack.getDealerSum() + ", YOU WIN!");
            }
            else if(blackjack.compareCards() == 0) {
                this.createResultFrame("You had: " + blackjack.getPlayerSum() + ", Dealer had: " + blackjack.getDealerSum() + ", PUSH!");
            }
            else {
                this.createResultFrame("You had: " + blackjack.getPlayerSum() + ", Dealer had: " + blackjack.getDealerSum() + ", YOU LOSE!");
            }
        }
        if(e.getSource() == blackjackButton) {
            currentGame = "blackjack";
            this.createBlackjackFrame();
            menuFrame.dispose();
        }
        if(e.getSource() == sudokuButton) {
            currentGame = "sudoku";
            this.createSudokuFrame();
            menuFrame.dispose();
        }
        if(e.getSource() == euchreButton) {
            currentGame = "euchre";
            this.createEuchreFrame();
            menuFrame.dispose();
        }

        if(e.getSource() == sudokuPlayButton) {
            sudoku.master();
            sudoku.startGame();
            gridPanelLabels = new JLabel[9][9];
            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    if(!sudoku.getLevelStart()[i][j].equals("x")) {
                        gridPanelLabels[i][j] = new JLabel(sudoku.getLevelStart()[i][j]);
                        gridPanelLabels[i][j].setFont(new Font("Calbari", 10, 24));
                        gridPanelLabels[i][j].setForeground(new Color(253, 217, 179));
                        gridPanelLabels[i][j].setAlignmentX(CENTER_ALIGNMENT);
                        gridPanels[i][j].add(gridPanelLabels[i][j]);
                        if(sudoku.getLevelStart()[i][j].equals(sudoku.getMasterKey()[i][j])) {
                            gridPanels[i][j].setBorder(new LineBorder(Color.green, 2, false));
                        }
                    }
                    else {
                        gridPanelLabels[i][j] = new JLabel();
                        gridPanels[i][j].add(gridPanelLabels[i][j]);
                    }
                }
            }
        }
        if(e.getSource() == euchrePlayButton) {
            euchre.generateDeck();
            euchre.distributeCards();
            for(int i = 0; i < 4; i ++) {
                for(int j = 0; j < 5; j++) {
                    playerCardButtons[i][j] = new JButton(euchre.getPlayerCard(j, i).getImage());
                    playerCardButtons[i][j].setBounds(62 + (76 * j), 66 + (118 * i), 66, 96);
                    playerCardButtons[i][j].addActionListener(this);
                    euchreLabel.add(playerCardButtons[i][j]);
                }
            }
            euchre.setTrump(euchre.getPlayerCard(0, 0).getSuit());
            trumpLabel = new JLabel("Trump Suit: " + euchre.getTrump());
            trumpLabel.setBounds(740, 240, 193, 70);
            trumpLabel.setForeground(new Color(253, 217, 179));
            euchreLabel.add(trumpLabel);
            SwingUtilities.updateComponentTreeUI(euchreFrame);
        }
        if(currentGame.equals("euchre")) {
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 5; j++) {
                    if(e.getSource() == playerCardButtons[i][j]) {
                        euchre.setPlayedCard(euchre.getPlayerCard(j, i).getCard(), i);
                        playedCard[i] = euchre.getPlayerCard(j, i).getCard();
                    }
                }
            }
        }

        if(currentGame.equals("sudoku")) {
            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    if(e.getSource() == gridPanels[i][j]) {
                        // gridPanels[i][j].setBorder(new LineBorder(Color.cyan, 2, false));
                        currentPanelRow = i;
                        currentPanelColumn = j;
                        System.out.println("Row: " + (currentPanelRow + 1) + ", Column: " + (currentPanelColumn + 1));
                    }
                    else {
                        gridPanels[i][j].setBorder(new LineBorder(new Color(253, 217, 179), 2, false));
                    }
                    SwingUtilities.updateComponentTreeUI(sudokuFrame);
                }
            }
            if(e.getSource() == gridPanels[currentPanelRow][currentPanelColumn]) {
                gridPanels[currentPanelRow][currentPanelColumn].setBorder(new LineBorder(Color.cyan, 2, false));
                SwingUtilities.updateComponentTreeUI(sudokuFrame);
            }
            if(e.getSource() == numberPad[0][0]) {
                inputText = "9";
    
                gridPanels[currentPanelRow][currentPanelColumn].remove(gridPanelLabels[currentPanelRow][currentPanelColumn]);
    
                gridPanelLabels[currentPanelRow][currentPanelColumn] = new JLabel(inputText);
                gridPanelLabels[currentPanelRow][currentPanelColumn].setFont(new Font("Calbari", 10, 24));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setForeground(new Color(253, 217, 179));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setAlignmentX(CENTER_ALIGNMENT);
                gridPanels[currentPanelRow][currentPanelColumn].add(gridPanelLabels[currentPanelRow][currentPanelColumn]);
                if(inputText.equals(sudoku.getMasterKey()[currentPanelRow][currentPanelColumn])) {
                    System.out.println("Test");
                    gridPanels[currentPanelRow][currentPanelColumn].setBorder(new LineBorder(Color.green, 2, false));
                }
            }
            if(e.getSource() == numberPad[0][1]) {
                inputText = "8";
    
                gridPanels[currentPanelRow][currentPanelColumn].remove(gridPanelLabels[currentPanelRow][currentPanelColumn]);
    
                gridPanelLabels[currentPanelRow][currentPanelColumn] = new JLabel(inputText);
                gridPanelLabels[currentPanelRow][currentPanelColumn].setFont(new Font("Calbari", 10, 24));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setForeground(new Color(253, 217, 179));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setAlignmentX(CENTER_ALIGNMENT);
                gridPanels[currentPanelRow][currentPanelColumn].add(gridPanelLabels[currentPanelRow][currentPanelColumn]);
                System.out.println(sudoku.getMasterKey()[currentPanelRow][currentPanelColumn]);
                if(inputText.equals(sudoku.getMasterKey()[currentPanelRow][currentPanelColumn])) {
                    System.out.println("Test");
                    gridPanels[currentPanelRow][currentPanelColumn].setBorder(new LineBorder(Color.green, 2, false));
                }
            }
            if(e.getSource() == numberPad[0][2]) {
                inputText = "7";
    
                gridPanels[currentPanelRow][currentPanelColumn].remove(gridPanelLabels[currentPanelRow][currentPanelColumn]);
    
                gridPanelLabels[currentPanelRow][currentPanelColumn] = new JLabel(inputText);
                gridPanelLabels[currentPanelRow][currentPanelColumn].setFont(new Font("Calbari", 10, 24));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setForeground(new Color(253, 217, 179));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setAlignmentX(CENTER_ALIGNMENT);
                gridPanels[currentPanelRow][currentPanelColumn].add(gridPanelLabels[currentPanelRow][currentPanelColumn]);
                if(inputText == sudoku.getMasterKey()[currentPanelRow][currentPanelColumn]) {
                    System.out.println("Test");
                    gridPanels[currentPanelRow][currentPanelColumn].setBorder(new LineBorder(Color.green, 2, false));
                }
            }
            if(e.getSource() == numberPad[1][0]) {
                inputText = "6";
    
                gridPanels[currentPanelRow][currentPanelColumn].remove(gridPanelLabels[currentPanelRow][currentPanelColumn]);
    
                gridPanelLabels[currentPanelRow][currentPanelColumn] = new JLabel(inputText);
                gridPanelLabels[currentPanelRow][currentPanelColumn].setFont(new Font("Calbari", 10, 24));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setForeground(new Color(253, 217, 179));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setAlignmentX(CENTER_ALIGNMENT);
                gridPanels[currentPanelRow][currentPanelColumn].add(gridPanelLabels[currentPanelRow][currentPanelColumn]);
                if(inputText == sudoku.getMasterKey()[currentPanelRow][currentPanelColumn]) {
                    System.out.println("Test");
                    gridPanels[currentPanelRow][currentPanelColumn].setBorder(new LineBorder(Color.green, 2, false));
                }
            }
            if(e.getSource() == numberPad[1][1]) {
                inputText = "5";
    
                gridPanels[currentPanelRow][currentPanelColumn].remove(gridPanelLabels[currentPanelRow][currentPanelColumn]);
    
                gridPanelLabels[currentPanelRow][currentPanelColumn] = new JLabel(inputText);
                gridPanelLabels[currentPanelRow][currentPanelColumn].setFont(new Font("Calbari", 10, 24));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setForeground(new Color(253, 217, 179));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setAlignmentX(CENTER_ALIGNMENT);
                gridPanels[currentPanelRow][currentPanelColumn].add(gridPanelLabels[currentPanelRow][currentPanelColumn]);
                if(inputText == sudoku.getMasterKey()[currentPanelRow][currentPanelColumn]) {
                    System.out.println("Test");
                    gridPanels[currentPanelRow][currentPanelColumn].setBorder(new LineBorder(Color.green, 2, false));
                }
            }
            if(e.getSource() == numberPad[1][2]) {
                inputText = "4";
    
                gridPanels[currentPanelRow][currentPanelColumn].remove(gridPanelLabels[currentPanelRow][currentPanelColumn]);
    
                gridPanelLabels[currentPanelRow][currentPanelColumn] = new JLabel(inputText);
                gridPanelLabels[currentPanelRow][currentPanelColumn].setFont(new Font("Calbari", 10, 24));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setForeground(new Color(253, 217, 179));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setAlignmentX(CENTER_ALIGNMENT);
                gridPanels[currentPanelRow][currentPanelColumn].add(gridPanelLabels[currentPanelRow][currentPanelColumn]);
                if(inputText == sudoku.getMasterKey()[currentPanelRow][currentPanelColumn]) {
                    System.out.println("Test");
                    gridPanels[currentPanelRow][currentPanelColumn].setBorder(new LineBorder(Color.green, 2, false));
                }
            }
            if(e.getSource() == numberPad[2][0]) {
                inputText = "3";
    
                gridPanels[currentPanelRow][currentPanelColumn].remove(gridPanelLabels[currentPanelRow][currentPanelColumn]);
    
                gridPanelLabels[currentPanelRow][currentPanelColumn] = new JLabel(inputText);
                gridPanelLabels[currentPanelRow][currentPanelColumn].setFont(new Font("Calbari", 10, 24));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setForeground(new Color(253, 217, 179));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setAlignmentX(CENTER_ALIGNMENT);
                gridPanels[currentPanelRow][currentPanelColumn].add(gridPanelLabels[currentPanelRow][currentPanelColumn]);
                if(inputText == sudoku.getMasterKey()[currentPanelRow][currentPanelColumn]) {
                    System.out.println("Test");
                    gridPanels[currentPanelRow][currentPanelColumn].setBorder(new LineBorder(Color.green, 2, false));
                }
            }
            if(e.getSource() == numberPad[2][1]) {
                inputText = "2";
    
                gridPanels[currentPanelRow][currentPanelColumn].remove(gridPanelLabels[currentPanelRow][currentPanelColumn]);
    
                gridPanelLabels[currentPanelRow][currentPanelColumn] = new JLabel(inputText);
                gridPanelLabels[currentPanelRow][currentPanelColumn].setFont(new Font("Calbari", 10, 24));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setForeground(new Color(253, 217, 179));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setAlignmentX(CENTER_ALIGNMENT);
                gridPanels[currentPanelRow][currentPanelColumn].add(gridPanelLabels[currentPanelRow][currentPanelColumn]);
                if(inputText == sudoku.getMasterKey()[currentPanelRow][currentPanelColumn]) {
                    System.out.println("Test");
                    gridPanels[currentPanelRow][currentPanelColumn].setBorder(new LineBorder(Color.green, 2, false));
                }
            }
            if(e.getSource() == numberPad[2][2]) {
                inputText = "1";
    
                gridPanels[currentPanelRow][currentPanelColumn].remove(gridPanelLabels[currentPanelRow][currentPanelColumn]);
    
                gridPanelLabels[currentPanelRow][currentPanelColumn] = new JLabel(inputText);
                gridPanelLabels[currentPanelRow][currentPanelColumn].setFont(new Font("Calbari", 10, 24));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setForeground(new Color(253, 217, 179));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setAlignmentX(CENTER_ALIGNMENT);
                gridPanels[currentPanelRow][currentPanelColumn].add(gridPanelLabels[currentPanelRow][currentPanelColumn]);
                if(inputText == sudoku.getMasterKey()[currentPanelRow][currentPanelColumn]) {
                    System.out.println("Test");
                    gridPanels[currentPanelRow][currentPanelColumn].setBorder(new LineBorder(Color.green, 2, false));
                }
            }
            if(e.getSource() == numberPad[3][0]) {
                inputText = "0";
    
                gridPanels[currentPanelRow][currentPanelColumn].remove(gridPanelLabels[currentPanelRow][currentPanelColumn]);
    
                gridPanelLabels[currentPanelRow][currentPanelColumn] = new JLabel(inputText);
                gridPanelLabels[currentPanelRow][currentPanelColumn].setFont(new Font("Calbari", 10, 24));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setForeground(new Color(253, 217, 179));
                gridPanelLabels[currentPanelRow][currentPanelColumn].setAlignmentX(CENTER_ALIGNMENT);
                gridPanels[currentPanelRow][currentPanelColumn].add(gridPanelLabels[currentPanelRow][currentPanelColumn]);
                if(inputText.equals(sudoku.getMasterKey()[currentPanelRow][currentPanelColumn])) {
                    System.out.println("Test");
                    gridPanels[currentPanelRow][currentPanelColumn].setBorder(new LineBorder(Color.green, 2, false));
                }
            }
            if(e.getSource() == numberPad[3][1]) {
                gridPanels[currentPanelRow][currentPanelColumn].remove(gridPanelLabels[currentPanelRow][currentPanelColumn]);
    
                gridPanelLabels[currentPanelRow][currentPanelColumn] = new JLabel();
    
                gridPanels[currentPanelRow][currentPanelColumn].add(gridPanelLabels[currentPanelRow][currentPanelColumn]);
            }
            if(e.getSource() == numberPad[3][2]) {
                for(int i = 0; i < 9; i++) {
                    for(int j = 0; j < 9; j++) {
                        gridPanels[i][j].remove(gridPanelLabels[i][j]);
                        gridPanelLabels[i][j] = new JLabel(sudoku.getMasterKey()[i][j]);
                        gridPanelLabels[i][j].setFont(new Font("Calbari", 10, 24));
                        gridPanelLabels[i][j].setForeground(new Color(253, 217, 179));
                        gridPanelLabels[i][j].setAlignmentX(CENTER_ALIGNMENT);
                        gridPanels[i][j].add(gridPanelLabels[i][j]);
                    }
                }
            }
        }
        if(e.getSource() == finalizeButton) {
            this.createResultFrame("Player " + euchre.getWinner().getPlayerNumber() + " wins!");
        }
        
        
        SwingUtilities.updateComponentTreeUI(this);
    }

    /**
     * Method that makes the menu frame pop up on screen.
     * 
     * @author Matthew Polter
     */
    public void createMenuFrame() {

        // Frame setup
        menuFrame = new JFrame("Menu");
        menuFrame.setSize(400, 200);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setVisible(true);
        menuFrame.setLocationRelativeTo(null);

        menuBackgroundPanel = new JPanel();
        menuBackgroundPanel.setSize(400, 200);

        menuFrame.add(menuBackgroundPanel);

        // Create button to take you to the Blackjack frame
        blackjackButton = new JButton("Blackjack");
        blackjackButton.setBounds(50, 5, 300, 50);
        blackjackButton.setFocusable(false);
        blackjackButton.setBorderPainted(false);
        menuBackgroundPanel.add(blackjackButton);
        blackjackButton.addActionListener(this);

        // Create button to take you to the Sudoku frame
        sudokuButton = new JButton("Sudoku");
        sudokuButton.setBounds(50, 55, 300, 50);
        sudokuButton.setFocusable(false);
        sudokuButton.setBorderPainted(false);
        menuBackgroundPanel.add(sudokuButton);
        sudokuButton.addActionListener(this);

        // Create button to take you to the Euchre frame
        euchreButton = new JButton("Euchre");
        euchreButton.setBounds(50, 105, 300, 50);
        euchreButton.setFocusable(false);
        euchreButton.setBorderPainted(false);
        menuBackgroundPanel.add(euchreButton);
        euchreButton.addActionListener(this);
    }

    /**
     * Method to make the Blackjack frame pop up on screen
     * 
     * @author Matthew Polter
     */
    public void createBlackjackFrame() {
        blackjackFrame = new JFrame("Blackjack");
        blackjackLabelBackground = new ImageIcon("Images" + File.separator + "Blackjack-background.png");
        backgroundLabel = new JLabel(blackjackLabelBackground);
        backgroundLabel.setBounds(0, 0, 1000, 600);

        /*
         * Creates the play button
         */
        playButton = new JButton(playBackground);
        playButton.setBounds(740, 72, 193, 70);
        playButton.setFocusable(false);
        playButton.setBorderPainted(false);
        backgroundLabel.add(playButton);
        playButton.addActionListener(this);

        quitButton = new JButton(quitBackground);
        quitButton.setBounds(740, 148, 193, 70);
        quitButton.setFocusable(false);
        quitButton.setBorderPainted(false);
        backgroundLabel.add(quitButton);
        quitButton.addActionListener(this);

        hitButton = new JButton(hitBackground);
        hitButton.setBounds(740, 279, 193, 70);
        hitButton.setFocusable(false);
        hitButton.setBorderPainted(false);
        backgroundLabel.add(hitButton);
        hitButton.addActionListener(this);
        hitButton.setEnabled(false);

        stayButton = new JButton(stayBackground);
        stayButton.setBounds(740, 367, 193, 70);
        stayButton.setFocusable(false);
        stayButton.setBorderPainted(false);
        backgroundLabel.add(stayButton);
        stayButton.addActionListener(this);
        stayButton.setEnabled(false);

        splitButton = new JButton(splitBackground);
        splitButton.setBounds(740, 454, 193, 70);
        splitButton.setFocusable(false);
        splitButton.setBorderPainted(false);
        backgroundLabel.add(splitButton);
        splitButton.addActionListener(this);
        splitButton.setEnabled(false);

        blackjackFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        blackjackFrame.setLayout(null);
        blackjackFrame.setLocationRelativeTo(null);
        blackjackFrame.setVisible(true);
        blackjackFrame.setSize(1000, 600);
        blackjackFrame.add(backgroundLabel);
        blackjackFrame.setLocationRelativeTo(null);
    }

    public void createResultFrame(String text) {
        playerResultFrame = new JFrame("Blackjack");
        playerResultText = new JLabel(text);
        playerResultExitButton = new JButton("EXIT");
        playerResultExitButton.setBounds(75, 150, 50, 25);
        playerResultExitButton.setFocusable(false);
        playerResultExitButton.addActionListener(this);

        
        playerResultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playerResultFrame.setLayout(null);
        playerResultFrame.setVisible(true);
        playerResultFrame.setSize(400, 200);
        playerResultFrame.setLocationRelativeTo(null);

        playerResultPanel = new JPanel();
        playerResultPanel.setSize(400, 200);
        playerResultFrame.add(playerResultPanel);
        playerResultText.setBounds(50, 90, 100, 20);
        playerResultPanel.add(playerResultText);
        playerResultPanel.add(playerResultExitButton);
    }

    public void createSudokuFrame() {

        sudokuLabelBackground = new ImageIcon("Images" + File.separator + "sudoku-background.png");
        sudokuFrame = new JFrame("Sudoku");
        sudokuLabel = new JLabel(sudokuLabelBackground);
        
        sudokuFrame.setSize(1000, 600);
        sudokuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sudokuFrame.setVisible(true);
        sudokuFrame.setLocationRelativeTo(null);

        sudokuLabel.setBounds(0, 0, 1000, 600);
        sudokuFrame.add(sudokuLabel);

        sudokuQuitButton = new JButton(quitBackground);
        sudokuQuitButton.setBounds(732, 132, 193, 70);
        sudokuQuitButton.setFocusable(false);
        sudokuQuitButton.setBorderPainted(false);
        sudokuLabel.add(sudokuQuitButton);
        sudokuQuitButton.addActionListener(this);

        sudokuPlayButton = new JButton(playBackground);
        sudokuPlayButton.setBounds(732, 52, 193, 70);
        sudokuPlayButton.setFocusable(false);
        sudokuPlayButton.setBorderPainted(false);
        sudokuLabel.add(sudokuPlayButton);
        sudokuPlayButton.addActionListener(this);

        mainGridPanel = new JPanel(new GridLayout(9, 9));
        mainGridPanel.setBorder(new LineBorder(new Color(253, 217, 179), 2, false));
        mainGridPanel.setBackground(new Color(253, 217, 179));

        mainGridPanel.setBounds(135, 57, 450, 450);
        mainGridPanel.setVisible(true);

        gridPanels = new JButton[9][9];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                gridPanels[i][j] = new JButton();
                gridPanels[i][j].setBorder(new LineBorder(new Color(253, 217, 179), 2, false));
                gridPanels[i][j].setBackground(new Color(102, 57, 49));
                mainGridPanel.add(gridPanels[i][j]);
                gridPanels[i][j].addActionListener(this);
            }
        }

        numberPadPanel = new JPanel(new GridLayout(3, 4));
        numberPadPanel.setBorder(new LineBorder(new Color(253, 217, 179), 2, false));
        numberPadPanel.setBackground(new Color(253, 217, 179));

        numberPadPanel.setBounds(729, 247, 200, 271);
        numberPadPanel.setVisible(true);

        numberPad = new JButton[4][3];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 3; j++) {
                numberPad[i][j] = new JButton();
                numberPad[i][j].setBorder(new LineBorder(new Color(253, 217, 179), 2, false));
                numberPad[i][j].setBackground(new Color(102, 57, 49));
                numberPadPanel.add(numberPad[i][j]);
                numberPad[i][j].addActionListener(this);
            }
        }
        numbers = new JLabel[12];
        numbers[0] = new JLabel("1");
        numbers[1] = new JLabel("2");
        numbers[2] = new JLabel("3");
        numbers[3] = new JLabel("4");
        numbers[4] = new JLabel("5");
        numbers[5] = new JLabel("6");
        numbers[6] = new JLabel("7");
        numbers[7] = new JLabel("8");
        numbers[8] = new JLabel("9");
        numbers[9] = new JLabel("0");
        numbers[10] = new JLabel("*");
        numbers[11] = new JLabel(">");

        for(int i = 0; i < 12; i++) {
            numbers[i].setFont(new Font("Calbari", 10, 32));
            numbers[i].setForeground(new Color(253, 217, 179));
            numbers[i].setAlignmentX(CENTER_ALIGNMENT);
        }

        numberPad[0][0].add(numbers[8]);
        numberPad[0][1].add(numbers[7]);
        numberPad[0][2].add(numbers[6]);
        numberPad[1][0].add(numbers[5]);
        numberPad[1][1].add(numbers[4]);
        numberPad[1][2].add(numbers[3]);
        numberPad[2][0].add(numbers[2]);
        numberPad[2][1].add(numbers[1]);
        numberPad[2][2].add(numbers[0]);
        numberPad[3][0].add(numbers[9]);
        numberPad[3][1].add(numbers[10]);
        numberPad[3][2].add(numbers[11]);

        sudokuLabel.add(mainGridPanel);
        sudokuLabel.add(numberPadPanel);
    }
    public void createEuchreFrame() {
        euchreLabelBackground = new ImageIcon("Images" + File.separator + "Blackjack-background.png");
        euchreFrame = new JFrame("Euchre");
        euchreLabel = new JLabel(euchreLabelBackground);
        
        euchreFrame.setSize(1000, 600);
        euchreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        euchreFrame.setVisible(true);
        euchreFrame.setLocationRelativeTo(null);

        euchreLabel.setBounds(0, 0, 1000, 600);
        euchreFrame.add(euchreLabel);

        euchreQuitButton = new JButton(quitBackground);
        euchreQuitButton.setBounds(732, 132, 193, 70);
        euchreQuitButton.setFocusable(false);
        euchreQuitButton.setBorderPainted(false);
        euchreLabel.add(euchreQuitButton);
        euchreQuitButton.addActionListener(this);

        euchrePlayButton = new JButton(playBackground);
        euchrePlayButton.setBounds(732, 52, 193, 70);
        euchrePlayButton.setFocusable(false);
        euchrePlayButton.setBorderPainted(false);
        euchreLabel.add(euchrePlayButton);
        euchrePlayButton.addActionListener(this);

        finalizeButton = new JButton("Finalize Results");
        finalizeButton.setBounds(732, 400, 193, 70);
        finalizeButton.setFocusable(false);
        finalizeButton.setBorderPainted(false);
        euchreLabel.add(finalizeButton);
        finalizeButton.addActionListener(this);
    }
}
