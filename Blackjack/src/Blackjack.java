import java.util.*;

public class Blackjack {

    private int playerSum;
    private int dealerSum;
    private int newCard;
    private ArrayList<Integer> playerCards = new ArrayList<Integer>();
    private ArrayList<Integer> dealerCards = new ArrayList<Integer>();

    /*
     * Constructor.
     */
    public Blackjack() {
        this.playerSum = 0;
        this.dealerSum = 0;
    }

    /*
     * Gives the player and the dealer two cards to start the game.
     */
    public void startGame(ArrayList<Integer> playerCards, ArrayList<Integer> dealerCards, Random rand) {

        /*
         * Makes sure there are no cards in either deck before they are
         * given out.
         */
        playerCards.clear();
        dealerCards.clear();

        /*
         * Gives the player two cards to start the game then calculates
         * the sum of those two numbers.
         */
        playerCards.add(rand.nextInt(11) + 1);
        playerCards.add(rand.nextInt(11) + 1);
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

    /*
     * If the player chooses to hit, use this method to draw an additional
     * random card from the deck and add the value to the existing sum.
     */
    public void playerHit(ArrayList<Integer> playerCards, Random rand) {
        newCard = rand.nextInt(11) + 1;
        playerCards.add(newCard);
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
    public static void main(String[] args) {
        int play = 0;
        int hit = 0;
        boolean bust = false;
        boolean dealerBust = false;
        boolean dealerBlackjack = false;
        int endgame = 1;
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        ArrayList<Integer> playerCards = new ArrayList<Integer>();
        ArrayList<Integer> dealerCards = new ArrayList<Integer>();

        Blackjack test = new Blackjack();

        // Prompts the player to start the game.
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
