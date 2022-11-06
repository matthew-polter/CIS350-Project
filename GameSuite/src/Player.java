import java.util.ArrayList;

public class Player extends Card {
    
    private int playerNum = 0;
    private boolean isDealer = false;
    private boolean isPlayerTurn = false;
    private ArrayList<Card> playerCards = new ArrayList<Card>();

    /**
     * Creaete a new player.
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
     * @author Matthew Polter
     * @return Get the player number
     */
    public int getPlayerNumber() {
        return this.playerNum;
    }

    /**
     * @author Matthew Polter
     * @return True if the pplayer is the dealer, false otherwise.
     */
    public boolean isDealer() {
        return this.isDealer;
    }

    /**
     * @author Matthew Polter
     * @return True if it is the players turn, false otherwise.
     */
    public boolean isPlayerTurn() {
        return this.isPlayerTurn;
    }

    /**
     * @author Matthew Polter
     * @param isPlayerTurn Sets isPlayerTurn to whatever boolean
     * value is given in this parameter.
     */
    public void setPlayerTurn(boolean isPlayerTurn) {
        this.isPlayerTurn = isPlayerTurn;
    }

    /**
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
     * @author Matthew Polter
     * @param card Gives a card object to the player.
     */
    public void giveCard(Card card) {
        this.playerCards.add(card);
    }

    /**
     * @author Matthew Polter
     * @param index The index of the card you want to return.
     * @return The card at the index given as a parameter.
     */
    public Card getCard(int index) {
        return this.playerCards.get(index);
    }

    /**
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
