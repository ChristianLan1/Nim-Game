import java.util.InputMismatchException;

/**
 * 
 * @author Xinjie Lan, Student Id: 910030, Username:xinjiel2
 *@version 2.8
 *@description this is the third project for COMP90041
 * This class controls how the game operated
 */


public class NimGame {
	private int count; // count turns in order to determine who will be the winner
	public int numberOfStone;// total number of the stones
	public int upperBound;

	private void playerTurn(int numberOfStone, StringBuilder asterisks, int count, NimPlayer ply, int Keyboard,
			NimGame game,boolean firstMove) {

		if (numberOfStone > 0) {
			

			int numOfRemoval = 0;
			if(ply instanceof NimHumanPlayer){
				
				numOfRemoval = ((NimHumanPlayer)ply).removeStone(Keyboard);
			}if(ply instanceof NimAIPlayer){
				numOfRemoval = ((NimAIPlayer)ply).removeStone(game.upperBound,numberOfStone,firstMove);
			}

			numberOfStone -= numOfRemoval;

			asterisks.delete(0, numOfRemoval * 2);
			count++;
			game.numberOfStone = numberOfStone;
			game.count = count;

			if (numberOfStone > 0) {// in case there is an extra line when
									// stones <=0
				System.out.println(numberOfStone + " stones left:" + asterisks);
			}
		}

	}
	private int checkHumanInputInvalid(NimPlayer ply,StringBuilder asterisks){
		boolean correct = false;
		int removal = 0;
		
			removal = Nimsys.Keyboard.nextInt();
			correct = checkCorrectInput(removal);
			while (!correct) {
				System.out.println(numberOfStone + " stones left:" + asterisks);
				System.out.println(ply.getGivenName() + "'s turn - remove how many?");
				System.out.println();
				try {
					removal = Nimsys.Keyboard.nextInt();
				correct = checkCorrectInput(removal);
				} catch (InputMismatchException e) {
					e.printStackTrace();// TODO: handle exception
				}
				
			}
		return removal;
	}

	private boolean checkCorrectInput(int removal) {
		// This method considers all the incorrect inputs which has 6 possible
		//String invalidSyntaxBound = "Invalid move. You must remove between 1 and " + upperBound + " stones.";
		//String invalidSyntaxLeftStones = "Invalid move. You must remove between 1 and " + numberOfStone + " stones.";
		while (removal > upperBound || removal > numberOfStone || removal == 0) {
			//System.out.println();
			try{
			if (removal == 0 && upperBound > numberOfStone) {
				throw new InvalidMoveException(numberOfStone);
				
			} else if ((removal == 0 && upperBound < numberOfStone) || removal == 0) {
				
				throw new InvalidMoveException(upperBound);
			} else if (removal > upperBound && upperBound > numberOfStone) {
				throw new InvalidMoveException(numberOfStone);
				
			} else if (removal > upperBound && upperBound < numberOfStone) {
				
				throw new InvalidMoveException(upperBound);
			} else if (removal >= numberOfStone && upperBound <= numberOfStone) {
				
				throw new InvalidMoveException(upperBound);
			} else if (removal >= numberOfStone && upperBound >= numberOfStone) {
				throw new InvalidMoveException(numberOfStone);
				
			}}
			catch(InvalidMoveException e){
				int msg = e.getStones();
				System.out.println("Invalid move. You must remove between 1 and "
						+ msg + " stones.");
				System.out.println();
				return false;
			}
			// System.out.println();
			// removal = Nimsys.Keyboard.nextInt();
		}
		return true;
	}

	public void gameStart(NimPlayer ply1, NimPlayer ply2, NimGame game) {
		
		System.out.println();

		StringBuilder asterisks = new StringBuilder();
		for (int i = 0; i < numberOfStone; i++) {
			asterisks.append(" " + "*");
		}

		System.out.println(numberOfStone + " stones left:" + asterisks);

		while (numberOfStone > 0) {// This loop is used for repeating player's
									// turns

			int removal = 0;
			//boolean correct = false;
			boolean firstMove = true;
			System.out.println(ply1.getGivenName() + "'s turn - remove how many?");
			System.out.println();
			if(ply1 instanceof NimHumanPlayer){
			removal = checkHumanInputInvalid(ply1,asterisks);}
			
			

			playerTurn(numberOfStone, asterisks, count, ply1, removal, game,firstMove);

			if (numberOfStone > 0) {

				System.out.println(ply2.getGivenName() + "'s turn - remove how many?");
				firstMove = false;
				System.out.println();
				if(ply2 instanceof NimHumanPlayer){
				removal = checkHumanInputInvalid(ply2,asterisks);}
				
				
				playerTurn(numberOfStone, asterisks, count, ply2, removal, game,firstMove);
				// System.out.println("Second count " + count);
			}

		}
		if (numberOfStone <= 0) {
			System.out.println("Game Over");
			String deleteExtraLine = Nimsys.Keyboard.nextLine();// To eliminate
																// extra $ which
																// created by
																// removal
			ply1.numOfGamePlayed += 1;
			ply2.numOfGamePlayed += 1;

			if (count % 2 == 0) {// odd number is player1's turn vice-versa

				System.out.println(ply1.getGivenName() + " " + ply1.getFamilyName() + " wins!");
				ply1.numOfGameWon += 1;
			} else {
				System.out.println(ply2.getGivenName() + " " + ply2.getFamilyName() + " wins!");
				ply2.numOfGameWon += 1;
			}

			count = 0;// reset count every game
		}

	}
}
