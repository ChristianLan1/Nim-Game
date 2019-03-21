

/**
 * @author Xinjie Lan, Student Id: 910030, Username:xinjiel2
 * @version 3.8
 * @description This is the third project for COMP90041
 * 
 * This class in prepared for missing arguments 
 * 
 */


public class InvalidNumOfArgsException extends Exception{
	public InvalidNumOfArgsException(){
		super("Bad number of arguments");
	}
	public InvalidNumOfArgsException(String message){
		super();
	}

}
