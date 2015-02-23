/*
Game.java
Keaton Watts (kcwatts@andrew.cmu.edu)
Course: 95-712: Object-Oriented Programming in Java
Assignment: Homework 2-2 - Game Class
Description: Set up all the steps of the Montey Hall Game
*/


//this first part was provided in class during the lecture
import java.util.*;
public class Game {
    Door door1, door2, door3;
    static Random r = new Random();
    void setUpGame() {
    	//initialize doors
        door1 = new Door();door2 = new Door();door3 = new Door();
        
        //grand prize is randomly placed in a door by using a random and switch
        int grandPrizeDoor = r.nextInt(3);
        switch(grandPrizeDoor) {
        case 0: door1.hasGrandPrize = true; break;
        case 1: door2.hasGrandPrize = true; break;
        case 2: door3.hasGrandPrize = true; break;
        }
    }
        //contestant randomly chooses a door
    void contestantChooseDoor() {
        int contestantDoor = r.nextInt(3);
        switch(contestantDoor) {
        case 0: door1.chosenByContestant = true; break;
        case 1: door2.chosenByContestant = true; break;
        case 2: door3.chosenByContestant = true; break;
        }
    }
    
	//Montey chooses door does NOT have a prize and that the contestant did NOT choose
    
	void monteyChooseDoor() {
		if (door1.hasGrandPrize == true) { //if door 1 has grand prize
			if (door1.chosenByContestant == true) { //and door 1 was chosen by contestant
				int doorNotChosen = r.nextInt(2);
				switch (doorNotChosen) { //then choose either door 2 or 3 to open by random
				case 0:
					door2.open = true;
				case 1:
					door3.open = true;
				}
			}
			if (door2.chosenByContestant == true) { //if d1 has grand prize and d2 was chosen, open d3
				door3.open = true;
			}
			if (door3.chosenByContestant == true) { //if d1 has grand prize and d3 was chosen, open d2
				door2.open = true;
			}
			
		} 		

		if (door2.hasGrandPrize == true) { //if door 2 has grand prize
			if (door2.chosenByContestant == true) { //and door 2 was chosen by constestant
				int doorNotChosen = r.nextInt(2);
				switch (doorNotChosen) { //then choose either door 1 or 3 to open by random
				case 0:
					door1.open = true;
				case 1:
					door3.open = true;
				}
			}
			if (door1.chosenByContestant == true) { //if d2 has grand prize and d1 was chosen, open d3
				door3.open = true;
			}
			if (door3.chosenByContestant == true) { //id d2 has grand prize and d3 was chosen, open d1
				door1.open = true;
			}
		}

		if (door3.hasGrandPrize == true) { //if door 3 has grand prize
			if (door3.chosenByContestant == true) { //and door 3 was chosen by contestant
				int doorNotChosen = r.nextInt(2);
				switch (doorNotChosen) { //then choose either door 1 or 2 to open by random
				case 0:
					door1.open = true;
				case 1:
					door2.open = true;
				}
			}
			if (door1.chosenByContestant == true) { //if d3 has prize and d1 was chosen, open d2
				door2.open = true;
			}
			if (door2.chosenByContestant == true) { //if d3 has grand prize and d2 was chosen, open d1
				door1.open = true;
			}
		}
	}
    
	
	//check to see if door was open by Montey or by the contestant and only switch doors
	//if the user specified to switch
	
	void toSwitchOrNotToSwitch(boolean toSwitch) {
		if (toSwitch == true) { //if user chooses to switch everytime
			if (door1.open == true) { //checks to see if d1 is open
				if (door2.chosenByContestant == true) { //if d1 open and d2 chosen by contestant then switch do d3
					door2.chosenByContestant = false;
					door3.chosenByContestant = true;
				} else if (door3.chosenByContestant == true) { //if d1 open and d3 chosen by contestant then switch to d2
					door2.chosenByContestant = true;
					door3.chosenByContestant = false;
				}
			}
			if (door2.open == true) { //checks to see if d2 is open
				if (door1.chosenByContestant == true) { //if d2 is open and d1 is chosen by contestant then switch to d3
					door1.chosenByContestant = false;
					door3.chosenByContestant = true;
				} else if (door3.chosenByContestant == true) { //if d2 is open and d3 is chosen by contestant then switch to d1
					door1.chosenByContestant = true;
					door3.chosenByContestant = false;
				}

			}
			if (door3.open == true) { //checks to see if d3 is open
				if (door1.chosenByContestant == true) { //if d3 is open and d1 is chosen by contestant then switch to d2
					door1.chosenByContestant = false;
					door2.chosenByContestant = true;
				} else if (door2.chosenByContestant == true) { //if d3 is open and d2 is chosen by contestant then switch to d1
					door1.chosenByContestant = true;
					door2.chosenByContestant = false;
				}

			}
		}
	}

    //does player win or not?
	
	boolean didPlayerWin() {
		if ((door1.chosenByContestant && door1.hasGrandPrize) //if d1 was chosen by contestant and has prize, then win!
				|| (door2.chosenByContestant && door2.hasGrandPrize) //if d2 was chosen by contestant and has prize, then win!
				|| door3.chosenByContestant && door3.hasGrandPrize) { //if d3 was chosen by contestant and has prize, then win!
			return true; //returns a true to initiate win counter
		} else {
			return false;
		}
	}
}

