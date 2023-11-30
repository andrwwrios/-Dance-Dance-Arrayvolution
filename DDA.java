// andrew rios
// 11/28/2023

// this class is CSE 121 version of Dance Dance evolution that prompts players
// possible moves and their correct responses and gives the user a score based
// on their response to the action shown to them.
// in the end the players score is printed
import java.util.*;

public class DDA {
    public static final String[] ACTIONS = {"⬆️", "➡️", "⬇️", "⬅️", "⏫"};
    public static final String[] RESPONSES = {"UP", "RIGHT", "DOWN", "LEFT", "JUMP"};
    public static final String STAR = "⭐";
    
    // this is the main method. there is no return value and takes 
    // String[] args as a parameter
    // it is used to call some of the methods in the class and help with organization
    // and play the game
    public static void main(String[] args) {
    	Random random = new Random();
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.println("Welcome to Dance Dance Arrayvolution!");
    	System.out.println();
    	
    	showActions();
    	System.out.println();
    	
    	String[] actions = makeMoves(scanner,random);
    	System.out.println();
    	
    	double score = playGame(scanner,actions);
    	System.out.println();
    	
    	endScreen(score,actions.length);
    }
    
    // this method has no parameters and no return value
    // it prints each action with its accompanying correct response.
    public static void showActions() {
    	System.out.println("These are the possible actions and their correct responses:");
    	for(int index = 0; index<ACTIONS.length; index++) {
    		 System.out.println(ACTIONS[index] + ": " + RESPONSES[index]);
    	}
    }
    
    // this method takes in a scanner and random objects as parameters
    // it prompts the user the number of moves they want to make
    // then creates an array of that size
    // the array is filled in with random actions and is then returned 
    public static String[] makeMoves(Scanner scanner, Random random) {
    	System.out.print("How many moves would you like to play? ");
    	int userMoves = scanner.nextInt();
    	
    	// create array of userMoves size
    	String[] randomActions = new String[userMoves];
    	for(int index = 0; index<randomActions.length; index++) {
    		int action = random.nextInt(ACTIONS.length);
    		randomActions[index] = ACTIONS[action];	
    	}
    	System.out.println();
    	System.out.print("Let's Dance!");
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
      this method takes in two parameters, the players score, 
      and the max score they could've obtained
      this method prints out the score the player received
      and the number of stars correlating with that score 
    */
    public static void endScreen(double playerScore, int maxScore) {
    	System.out.println("Woah that was groovy!");
	    System.out.print("You Scored: "); 
	    double limit = 0.2 * maxScore;
	    for(int star = 0; star<Math.floor(playerScore*(1/limit)); star++) {
	    	System.out.print(STAR);
	    }
	    if(playerScore < limit) {
	    	System.out.print(STAR);
	    }
      System.out.println(" (" + playerScore + "/" + (double)maxScore + ")");
      System.out.println("Thanks for playing!");
    }
}
