


import java.util.Comparator;

/**
 * 
 * @author Xinjie Lan, Student Id: 910030, Username:xinjiel2
 *@version 3.8
 *@description this is the third project for COMP90041
 * This abstract class prepare for Human player and AI player
 */




public abstract class NimPlayer implements Comparable<NimPlayer>,Comparator<NimPlayer>{
	
	/*
	 * for security reason, username,givenname, and familyname should be private
	 */
	public String username;
	public String givenName;
	public String familyName;
	public int numOfGamePlayed=0;
	public int numOfGameWon=0;
	public double winningRatio;
	
	public abstract int removeStone();

	@Override
	public int compareTo(NimPlayer o) {
		//override compareTo to determine 
		//	the sequence of the object array by it's winning ratio(username if tie)
		
		double i = winningRatio -o.winningRatio;
		if(i>0){
			return -1;
		}if(i<0){
			return 1;
		}else{
		return this.username.compareTo(o.username);}
	}
	@Override
	public int compare(NimPlayer o1, NimPlayer o2) {
		double i = o1.winningRatio-o2.winningRatio;
		if(i>0){
			return 1;
		}if(i<0){
			return -1;
		}else{
		return o1.username.compareTo(o2.username);}
	}
	public NimPlayer(){
		super();
	}
	public NimPlayer( String username, String givenName, String familyName,
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
	public String toString() {
		//In order to output the ranking by specific format, several if-else statements are used
		long winRate = Math.round((winningRatio*100));
		if(numOfGamePlayed<10){
				
			if(winRate==100){
			
			return winRate+"% "+"| "+"0"+numOfGamePlayed+" games"+" | "+ givenName+" "+familyName;
		}
		else if(winRate==0){
			return winRate+"%   "+"| "+"0"+numOfGamePlayed+" games"+" | "+ givenName+" "+familyName;
		}
		else{
		return winRate+"%  "+"| "+"0"+numOfGamePlayed+" games"+" | "+ givenName+" "+familyName;
		}	}
		else{
			if(winRate==100){
				
				return winRate+"% "+"| "+numOfGamePlayed+" games"+" | "+ givenName+" "+familyName;
			}
			else if(winRate==0){
				return winRate+"%   "+"| "+numOfGamePlayed+" games"+" | "+ givenName+" "+familyName;
			}
			else{
			return winRate+"%  "+"| "+numOfGamePlayed+" games"+" | "+ givenName+" "+familyName;
			}
		}
	}

	public double getWinningRatio() {
		return winningRatio;
	}

	public void setWinningRatio(double winningRatio) {
		this.winningRatio = winningRatio;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public int getNumOfGamePlayed() {
		return numOfGamePlayed;
	}

	public void setNumOfGamePlayed(int numOfGamePlayed) {
		this.numOfGamePlayed = numOfGamePlayed;
	}

	public int getNumOfGameWon() {
		return numOfGameWon;
	}

	public void setNumOfGameWon(int numOfGameWon) {
		this.numOfGameWon = numOfGameWon;
	}
	


}
