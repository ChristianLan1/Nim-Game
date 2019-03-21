









import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InvalidPropertiesFormatException;
import java.util.Scanner;

import javax.naming.directory.InvalidSearchFilterException;

/**
 * @author Xinjie Lan, Student Id: 910030, Username:xinjiel2
 * @version 3.8
 * @description This is the third project for COMP90041
 * Main method in this class
 * This class contains lots of methods in order to run the program
 * 
 */

public class Nimsys {
	private final int IN_GAME = 0;
	private final int IDLE = -1;
	private final int FIRST_POSITION = 0;
	private final int SECOND_POSITION = 1;
	private final int THIRD_POSITION = 2;
	private final int FOURTH_POSITION = 3;
	private final int FIVTH_POSITION = 4;
	private final int SIXTH_POSITION = 5;
	private final int MAX_PLAYER = 100;
	private final int Length_OF_ARRAY = 2;
	private final int MAX_OUTPUT_PLAYERS = 10;
	private final int HUMAN_LENGTH = 6;

	public static Scanner Keyboard = new Scanner(System.in);// The only static variable
	

	public int checkIndex(String[] command, NimPlayer[] ply) {
		int index = 0;
		for (int i = 0; i < ply.length; i++) {
			if (ply[i] != null) {
				if (command[SECOND_POSITION].equals(ply[i].getUsername())) {
					index = i;
					break;
				}
			}
		}
		return index;
	}

	public int[] checkTwoIndex(String[] command, NimPlayer[] ply) {
		// This method return the position of the player in array
		int[] indexForPlayers = new int[Length_OF_ARRAY];
		for (int i = 0; i < ply.length; i++) {
			if (ply[i] != null) {
				if (command[FOURTH_POSITION].equals(ply[i].getUsername())) {
					indexForPlayers[FIRST_POSITION] = i;
				}
				if (command[FIVTH_POSITION].equals(ply[i].getUsername())) {
					indexForPlayers[SECOND_POSITION] = i;
				}
			}
		}
		return indexForPlayers;
	}

	public boolean checkArr(String[] command, NimPlayer[] ply) {
		// This method check if the player exists
		for (int i = 0; i < ply.length; i++) {
			if (ply[i] != null & command[SECOND_POSITION] != null) {
				if (command[SECOND_POSITION].equals(ply[i].getUsername())) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkTwoUsers(String[] command, NimPlayer[] ply) {
		// This method check if both player in the array
		int count = 0;
		for (int i = 0; i < ply.length; i++) {
			if (ply[i] != null) {
				if (command[FOURTH_POSITION].equals(ply[i].getUsername())) {
					count += 1;
				}
				if (command[FIVTH_POSITION].equals(ply[i].getUsername())) {
					count += 1;
				}
				if (count == 2) {
					return false;
				}
			}
		}
		return true;
	}

	public NimPlayer[] rankingList(NimPlayer[] playerData,boolean order) {
		// This method returns the sorted player list by winning ratio(alphabet
		// if tie)
		NimPlayer[] plyRanking = new NimPlayer[playerData.length];
		int index = 0;
		for (int i = 0; i < playerData.length; i++) {
			if (playerData[i] == null) {
				continue;
			}
			if (playerData[i].numOfGamePlayed != 0) {
				playerData[i].winningRatio = 
					(double) playerData[i].numOfGameWon / playerData[i].numOfGamePlayed;
				plyRanking[i] = playerData[i];
				index = i + 1;
			}
			if (playerData[i].numOfGamePlayed == 0) {
				playerData[i].winningRatio = 0;
				plyRanking[i] = playerData[i];
				index = i + 1;
			}
		}

		NimPlayer[] newPlyRanking = new NimPlayer[index];

		System.arraycopy(plyRanking, 0, newPlyRanking, 0, index);
		// copy the sorted array into the new array without null
		if(order){
		Arrays.sort(newPlyRanking);// override compareTo method in NimPlayer class
		}else{
			Arrays.sort(newPlyRanking,new NimHumanPlayer());//compare method
		}
		return newPlyRanking;
	}

	public void addPlayer(Nimsys system, String[] command, NimPlayer[] playerData, boolean AI) {
		if (system.checkArr(command, playerData)) {
			for (int i = 0; i < playerData.length; i++) {
				if (playerData[i] == null) {
					if(!AI){
						playerData[i] = new NimHumanPlayer();
					}else{
						playerData[i] = new NimAIPlayer();
					}
					
					playerData[i].setUsername(command[SECOND_POSITION]);
					playerData[i].setGivenName(command[FOURTH_POSITION]);
					playerData[i].setFamilyName(command[THIRD_POSITION]);
					break;
				}
			}
		} else {
			System.out.println("The player already exists.");
		}
	}

	public void removePlayer(Nimsys system, String[] command, NimPlayer[] playerData) {
		if (command.length < Length_OF_ARRAY) {
			System.out.println("Are you sure you want to remove all players? (y/n)");
			String removeAll = Keyboard.nextLine();
			if (removeAll.equals("y") || removeAll.equals("Y")) {
				for (int i = 0; i < playerData.length; i++) {
					playerData[i] = null;
				}
			}
			// continue;
		} else if (!system.checkArr(command, playerData)) {
			playerData[system.checkIndex(command, playerData)] = null;
		} else {
			System.out.println("The player does not exist.");
		}
	}

	public void editPlayer(Nimsys system, String[] command, NimPlayer[] playerData) {
		if (!system.checkArr(command, playerData)) {
			int playerNum = system.checkIndex(command, playerData);
			playerData[playerNum].setGivenName(command[FOURTH_POSITION]);
			
			playerData[playerNum].setFamilyName(command[THIRD_POSITION]);
		} else {
			System.out.println("The player does not exist.");
		}

	}

	public void displayPlayer(Nimsys system, String[] command, NimPlayer[] playerData) {
		String[] usernameOrder = new String[playerData.length];
		// put all the usernames into a new array
		int index = 0;
		for (int i = 0; i < playerData.length; i++) {
			if (playerData[i] != null) {
				usernameOrder[i] = playerData[i].getUsername();
				index = i + 1;
			}

		}
		String[] newUsernameOrder = new String[index];

		System.arraycopy(usernameOrder, 0, newUsernameOrder, 0, index);// eliminate all the null

		Arrays.sort(newUsernameOrder);

		NimPlayer storedHumanPlayer = new NimHumanPlayer();
		NimPlayer storedAIPlayer = new NimAIPlayer();
		// creating a instance of NimPlayer to store the value
		for (int i = 0; i < newUsernameOrder.length; i++) {
			// re-assign the positions of playerData by it's username's positions
			for (int j = 0; j < playerData.length; j++) {
				if (playerData[j] != null) {
					if (newUsernameOrder[i].equals(playerData[j].getUsername())) {
						if(playerData[j] instanceof NimHumanPlayer){
							storedHumanPlayer = playerData[i];
						playerData[i] = playerData[j];
						playerData[j] = storedHumanPlayer;
						}else{
							storedAIPlayer = playerData[i];
							playerData[i] = playerData[j];
							playerData[j] = storedAIPlayer;
						}
						
					}
				}
			}
		}

		if (command.length < Length_OF_ARRAY) {
			for (int i = 0; i < playerData.length; i++) {
				if (playerData[i] != null) {
					System.out.println(playerData[i].getUsername() + "," + playerData[i].getGivenName() + ","
							+ playerData[i].getFamilyName() + "," + playerData[i].numOfGamePlayed + " games" + ","
							+ playerData[i].numOfGameWon + " wins");
				}
			}
			// continue;
		} else if (!system.checkArr(command, playerData) && command[SECOND_POSITION] != null) {
			int playerNum = system.checkIndex(command, playerData);
			System.out.println(playerData[playerNum].getUsername() + ","
					+ playerData[playerNum].getGivenName() + ","
					+ playerData[playerNum].getFamilyName() + ","
					+ playerData[playerNum].numOfGamePlayed + " games" + ","
					+ playerData[playerNum].numOfGameWon + " wins");
		} else {
			System.out.println("The player does not exist.");
		}
	}

	public void resetStats(Nimsys system, String[] command, NimPlayer[] playerData) {
		if (command.length < Length_OF_ARRAY) {
			System.out.println("Are you sure you want to reset all player statistics? (y/n)");
			String resetAll = Keyboard.nextLine();
			//System.out.println("command " + resetAll);
			if (resetAll.equals("y") || resetAll.equals("Y")) {

				for (int i = 0; i < playerData.length; i++) {

					if (playerData[i] != null) {
						playerData[i].numOfGamePlayed = 0;
						playerData[i].numOfGameWon = 0;
					}
				}
			}
			//System.out.println();
			// continue;
		} else if (!system.checkArr(command, playerData)) {
			playerData[system.checkIndex(command, playerData)].numOfGamePlayed = 0;
			playerData[system.checkIndex(command, playerData)].numOfGameWon = 0;
		} else {
			System.out.println("The player does not exist.");
		}
	}
	public void descOutput(NimPlayer[] playerData){
		if(playerData.length<=MAX_OUTPUT_PLAYERS){
			for (int i = 0; i < playerData.length; i++) {
				if (playerData[i] != null) {
					System.out.println(playerData[i]);
				}
			}
			// continue;
		}		else{
						for (int i = 0; i < 10; i++) {
								if (playerData[i] != null) {
									System.out.println(playerData[i]);
				}
			}
		}
	}

	public void rankings(Nimsys system, String[] command, NimPlayer[] playerData) {
		boolean order = true;
		playerData = system.rankingList(playerData,order);// getting the sorted rankinglist
		if (command.length < Length_OF_ARRAY) {
			descOutput(playerData);
			
		} else if (command[SECOND_POSITION].equals("desc")) {
			descOutput(playerData);
		} else if (command[SECOND_POSITION].equals("asc")) {
			order = false;
			playerData = system.rankingList(playerData,order);
			descOutput(playerData);
			
		}
		
	}
	public void writeFile(NimPlayer[] playerData){
		//This method write player data into file players.dat
		try {
			PrintWriter outputStreamName = new PrintWriter(new FileOutputStream("players.dat"));
			for (int i = 0; i < playerData.length; i++) {
				if(playerData[i]!=null){
					
					boolean test4Human = playerData[i] instanceof NimHumanPlayer;
					boolean test4AI = playerData[i] instanceof NimAIPlayer;
					//System.out.println("test4 "+test4);
					//System.out.println("test5 "+test5);
					if(test4AI){
						 
						String line =  (playerData[i].getUsername()+","+playerData[i].getGivenName()
								+","+playerData[i].getFamilyName()+","+playerData[i].getNumOfGamePlayed()+","
								+playerData[i].getNumOfGameWon()+","+playerData[i].getWinningRatio()+","
								+((NimAIPlayer)playerData[i]).isAIPlayer());
					outputStreamName.print(line+"\r\n");
					}else if (test4Human){//can be extra to a method
						
						String line = playerData[i].getUsername()+","
						+playerData[i].getGivenName()+","+playerData[i].getFamilyName()+","
						+playerData[i].getNumOfGamePlayed()+","+playerData[i].getNumOfGameWon()+","
						+playerData[i].getWinningRatio();
				outputStreamName.print(line+"\r\n");}
				
				
				
			}}
			outputStreamName.flush();
			outputStreamName.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public NimPlayer[] fileReader(NimPlayer[] playerData){
		//This method read file players.dat
		try {
			File file = new File("."+File.separator+"players.dat");
			//in order to store in same directory
			if(!file.exists()){
				file.createNewFile();
				//System.out.println("done written file");
			}else{
			BufferedReader inputStream = new BufferedReader(new FileReader("players.dat"));
			String line = null;
			
			
			int count = 0;
			while((line =inputStream.readLine())!=null){
				
				
				String regex = "[^a-zA-Z_0-9.]";
				
				String[] info = line.split(regex);
				
				if(info.length==HUMAN_LENGTH){
					playerData[count] = new NimHumanPlayer();
					playerData[count].setUsername(info[FIRST_POSITION]);
					
					playerData[count].setGivenName(info[SECOND_POSITION]);
					playerData[count].setFamilyName(info[THIRD_POSITION]);
					playerData[count].setNumOfGamePlayed(Integer.parseInt(info[FOURTH_POSITION]));
					playerData[count].setNumOfGameWon(Integer.parseInt(info[FIVTH_POSITION]));
					playerData[count].setWinningRatio(Double.parseDouble(info[SIXTH_POSITION]));}
				else{
					playerData[count] = new NimAIPlayer();
					playerData[count].setUsername(info[FIRST_POSITION]);
					
					playerData[count].setGivenName(info[SECOND_POSITION]);
					playerData[count].setFamilyName(info[THIRD_POSITION]);
					playerData[count].setNumOfGamePlayed(Integer.parseInt(info[FOURTH_POSITION]));
					playerData[count].setNumOfGameWon(Integer.parseInt(info[FIVTH_POSITION]));
					playerData[count].setWinningRatio(Double.parseDouble(info[SIXTH_POSITION]));
				}
					count += 1;
				//System.out.println("test"+playerData.length);	
				//}
			}
			//System.out.println("done");
			inputStream.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File players.dat was not found");
		} catch(IOException e){
			System.out.println("Error reading from players.dat");
		}
		return playerData;
	}

	public void systemCommand(NimGame game, Nimsys system, NimPlayer[] playerData) {
		
			
		playerData = fileReader(playerData);
		//System.out.println(playerData[0].getFamilyName());
		System.out.println("Welcome to Nim");
		int state = IDLE;
		while (state != IN_GAME) {
			try {System.out.println();
			System.out.print("$");
			String commandInput = Keyboard.nextLine();

			String regex = "[^a-zA-Z_0-9]";
			String[] command = commandInput.split(regex);

			if (command[FIRST_POSITION].equals("addplayer")) {
				// switch may not work on server so using if-else if-else here
				boolean AI = false;
				if(command.length<4){
					throw new InvalidNumOfArgsException();
				}

				addPlayer(system, command, playerData,AI);

			} else if (command[FIRST_POSITION].equals("addaiplayer")) {
				// switch may not work on server so using if-else if-else here
				boolean AI = true;
				if(command.length<4){
					throw new InvalidNumOfArgsException();
				}

				addPlayer(system, command, playerData,AI);

			}
			else if (command[FIRST_POSITION].equals("removeplayer")) {
				removePlayer(system, command, playerData);

			} else if (command[FIRST_POSITION].equals("editplayer")) {
				if(command.length<4){
					throw new InvalidNumOfArgsException();
				}
				editPlayer(system, command, playerData);

			} else if (command[FIRST_POSITION].equals("resetstats")) {
				resetStats(system, command, playerData);
			} else if (command[FIRST_POSITION].equals("displayplayer")) {
				displayPlayer(system, command, playerData);

			} else if (command[FIRST_POSITION].equals("rankings")) {
				rankings(system, command, playerData);

			} else if (command[FIRST_POSITION].equals("startgame")) {
				if(command.length<5){
					throw new InvalidNumOfArgsException();
				}
				if (!system.checkTwoUsers(command, playerData)) {
					int[] indexForPlayers = system.checkTwoIndex(command, playerData);
					//store the specified players into an array (indexForPlayers)
					System.out.println();
					System.out.println("Initial stone count: " + command[SECOND_POSITION]);
					
					game.numberOfStone = Integer.parseInt(command[SECOND_POSITION]);
					System.out.println("Maximum stone removal: " + command[THIRD_POSITION]);
					
					game.upperBound = Integer.parseInt(command[THIRD_POSITION]);
					
					System.out.println("Player 1: " 
					+ playerData[indexForPlayers[FIRST_POSITION]].getGivenName() 
					+ " "+ playerData[indexForPlayers[FIRST_POSITION]].getFamilyName());
					
					System.out.println("Player 2: " 
					+playerData[indexForPlayers[SECOND_POSITION]].getGivenName()
					+ " "+ playerData[indexForPlayers[SECOND_POSITION]].getFamilyName());

					game.gameStart(playerData[indexForPlayers[FIRST_POSITION]],
							playerData[indexForPlayers[SECOND_POSITION]], game);
					//use user-specified players to initiate game
				} else {
					System.out.println("One of the players does not exist.");
				}
			} else if (command[FIRST_POSITION].equals("exit")) {
				//System.out.println(playerData[0]);
				writeFile(playerData);
				System.out.println();
				System.exit(0);
				state = IN_GAME;
			}else{
			throw new InvalidCommandException(command[FIRST_POSITION]);}
		}catch (InvalidCommandException e) {
			String msg = e.getMessage();
			System.out.println("'"+msg+"'"+" is not a valid command.");
		
		}catch(InvalidNumOfArgsException e){
			String msg = e.getMessage();
			System.out.println("Incorrect number of arguments supplied to command.");
			}
		}

	}

	public static void main(String[] args) {
		NimGame game = new NimGame();// creating an instance of the game
		Nimsys system = new Nimsys();
		NimPlayer[] playerData = new NimPlayer[system.MAX_PLAYER];
		system.systemCommand(game, system, playerData);
	}

}
