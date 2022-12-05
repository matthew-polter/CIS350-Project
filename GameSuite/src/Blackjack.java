import java.io.File;
import java.util.*;

import javax.swing.*;

public class Blackjack extends Player {

    private int playerSum;
    private int dealerSum;
    private Card newCard;
    private int flag;
    private int ace = 0;
    private int currentDeckSize;
    private int randomIndex;
    private ArrayList<Card> blackjackDeck = new ArrayList<Card>();
    private Random rand = new Random();
    private Scanner scan = new Scanner(System.in);
    // private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Integer> aceCheckedCards = new ArrayList<Integer>();
    private ArrayList<Card> playerCards = new ArrayList<Card>();
    private ArrayList<Card> dealerCards = new ArrayList<Card>();

    /*
     * Constructor.
     */
    public Blackjack() {
        super(0);
        this.playerSum = 0;
        this.dealerSum = 0;
        this.flag = 0;
    }

    /**
     * Initializes all 52 cards of the Blackjack deck.
     * 
     * @author Matthew Polter
     */
    public void generateDeck() {
        
        // Generate Hearts cards
        blackjackDeck.add(new Card(1, 2, "Hearts", new ImageIcon("Images" + File.separator + "2-of-hearts.png")));

        blackjackDeck.add(new Card(2, 3, "Hearts", new ImageIcon("Images" + File.separator + "3-of-hearts.png")));

        blackjackDeck.add(new Card(3, 4, "Hearts", new ImageIcon("Images" + File.separator + "4-of-hearts.png")));

        blackjackDeck.add(new Card(4, 5, "Hearts", new ImageIcon("Images" + File.separator + "5-of-hearts.png")));

        blackjackDeck.add(new Card(5, 6, "Hearts", new ImageIcon("Images" + File.separator + "6-of-hearts.png")));

        blackjackDeck.add(new Card(6, 7, "Hearts", new ImageIcon("Images" + File.separator + "7-of-hearts.png")));

        blackjackDeck.add(new Card(7, 8, "Hearts", new ImageIcon("Images" + File.separator + "8-of-hearts.png")));

        blackjackDeck.add(new Card(8, 9, "Hearts", new ImageIcon("Images" + File.separator + "9-of-hearts.png")));

        blackjackDeck.add(new Card(9, 10, "Hearts", new ImageIcon("Images" + File.separator + "10-of-hearts.png")));

        blackjackDeck.add(new Card(10, 10, "Hearts", new ImageIcon("Images" + File.separator + "jack-of-hearts.png")));

        blackjackDeck.add(new Card(11, 10, "Hearts", new ImageIcon("Images" + File.separator + "queen-of-hearts.png")));

        blackjackDeck.add(new Card(12, 10, "Hearts", new ImageIcon("Images" + File.separator + "king-of-hearts.png")));

        blackjackDeck.add(new Card(13, 11, "Hearts", new ImageIcon("Images" + File.separator + "ace-of-hearts.png")));

        // Generate Diamonds cards
        blackjackDeck.add(new Card(14, 2, "diamonds", new ImageIcon("Images" + File.separator + "2-of-diamonds.png")));

        blackjackDeck.add(new Card(15, 3, "diamonds", new ImageIcon("Images" + File.separator + "3-of-diamonds.png")));

        blackjackDeck.add(new Card(16, 4, "diamonds", new ImageIcon("Images" + File.separator + "4-of-diamonds.png")));

        blackjackDeck.add(new Card(17, 5, "diamonds", new ImageIcon("Images" + File.separator + "5-of-diamonds.png")));

        blackjackDeck.add(new Card(18, 6, "diamonds", new ImageIcon("Images" + File.separator + "6-of-diamonds.png")));

        blackjackDeck.add(new Card(19, 7, "diamonds", new ImageIcon("Images" + File.separator + "7-of-diamonds.png")));

        blackjackDeck.add(new Card(20, 8, "diamonds", new ImageIcon("Images" + File.separator + "8-of-diamonds.png")));

        blackjackDeck.add(new Card(21, 9, "diamonds", new ImageIcon("Images" + File.separator + "9-of-diamonds.png")));

        blackjackDeck.add(new Card(22, 10, "diamonds", new ImageIcon("Images" + File.separator + "10-of-diamonds.png")));

        blackjackDeck.add(new Card(23, 10, "diamonds", new ImageIcon("Images" + File.separator + "jack-of-diamonds.png")));

        blackjackDeck.add(new Card(24, 10, "diamonds", new ImageIcon("Images" + File.separator + "queen-of-diamonds.png")));

        blackjackDeck.add(new Card(25, 10, "diamonds", new ImageIcon("Images" + File.separator + "king-of-diamonds.png")));

        blackjackDeck.add(new Card(26, 11, "diamonds", new ImageIcon("Images" + File.separator + "ace-of-diamonds.png")));

        // Generate Clubs cards
        blackjackDeck.add(new Card(27, 2, "clubs", new ImageIcon("Images" + File.separator + "2-of-clubs.png")));

        blackjackDeck.add(new Card(28, 3, "clubs", new ImageIcon("Images" + File.separator + "3-of-clubs.png")));

        blackjackDeck.add(new Card(29, 4, "clubs", new ImageIcon("Images" + File.separator + "4-of-clubs.png")));

        blackjackDeck.add(new Card(30, 5, "clubs", new ImageIcon("Images" + File.separator + "5-of-clubs.png")));

        blackjackDeck.add(new Card(31, 6, "clubs", new ImageIcon("Images" + File.separator + "6-of-clubs.png")));

        blackjackDeck.add(new Card(32, 7, "clubs", new ImageIcon("Images" + File.separator + "7-of-clubs.png")));

        blackjackDeck.add(new Card(33, 8, "clubs", new ImageIcon("Images" + File.separator + "8-of-clubs.png")));

        blackjackDeck.add(new Card(34, 9, "clubs", new ImageIcon("Images" + File.separator + "9-of-clubs.png")));

        blackjackDeck.add(new Card(35, 10, "clubs", new ImageIcon("Images" + File.separator + "10-of-clubs.png")));

        blackjackDeck.add(new Card(36, 10, "clubs", new ImageIcon("Images" + File.separator + "jack-of-clubs.png")));

        blackjackDeck.add(new Card(37, 10, "clubs", new ImageIcon("Images" + File.separator + "queen-of-clubs.png")));

        blackjackDeck.add(new Card(38, 10, "clubs", new ImageIcon("Images" + File.separator + "king-of-clubs.png")));

        blackjackDeck.add(new Card(39, 11, "clubs", new ImageIcon("Images" + File.separator + "ace-of-clubs.png")));

        // Generate Spades cards
        blackjackDeck.add(new Card(40, 2, "spades", new ImageIcon("Images" + File.separator + "2-of-spades.png")));

        blackjackDeck.add(new Card(41, 3, "spades", new ImageIcon("Images" + File.separator + "3-of-spades.png")));

        blackjackDeck.add(new Card(42, 4, "spades", new ImageIcon("Images" + File.separator + "4-of-spades.png")));

        blackjackDeck.add(new Card(43, 5,"spades", new ImageIcon("Images" + File.separator + "5-of-spades.png")));

        blackjackDeck.add(new Card(44, 6, "spades", new ImageIcon("Images" + File.separator + "6-of-spades.png")));

        blackjackDeck.add(new Card(45, 7, "spades", new ImageIcon("Images" + File.separator + "7-of-spades.png")));

        blackjackDeck.add(new Card(46, 8, "spades", new ImageIcon("Images" + File.separator + "8-of-spades.png")));

        blackjackDeck.add(new Card(47, 9, "spades", new ImageIcon("Images" + File.separator + "9-of-spades.png")));

        blackjackDeck.add(new Card(48, 10, "spades", new ImageIcon("Images" + File.separator + "10-of-spades.png")));

        blackjackDeck.add(new Card(49, 10, "spades", new ImageIcon("Images" + File.separator + "jack-of-spades.png")));

        blackjackDeck.add(new Card(50, 10, "spades", new ImageIcon("Images" + File.separator + "queen-of-spades.png")));

        blackjackDeck.add(new Card(51, 10, "spades", new ImageIcon("Images" + File.separator + "king-of-spades.png")));

        blackjackDeck.add(new Card(1, 11, "spades", new ImageIcon("Images" + File.separator + "motorhead.png")));
    }

    public ArrayList<Card> getBlackjackDeck() {
        return this.blackjackDeck;
    }

    /**
     * Starts the round by generating the deck of 52 cards and giving
     * the player and the dealer two cards each.
     * 
     * @author Matthew Polter
     */
    public void startGame() {

        /*
         * Makes sure there are no cards in either deck before they are
         * given out.
         */
        this.aceCheckedCards.clear();
        this.playerCards.clear();
        this.dealerCards.clear();
        this.playerSum = 0;
        this.dealerSum = 0;
        this.generateDeck();

        /*
         * Gives the player two cards to start the game then calculates
         * the sum of those two numbers.
         */
        System.out.println("Current deck size: " + this.getCurrentSizeOfDeck());
        this.playerCards.add(blackjackDeck.get(this.getRandomDeckIndex()));
        this.blackjackDeck.remove(randomIndex);
        System.out.println("Current deck size: " + this.getCurrentSizeOfDeck());
        this.playerCards.add(blackjackDeck.get(this.getRandomDeckIndex()));
        this.blackjackDeck.remove(randomIndex);
        System.out.println("Current deck size: " + this.getCurrentSizeOfDeck());
        aceCheckedCards.add(0);
        aceCheckedCards.add(0);
        this.playerSum = this.playerCards.get(0).getValue() + playerCards.get(1).getValue();

        /*
         * Gives the dealer two cards to start then calculates the sum of
         * those two numbers.
         */
        this.dealerCards.add(blackjackDeck.get(this.getRandomDeckIndex()));
        this.blackjackDeck.remove(randomIndex);
        System.out.println("Current deck size: " + this.getCurrentSizeOfDeck());
        this.dealerCards.add(blackjackDeck.get(this.getRandomDeckIndex()));
        this.blackjackDeck.remove(randomIndex);
        System.out.println("Current deck size: " + this.getCurrentSizeOfDeck());
        aceCheckedCards.add(0);
        aceCheckedCards.add(0);
        this.dealerSum = this.dealerCards.get(0).getValue() + dealerCards.get(1).getValue();
    }

    public int getCurrentSizeOfDeck() {
        currentDeckSize = blackjackDeck.size();
        return currentDeckSize;
    }

    public int getRandomDeckIndex() {
        randomIndex = this.rand.nextInt(this.getCurrentSizeOfDeck());
        return randomIndex;
    }

    /**
     * Gives the player a new Card object from the deck of 52 cards.
     * The value of that card is then added to the sum of player
     * cards.
     * 
     * @author Matthew Polter
     */
    public void playerHit() {
        newCard = blackjackDeck.get(this.getRandomDeckIndex());
        blackjackDeck.remove(randomIndex);
        this.playerCards.add(newCard);
        System.out.println("Card Number drawn: " + this.newCard.getNumber());
        System.out.println("Card Value: " + this.newCard.getValue());
        System.out.println("Card Suit: " + this.newCard.getSuit());
        aceCheckedCards.add(0);
        this.playerSum += newCard.getValue();
    }

    /**
     * Once the player has chosen to stay, the dealer draws additional
     * cards using this method until the sum is greater than 17.
     * @author Matthew Polter
     */
    public void dealerHit() {
        newCard = blackjackDeck.get(this.getRandomDeckIndex());
        blackjackDeck.remove(randomIndex);
        this.dealerCards.add(newCard);
        aceCheckedCards.add(0);
        this.dealerSum += newCard.getValue();
    }

    /**
     * Compares the sum of player cards and the sum of dealer cards.
     * @author Matthew Polter
     * @return An integer value as a usable repsentation of the comparison.
     * between the sum of player cards and the sum of dealer cards. The
     * results are as follows:
     * 
     * 1 - player sum > dealer sum
     * 0 - player sum = dealer sum
     * -1 - player sum < dealer sum
     */
    public int compareCards() {
        if(this.playerSum > this.dealerSum) {
            return 1;
        }
        else if(this.playerSum == this.dealerSum) {
            return 0;
        }
        else {
            return -1;
        }
    }

    /**
     * Checks if the user has an Ace and Asks if they would like its
     * value to be 1 or 11. This only occurs once per ace card.
     * @author Matthew Polter
     * @param ace The integer flag used to determine if the player has
     * an ace or not.
     * @param scan The scanner used to read user input.
     */
    public void checkAce() {
        for(int i = 0; i < playerCards.size(); i++) {
            /**
             * If the card hasn't already been checked for an ace, check
             * to see if it is an ace.
             */
            if(this.getPlayerCards().get(i).getValue() == 11 && this.getAceChecked().get(i) != 1) {
                System.out.println("Would you like to turn your ace into a 1 or keep it as 11? (1 == 1 / 0 == 11):");
                ace = this.scan.nextInt();
                /**
                 * If the user has chosen to make the ace a 1, set the
                 * value of the card to 1 and re-calculate the sum of the
                 * cards.
                 */
                if(ace == 1) {
                    this.getPlayerCards().get(i).setValue(1);
                    this.playerSum = 0;
                    for(int j = 0; j < this.getPlayerCards().size(); j++) {
                        this.playerSum += this.getPlayerCards().get(j).getValue();
                    }
                }
                else if(ace == 0) {
                    this.flag = 1;
                }
            }
            // Marks the card as checked once it checks if it is an ace.
            this.getAceChecked().set(i, 1);
        }
    }

    /**
     * @author Matthew Polter
     * @return Returns true if there is an ace remaining in the deck that has already been checked and no longer needs a user prompt.
     */
    public boolean aceChecked() {
        if(this.flag == 1) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Outputs the players current cards.
     * @author Matthew Polter
     * @return The ArrayList of individual player cards.
     */
    public ArrayList<Card> getPlayerCards() {
        return this.playerCards;
    }

    /**
     * Outputs the dealers current cards.
     * @author Matthew Polter
     * @return The ArrayList of individual dealer cards.
     */
    public ArrayList<Card> getDealerCards() {
        return this.dealerCards;
    }

    /**
     * Outputs the sum of the players current cards.
     * @author Matthew Polter
     * @return The integer sum of the players current cards.
     */
    public int getPlayerSum() {
        return this.playerSum;
    }

    /**
     * Outputs the sum of the dealers current cards.
     * @author Matthew Polter
     * @return The integer sum of the players current cards.
     */
    public int getDealerSum() {
        return this.dealerSum;
    }

    /**
     * Used to check if an ace has already been checked.
     * @return Returns the ArrayList of checked cards.
     */
    public ArrayList<Integer> getAceChecked() {
        return this.aceCheckedCards;
    }

    /**
     * Allows the user to change the value of a specific dealer card.
     * @author Matthew Polter
     * @param index The index value of what card you would like to
     * change.
     * @param value The value of what you want the card to be at the
     * given index.
     */
    public void setDealerCards(int index, int value) {
        this.getDealerCards().get(index).setValue(value);
    }

    /**
     * Allows the user to change the value of the dealers sum of cards.
     * @author Matthew Polter
     * @param dealerSum What you would like the dealers sum of
     * cards to be.
     */
    public void setDealerSum(int dealerSum) {
        this.dealerSum = dealerSum;
    }
    
    /**
     * Allows the user to change the value of a specific player card.
     * @author Matthew Polter
     * @param index The index value of what card you would like to change.
     * @param value The value of what you want the card to be at the given
     * index.
     */
    public void setPlayerCards(int index, int value) {
        this.getPlayerCards().get(index).setValue(value);;
    }

    /**
     * Allows the user to change the value of the players sum of cards.
     * @author Matthew Polter
     * @param sum What you would like the players sum of cards to be.
     */
    public void setPlayerSum(int sum) {
        this.playerSum = sum;
    }

    public static void main(String[] args) {
        int play = 0;
        int hit = 0;
        boolean bust = false;
        boolean dealerBust = false;
        boolean dealerBlackjack = false;
        int endgame = 1;
        Scanner scan = new Scanner(System.in);

        Blackjack test = new Blackjack();

        //Prompts the player to start the game.
        System.out.println("Would you like to play Blackjack? (1 = yes / 0 = no):");
        play = scan.nextInt();

        // Continue to play the game as long as the user hasn't chosen to
        // end it.
        while(endgame == 1) {
            bust = false;
            dealerBust = false;
            play = 1;

            /**
             * If the user has chosen to play, call the startGame method
             * to give the player and the dealer two cards to start.
             */
            if(play == 1) {
                test.startGame();
                test.checkAce();
                if(test.getDealerSum() == 21) {
                    dealerBlackjack = true;
                    play = 0;
                }
                else {
                    System.out.println(test.getPlayerCards().get(0).getValue() + " " + test.getPlayerCards().get(0).getSuit() + " " + test.getPlayerCards().get(1).getValue() + " " + test.getPlayerCards().get(1).getSuit() + " " + test.getPlayerSum());
                    System.out.println(test.getDealerCards().get(0).getValue() + " " + "+" + " " + "?");
                }
            }

            /**
             * As long as the game is being played, uses a loop to allow the
             * user to hit as many times as they would like.
             */
            while(play == 1) {
                System.out.println("Would you like to hit or stay? (1 = hit / 0 = stay):");
                hit = scan.nextInt();
                if(hit == 1) {
                    test.playerHit();
                    if(!test.aceChecked()) {
                        test.checkAce();
                    }
                    /**
                     * Checks if the user has exceded a sum of 21 and breaks
                     * the loop if so, resulting in a loss.
                     */
                    if(test.getPlayerSum() > 21) {
                        System.out.println("You have a " + test.getPlayerSum() + ", Bust!");
                        bust = true;
                        break;
                    }
                    else {
                        System.out.println(test.getPlayerCards() + " " + test.getPlayerSum());
                    }
                }
                /**
                 * When the user has decided to stay, generate the necessary
                 * dealer cards.
                 */
                else if(hit == 0) {

                    // Continue to add dealer cards until the sum > 17.
                    while(test.getDealerSum() < 17) {
                        test.dealerHit();
                    }

                    /**
                     * Checks if the dealer has gone bust. If so, end the
                     * game and give an automatic victory to the user.
                     */
                    if(test.getDealerSum() > 21) {
                        System.out.println(test.getDealerSum());
                        System.out.println("The dealer has gone bust, you Win!");
                        dealerBust = true;
                        break;
                    }
                    else {
                        System.out.println(test.getPlayerCards() + " " + test.getPlayerSum());
                        System.out.println(test.getDealerCards() + " " + test.getDealerSum());
                    }

                    // Breaks the play while loop to prevent infinite looping.
                    play = 0;
                }
            }

            // Gives the game results if the dealer gets a blackjack.
            if(dealerBlackjack) {
                if(test.getPlayerSum() == 21) {
                    System.out.println("You both got a blackjack! Push!");
                }
                else {
                    System.out.println("The dealer got a blackjack! You lose!");
                }
            }

            // Gives the game results if player sum > dealer sum.
            if(test.compareCards() == 1 && !bust  && !dealerBust && !dealerBlackjack) {
                System.out.println("Dealer had " + test.getDealerSum() + ", you had " + test.getPlayerSum() + ", you win!");
            }

            // Gives game results if player sum = dealer sum.
            else if(test.compareCards() == 0 && !bust  && !dealerBust && !dealerBlackjack) {
                System.out.println("Dealer had " + test.getDealerSum() + ", you had " + test.getPlayerSum() + ", push!");
            }

            // Give game results if player sum < dealer sum.
            else if(test.compareCards() == -1 && !bust  && !dealerBust && !dealerBlackjack) {
                System.out.println("Dealer had " + test.getDealerSum() + ", you had " + test.getPlayerSum() + ", you lose!");
            }

            /**
             * Asks the user if they want to play again, if they say yes,
             * the game clears and starts over, if they say no, the program
             * ends completely.
             */
            System.out.println("Would you like to play again? (1 = yes / 0 = no):");
            endgame = scan.nextInt();
        }
        scan.close();
    }
}
