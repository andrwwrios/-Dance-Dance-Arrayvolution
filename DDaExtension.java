// andrew rios
// 11/28/2023

// This is my extension of the DDA class. It does all the same thing as the other class,
// but with an added array to store the scores of each round, the user can now see their 
// score for each round, and their highest score.
import java.util.*;

public class DDA {

    public static final String[] ACTIONS = {"⬆️", "➡️", "⬇️", "⬅️", "⏫"};
    public static final String[] RESPONSES = {"UP", "RIGHT", "DOWN", "LEFT", "JUMP"};
    public static final String STAR = "⭐";
    
    // this is the main method. there is no return value and takes 
    // String[] args as a parameter
    // it is used to call some of the methods in the class and help with organization
    public static void main(String[] args) {
    	Random random = new Random();
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Welcome to Dance Dance Arrayvolution!");
    	System.out.println();
    	
    	showActions();
    	System.out.println();
    	
    	int rounds = rounds(scanner);
    	double[] scores = new double[rounds]; // additional array
    	int moves = moves(scanner);
    	System.out.println();
    	System.out.println("Playing " + rounds + " rounds");
    	System.out.println("With " + moves + " moves...");
    	System.out.println();
    	
    	double highScore = 0;
    	for(int round = 1; round<=rounds; round++) {
    		System.out.println("Round #" + round);
    		
    		String[] actions = makeMoves(random,moves);
    		double score = playGame(scanner,actions);
    		highScore = highScore(score,highScore);
    		
    		scores[round-1] = score;
    		System.out.println();
    	}
    	endScreen(scores, moves, highScore);
    }
    
    // this method has no parameters and no return value
    // it prints each action with its accompanying correct response.
    public static void showActions() {
    	System.out.println("These are the possible actions and their correct responses:");
    	for(int index = 0; index<ACTIONS.length; index++) {
    		 System.out.println(ACTIONS[index] + ": " + RESPONSES[index]);
    	}
    }
    
    // this method takes in a random objects, and the number
    // of user moves as parameters. It then creates an array of that size
    // and fills it in with random actions
    // the array is then returned 
    public static String[] makeMoves(Random random, int userMoves) {
    	
    	String[] randomActions = new String[userMoves];
    	for(int index = 0; index<randomActions.length; index++) {
    		int action = random.nextInt(ACTIONS.length);
    		randomActions[index] = ACTIONS[action];	
    	}
    	System.out.println();
    	System.out.println("Let's Dance!");
    	return randomActions;
    }
    
    /* this method has two parameters: Scanner, and an array of moves
       it prints out each move in the array one at a time and waits for players response
       before printing the next move.
       it returns the score of the player as a double.
       if the users input matches the correct response (ignoring case sensitivity)
       they get 1 point
       if not, if the users input starts with the correct response but contains more than what
       the answer is (ex. Duck, vs duckling) the user gets 0.5 points
     */
    public static double playGame(Scanner scanner, String[] moves) {
    	double score = 0;
    	for(int move = 1; move<=moves.length; move++) {
    		System.out.print("(" + move + ") " + moves[move-1] + ": ");
    		String input = scanner.next();
    		
    		int indexOfInput = indexOfResponses(input);
    		int indexOfMoves = indexOfMove(moves, move-1);
    		
    		if(indexOfInput == indexOfMoves) {
    			if(input.equalsIgnoreCase(RESPONSES[indexOfInput])) {
    				score += 1;
    			} else {
    				score += 0.5;
    			}
    		}	
    	}	
    	return score;
    }
    
    // This method is called in the playGame method and is used to find the index
    // of the users input if it matches/or contains any of the elements in RESPONES
    // Parameters: String input from a user
    // Returns the index of the user input as a int
    public static int indexOfResponses(String input) {
    	int responsesIndex = 0;
        while (responsesIndex < RESPONSES.length) {
            if (input.equalsIgnoreCase(RESPONSES[responsesIndex]) || 
                (input.toLowerCase().contains(RESPONSES[responsesIndex].toLowerCase()) &&
                		input.length()>RESPONSES[responsesIndex].length())) {
                return responsesIndex; // Return the index if a match is found
            }
            responsesIndex++;
        }
        return -1; // Return -1 if no match is found
    }
    
    /* this method takes in two parameters: 
     1. the array of random moves made from the makeMoves method
     2. an integer named count that helps us iterate through the
     moves array
     This method is called in the playGame method for loop
     and iterates through the indexes of moves until a match is found
     to the same string from ACTIONS.
     It then returns this index as an int.
     */
    public static int indexOfMove(String[] moves, int count) {
    	int index = 0;
    	int actionsIndex = 0;
    	while(!moves[count].equals(ACTIONS[actionsIndex])){
    		actionsIndex++;
    	}
    	index = actionsIndex;	
    	return index;
    } 
    
    /*
      this method takes in 3 parameters, an array of the users scores,
      the max score they could've obtained, and their highest score
      it prints our their score for each round the corresponding amount of stars
      then prints out their high score
    */
    public static void endScreen(double[] scores, int maxScore, double highScore) {
    	System.out.println("Woah that was groovy!");
    	for(int round = 0; round<scores.length; round++) {
    		System.out.print("Your score for round " + (round+1) + " was: "); 
    		double limit = 0.2 * maxScore;
	    	for(int star = 0; star<Math.floor(scores[round]*(1/limit)); star++) {
	    		System.out.print(STAR);
	    	}
	    	if(scores[round] < limit) {
	    	System.out.print(STAR);
	    	}
    		System.out.println(" (" + scores[round] + "/" + (double)maxScore + ")");	
    	}
    	System.out.println();
    	System.out.println("Your high score was: " + highScore + "!");
    }
    
    // this method takes in a scanner as a parameter
    // and returns the rounds the user wants to play as an int
    public static int rounds(Scanner scanner) {
    	System.out.print("Enter amount of rounds you wish to play: ");
    	int rounds = scanner.nextInt();
    	return rounds;
    }
    
    // this method takes in a scanner as a parameter
    // and returns the amount of moves the user wants to play
    // as an int
    public static int moves(Scanner scanner) {
    	System.out.print("How many moves would you like to play? ");
    	int moves = scanner.nextInt();
    	return moves;
    }
    
    // this method takes in the score, and high score as parameters
    // and returns the high score as a double
    public static double highScore(double newScore, double highScore) {
    	if(newScore>highScore) {
    		highScore = newScore;
    		System.out.println("New High Score! " + highScore);
    	} else {
    		System.out.println("New Score: " + newScore);
    	}
    	return highScore;
    }
}
