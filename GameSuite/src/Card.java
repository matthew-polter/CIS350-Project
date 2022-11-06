public class Card {
    
    private String name;
    private String color;
    private String suit;
    private int rank;

    /**
     * @author Matthew Polter
     * @param name Name of the Card. Ex. King
     * @param color What color the card is (Black or Red).
     * @param suit  What suit the card is
     * (Diamonds, Hearts, Clubs, Spades).
     * @param rank What rank the card is depending on what the
     * current trump card is.
     */
    public Card(String name, String color, String suit, int rank) {
        this.name = name;
        this.color = color;
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Allows the user the get the name of a card.
     * 
     * @author Matthew Polter
     * @return The name of the selected card
     */
    public String getName() {
        return this.name;
    }

    /**
     * Allows the user the get the color of a card.
     * 
     * @author Matthew Polter
     * @return The color of the selected card
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Allows the user the get the suit of a card.
     * 
     * @author Matthew Polter
     * @return The suit of the selected card
     */
    public String getSuit() {
        return this.suit;
    }

    /**
     * Allows the user the get the rank of a card.
     * 
     * @author Matthew Polter
     * @return The rank of the selected card
     */
    public int getRank() {
        return this.rank;
    }

    /**
     * Allows the user the set the name of a card.
     * 
     * @param name The name you want to set for the selected card.
     * @author Matthew Polter
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Allows the user the set the color of a card.
     * 
     * @param color The color you want to set for the selected card.
     * @author Matthew Polter
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Allows the user the set the suit of a card.
     * 
     * @param suit The suit you want to set for the selected card.
     * @author Matthew Polter
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

    /**
     * Allows the user the set the rank of a card.
     * 
     * @param rank The rank you want to set for the selected card.
     * @author Matthew Polter
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * Get the entire card object.
     * 
     * @author Matthew Polter
     * @return The current card object.
     */
    public Card getCard() {
        return this;
    }
}
