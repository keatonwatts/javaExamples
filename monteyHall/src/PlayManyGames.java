import java.util.Scanner;

public class PlayManyGames {
	public static void main(String[] args) {
		//create variables to capture user input, whether to switch or not and count wins
		String captureAnswer;
		Boolean toSwitch = false;
		double countWins = 0;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Let's test the Montey Hall Paradox.");
		System.out.println("If you want to always switch the door, type \"Switch\", otherwise type \"Never\".");
		captureAnswer = keyboard.nextLine();
		
		//if user says switch, then pass boolean to method toSwitchOrNotToSwitch in the Game class
		
		if (captureAnswer.equals("Switch")) {
			toSwitch = true;
		} else if (captureAnswer.equals("Never")) {
			toSwitch = false;
		}
		
		// start for loop to run game 10,000 times and count number of wins
		for (int i = 1; i <= 10000; i++) {
			Game theGame = new Game();
			theGame.setUpGame();
			theGame.contestantChooseDoor();
			theGame.monteyChooseDoor();
			theGame.toSwitchOrNotToSwitch(toSwitch);

			//if didPlayerWin method returns a true, then count each win
			if (theGame.didPlayerWin() == true) {
				countWins++;
			}
		}
		
		//print results!
		System.out.println("You have won " + countWins + " or " + ((countWins/10000)*100) + "% of the games.");
	}
}
