import java.util.*;

public class Sudoku {

    private String[][] levelStart = new String[9][9];
    private String[][] masterKey = new String[9][9];

    /**
     * Constuctor
     * 
     * Populates the levelStart array.
     * @author Matthew Polter
     */
    public Sudoku() {
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                levelStart[i][j] = "x";
            }
        }
    }

    /**
     * Initializes the starting level
     * @author Matthew Polter
     */
    public void startGame() {
         // Row 1
         levelStart[0][0] = "9";
         levelStart[0][1] = "1";
         levelStart[0][2] = "3";
         levelStart[0][5] = "8";
         levelStart[0][6] = "2";
         levelStart[0][7] = "7";
 
         // Row 2
         levelStart[1][0] = "6";
         levelStart[1][2] = "7";
         levelStart[1][5] = "2";
         levelStart[1][6] = "1";
         levelStart[1][8] = "9";
 
         // Row 3
         levelStart[2][0] = "2";
         levelStart[2][2] = "4";
         levelStart[2][5] = "7";
         levelStart[2][6] = "8";
         levelStart[2][7] = "3";
         levelStart[2][8] = "6";
 
         // Row 4
         levelStart[3][0] = "3";
         levelStart[3][1] = "4";
         levelStart[3][4] = "2";
         levelStart[3][5] = "6";
         levelStart[3][7] = "1";
         levelStart[3][8] = "8";
 
         // Row 5
         levelStart[4][0] = "8";
         levelStart[4][2] = "1";
         levelStart[4][4] = "7";
         levelStart[4][6] = "5";
 
         // Row 6
         levelStart[5][2] = "6";
 
         // Row 7
         levelStart[6][1] = "7";
         levelStart[6][2] = "9";
         levelStart[6][3] = "6";
         levelStart[6][4] = "8";
         levelStart[6][6] = "3";
 
         // Row 8
         levelStart[7][4] = "3";
         levelStart[7][5] = "4";
 
         // Row 9
         levelStart[8][0] = "5";
         levelStart[8][2] = "8";
         levelStart[8][5] = "9";
    }

    /**
     * Prints the current user Sudoku puzzle to the terminal.
     * @author Matthew Polter
     */
    public void printCurrentState() {
        System.out.println(" ------------------- ");

        // Print Row 1
        System.out.println("| " + levelStart[0][0] + " " + levelStart[0][1] + " " + levelStart[0][2] + " " + levelStart[0][3] + " " + levelStart[0][4] + " " + levelStart[0][5] + " " + levelStart[0][6] + " " + levelStart[0][7] + " " + levelStart[0][8] + " |");

        // Print Row 2
        System.out.println("| " + levelStart[1][0] + " " + levelStart[1][1] + " " + levelStart[1][2] + " " + levelStart[1][3] + " " + levelStart[1][4] + " " + levelStart[1][5] + " " + levelStart[1][6] + " " + levelStart[1][7] + " " + levelStart[1][8] + " |");

        // Print Row 3
        System.out.println("| " + levelStart[2][0] + " " + levelStart[2][1] + " " + levelStart[2][2] + " " + levelStart[2][3] + " " + levelStart[2][4] + " " + levelStart[2][5] + " " + levelStart[2][6] + " " + levelStart[2][7] + " " + levelStart[2][8] + " |");

        // Print Row 4
        System.out.println("| " + levelStart[3][0] + " " + levelStart[3][1] + " " + levelStart[3][2] + " " + levelStart[3][3] + " " + levelStart[3][4] + " " + levelStart[3][5] + " " + levelStart[3][6] + " " + levelStart[3][7] + " " + levelStart[3][8] + " |");

        // Print Row 5
        System.out.println("| " + levelStart[4][0] + " " + levelStart[4][1] + " " + levelStart[4][2] + " " + levelStart[4][3] + " " + levelStart[4][4] + " " + levelStart[4][5] + " " + levelStart[4][6] + " " + levelStart[4][7] + " " + levelStart[4][8] + " |");

        // Print Row 6
        System.out.println("| " + levelStart[5][0] + " " + levelStart[5][1] + " " + levelStart[5][2] + " " + levelStart[5][3] + " " + levelStart[5][4] + " " + levelStart[5][5] + " " + levelStart[5][6] + " " + levelStart[5][7] + " " + levelStart[5][8] + " |");

        // Print Row 7
        System.out.println("| " + levelStart[6][0] + " " + levelStart[6][1] + " " + levelStart[6][2] + " " + levelStart[6][3] + " " + levelStart[6][4] + " " + levelStart[6][5] + " " + levelStart[6][6] + " " + levelStart[6][7] + " " + levelStart[6][8] + " |");

        // Print Row 8
        System.out.println("| " + levelStart[7][0] + " " + levelStart[7][1] + " " + levelStart[7][2] + " " + levelStart[7][3] + " " + levelStart[7][4] + " " + levelStart[7][5] + " " + levelStart[7][6] + " " + levelStart[7][7] + " " + levelStart[7][8] + " |");

        // Print Row 9
        System.out.println("| " + levelStart[8][0] + " " + levelStart[8][1] + " " + levelStart[8][2] + " " + levelStart[8][3] + " " + levelStart[8][4] + " " + levelStart[8][5] + " " + levelStart[8][6] + " " + levelStart[8][7] + " " + levelStart[8][8] + " |");

        System.out.println(" ------------------- ");
    }

    /**
     * The multi-dimensional String array of user values in the
     * Sudoku puzzle.
     * 
     * @author Matthew Polter
     * @return The String array of values in the user sudoku
     * puzzle.
     */
    public String[][] getLevelStart() {
        return this.levelStart;
    }

    /**
     * Method used to fill in the empty spaces of the sudoku puzzle.
     * 
     * @author Matthew Polter
     * @param row The row you want to set.
     * @param column The column you want to set.
     * @param value The value you want to set at the selected index.
     */
    public void setIndex(int row, int column, String value) {
        levelStart[row-1][column-1] = value;
    }

    /**
     * A boolean method to break the while loop of the Sudoku game.
     * 
     * @author Matthew Polter
     * @param input If the input is the number 0, this indicates
     * that the user is done playing and wants their puzzle to be
     * checked for correctness.
     * @return True if the input is 0, and False otherwise.
     */
    public boolean gameComplete(int input) {
        if(input == 0) {
            System.out.println("Game complete");
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * For when the player wants to enter an entire row at once.
     * 
     * @author Matthew Polter
     * @param row The row you want to set.
     * @param arr The String array of numbers you want the row to be.
     */
    public void setRow(int row, String[] arr) {
        for(int i = 0; i < 9; i++) {
            this.levelStart[row-1][i] = arr[i];
        }
    }

    /**
     * Initializes the master key only when needed.
     * 
     * @author Matthew Polter
     */
    public void master() {
        // Master Key Row 1
        masterKey[0][0] = "9";
        masterKey[0][1] = "1";
        masterKey[0][2] = "3";
        masterKey[0][3] = "5";
        masterKey[0][4] = "6";
        masterKey[0][5] = "8";
        masterKey[0][6] = "2";
        masterKey[0][7] = "7";
        masterKey[0][8] = "4";

        // Master Key Row 2
        masterKey[1][0] = "6";
        masterKey[1][1] = "8";
        masterKey[1][2] = "7";
        masterKey[1][3] = "3";
        masterKey[1][4] = "4";
        masterKey[1][5] = "2";
        masterKey[1][6] = "1";
        masterKey[1][7] = "5";
        masterKey[1][8] = "9";
        
        // Master Key Row 3
        masterKey[2][0] = "2";
        masterKey[2][1] = "5";
        masterKey[2][2] = "4";
        masterKey[2][3] = "1";
        masterKey[2][4] = "9";
        masterKey[2][5] = "7";
        masterKey[2][6] = "8";
        masterKey[2][7] = "3";
        masterKey[2][8] = "6";

        // Master Key Row 4
        masterKey[3][0] = "3";
        masterKey[3][1] = "4";
        masterKey[3][2] = "5";
        masterKey[3][3] = "9";
        masterKey[3][4] = "2";
        masterKey[3][5] = "6";
        masterKey[3][6] = "7";
        masterKey[3][7] = "1";
        masterKey[3][8] = "8";

        // Master Key Row 5
        masterKey[4][0] = "8";
        masterKey[4][1] = "9";
        masterKey[4][2] = "1";
        masterKey[4][3] = "4";
        masterKey[4][4] = "7";
        masterKey[4][5] = "3";
        masterKey[4][6] = "5";
        masterKey[4][7] = "6";
        masterKey[4][8] = "2";

        // Master Key Row 6
        masterKey[5][0] = "7";
        masterKey[5][1] = "2";
        masterKey[5][2] = "6";
        masterKey[5][3] = "8";
        masterKey[5][4] = "5";
        masterKey[5][5] = "1";
        masterKey[5][6] = "4";
        masterKey[5][7] = "9";
        masterKey[5][8] = "3";

        // Master Key Row 7
        masterKey[6][0] = "4";
        masterKey[6][1] = "7";
        masterKey[6][2] = "9";
        masterKey[6][3] = "6";
        masterKey[6][4] = "8";
        masterKey[6][5] = "5";
        masterKey[6][6] = "3";
        masterKey[6][7] = "2";
        masterKey[6][8] = "1";

        // Master Key Row 8
        masterKey[7][0] = "1";
        masterKey[7][1] = "6";
        masterKey[7][2] = "2";
        masterKey[7][3] = "7";
        masterKey[7][4] = "3";
        masterKey[7][5] = "4";
        masterKey[7][6] = "9";
        masterKey[7][7] = "8";
        masterKey[7][8] = "5";

        // Master Key Row 9
        masterKey[8][0] = "5";
        masterKey[8][1] = "3";
        masterKey[8][2] = "8";
        masterKey[8][3] = "2";
        masterKey[8][4] = "1";
        masterKey[8][5] = "9";
        masterKey[8][6] = "6";
        masterKey[8][7] = "4";
        masterKey[8][8] = "7";
    }

    /**
     * Allows access to the String array master key.
     * 
     * @author Matthew Polter
     * @return Returns the String array master key.
     */
    public String[][] getMasterKey() {
        return this.masterKey;
    }

    /**
     * Method used for testing. Automatically sets the user puzzle
     * equal to the master key so they don't have to manually do it
     * every time.
     * 
     * @author Matthew Polter
     */
    public void setEqual() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                this.getLevelStart()[i][j] = this.getMasterKey()[i][j];
            }
        }
    }

    /**
     * Checks if there are any mistakes in the users puzzle by
     * comparing each index of the master key to the users puzzle.
     * 
     * If there are any mistakes, they are marked with an "x"
     * (testing purposes only) and prints out the puzzle so you can
     * see where you made mistakes along with the message "You lose!"
     * to make you feel bad about yourself.
     * 
     * @author Matthew Polter
     */
    public void checkCorrect() {

        int mistakes = 0;

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(this.getLevelStart()[i][j] != this.getMasterKey()[i][j]) {
                    mistakes++;
                    this.getLevelStart()[i][j] = "x";
                }
            }
        }
        if(mistakes == 0) {
            System.out.println("You solved the puzzle!");
        }
        else{
            this.printCurrentState();
            System.out.println("You lose!");
        }
    }
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        Sudoku test = new Sudoku();
        int inputRow = 0;
        int inputColumn = 0;
        String inputValue = "";
        boolean complete = false;

        test.startGame();      
        test.printCurrentState();

        // Allows the user to fill out the sudoku puzzle; not necessary for testing
        while(!complete) {
            System.out.println("Enter a row number or enter 0 to complete the game: ");

            inputRow = scan.nextInt();

            // if the user chooses to enter a 0 for the row, complete the game
            if(inputRow == 0) {
                complete = test.gameComplete(inputRow);
            }
            // if the user didnt choose to complete the game, get the column and value
            else {
                System.out.println("Enter a column number: ");
                inputColumn = scan.nextInt();

                System.out.println("Enter a value for the index: ");
                inputValue = scan.next();

                test.setIndex(inputRow, inputColumn, inputValue);
                test.printCurrentState();
            }
        }
        test.master();
        test.setEqual();
        test.printCurrentState();
        test.checkCorrect();
        
        scan.close();
    }
}
