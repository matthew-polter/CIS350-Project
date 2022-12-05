import javax.swing.ImageIcon;

public class Card {
    
    private String name;
    private String color;
    private String suit;
    private int rank;
    private int number;
    private int value;
    private ImageIcon image;

    /**
     * @author Matthew Polter
     * @param i Name of the Card. Ex. King
     * @param j What color the card is (Black or Red).
     * @param suit  What suit the card is
     * (Diamonds, Hearts, Clubs, Spades).
     * @param string What rank the card is depending on what the
     * current trump card is.
     */
    public Card(String name, String color, String suit, int rank, ImageIcon image) {
        this.name = name;
        this.color = color;
        this.suit = suit;
        this.rank = rank;
        this.image = image;
    }

    /**
     * The card constructor for the Blackjack game.
     * 
     * @author Matthew Polter
     * @param number The number of the card (1 - 52).
     * @param value The value of the card (2 - 11).
     * @param suit The suit of the card
     * (Hearts, Diamonds, Clubs, Spades).
     * @param image The image of the specified card.
     */
    public Card(int number, int value, String suit, ImageIcon image) {
        this.number = number;
        this.value = value;
        this.suit = suit;
        this.image = image;
    }

    /**
     * Get the number of the Blackjack card.
     * 
     * @author Matthew Polter
     * @return The number of the card (1 - 52).
     */
    public int getNumber() {
        return this.number;
    }

    public void setNumber(int num) {
        this.number = num;
    }

    /**
     * Get the value of the Blackjack card.
     * 
     * @author Matthew Polter
     * @return The value of the Blackjack card.
     */
    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Get the image of the blackjack card.
     * 
     * @author Matthew Polter
     * @return The image of the card.
     */
    public ImageIcon getImage() {
        return this.image;
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
