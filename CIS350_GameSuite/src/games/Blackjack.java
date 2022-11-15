package games;
import java.util.*;


/**
 * A Class that simulates a real game of Blackjack.
 * You can choose to play multiple games if you want
 * and the cards are all randomly generated.
 *
 * @author Matthew Polter
 *
 */
public class Blackjack {

	/**
	* Keeps a record of the sum of values that the players cards have.
	*/
    private int playerSum;
    /**
	 * Keeps a record of the sum of values that the dealers cards have.
	 */
    private int dealerSum;
    /**
     * Acts as a temp variable for when the player gets a new card.
     */
    private int newCard;
    /**
     * A flag variable for the ace checked method.
     */
    private int flag;
    /**
     * Integer to check if the card is an ace.
     */
    private int ace;
    /**
     * The ArrayList of 1's and 0's to indicate whether a card has been checked for an ace or not.
     */
    private ArrayList<Integer> aceCheckedCards = new ArrayList<Integer>();
    /**
     * The ArrayList of player cards that gets populated throughout the game.
     */
    private ArrayList<Integer> playerCards = new ArrayList<Integer>();
    /**
     * The ArrayList of dealer cards that gets populated throughout the game.
     */
    private ArrayList<Integer> dealerCards = new ArrayList<Integer>();

    /**
     * Constructor. Initializes some of the private variables.
     */
    public Blackjack() {
        this.playerSum = 0;
        this.dealerSum = 0;
        this.flag = 0;
    }

    /**
     * Starts the game by giving the player and the dealer two cards. One of the dealers
     * cards is hidden to prevent the player from knowing exactly what the dealer has.
     *
     * @param playerCards The list of cards you want the player to have.
     *
     * @param dealerCards The list of cards you want the dealer to have.
     * @param rand A random number generator to create the value for the cards given out
     * to start the round.
     */
    public void startGame(ArrayList<Integer> playerCards, ArrayList<Integer> dealerCards, Random rand) {

        /*
         * Makes sure there are no cards in either deck before they are
         * given out.
         */
        aceCheckedCards.clear();
        playerCards.clear();
        dealerCards.clear();

        /*
         * Gives the player two cards to start the game then calculates
         * the sum of those two numbers.
         */
        playerCards.add(rand.nextInt(11) + 1);
        playerCards.add(rand.nextInt(11) + 1);
        aceCheckedCards.add(0);
        aceCheckedCards.add(0);
        this.playerSum = playerCards.get(0) + playerCards.get(1);

        /*
         * Gives the dealer two cards to start then calculates the sum of
         * those two numbers.
         */
        dealerCards.add(rand.nextInt(11) + 1);
        dealerCards.add(rand.nextInt(11) + 1);
        this.dealerSum = dealerCards.get(0) + dealerCards.get(1);

        this.playerCards = playerCards;
        this.dealerCards = dealerCards;

    }

    /**
     * Gives the player an additional card.
     * @author Matthew Polter
     * @param playerCards The ArrayList of player cards.
     * @param rand The Random number generator used to assign the card to the player.
     */
    public void playerHit(ArrayList<Integer> playerCards, Random rand) {
        newCard = rand.nextInt(11) + 1;
        playerCards.add(newCard);
        aceCheckedCards.add(0);
        this.playerCards = playerCards;
        this.playerSum += newCard;
    }

    /**
     * Once the player has chosen to stay, the dealer draws additional
     * cards using this method until the sum is greater than 17.
     * @author Matthew Polter
     * @param dealerCards An Arraylist of the dealers individual cards.
     * @param rand The random number generator used to generate each card.
     */
    public void dealerHit(ArrayList<Integer> dealerCards, Random rand) {
        newCard = rand.nextInt(11) + 1;
        dealerCards.add(newCard);
        this.dealerCards = dealerCards;
        this.dealerSum += newCard;
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
    public void checkAce(Scanner scan) {
        for(int i = 0; i < playerCards.size(); i++) {
            /**
             * If the card hasn't already been checked for an ace, check
             * to see if it is an ace.
             */
            if(this.getPlayerCards().get(i) == 11 && this.getAceChecked().get(i) != 1) {
                System.out.println("Would you like to turn your ace into a 1 or keep it as 11? (1 == 1 / 0 == 11):");
                this.ace = scan.nextInt();
                /**
                 * If the user has chosen to make the ace a 1, set the
                 * value of the card to 1 and re-calculate the sum of the
                 * cards.
                 */
                if(this.ace == 1) {
                    this.getPlayerCards().set(i, 1);
                    this.playerSum = 0;
                    for(int j = 0; j < this.getPlayerCards().size(); j++) {
                        this.playerSum += this.getPlayerCards().get(j);
                    }
                }
                else if(this.ace == 0) {
                    this.flag = 1;
                }
            }
            // Marks the card as checked once it checks if it is an ace.
            this.getAceChecked().set(i, 1);
        }
    }

    /**
     * If the ace has already been checked, return true, return false if it hasn't.
     * 
     * @author Matthew Polter
     * @return Returns true if there is an ace in the deck that has already been checked and no longer needs a user prompt.
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
    public ArrayList<Integer> getPlayerCards() {
        return this.playerCards;
    }

    /**
     * Outputs the dealers current cards.
     * @author Matthew Polter
     * @return The ArrayList of individual dealer cards.
     */
    public ArrayList<Integer> getDealerCards() {
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
     * @author Matthew Polter
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
        this.getDealerCards().set(index, value);
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
        this.getPlayerCards().set(index, value);
    }

    /**
     * Allows the user to change the value of the players sum of cards.
     * @author Matthew Polter
     * @param sum What you would like the players sum of cards to be.
     */
    public void setPlayerSum(int sum) {
        this.playerSum = sum;
    }

    /**
     * Main method for testing code.
     * @param args Arguments that main reads.
     */
    public static void main(String[] args) {
        int play = 0;
        int hit = 0;
        boolean bust = false;
        boolean dealerBust = false;
        boolean dealerBlackjack = false;
        int endgame = 1;
        final Scanner scan = new Scanner(System.in);
        final Random rand = new Random();

        final ArrayList<Integer> playerCards = new ArrayList<Integer>();
        final ArrayList<Integer> dealerCards = new ArrayList<Integer>();

        final Blackjack test = new Blackjack();

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
                test.startGame(playerCards, dealerCards, rand);
                test.checkAce(scan);
                if(test.getDealerSum() == 21) {
                    dealerBlackjack = true;
                    play = 0;
                }
                else {
                    System.out.println(test.getPlayerCards() + " " + test.getPlayerSum());
                    System.out.println(test.getDealerCards().get(0) + " " + "+" + " " + "?");
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
                    test.playerHit(playerCards, rand);
                    if(!test.aceChecked()) {
                        test.checkAce(scan);
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
                        test.dealerHit(dealerCards, rand);
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
