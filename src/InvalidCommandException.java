
/**
 * @author Xinjie Lan, Student Id: 910030, Username:xinjiel2
 * @version 3.8
 * @description This is the third project for COMP90041
 * 
 * This class in prepared for invalid command exception
 * 
 */

public class InvalidCommandException extends Exception{
	public InvalidCommandException(String message){
		super(message);
	}
	public InvalidCommandException(){
		super("Invalid Command");
	}

}
