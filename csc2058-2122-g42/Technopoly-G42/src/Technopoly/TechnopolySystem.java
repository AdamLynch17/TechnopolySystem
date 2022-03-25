package src.Technopoly;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class TechnopolySystem {

	public static BoardSquare[] board = new BoardSquare[23];
	public static Player[] players = new Player[0];
	public static int maxRoundLimit=0;
	public static int currentRound = 1;
	public static int currentPlayerturn = 0;
	public static boolean endGame = false;
	public static int currentPosCounter = 6;
	// pathway for csv board squares
	public static String pathname = "Technopoly-G42/files/board-squares.csv";

	// note that players are indexed from 0

	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);

		// starts the game and sets the variables accordingly
		startGame(s);

		// sets the board squares and property squares
		setBoardSq();

		boolean reply = true;

		while(reply){
			// game play
			gamePlay(s);

			reply = playAgain(s);

			if(reply){
				System.out.println("User wants to play again");

				if(reply){
					// resets player & board squares
					playAgainSetVariables(s);

				}
			}

		}

		s.close();

	}

	// resets the variables for play again

	public static void playAgainSetVariables(Scanner s){
		// resets player data
		Player[] newPlayers = new Player[players.length];
		for(int i=0; i< players.length; i++){
			Player newPlay = new Player(i,players[i].getName());
			newPlayers[i] = newPlay;
		}
		players = newPlayers;

		// resets board squares values
		for(BoardSquare sq:board){
			if(sq instanceof PropertySq){
				// reset owner id
				((PropertySq) sq).setOwnerID(-1);
				// full set owned = false
				((PropertySq) sq).setFullSetOwned(false);
				// num offices = 0
				((PropertySq) sq).setNumOffices(0);
				// num hqs =0
				((PropertySq) sq).setNumHQs(0);
			}
		}

		// resets counters and end game variable
		currentPosCounter = players.length;
		currentPlayerturn = 0;
		currentRound = 1;
		endGame = false;
		setNumRounds(s);

	}

	public static boolean checkNameExists(Player[] players, String playerName, int j) {
		if (players == null) {
			return false;
		} else {
			for (int i = 0; i < j; i++) {
				if (players[i].getName().equalsIgnoreCase(playerName.toLowerCase())) {
					System.out.println("This player name already exists. Please enter a different one:");
					return true;
				}
			}
		}
		return false;
	}

	// start game
	public static void startGame(Scanner s) {
		boolean flag = false;
		//Prompts user to enter the s key to start the game through a do/while loop
		do {
			System.out.println("Please press the 'S' key to begin :");
			char input = s.next().charAt(0);
			//Relevant validation checks
			if ((input == 's') || (input == 'S')) {
				flag = true;
			}
		} while (!flag);

		System.out.println("The game begins.");

		// sets the number of players for the game
		int numPlayers = setNumPlayers(s);
		currentPosCounter = numPlayers;
		s.nextLine();
		// initialise the array
		players = new Player[numPlayers];

		// sets player names
		setPlayerNames(s, numPlayers);

		// sets max round limit
		setNumRounds(s);

		System.out.println("Round Limit set! \nLet's get started playing Technopoly!");
		System.out.println("Remember: each player has 1000 PC's to get started!");
	}

	// sets the number of players for the game
	public static int setNumPlayers(Scanner s) {
		// set number of players
		int numPlayers = 0;
		boolean validInput = false;
		//Prompts user tp enter no.of players
		do {
			System.out.println("How many players would you like to play? \n (Minimum of 2 and maximum of 8 players): ");
			String input = s.next();
			//validation for if a non-integer digit is entered
			try {
				numPlayers = Integer.parseInt(input);
				//validation that no.of players must be between 2-8 players
				if ((numPlayers >= 2) && (numPlayers <= 8)) {
					validInput = true;
				} else {
					System.out.println("Number of players entered is not within the game limits.");
				}

			} catch (Exception ex) {
				System.out.println("Please enter a valid number of players.");
			}

		} while (!validInput);

		return numPlayers;
	}

	// sets players names
	public static void setPlayerNames(Scanner s, int numPlayers) {
		String playerName=" ";
		boolean nameExists;
		boolean validData=false;
		for (int i = 0; i < numPlayers; i++) {

			do {
				System.out.println("What is the name of player " + (i + 1) + ": ");
				playerName = s.nextLine();
					if((playerName.equals("")) || (playerName.equals(null)) || (playerName.contains(" "))){
						System.out.println("Please enter a valid name");
						nameExists = true;
					}
					else if( (i == 0) ) {
						nameExists = false;
					} else {
						nameExists = checkNameExists(players, playerName, i);
						if (!nameExists) {
							validData = true;
						}
					}
			} while (nameExists );


			players[i] = setPlayerDetails(i, playerName);

		}

	}

	// sets the player details
	public static Player setPlayerDetails(int id, String name) {
		Player addNew = new Player(id, name);

		if ((addNew.getName().equals(name)) && (addNew.getPlayerID() == id)) {
			System.out.println("Player successfully created!");
		} else {
			System.out.println("Error creating player. Please try again.");
		}
		return addNew;
	}

	public static void setNumRounds(Scanner s) {
		boolean validMaxRounds = false;
		do {
			System.out.println("\nWhat would you like to be the Maximum Round Limit? (between a maximum of 1-10 rounds): ");
			String input = s.next();
			try {
				maxRoundLimit = Integer.parseInt(input);
				if (maxRoundLimit >= 1 && maxRoundLimit <= 10) {
					validMaxRounds = true;
				} else {
					System.out.println("Please select a round limit within the limits of the game.");
				}
			} catch (Exception ex) {
				System.out.println("Please enter a valid round limit.");
			}
		} while (!validMaxRounds);
	}

	public static boolean playAgain(Scanner s){
		boolean validPlayAgain = false;
		String userAns = "N";

		do {
			System.out.println("\nWould you like to play Technopoly again? Y/N ");
			String input = s.next();
			try {
				userAns = input;
				if (userAns.equals("Y") || userAns.equals("N")) {
					validPlayAgain = true;
				} else {
					System.out.println("Please enter in  a 'Y' for Yes or a 'N' for No: ");
				}
			} catch (Exception ex) {
				System.out.println("Please enter a valid response.");
			}
		} while (!validPlayAgain);
		return userAns.equals("Y");
	}

	public static void gamePlay(Scanner s) {
		// loop for game
		while (!endGame) {

			calcTurn();
			if (checkEndGameStatus()) {
				break;
			}
			s.nextLine();
			performTurn(s);
			s.nextLine();

			// Checks the end game status
			if (checkEndGameStatus()) {
				break;
			}
		}

		s.nextLine();
		endGame();
	}

	// calculate the turn of each player and who goes next
	public static void calcTurn() {
		if (currentPlayerturn >= players.length) {
			currentPlayerturn = 1;
			currentRound++;

		} else {
			currentPlayerturn++;
		}

		while(!players[currentPlayerturn - 1].isActivePlayer()){
			if(!players[currentPlayerturn - 1].isActivePlayer()){
				//The current player is not active
				if (currentPlayerturn >= players.length) {
					currentPlayerturn = 1;
					currentRound++;

				} else {
					currentPlayerturn++;
				}
			}
		}


		if (!checkEndGameStatus()) {
			System.out.println("----------------------------------------");
			System.out.println("Calculating the turn of the next player");

			//Increment the currentPlayerTurn Variable and display to the user in text
			if (currentPlayerturn == 1) {
				System.out.println("Current Round of the game: " + currentRound);
			}
			System.out.println("The next player is: Player " + (currentPlayerturn) + " (" + players[currentPlayerturn - 1].getName() + ")");

			System.out.println("----------------------------------------");

		}

	}

	// performs the turn for each player
	public static void performTurn(Scanner s) {
		//Let the user know who is taking the turn
		System.out.println("Player " + (currentPlayerturn) + " (" + players[currentPlayerturn - 1].getName() + ")" + ": is taking a turn.");

		//Randomly roll the dice
		int dice1 = rollDice();
		int dice2 = rollDice();

		int moves = dice1+dice2;

		s.nextLine();
		System.out.println("You have rolled a " + dice1 + " and a " + dice2);
		System.out.println("You move forward " + moves + " spaces.");
		s.nextLine();

		int currentSquare = players[currentPlayerturn - 1].getCurrentSquareID();
		//Modulus 23 is the ensure that the square id does not exceed 22. Say square index 22 (square 23) + moved 5 spaces = index 27(square 28). 27%23 = index 4.(square 5)
		// The player is now on player 4 as they have looped around

		players[currentPlayerturn-1].setCurrentSquareID((currentSquare + moves) % 23);
		//players[currentPlayerturn - 1].setCurrentSquareID(6);

		int newSquare = players[currentPlayerturn - 1].getCurrentSquareID();

		//At this point the player should have moved and now we should check for the square they have landed on

		//Need to Check Square landed on
		System.out.println("You have landed on " + board[newSquare].getName());

		//Perform the mandatory actions i.e pass go, pay rent/ tax, etc

		//Pass go
		if (newSquare < currentSquare) {
			System.out.println("Well done " + players[currentPlayerturn - 1].getName() + ", you have passed go and collect 200 PC's!");
			players[currentPlayerturn - 1].setBalance(players[currentPlayerturn - 1].getBalance() + 200);
		}

		checkSquare(newSquare,s);

	}
	public static void checkSquare(int newSquare,Scanner s){
		//If it is a PROPERTY SQUARE
		if (board[newSquare] instanceof PropertySq) {
			PropertySq currentSq = (PropertySq) board[newSquare];
			//If the property square is OWNED
			if ((currentSq.getOwnerID() != -1) && (currentSq.getOwnerID() != players[currentPlayerturn - 1].getPlayerID())) {
				int rentOwed = currentSq.getRentValue();
				System.out.println("You have landed on " + players[currentSq.getOwnerID()].getName() + "'s research field.");


				System.out.println("A contribution of " + rentOwed + " PC's are required");
				s.nextLine();

				//Transferring money
				if (calcDebts(rentOwed, players[currentPlayerturn - 1])) {
					players[currentPlayerturn - 1].updateBalance(-rentOwed);
					players[currentSq.getOwnerID()].updateBalance(rentOwed);
					System.out.println("Update balance for renter: " + players[currentSq.getOwnerID()].getName() + " is: " + players[currentSq.getOwnerID()].getBalance() );
				}

			}
		}
		//IF it is a SPECIAL SQUARE
		else {
			//If it is a chance PRESS RELEASE
			if (((SpecialSquare) board[newSquare]).getAction().equals("Chance")) {
				int whatTypeOfChance = (int) (Math.floor(Math.random() * (4 - 1 + 1) + 1));
				performChance(whatTypeOfChance, players, currentPlayerturn - 1);
			}
		}
		if (players[currentPlayerturn - 1].isActivePlayer()) {
			takeOptionalAction(s);
		}

	}

    // randomises a number between 1 and 6 for the player to move
	public static int rollDice() {
		return (int) ((Math.random() * 6) + 1);
	}

	// ends the game
	public static void endGame() {

		System.out.println("The game ends.");
		// stats calculated in display stats
		// display stats
		displayStats();

	}

	// checks the end game status
	public static boolean checkEndGameStatus() {
		// checks turn limits
		if (currentRound > maxRoundLimit) {
			endGame = true;
		}
		// check num players
		int numActive = 0;
		for (Player player : players) {
			if (player.isActivePlayer()) {
				numActive++;
			}
		}

		if (numActive <= 1) {
			endGame = true;
		}

		// check status of end game variable
		return endGame;
	}

	// displays the stats to the players
	public static void displayStats() {

		System.out.println("----------------------------------------------");
		System.out.println("The stats are displayed at the end of the game");

		calcStats();
		// Ranks
		Player[] rankDisplay = new Player[players.length];

		for (Player player : players) {
			rankDisplay[player.getRanking()-1] = player;
		}

		// Name & final balance
		System.out.println("----LEADERBOARD----");
		for (int i = 0; i < rankDisplay.length; i++) {
			if (i == 0) {
				System.out.println("1st: " + rankDisplay[i].getName());
			} else if (i == 1) {
				System.out.println("2nd: " + rankDisplay[i].getName());
			} else if (i == 2) {
				System.out.println("3rd: " + rankDisplay[i].getName());
			} else {
				System.out.println((i + 1) + "th: " + rankDisplay[i].getName());
			}
			System.out.println("    Total Assets for player: " + rankDisplay[i].calcTotalAsset(board));
		}
		System.out.println("-------------------");
		System.out.println("----------------------------------------------");
	}

	public static void calcStats() {
		int numActive = 0;
		for (Player player : players) {
			if (player.isActivePlayer()) {
				numActive++;
			}
		}

		Player[] ranks = new Player[numActive];
		for (int i = 0; i < players.length; i++) {
			if (players[i].isActivePlayer()) {
				ranks[i] = players[i];
			}
		}

		int n = ranks.length;

		Player temp;
		for(int i=0; i < n; i++){
			for(int j=1; j < (n-i); j++){
				if(ranks[j-1].calcTotalAsset(board) > ranks[j].calcTotalAsset(board)){
					//swap elements
					temp = ranks[j-1];
					ranks[j-1] = ranks[j];
					ranks[j] = temp;
				}

			}
		}

		for (int i = 0; i <n; i++) {
			ranks[i].declareBankruptcyEndGame(currentPosCounter, board);
			currentPosCounter -= 1;
		}

	}

	// performs the chance card
	public static void performChance(int whatTypeOfChance, Player[] players2, int currentPlayerInt) {
		Player currentPlayer = players2[currentPlayerInt];
		switch (whatTypeOfChance) {
			case 1 -> {
				//player gains a random amount of money between 100 & 500 in 50 intervals
				System.out.println(currentPlayer.getName() + " gained " + gainMoney(players2, currentPlayerInt) + "PCs.");
				System.out.println(currentPlayer.getName() + "'s new balance is " + currentPlayer.getBalance() + " PCs");
			}
			case 2 -> {
				//player looses a random amount of money between 100 & 500 in 50 intervals
				System.out.println(currentPlayer.getName() + " lost " + looseMoney(players2, currentPlayerInt) + "PCs.");
				System.out.println(currentPlayer.getName() + "'s new balance is " + currentPlayer.getBalance());
			}
			case 3 -> {
				//player gains money from every player
				System.out.println(currentPlayer.getName() + " gained " + gainMoneyFromEveryPlayer(players2, currentPlayerInt) + "PCs from every player.");
				System.out.println(currentPlayer.getName() + "'s new balance is " + currentPlayer.getBalance());
			}
			case 4 -> {
				//player moves square
				chanceMovePlayer();
				System.out.println("The Press release moved " + currentPlayer.getName() + " to the square " + board[currentPlayer.getCurrentSquareID()].getName());
			}
		}
	}

	public static int gainMoney(Player[] player1, int currentPlayerInt) {
		//player gains a random amount of money between 100 & 500 in 50 intervals
		Player currentPlayer = player1[currentPlayerInt];
		int amountGained = (int) (Math.floor(Math.random() * (10 - 2 + 1) + 2)) * 50;
		currentPlayer.setBalance(currentPlayer.getBalance() + amountGained);
		return amountGained;
	}

	public static int looseMoney(Player[] player1, int currentPlayerInt) {
		//player looses a random amount of money between 100 & 500 in 50 intervals
		Player currentPlayer = player1[currentPlayerInt];
		int amountLost = (int) (Math.floor(Math.random() * (10 - 2 + 1) + 2)) * 50;
		//Transferring money
		if (calcDebts(amountLost, currentPlayer)) {
			currentPlayer.updateBalance(-amountLost);
		}
		return amountLost;
	}

	public static int gainMoneyFromEveryPlayer(Player[] player1, int currentPlayerInt) {
		//player gains money from every player
		Player currentPlayer = player1[currentPlayerInt];
		int amountLostPerPlayer = (int) (Math.floor(Math.random() * (5 - 1 + 1) + 1)) * 50;
		for (Player player : player1) {
			if (calcDebts(amountLostPerPlayer, player)) {
				player.updateBalance(-amountLostPerPlayer);
				currentPlayer.updateBalance(amountLostPerPlayer);
			}
		}
		return amountLostPerPlayer;
	}

	public static int chanceMovePlayer() {
		//player moves square
		boolean notOnCurrentSquare = false;
		int squareToMoveTo = -1;
		while (!notOnCurrentSquare) {
			squareToMoveTo = (int) (Math.floor(Math.random() * (23 + 1)));
			notOnCurrentSquare = true;
		}

		players[currentPlayerturn - 1].setCurrentSquareID(squareToMoveTo);

		return squareToMoveTo;
	}

	// calculates the debts of the player
	public static boolean calcDebts(int rentOwed, Player player) {

		//If they have SUFFICIENT FUNDS
		if (player.getBalance() >= rentOwed) {
			return true;
		}
		//If they NOT SUFFICIENT FUNDS prompt to SELL ASSET over and over again until it is possible
		else {
			//Bankrupt case
			if (player.calcTotalAsset(board) < rentOwed) {
				player.declareBankruptcy(currentPosCounter, board);
				currentPosCounter -= 1;
				return false;
			} else {
				do {
					player.sellAsset(board);
				} while (player.getBalance() < rentOwed);
				return true;
			}
		}
	}

	// the player can choose from optional actions and perform them
	public static void takeOptionalAction(Scanner s) {

		String[] options = {"1. Display Assets", "2. End Turn", "3. Buy Property", "4. Sell Asset", "5. Buy Building"};
		int userChoice;

		int currentSqId = players[currentPlayerturn - 1].getCurrentSquareID();
		boolean canBuyProp = false;
		boolean canSell = false;
		boolean canBuyBuild = false;

		if (board[currentSqId] instanceof PropertySq) {
			if (((PropertySq) board[currentSqId]).getOwnerID() == -1) {
				// if the sq is unowned - displays the price
				System.out.println("The property you have landed is available to buy and costs: " + ((PropertySq) board[currentSqId]).getBuyValue() + " PCs");
				System.out.println("Your current balance is: " + players[currentPlayerturn - 1].getBalance() + "PCs");
				canBuyProp = true;
			}
		}

		do {
			s.nextLine();
			canBuyProp = false;
			canSell = false;
			canBuyBuild = false;

			if (board[currentSqId] instanceof PropertySq) {
				if (((PropertySq) board[currentSqId]).getOwnerID() == -1) {
					canBuyProp = true;
				}
			}
			if(countPlayerProperties(currentPlayerturn-1)>0){
				canSell = true;
			}

			if(ownsFullSet(currentPlayerturn-1)){
				canBuyBuild = true;
			}

			System.out.println("-----------------");
			System.out.println("Optional Actions:");
			System.out.println("-----------------");

			for(int i=0;i< options.length;i++){
				if(i==2 && canBuyProp){
					// buy property option displayed
					System.out.println(options[i]);
				} else if(i==3 && canSell){
					// sell asset option displayed
					System.out.println(options[i]);
				} else if(i==4 && canBuyBuild){
					// buy building option displayed
					System.out.println(options[i]);
				} else if(i==0 || i==1 || i==5){
					// display options
					System.out.println(options[i]);
				}
			}

			System.out.println("Please choose an option from above: ");
			userChoice = s.nextInt();

			if((userChoice==3 && !canBuyProp) || (userChoice==4 && !canSell) || (userChoice==5 && !canBuyBuild)){
				System.out.println("Sorry this option is not available to you");
			} else{
				if (userChoice != 2) {
					switch (userChoice) {
						case 1 -> {
							displayUserAsset();
						}
						case 2 ->{
							System.out.println("User ends their turn.");
							System.out.println("The user has chosen option: *" + userChoice + "*");
						}
						case 3 -> {
							// buys property
							buyProperty(currentSqId);
							canBuyProp = false;
						}
						case 4 ->{
							// user sells asset
							players[currentPlayerturn - 1].sellAsset(board);
						}
						case 5 ->{
							//buy's building
							boolean available = false;
							//lists all properties that you can buy a house or hotel on
							for (int i = 0; i < 23; i++) {
								if (board[i] instanceof PropertySq) {
									// check full set is owned by player
									if (buyBuildingTest(i)) {
										if (!available) {
											System.out.println("You are able to buy buildings on these properties:");
										}
										System.out.println((board[i]).getName() + " to choose this property enter " + i);
										available = true;
									}
								}
							}
							if (!available) {
								System.out.println("You are unable to buy any buildings.");
								System.out.println("To buy a building on  this square you need to buy the squares:");
								for (BoardSquare place : board) {
									if (place instanceof PropertySq) {
										if (place.getField().equals(board[currentSqId].getField())) {
											if (((PropertySq) place).getOwnerID() != players[currentPlayerturn - 1].getPlayerID()) {
												System.out.println(place.getName());
											}
										}
									}
								}
							}
							if (available) {
								int input = s.nextInt();
								//s.nextLine();
								//asks the user which property to buy a building on
								buyBuilding(input);
							}
						}

					}
				}
			}


		} while (userChoice != 2);

	}

	public static int countPlayerProperties(int playerID){
		int numProp =0;

		for(BoardSquare sq:board){
			if(sq instanceof PropertySq){
				if(((PropertySq) sq).getOwnerID()==playerID){
					numProp++;
				}
			}
		}

		return numProp;
	}

	public static boolean ownsFullSet(int playerID){
		boolean ownsFullSet = false;

		for(BoardSquare sq:board){
			if(sq instanceof PropertySq){
				if(((PropertySq) sq).getOwnerID()==playerID && ((PropertySq) sq).getFullSetOwned()){
					return true;
				}
			}
		}
		return ownsFullSet;
	}

	public static boolean buyBuildingTest(int SqId) {
		if ((((PropertySq) board[SqId]).getOwnerID()) == players[currentPlayerturn - 1].getPlayerID()) {
			return ((PropertySq) board[SqId]).getFullSetOwned();
		}
		return false;
	}

	public static void buyBuilding(int SqId) {
		// Sq Id is the property to add the building to

		//If it is a property
		if (board[SqId] instanceof PropertySq) {
			//If the property is owned by the current player
			if ((((PropertySq) board[SqId]).getOwnerID()) == players[currentPlayerturn - 1].getPlayerID()) {


				//If the full set is owned
				if (((PropertySq) board[SqId]).getFullSetOwned()) {
					//If they have less than 4 offices

					if (((PropertySq) board[SqId]).getDetails().getNumOffice() < 4 && ((PropertySq) board[SqId]).getDetails().getNumHq() < 1) {
						//checks that the square have an office purchased on it
						players[currentPlayerturn - 1].updateBalance(-((PropertySq) board[SqId]).calcOfficePrice());
						//the player buys the office
						((PropertySq) board[SqId]).setNumOffices(((PropertySq) board[SqId]).getNumOffices() + 1);
						//number off offices on that square is increased by 1
						System.out.println("You payed " + ((PropertySq) board[SqId]).calcOfficePrice() + board[SqId].getName());
						System.out.println("You have now have " + ((PropertySq) board[SqId]).getNumOffices() + " Offices.");

					} else if (((PropertySq) board[SqId]).getNumOffices() == 4) {
						//if an HQ can be bought instead of office
						players[currentPlayerturn - 1].updateBalance(-((PropertySq) board[SqId]).calcHQPrice());
						//charges player for the HQ
						((PropertySq) board[SqId]).setNumOffices(0);
						((PropertySq) board[SqId]).setNumHQs(1);
						//removes offices and replaces with HQ
						System.out.println("You payed " + ((PropertySq) board[SqId]).calcHQPrice());
						System.out.println("You have now have " + ((PropertySq) board[SqId]).getNumHQs() + " HQs.");
					} else if (((PropertySq) board[SqId]).getNumHQs() == 1 && ((PropertySq) board[SqId]).getNumOffices() == 0) {
						System.out.println("You already have all available buildings on this square.");
					}
				} else {
					System.out.println("You don't currently own the full property set, do this before buying a house.");
				}
			} else {
				System.out.println("You can not build on this square.");
			}
		}
	}

	public static void buyProperty(int currentSqId) {

		if (board[currentSqId] instanceof PropertySq) {
			if ((((PropertySq) board[currentSqId]).getOwnerID()) == -1) {

				// Updates the balance of the player
				if (players[currentPlayerturn - 1].updateBalance(-((PropertySq) board[currentSqId]).getBuyValue())) {


					// changes the owner ID of the property
					((PropertySq) board[currentSqId]).setOwnerID(currentPlayerturn - 1);

					// CHECKS if player owns all property in the set
					int noInSet = 0;
					int numOwnedInSet = 0;
					int[] propID = {-1, -1, -1};

					// find out num of properties in the set
					// find out num of properties player owns with the same field
					for (BoardSquare place : board) {
						if (place instanceof PropertySq) {
							if (place.getField().equals(board[currentSqId].getField())) {
								noInSet += 1;
								propID[noInSet - 1] = place.getSqID();
								if (((PropertySq) place).getOwnerID() == (currentPlayerturn - 1)) {
									numOwnedInSet++;
								}
							}
						}
					}

					// if the player owns all properties in a set
					if (noInSet == numOwnedInSet) {
						System.out.println("You own all properties in this set now.");
						for (int i = 0; i < noInSet; i++) {
							if (board[propID[i]] instanceof PropertySq) {
								((PropertySq) board[propID[i]]).setFullSetOwned(true);
							}
						}
					}

					// displays to user their new balance
					System.out.println("You have successfully acquired:" + board[currentSqId].getName());
					System.out.println("Your balance is now: " + players[currentPlayerturn - 1].getBalance() + "PCs");
				} else {
					System.out.println("You do not have enough PC's to research this field.");

				}
			} else {
				System.out.println("The property is already owned.");
			}

		} else {
			System.out.println("The property you have landed on is not a property square, and you cannot buy it.");
		}
	}

	public static void displayUserAsset() {
		System.out.println("--------------------------");
		System.out.println("Display the user's assets");
		System.out.println("Your balance is: " + players[currentPlayerturn - 1].getBalance() + " PCs");

		ArrayList<Integer> properties = new ArrayList<>();

		for (BoardSquare prop : board) {
			if (prop instanceof PropertySq) {
				if (((PropertySq) prop).getOwnerID() == (currentPlayerturn - 1)) {
					properties.add(prop.getSqID());
				}
			}
		}

		if (properties.isEmpty()) {
			System.out.println("You have no properties.");
		} else {
			int maxName = 0;
			for (Integer property : properties) {
				//System.out.println((i+1) + ". " + board[properties.get(i)].getName());
				if (board[property].getName().length() > maxName) {
					maxName = board[property].getName().length();
				}
			}
			maxName++;

			for (int i = 0; i < properties.size(); i++) {
				if (board[properties.get(i)] instanceof PropertySq) {
					String id = Integer.toString(board[properties.get(i)].getSqID());
					String name = board[properties.get(i)].getName();
					String field = board[properties.get(i)].getField();
					int value = ((PropertySq) board[properties.get(i)]).getBuyValue();
					int numHQ = ((PropertySq) board[properties.get(i)]).getDetails().getNumHq();
					int numOffice = ((PropertySq) board[properties.get(i)]).getNumOffices();
					boolean fullSet = (((PropertySq) board[properties.get(i)]).getFullSetOwned());

					while (id.length() < 3) {
						id += " ";
					}

					while (name.length() < maxName) {
						name += " ";
					}

					System.out.println((i + 1) + ". Name: " + name);
					System.out.println("   Property Field: " + field);
					System.out.println("   Value: " + value + " PCs");
					System.out.println("   No. offices on this property: " + numOffice + ", No. HQs on this property: " + numHQ);
					System.out.println("   Full Set Owned: " + fullSet);

				}
			}

		}

		System.out.println("--------------------------");

	}

	// sets the board squares
	public static void setBoardSq() throws IOException {
		try{
			Scanner sc = new Scanner(new File(pathname));
			String id_sq = "";
			String name_sq = "";
			String field_sq;

			// skips the first lines in the csv - titles
			sc.next();

			// loops data to be collected
			int loop = 1;
			// adds to the board array accordingly
			int counter = 0;

			sc.useDelimiter(",");   //sets the delimiter pattern

			while (sc.hasNext())  //returns a boolean value
			{
				if (loop == 1) {
					id_sq = sc.next();
					loop++;
				} else if (loop == 2) {
					name_sq = sc.next();
					loop++;
				} else if (loop == 3) {
					field_sq = sc.next();

					String noSpaceStr = id_sq.replaceAll("\\s", ""); // using built in method
					int id_to_add = Integer.parseInt(noSpaceStr);

					if (field_sq.equals("None")) {

						// power cut - nothing happens
						// press release - pay the bank x money
						// go - collect 250 Computers

						String action = switch (name_sq) {
							case "Go" -> "Collect 250 as you pass Go!";
							case "Power Cut" -> "There has been a power cut, you can't do anything this turn.";
							default -> "Chance";
						};

						//board[counter] = new SpecialSquare(id_to_add, name_sq, field_sq,action);
						addBoardSq("special", counter, id_to_add, name_sq, field_sq, action, 0);


					} else {
						// this is a property square

						// getting the price of each card
						String card_price = sc.next();
						noSpaceStr = card_price.replaceAll("\\s", ""); // using built in method
						int card_price_add = Integer.parseInt(noSpaceStr);

						addBoardSq("property", counter, id_to_add, name_sq, field_sq, null, card_price_add);
					}

					counter++;
					loop = 1;
				}

			}
			sc.close();  //closes the scanner

		} catch (Exception ex) {
			System.out.println("The CSV for the board squares could not be read. Please check it is in the correct format.");
		}


	}

	public static void addBoardSq(String type, int counter, int id, String name, String field, String action, int price){

		if(type.equals("special")){
			board[counter] = new SpecialSquare(id, name, field,action);
		} else if(type.equals("property")){

			// Rent Value is calculated as:
			// rent Val = [BASE RATE, FULL SET, 1 HOUSE, 2 HOUSE, 3 HOUSE, 4 HOUSE, HOTEL]
			// base rate = card price / 2
			// full set = card price
			// rent is 160% - one house
			// rent is 170% - two houses
			// rent is 180% - three houses
			// rent is 190% - four houses
			// rent is 200% - one hotel

			int[] rentVal = new int[]{(price / 2), price, (int) (price * 1.6), (int) (price * 1.7), (int) (price * 1.8), (int) (price * 1.9), (price * 2)};

			// BuildingInfo
			// houses/hotels cost 80% of buy value for that property
			BuildingInfo newInfo = new BuildingInfo();

			board[counter] = new PropertySq(id, name, field, price, rentVal, newInfo);

		}

	}
}
