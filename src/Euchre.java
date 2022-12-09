import java.io.File;
import java.util.*;

import javax.swing.ImageIcon;

public class Euchre extends Player{
    
    // 2 teams of 2
    // In pre-round, take 2-8 of each suit out of the deck of cards
    // Deal each player 5 cards
    // Keep the remaining 4 cards with the dealer
    // Flip the top card face up in the middle of the table
    // If any player agrees to this card, its suit becomes trump.
    // The dealer then replaces one of their cards with the trump card
    // If nobody says yes to the original suit, the person to the dealers left can choose any suit they want
    /**
     * Once trump is established, the card ranking goes as such:
     * 
     * 1. Jack of trump
     * 2. Jack of same color
     * 3. Ace of trump
     * 4. King of trump
     * 5. Queen of trump
     * 6. 10 of trump
     * 7. 9 of trump
     */

    // Once the round begins, the player to the dealers left is the first to go, with that card becoming the leading suit
    // Everybody else must then play a card of the suit if they have it.
    // if they don't, they can play a card of any suit.
    // Whoever has the highest ranking card wins the trick and collects the cards
    // Whoever wins the trick plays the leading card for the next trick.
    /**
     * Once the game is complete, the scoring goes as follows:
     * 
     * The team that chose the trump suit gets one point for every
     * trick they won 3-4 tricks, or 2 points if they won 5 tricks
     * 
     * The defending team gets two points for every trick they won
     * 3-4 tricks, or 4 points per trick if they won 5 tricks.
     * 
     * If the team that chose the trump suit fails to win at least 3
     * tricks the defending team gets 2 points
     * 
     * keep playing rounds until one team has at least 10 points.
     */
    private ArrayList<Card> deck = new ArrayList<Card>();

    private Random rand = new Random();
    private int randomCard = 0;
    private int numberToLeft = 0;
    private int currentPlayerNum = 0;
    private String trump = "";
    private String trumpColor = "";
    private Scanner scan = new Scanner(System.in);
    private int playCard = 0;
    private Card[] playerCardPlayed = new Card[4];
    private int winnerIndex = 0;
    private int winnerRank = 8;

    Player[] team1 = new Player[2];
    Player[] team2 = new Player[2];

    Player[] player = new Player[4];

    /**
     * Constructor that creates the 4 players and assigns them
     * to teams.
     * 
     * @author Matthew Polter
     */
    public Euchre() {
        super(1, true, true);
        player[0] = new Player(1, true, true);
        player[1] = new Player(2, false, false);
        player[2] = new Player(3, false, false);
        player[3] = new Player(4, false, false);

        team1[0] = player[0];
        team1[1] = player[2];

        team2[0] = player[1];
        team2[1] = player[3];
    }

    /**
     * Creates the necessary 24 cards for the game
     * 
     * @author Matthew Polter
     */
    public void generateDeck() {

        // Generate Diamonds cards
        deck.add(new Card("jack", "red", "diamonds", 1, new ImageIcon("Images" + File.separator + "jack-of-diamonds.png")));
        deck.add(new Card("ace", "red", "diamonds", 1, new ImageIcon("Images" + File.separator + "ace-of-diamonds.png")));
        deck.add(new Card("king", "red", "diamonds", 1, new ImageIcon("Images" + File.separator + "king-of-diamonds.png")));
        deck.add(new Card("queen", "red", "diamonds", 1, new ImageIcon("Images" + File.separator + "queen-of-diamonds.png")));
        deck.add(new Card("10", "red", "diamonds", 1, new ImageIcon("Images" + File.separator + "10-of-diamonds.png")));
        deck.add(new Card("9", "red", "diamonds", 1, new ImageIcon("Images" + File.separator + "9-of-diamonds.png")));

        // Generate Hearts cards
        deck.add(new Card("jack", "red", "hearts", 1, new ImageIcon("Images" + File.separator + "jack-of-hearts.png")));
        deck.add(new Card("ace", "red", "hearts", 1, new ImageIcon("Images" + File.separator + "ace-of-hearts.png")));
        deck.add(new Card("king", "red", "hearts", 1, new ImageIcon("Images" + File.separator + "king-of-hearts.png")));
        deck.add(new Card("queen", "red", "hearts", 1, new ImageIcon("Images" + File.separator + "queen-of-hearts.png")));
        deck.add(new Card("10", "red", "hearts", 1, new ImageIcon("Images" + File.separator + "10-of-hearts.png")));
        deck.add(new Card("9", "red", "hearts", 1, new ImageIcon("Images" + File.separator + "9-of-hearts.png")));

        // Generate Spades cards
        deck.add(new Card("jack", "black", "spades", 1, new ImageIcon("Images" + File.separator + "jack-of-spades.png")));
        deck.add(new Card("ace", "black", "spades", 1, new ImageIcon("Images" + File.separator + "motorhead.png")));
        deck.add(new Card("king", "black", "spades", 1, new ImageIcon("Images" + File.separator + "king-of-spades.png")));
        deck.add(new Card("queen", "black", "spades", 1, new ImageIcon("Images" + File.separator + "queen-of-spades.png")));
        deck.add(new Card("10", "black", "spades", 1, new ImageIcon("Images" + File.separator + "10-of-spades.png")));
        deck.add(new Card("9", "black", "spades", 1, new ImageIcon("Images" + File.separator + "9-of-spades.png")));

        // Generate Clubs cards
        deck.add(new Card("jack", "black", "clubs", 1, new ImageIcon("Images" + File.separator + "jack-of-clubs.png")));
        deck.add(new Card("ace", "black", "clubs", 1, new ImageIcon("Images" + File.separator + "ace-of-clubs.png")));
        deck.add(new Card("king", "black", "clubs", 1, new ImageIcon("Images" + File.separator + "king-of-clubs.png")));
        deck.add(new Card("queen", "black", "clubs", 1, new ImageIcon("Images" + File.separator + "queen-of-clubs.png")));
        deck.add(new Card("10", "black", "clubs", 1, new ImageIcon("Images" + File.separator + "10-of-clubs.png")));
        deck.add(new Card("9", "black", "clubs", 1, new ImageIcon("Images" + File.separator + "9-of-clubs.png")));
    }

    /**
     * Gives 5 cards at random to each player. When a player
     * recieves a card, it is removed from the deck of cards.
     * 
     * @author Matthew Polter
     */
    public void distributeCards() {

        for(int i = 0; i < 4; i++) {
            System.out.println("PLayer " + (i + 1) + " Cards:");
            System.out.println("");
            for(int j = 0; j < 5; j++) {
                randomCard = rand.nextInt(this.getDeck().size());
                player[i].giveCard(this.getDeck().get(randomCard));
                this.getDeck().remove(randomCard);
                System.out.println(player[i].getCards().get(j).getName() + " of " + player[i].getCards().get(j).getSuit());
            }
            System.out.println("");
        }

    }

    /**
     * Checks whose turn it is.
     * 
     * @author Matthew Polter
     * @return The index of the current player.
     */
    public int updateCurrentPlayerNum() {
        for(int i = 0; i < 4; i++) {
            if(this.player[i].isPlayerTurn()) {
                currentPlayerNum = i;
            }
        }
        return currentPlayerNum;
    }

    /**
     * @author Matthew Polter
     * @return The player to the left of the current player.
     */
    public Player toLeft() {
        for(int i = 0; i < 4; i++) {
            if(this.player[i].isPlayerTurn() && i < 3) {
                numberToLeft = i + 1;
            }
            else {
                numberToLeft = 1;
            }
        }
        return this.player[numberToLeft];
    }

    /**
     * Changes the current player to whoever is to the left of
     * the current player.
     * 
     * @author Matthew Polter
     */
    public void changeTurn() {
        if(this.currentPlayerNum < 3) {
            this.player[currentPlayerNum + 1].setPlayerTurn(true);
            this.player[currentPlayerNum].setPlayerTurn(false);
        }
        else {
            this.player[0].setPlayerTurn(true);
            this.player[currentPlayerNum].setPlayerTurn(false);
        }
    }

    /**
     * Gets the ArrayList of card currently in the deck.
     * 
     * @author Matthew Polter
     * @return The current state of the deck of cards.
     */
    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    /**
     * Assigns a rank to the cards that the players chose in the
     * round based on the following rules:
     * 
     * 1. Jack of trump
     * 2. Jack of same color
     * 3. Ace of trump
     * 4. King of trump
     * 5. Queen of trump
     * 6. 10 of trump
     * 7. 9 of trump
     * 8. Anything else
     * 
     * @author Matthew Polter
     */
    public void rankCards() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                if(this.player[i].getCards().get(j).getSuit() == this.trump && this.player[i].getCards().get(j).getName() == "jack") {
                    this.player[i].getCards().get(j).setRank(1);
                }
                else if(this.player[i].getCards().get(j).getName() == "jack" && this.player[i].getCards().get(j).getColor() == this.trumpColor && this.player[i].getCards().get(j).getSuit() != this.trump) {
                    this.player[i].getCards().get(j).setRank(2);
                }
                else if(this.player[i].getCards().get(j).getSuit() == this.trump && this.player[i].getCards().get(j).getName() == "ace") {
                    this.player[i].getCards().get(j).setRank(3);
                }
                else if(this.player[i].getCards().get(j).getSuit() == this.trump && this.player[i].getCards().get(j).getName() == "king") {
                    this.player[i].getCards().get(j).setRank(4);
                }
                else if(this.player[i].getCards().get(j).getSuit() == this.trump && this.player[i].getCards().get(j).getName() == "queen") {
                    this.player[i].getCards().get(j).setRank(5);
                }
                else if(this.player[i].getCards().get(j).getSuit() == this.trump && this.player[i].getCards().get(j).getName() == "10") {
                    this.player[i].getCards().get(j).setRank(6);
                }
                else if(this.player[i].getCards().get(j).getSuit() == this.trump && this.player[i].getCards().get(j).getName() == "9") {
                    this.player[i].getCards().get(j).setRank(7);
                }
                else {
                    this.player[i].getCards().get(j).setRank(8);
                }
            }
        }
    }

    /**
     * Gets the current color of the trump card to help determine the ranks
     * of the cards in the deck.
     * 
     * @author Matthew Polter
     * @return The color of the current trump card.
     */
    public String getTrumpColor() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                if(this.player[i].getCards().get(j).getSuit() == this.trump) {
                    this.trumpColor = this.player[i].getCards().get(j).getColor();
                }
            }
        }
        return this.trumpColor;
    }

    /**
     * PLays the round.
     * 
     * Each player chooses a card to play and the ranks of those
     * cards are compared to determine the winner.
     * 
     * @author Matthew polter
     */
    public void playRound() {
        System.out.println("Top dealer card:");
        System.out.println(this.getDeck().get(0).getName() + " of " + this.getDeck().get(0).getSuit());
        System.out.println("");

        this.setTrump(this.getDeck().get(0).getSuit());
        System.out.println("Trump suit: " + this.getTrump());
        this.getTrumpColor();
        this.rankCards();

        this.updateCurrentPlayerNum();
        this.changeTurn();
        this.updateCurrentPlayerNum();

        //First player turn
        System.out.println("Player " + this.player[this.currentPlayerNum].getPlayerNumber() + "'s turn");
        System.out.println("Choose a card to play:");
        playCard = scan.nextInt();
        System.out.println("You have chosen to play the " + this.player[this.currentPlayerNum].getCard(playCard).getName() + " of " + this.player[this.currentPlayerNum].getCard(playCard).getSuit());
        this.setPlayedCard(this.player[this.currentPlayerNum].getCard(playCard), this.currentPlayerNum);
        System.out.println("Card rank: " + this.player[this.currentPlayerNum].getCard(playCard).getRank());
        this.changeTurn();
        this.updateCurrentPlayerNum();

        //Next players turn
        System.out.println("Player " + this.player[this.currentPlayerNum].getPlayerNumber() + "'s turn");
        System.out.println("Choose a card to play:");
        playCard = scan.nextInt();
        System.out.println("You have chosen to play the " + this.player[this.currentPlayerNum].getCard(playCard).getName() + " of " + this.player[this.currentPlayerNum].getCard(playCard).getSuit());
        this.setPlayedCard(this.player[this.currentPlayerNum].getCard(playCard), this.currentPlayerNum);
        System.out.println("Card rank: " + this.player[this.currentPlayerNum].getCard(playCard).getRank());
        this.changeTurn();
        this.updateCurrentPlayerNum();

        //Next players turn
        System.out.println("Player " + this.player[this.currentPlayerNum].getPlayerNumber() + "'s turn");
        System.out.println("Choose a card to play:");
        playCard = scan.nextInt();
        System.out.println("You have chosen to play the " + this.player[this.currentPlayerNum].getCard(playCard).getName() + " of " + this.player[this.currentPlayerNum].getCard(playCard).getSuit());
        this.setPlayedCard(this.player[this.currentPlayerNum].getCard(playCard), this.currentPlayerNum);
        System.out.println("Card rank: " + this.player[this.currentPlayerNum].getCard(playCard).getRank());
        this.changeTurn();
        this.updateCurrentPlayerNum();

        //Next players turn
        System.out.println("Player " + this.player[this.currentPlayerNum].getPlayerNumber() + "'s turn");
        System.out.println("Choose a card to play:");
        playCard = scan.nextInt();
        System.out.println("You have chosen to play the " + this.player[this.currentPlayerNum].getCard(playCard).getName() + " of " + this.player[this.currentPlayerNum].getCard(playCard).getSuit());
        this.setPlayedCard(this.player[this.currentPlayerNum].getCard(playCard), this.currentPlayerNum);
        System.out.println("Card rank: " + this.player[this.currentPlayerNum].getCard(playCard).getRank());
    }

    /**
     * Sets the card that a player has played for the current round.
     * 
     * @author Matthew Polter
     * @param card The card the player wants to play.
     * @param index The index of the player card played.
     */
    public void setPlayedCard(Card card, int index) {
        this.playerCardPlayed[index] = card;
    }

    /**
     * Gets the card that a player has played for the current round.
     * 
     * @author Matthew Polter
     * @param index The index of the card played.
     * @return The played Card at the index.
     */
    public Card getPlayedCard(int index) {
        return this.playerCardPlayed[index];
    }

    /**
     * Finds the winner of the game by comparing the ranks of each of the player
     * cards played.
     * 
     * @author Matthew Polter
     * @return The player that won the round.
     */
    public Player getWinner() {
        for(int i = 0; i < 4; i++) {
            if(this.getPlayedCard(i).getRank() < winnerRank) {
                winnerRank = this.getPlayedCard(i).getRank();
                this.winnerIndex = i;
            }
        }
        return this.player[this.winnerIndex];
    }

    /**
     * Sets the current trump card.
     * 
     * @author Matthew Polter
     * @param trump Set the value of the current trump card.
     */
    public void setTrump(String trump) {
        this.trump = trump;
    }

    /**
     * Gets the current trump suit.
     * 
     * @author Matthew Polter
     * @return The current value of the trump card.
     */
    public String getTrump() {
        return this.trump;
    }

    /**
     * Gets the specified player object based on the parameter.
     * 
     * @author Matthew Polter
     * @param playerNum The number of the player you would like to get.
     * @return The player specified by the parameter.
     */
    public Player getPlayer(int playerNum) {
        return this.player[playerNum];
    }

    /**
     * Gets a specific Card for a specific Player.
     * 
     * @author Matthew Polter
     * @param index The number of the Card you would like to get.
     * @param playerNum The number of the Player you would like to get.
     * @return
     */
    public Card getPlayerCard(int index, int playerNum) {
        return player[playerNum].getCard(index);
    }

    public static void main(String[] args) {

        Euchre euchre = new Euchre();

        euchre.generateDeck();
        euchre.distributeCards();

        euchre.playRound();
        euchre.scan.close();
        System.out.println("Player " + euchre.getWinner().getPlayerNumber() + " wins!");
    }
}
