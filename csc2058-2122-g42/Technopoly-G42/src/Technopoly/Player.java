package src.Technopoly;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	private int playerID;
	private String name;
	private int balance;
	private int currentSquareID;
	private boolean activePlayer;
	private int ranking;

	
	public Player(int newID, String newName){
		playerID = newID;
		name = newName;
		balance = 1000;
		// first square id is 0
		currentSquareID = 0;
		activePlayer = true;
		ranking = -1;
	}
	 
	//getters and setters

	public int getPlayerID() {
		return playerID;
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getCurrentSquareID() {
		return currentSquareID;
	}

	public void setCurrentSquareID(int currentSquareID) {
		this.currentSquareID = currentSquareID;
	}

	public boolean isActivePlayer() {
		return activePlayer;
	}

	public int getRanking() {
		return ranking;
	}

	// methods
	public boolean updateBalance(int value) {
		if(balance+value<0)
		{
			return false;
		}
		else
		{
			balance = balance + value;
			return true;
		}

	}

	public int calcTotalAsset(BoardSquare[] squares) {
		//System.out.println("Total Asset Calculated--NEEDS IMPLEMENTED");
		// get players balance

		int propertyVal =0;
		int assetsVal=0;

		// get players properties
		for(int i=0;i<squares.length;i++){
			if(squares[i] instanceof PropertySq){
				if(((PropertySq) squares[i]).getOwnerID()==playerID){
					// players owns the property
					// gets the buy price for the property
					propertyVal += ((PropertySq) squares[i]).getBuyValue();
					BuildingInfo info = ((PropertySq) squares[i]).getDetails();
					// gets the number of offices and their value
					assetsVal+= ((PropertySq) squares[i]).calcOfficePrice() * info.getNumOffice();
					// gets the number of hqs and their value
					assetsVal+= ((PropertySq) squares[i]).calcHQPrice() * info.getNumHq();
				}
			}
		}

		// add up to create total value
		return balance + assetsVal + propertyVal;
	}

	public void sellAsset(BoardSquare[] squares) {

		Scanner s = new Scanner(System.in);

		// check user has at least 1 property to sell
		int count = 0;
		for (BoardSquare board : squares) {
			if (board instanceof PropertySq)
				if (getPlayerID() == ((PropertySq) board).getOwnerID()) {
					count++;
				}
		}
		if (count >= 1) {

			// User input to select what they would like to sell
			System.out.println("What would you like to sell?\n1: Property\n2: Office\n3: HQ");
			String[] options = {"Property", "Office", "HQ"};
			int userChoice = 0;
			userChoice = s.nextInt();
			s.nextLine();

			switch (userChoice) {
				case 1:
					// check user owns property
					System.out.println("Which property would you like to sell?");
					//print property(s) owned by current player
					for (BoardSquare board : squares) {
						if (board instanceof PropertySq)
							if (getPlayerID() == ((PropertySq) board).getOwnerID()) {
								System.out.println(((PropertySq) board).getSqID() + ": " + board.getName());
							}
					}

					System.out.println("Enter the property number you wish to sell: ");
					int userChoices = s.nextInt();
					s.nextLine();
					// if they own property
					if (squares[userChoices] instanceof PropertySq) {

						if (((PropertySq) squares[userChoices]).getOwnerID() == getPlayerID()) {
							//check if they have any offices or hqs on the property
							// if no offices or hqs:
							if (((PropertySq) squares[userChoices]).getNumBuildings() == 0) {
								//return property to the bank and remove from the user's assets
								((PropertySq) squares[userChoices]).setOwnerID(-1);
								updateBalance(((PropertySq) squares[userChoices]).getBuyValue());
								System.out.println("You have sold a property.");
								System.out.println("Your balance is now: " + getBalance() + " PC's.");

								if (((PropertySq) squares[userChoices]).getFullSetOwned()){
									String fieldToSell = ((PropertySq) squares[userChoices]).getField();
									for(BoardSquare prop: squares){
										if(prop instanceof PropertySq){
											if(prop.getField().equals(fieldToSell)){
												((PropertySq) prop).setFullSetOwned(false);
											}
										}
									}
								}

							} else {
								// repeat choices
								System.out.println("You must sell Offices and HQs first.");
								sellAsset(squares);
							}
						} else {
							System.out.println("You don't own this property.");
						}
					} else {
						System.out.println("This is not a valid property.");
					}
					break;

				case 2:
					//find out which property they want to sell an office on
					int numPropertiesWithOffices = 0;

					for (BoardSquare square : squares) {
						if (square instanceof PropertySq) {
							if (((PropertySq) square).getOwnerID() == getPlayerID() && ((PropertySq) square).getDetails().getNumOffice() > 0) {
								numPropertiesWithOffices++;
								System.out.println(square.getSqID() + ": " + square.getName());
							}
						}
					}
					if (numPropertiesWithOffices > 0) {
						System.out.println("Enter the property number you wish to sell an office on: ");

						int userChoice1 = s.nextInt();
						s.nextLine();
						if (squares[userChoice1] instanceof PropertySq) {

							if (((PropertySq) squares[userChoice1]).getOwnerID() == getPlayerID()) {
								//check property has an office to sell
								if (((PropertySq) squares[userChoice1]).getOwnerID() == getPlayerID()) {
									if (((PropertySq) squares[userChoice1]).getDetails().getNumOffice() > 0) {
										//return property to the bank and remove from the user's assets
										((PropertySq) squares[userChoice1]).getDetails().setNumOffice(((PropertySq) squares[userChoice1]).getDetails().getNumOffice() - 1);
										updateBalance(((PropertySq) squares[userChoice1]).calcOfficePrice());
										System.out.println("You have sold an office.");
										System.out.println("Your balance is now: " + getBalance() + " PC's.");
									} else {
										System.out.println("There are no offices on this property.");
									}
								}
							} else {
								System.out.println("You don't own this property.");
							}
						} else {
							System.out.println("This is not a valid property.");
						}
					} else {
						System.out.println("You have no properties with offices.");
					}
					break;

				case 3:
					int numPropertiesWithHQs = 0;
					for (BoardSquare square : squares) {
						if (square instanceof PropertySq) {
							if (((PropertySq) square).getOwnerID() == getPlayerID() && ((PropertySq) square).getDetails().getNumHq() > 0) {
								numPropertiesWithHQs++;
								System.out.println(square.getSqID() + ": " + square.getName());
							}
						}
					}
					if (numPropertiesWithHQs > 0) {
						System.out.println("Enter the property number you wish to sell an HQ on: ");

						// find out which property they want to sell an office on
						//System.out.println("Please enter the number of the property you wish to sell an HQ from");
						userChoice = s.nextInt();
						s.nextLine();
						if (squares[userChoice] instanceof PropertySq) {

							//check property has an HQ to sell
							if (((PropertySq) squares[userChoice]).getOwnerID() == getPlayerID()) {
								if (((PropertySq) squares[userChoice]).getDetails().getNumHq() > 0) {
									//return property to the bank and remove from the user's assets
									((PropertySq) squares[userChoice]).getDetails().setNumHq(((PropertySq) squares[userChoice]).getDetails().getNumHq() - 1);
									updateBalance(((PropertySq) squares[userChoice]).calcHQPrice());
									System.out.println("You have sold an HQ.");
									System.out.println("Your balance is now: " + getBalance() + " PC's");
								} else {
									System.out.println("There are no HQ's on this property.");
								}

							} else {
								System.out.println("You don't own this property.");
							}
						} else {
							System.out.println("This is not a valid property.");
					}
					}else{
						System.out.println("You have no properties with HQs.");
					}
					break;
				}

		}else{
			System.out.println("You have nothing to sell.");
		}
	}

	public void declareBankruptcy(int position, BoardSquare[] board) {

		System.out.println("You declare bankruptcy and have been removed from the game");

		ArrayList<Integer> properties = new ArrayList<>();

		for (BoardSquare prop : board) {
			if (prop instanceof PropertySq) {
				if (((PropertySq) prop).getOwnerID() == (playerID)) {
					properties.add(prop.getSqID());

					// CONVERTS PROPERTIES LEFT TO MONEY
					int propertyVal = ((PropertySq) prop).getBuyValue();
					BuildingInfo info = ((PropertySq) prop).getDetails();
					int assetsVal =0;

					// gets the number of offices and their value
					assetsVal+= ((PropertySq) prop).calcOfficePrice() * info.getNumOffice();
					// gets the number of hqs and their value
					assetsVal+= ((PropertySq) prop).calcHQPrice() * info.getNumHq();

					balance += assetsVal;
					balance += propertyVal;

					// gives the property back to the bank
					((PropertySq) prop).setOwnerID(-1);
					((PropertySq) prop).setNumOffices(0);
					((PropertySq) prop).setNumHQs(0);

				}
			}
		}

		balance = 0;
		activePlayer = false;

		// Calculate ranking
		ranking = position;
	}

	public void declareBankruptcyEndGame(int position, BoardSquare[] board) {

		System.out.println("You declare bankruptcy and have been removed from the game");

		ArrayList<Integer> properties = new ArrayList<>();

		for (BoardSquare prop : board) {
			if (prop instanceof PropertySq) {
				if (((PropertySq) prop).getOwnerID() == (playerID)) {
					properties.add(prop.getSqID());

					// CONVERTS PROPERTIES LEFT TO MONEY
					int propertyVal = ((PropertySq) prop).getBuyValue();
					BuildingInfo info = ((PropertySq) prop).getDetails();
					int assetsVal =0;

					// gets the number of offices and their value
					assetsVal+= ((PropertySq) prop).calcOfficePrice() * info.getNumOffice();
					// gets the number of hqs and their value
					assetsVal+= ((PropertySq) prop).calcHQPrice() * info.getNumHq();

					balance += assetsVal;
					balance += propertyVal;

					// gives the property back to the bank
					((PropertySq) prop).setOwnerID(-1);
					((PropertySq) prop).setNumOffices(0);
					((PropertySq) prop).setNumHQs(0);

				}
			}
		}


		activePlayer = false;

		// Calculate ranking
		ranking = position;
	}
}
