import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player extends Card {
    
    private int playerNum = 0;
    private boolean isDealer = false;
    private boolean isPlayerTurn = false;
    private ArrayList<Card> playerCards = new ArrayList<Card>();

    /**
     * Euchre Constructor.
     * 
     * @author Matthew Polter
     * @param playerNum Sets the player number.
     * @param isDealer Sets whether or not the player is the current
     * dealer.
     * @param isPlayerTurn Sets whether or not it is currently
     * this players turn.
     */
    public Player(int playerNum, boolean isDealer, boolean isPlayerTurn) {
        super("super", "super", "super", 0, new ImageIcon());
        this.playerNum = playerNum;
        this.isDealer = isDealer;
        this.isPlayerTurn = isPlayerTurn;
    }

    /**
     * Blackjack constructor.
     * 
     * @author Matthew Polter
     * @param playerNum The number you would like to set for the Player
     */
    public Player(int playerNum) {
        super(0, 0, "sample", new ImageIcon());
    }

    /**
     * Gets the number of the current player.
     * 
     * @author Matthew Polter
     * @return Get the player number
     */
    public int getPlayerNumber() {
        return this.playerNum;
    }

    /**
     * Returns whether or not the current player is the dealer.
     * 
     * @author Matthew Polter
     * @return True if the pplayer is the dealer, false otherwise.
     */
    public boolean isDealer() {
        return this.isDealer;
    }

    /**
     * Checks if it is the current players turn.
     * 
     * @author Matthew Polter
     * @return True if it is the players turn, false otherwise.
     */
    public boolean isPlayerTurn() {
        return this.isPlayerTurn;
    }

    /**
     * Sets the players turn to true or false.
     * 
     * @author Matthew Polter
     * @param isPlayerTurn Sets isPlayerTurn to whatever boolean
     * value is given in this parameter.
     */
    public void setPlayerTurn(boolean isPlayerTurn) {
        this.isPlayerTurn = isPlayerTurn;
    }

    /**
     * Gives each player 5 cards to start the round.
     * 
     * @author Matthew Polter
     * @param arr Give an array of cards to this player to start the
     * game.
     */
    public void setPreroundCards(Card[] arr) {
        for(int i = 0; i < arr.length; i++) {
            this.playerCards.add(arr[i]);
        }
    }

    /**
     * Gives a card to the current player.
     * 
     * @author Matthew Polter
     * @param card Gives a card object to the player.
     */
    public void giveCard(Card card) {
        this.playerCards.add(card);
    }

    /**
     * Gets a specific card from the Players deck.
     * 
     * @author Matthew Polter
     * @param index The index of the card you want to return.
     * @return The card at the index given as a parameter.
     */
    public Card getCard(int index) {
        return this.playerCards.get(index);
    }

    /**
     * Gets the ArrayList of Cards the player has.
     * 
     * @author Matthew Polter
     * @return The ArrayList of cards that the player currently has.
     */
    public ArrayList<Card> getCards() {
        return this.playerCards;
    }

    public static void main(String[] args) {

        // Card jackDiamond = new Card("Jack", "Red", "Diamond", 1);
        // Player test = new Player(1, true);
        // test.giveCard(jackDiamond);
        // System.out.println(test.getCards().get(0).getName() + " of " + test.getCards().get(0).getSuit());
    }
}
