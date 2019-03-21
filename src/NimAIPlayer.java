import java.util.Random;

/*
NimAIPlayer.java

This class is provided as a skeleton code for the tasks of 
Sections 2.4, 2.5 and 2.6 in Project C. Add code (do NOT delete any) to it
to finish the tasks. 
*/
/**
 * 
 * @author Xinjie Lan, Student Id: 910030, Username:xinjiel2
 *@version 3.8
 *@description this is the third project for COMP90041
 * This  class stores all the data for AI player
 * Method removeStone is strategy for AI to win under certain conditions
 */
public class NimAIPlayer extends NimPlayer implements Testable {//toString later
	public String AIPlayer = "AI";
// you may further extend a class or implement an interface
// to accomplish the tasks.	

public NimAIPlayer() {
	super();
			
}
public NimAIPlayer(String AIPlayer){
	super();
	this.AIPlayer= AIPlayer;
}

public String isAIPlayer() {
	return AIPlayer;
}
public void setAIPlayer(String aIPlayer) {
	AIPlayer = aIPlayer;
}


public NimAIPlayer( String username, String givenName, String familyName,
		int numOfGamePlayed, int numOfGameWon, double winningRatio) {
	super();
	
	this.username = username;
	this.givenName = givenName;
	this.familyName = familyName;
	this.numOfGamePlayed = numOfGamePlayed;
	this.numOfGameWon = numOfGameWon;
	this.winningRatio = winningRatio;
}

public String advancedMove(boolean[] available, String lastMove) {
	// the implementation of the victory
	// guaranteed strategy designed by you
	String move = "";
	
	return move;
}

@Override
public int removeStone(){
	return 0;
}
public int removeStone(int maxRemoval,int numberOfLeftStone,boolean firstMove) {
	//overloading in order to use this method by different parameters
	int result = (numberOfLeftStone-1)%(maxRemoval+1);
	//System.out.println("result"+result);
	int numberOfRemoval;
	//int testNumOfRemoval = -1;
	Random num = new Random();
	if((result!=0&&firstMove)){
		//testNumOfRemoval = result;
		numberOfRemoval = result;
	}
	
	else if(!firstMove){
		if(result!=0){
			//testNumOfRemoval = result;
			numberOfRemoval = result;
		}else{
			
			numberOfRemoval = num.nextInt(maxRemoval)+1;
			//System.out.println("random result");
		}
	}
	else{
		//Random num = new Random();
		numberOfRemoval = num.nextInt(maxRemoval)+1;
		//System.out.println("random result");
	}
	 
	
	return numberOfRemoval;
}
}