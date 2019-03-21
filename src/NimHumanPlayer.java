
/**
 * 
 * @author Xinjie Lan, Student Id: 910030, Username:xinjiel2
 *@version 3.8
 *@description this is the third project for COMP90041
 * This  class stores all the data for Human player
 */
public class NimHumanPlayer extends NimPlayer{
	NimHumanPlayer(){
		super();
	}
	public NimHumanPlayer( String username, String givenName, String familyName,
			int numOfGamePlayed, int numOfGameWon, double winningRatio) {
		super();
		
		this.username = username;
		this.givenName = givenName;
		this.familyName = familyName;
		this.numOfGamePlayed = numOfGamePlayed;
		this.numOfGameWon = numOfGameWon;
		this.winningRatio = winningRatio;
	}
	@Override
		public int removeStone(){
			return 0;
			
		}
		public int removeStone(int enteredRemovalNum){
		
			int numberOfRemoval = enteredRemovalNum;
		
			return numberOfRemoval;
	}
		

}
