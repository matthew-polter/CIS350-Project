package games;
import java.util.ArrayList;

/**
 * A Class to create a player for the Euchre class.
 * @author Matthew Polter
 *
 */
public class Player extends Card {
    
	/**
	 * The number assigned to the player. This number determines the players role in each round.
	 */
    private int playerNum = 0;
    /**
     * The boolean value telling whether or not this player is currently the dealer. True for yes, False for no.
     */
    private boolean isDealer = false;
    /**
     * The boolean value telling whether or not it is this players turn.
     */
    private boolean isPlayerTurn = false;
    /**
     * The ArrayList of Card objects given to this player.
     */
    private ArrayList<Card> playerCards = new ArrayList<Card>();

    /**
     * Create a new player.
     * 
     * @author Matthew Polter
     * @param playerNum Sets the player number.
     * @param isDealer Sets whether or not the player is the current
     * dealer.
     * @param isPlayerTurn Sets whether or not it is currently
     * this players turn.
     */
    public Player(int playerNum, boolean isDealer, boolean isPlayerTurn) {
        super("super", "super", "super", 0);
        this.playerNum = playerNum;
        this.isDealer = isDealer;
        this.isPlayerTurn = isPlayerTurn;
    }

    /**
     * Gets the current number of this player.
     * 
     * @author Matthew Polter
     * @return Get the player number
     */
    public int getPlayerNumber() {
        return this.playerNum;
    }

    /**
     * Returns a boolean value telling whether or not this player is currently the dealer.
     * 
     * @author Matthew Polter
     * @return True if the player is the dealer, false otherwise.
     */
    public boolean isDealer() {
        return this.isDealer;
    }

    /**
     * Returns a boolean value telling whether or not it is currently this players turn.
     * 
     * @author Matthew Polter
     * @return True if it is the players turn, false otherwise.
     */
    public boolean isPlayerTurn() {
        return this.isPlayerTurn;
    }

    /**
     * Sets the players turn to some boolean value.
     * 
     * @author Matthew Polter
     * @param isPlayerTurn Sets isPlayerTurn to whatever boolean
     * value is given in this parameter.
     */
    public void setPlayerTurn(boolean isPlayerTurn) {
        this.isPlayerTurn = isPlayerTurn;
    }

    /**
     * Gives this player the number of cards in the parameter.
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
     * Give an additional card to a player.
     * 
     * @author Matthew Polter
     * @param card Gives a card object to the player.
     */
    public void giveCard(Card card) {
        this.playerCards.add(card);
    }

    /**
     * Get one of the players cards at a specified index.
     * 
     * @author Matthew Polter
     * @param index The index of the card you want to return.
     * @return The card at the index given as a parameter.
     */
    public Card getCard(int index) {
        return this.playerCards.get(index);
    }

    /**
     * Returns the full array of this players cards.
     * 
     * @author Matthew Polter
     * @return The ArrayList of cards that the player currently has.
     */
    public ArrayList<Card> getCards() {
        return this.playerCards;
    }

//    public static void main(String[] args) {
//
//         Card jackDiamond = new Card("Jack", "Red", "Diamond", 1);
//         Player test = new Player(1, true);
//         test.giveCard(jackDiamond);
//         System.out.println(test.getCards().get(0).getName() + " of " + test.getCards().get(0).getSuit());
//    }
}
