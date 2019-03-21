


/**
 * @author Xinjie Lan, Student Id: 910030, Username:xinjiel2
 * @version 3.8
 * @description This is the third project for COMP90041
 * 
 * This class in prepared for invalid move exception
 * 
 */
public class InvalidMoveException extends Exception{
	private int stones;
	public InvalidMoveException(){
		super("Invalid Move");
	}
	public InvalidMoveException(int message){
		super("Invalid Move");
		stones = message;
	}
	public int getStones(){
		return stones;
	}
	
}
