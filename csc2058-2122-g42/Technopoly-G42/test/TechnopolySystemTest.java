package test;

import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;
import src.Technopoly.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static src.Technopoly.TechnopolySystem.*;

class TechnopolySystemTest {

    // 3.1
    @Test
    void testCheckNameExists_1() {
        System.out.println("+++ Test Case 3.1: Check Name Exists - Name doesn't exist  +++");

        Player[] testplayers = new Player[3];
        testplayers[0] = new Player(0, "Adam");
        testplayers[1] = new Player(1, "Olivia");

        boolean expected = false;
        boolean actual = TechnopolySystem.checkNameExists(testplayers, "Tom", 2);
        assertEquals(expected, actual);

        if(actual){
            System.out.println("The name does exist in the array - test Failed");
        }else{
            System.out.println("The name does not exist in the array - test Passed");
        }
        System.out.println("\n+++ End Test Case ++++++++++++++++++++++++\n");

    }
    // 3.2
    @Test
    void testCheckNameExists_2() {
        System.out.println("+++ Test Case 3.2: Check Name Exists - Name doesn't exist  +++");

        Player[] testplayers = new Player[3];
        testplayers[0] = new Player(0, "Adam");
        testplayers[1] = new Player(1, "Olivia");

        boolean expected = true;
        boolean actual = TechnopolySystem.checkNameExists(testplayers, "Olivia", 2);
        assertEquals(expected, actual);

        if(actual){
            System.out.println("The name does exist in the array - test Failed");
        }else{
            System.out.println("The name does not exist in the array - test Passed");
        }
        System.out.println("\n+++ End Test Case ++++++++++++++++++++++++\n");

    }
    // 3.3
    @Test
    void testCheckNameExists_3() {
        System.out.println("+++ Test Case 3.3: Check Name Exists - Name doesn't exist  +++");

        Player[] testplayers = new Player[3];

        boolean expected = false;
        boolean actual = TechnopolySystem.checkNameExists(testplayers, "Olivia", 0);
        assertEquals(expected, actual);

        if(actual){
            System.out.println("The name does exist in the array - test Failed");
        }else{
            System.out.println("The name does not exist in the array - test Passed");
        }
        System.out.println("\n+++ End Test Case ++++++++++++++++++++++++\n");

    }
    // 7
    @Test
    void testSetPlayerDetails() {
        System.out.println("+++ Test Case 7.1: Check Name Exists - Name doesn't exist  +++");

        Player liv = TechnopolySystem.setPlayerDetails(0,"Olivia");

        boolean expected = true;
        boolean actual = (liv.getName().equals("Olivia") && (liv.getPlayerID()==0));
        assertEquals(expected, actual);

        if(actual){
            System.out.println("The player was created successfully - test Passed");
        }else{
            System.out.println("The player was not created - test Failed");
        }
        System.out.println("\n+++ End Test Case ++++++++++++++++++++++++\n");

    }
    // 8
    @Test
    void testSetNumRounds() {
    }
    // 9
    @Test
    void testplayAgain() {
        //currentPlayerturn = 1;
        //maxRoundLimit = 10;
        //Player tim1 = new Player(0,"tim1");
        //Player tim2 = new Player(1,"tim2");

        //TechnopolySystem.players[0] = tim1;
        //TechnopolySystem.players[1] = tim2;
        //boolean validPlayAgain = true;
        //Scanner s = new Scanner(System.in);
        //TechnopolySystem.playAgain(s);

    }
    // 10
    @Test
    void testGamePlay() {
    }
    // 11.1
    @Test
    void testCalcTurn_1() {
        //Calculating the turn number when we are not at the maximum turn number for 2 players. i.e 1
        currentPlayerturn = 1;

        players = new Player[2];
        Player tim1 = new Player(0,"tim1");
        Player tim2 = new Player(1,"tim2");

        TechnopolySystem.players[0] = tim1;
        TechnopolySystem.players[1] = tim2;

        System.out.println("Turn number before method being tested: " + currentPlayerturn);
        TechnopolySystem.calcTurn();
        System.out.println("Turn number after method being tested: " + currentPlayerturn);

        assertEquals(2,currentPlayerturn);
    }
    // 11.2
    @Test
    void testCalcTurn_2() {
        //Calculating the turn number when we are at the maximum turn number for 2 players. i.e 2
        //When game round limit is not being reached

        currentPlayerturn = 2;
        currentRound = 0;
        maxRoundLimit = 10;
        players = new Player[2];

        Player tim1 = new Player(0,"tim1");
        Player tim2 = new Player(1,"tim2");

        players[0] = tim1;
        players[1] = tim2;

        System.out.println("Turn number before method being tested: " +  currentPlayerturn);
        System.out.println("Round number before method being tested: " + currentRound);
        TechnopolySystem.calcTurn();
        System.out.println("Turn number after method being tested: " + currentPlayerturn);
        System.out.println("Round number after method being tested: " + currentRound);
        assertTrue((currentRound == 1)&&currentPlayerturn == 1);

    }
    // 11.3
    @Test
    void testCalcTurn_3()  {
        //Calculating the turn number when we are at the last turn number for 2 players. i.e 1
        //When game round limit is being reached. Last turn of 10 is finished

        currentPlayerturn = 2;
        currentRound = 10;
        maxRoundLimit = 10;
        players = new Player[2];
        currentPosCounter = 2;


        Player tim1 = new Player(0,"tim1");
        Player tim2 = new Player(1,"tim2");

        players[0] = tim1;
        players[1] = tim2;

        System.out.println("Turn number before method being tested: " +  currentPlayerturn);
        System.out.println("Round number before method being tested: " + currentRound);
        calcTurn();
        if(checkEndGameStatus()){
            TechnopolySystem.endGame();
        }

        System.out.println("Turn number after method being tested: " + currentPlayerturn);
        System.out.println("Round number after method being tested: " + currentRound);
    }
    // 11.4
    @Test
    void testCalcTurn_4() throws IOException {
        //Calculating the turn number when player 2 is bankrupt and there are 3 players
        //Limits not reached
        //Player 1 finishing the turn and player 3 should be next
        currentPlayerturn = 1;
        currentRound = 1;
        maxRoundLimit = 10;
        players = new Player[4];
        TechnopolySystem.setBoardSq();

        Player tim1 = new Player(0,"tim1");
        Player tim2 = new Player(1,"tim2");
        tim2.declareBankruptcy(3,board);
        Player tim3 = new Player(2,"tim3");
        tim3.declareBankruptcy(2,board);
        Player tim4 = new Player(3, "tim4");

        players[0] = tim1;
        players[1] = tim2;
        players[2] = tim3;
        players[3] = tim4;

        System.out.println("Turn number before method being tested: " +  currentPlayerturn);
        System.out.println("Round number before method being tested: " + currentRound);
        calcTurn();
        System.out.println("Turn number after method being tested: " + currentPlayerturn);
        System.out.println("Round number after method being tested: " + currentRound);
        assertEquals(4,currentPlayerturn);

    }
    // 13
    @Test
    void testRollDice() {
        System.out.println("+++ Test Case 13.1: Roll dice +++");

        Player p1 = new Player(0, "Mark");

        System.out.println("Player 1 position before rolling dice:");
        System.out.println(p1.getCurrentSquareID());
        System.out.println("Dice roll 1:");
        int dice1 = TechnopolySystem.rollDice();
        System.out.println(dice1);
        System.out.println("Dice roll 2:");
        int dice2 = TechnopolySystem.rollDice();
        System.out.println(dice2);
        System.out.println("Total spaces moved:");
        int moves = dice1 + dice2;
        System.out.println(moves);
    }
    // 14
    @Test
    void testEndGame() {
        System.out.println("+++ Test Case 14: Endgame +++");

        currentRound = 10;
        players = new Player[2];
        currentPosCounter = 2;
        Player tim1 = new Player(0,"tim1");
        Player tim2 = new Player(1,"tim2");
        players[0] = tim1;
        players[1] = tim2;

        TechnopolySystem.endGame();
    }
    // 15.1
    @Test
    void checkEndGameStatusMaxTurn() {
        System.out.println("+++ Test Case 15.1: Check endgame status +++");

        maxRoundLimit = 10;
        currentRound = 11;
        players = new Player[2];
        currentPosCounter = 2;
        Player tim1 = new Player(0,"tim1");
        Player tim2 = new Player(1,"tim2");
        players[0] = tim1;
        players[1] = tim2;

        System.out.println("Check Endgame - " + TechnopolySystem.checkEndGameStatus());
        assertEquals(true, endGame);
    }
    // 15.2
    @Test
    void checkEndGameStatusFinalPlayer() throws IOException {
        System.out.println("+++ Test Case 15.2: Check endgame status +++");
        currentRound = 1;
        players = new Player[2];
        currentPosCounter = 2;
        maxRoundLimit = 2;
        endGame = false;
        TechnopolySystem.setBoardSq();

        Player tim1 = new Player(0,"tim1");
        Player tim2 = new Player(1,"tim2");
        players[0] = tim1;
        players[1] = tim2;

        System.out.println("Check Endgame - " + TechnopolySystem.checkEndGameStatus());
        assertEquals(false, endGame);

        tim1.declareBankruptcy(3, board );
        System.out.println("Check Endgame - " + TechnopolySystem.checkEndGameStatus());
        assertEquals(true, endGame);

    }
    // 23
    @Test
    void testCalcDebts_1() {
        System.out.println("+++ Test Case 23.1: calcDebts - Sufficient funds +++");

        Player p1 = new Player(0, "Mark");
        int rentOwed = 200;

        System.out.println("Player balance: " + p1.getBalance());
        System.out.println("Debt owed: " + rentOwed);

        if(calcDebts(rentOwed, p1)){
            System.out.println("CalcDebts returns true");
            System.out.println("Test passed");
        }
        else{
            System.out.println("Test Failed");
        }
    }
    // 24.1
    @Test
    void testTakeOptionalAction() throws IOException {
        System.out.println("+++ Test Case 24 : Take Optional Action +++");

        Player Lauren = new Player(0,"Lauren");
        TechnopolySystem.setBoardSq();

        int currentSqId = 1;

        if (TechnopolySystem.board[currentSqId] instanceof PropertySq) {
            if (((PropertySq) TechnopolySystem.board[currentSqId]).getOwnerID() == -1) {
                // if the sq is unowned - displays the price
                System.out.println("The property you have landed is available to buy and costs: " + ((PropertySq) board[currentSqId]).getBuyValue() + " PCs");
                System.out.println("Your current balance is: " + Lauren.getBalance() + "PCs");
            }
        }

        System.out.println("--------------------------");
        System.out.println("Player enters 1: ");

        if (board[currentSqId] instanceof PropertySq) {
            if ((((PropertySq) board[currentSqId]).getOwnerID()) == -1) {
                if (Lauren.updateBalance(-((PropertySq) board[currentSqId]).getBuyValue())) {
                    ((PropertySq) board[currentSqId]).setOwnerID(Lauren.getPlayerID());
                    System.out.println("You have bought a property");
                    System.out.println("Your current balance is: " + Lauren.getBalance() + "PCs");
                }
            }
        }
        System.out.println("--------------------------");

        System.out.println("Player enters 2: ");
        if (((PropertySq)TechnopolySystem.board[1]).getNumBuildings() == 0) {
            //return property to the bank and remove from the user's assets
            ((PropertySq)TechnopolySystem.board[1]).setOwnerID(-1);
            Lauren.updateBalance(((PropertySq)TechnopolySystem.board[1]).getBuyValue());
            System.out.println("You have sold a property.");
            System.out.println("Your balance is now: " + Lauren.getBalance() + " PC's.");
        }
        System.out.println("--------------------------");

        System.out.println("Player enters 3: ");
        boolean available = false;
        //lists all properties that you can buy a house or hotel on
        for (int i = 0; i < 23; i++) {
            if (board[i] instanceof PropertySq) {
                if ((((PropertySq) board[1]).getOwnerID()) == Lauren.getPlayerID()) {
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
                        if (((PropertySq) place).getOwnerID() != Lauren.getPlayerID()) {
                            System.out.println(place.getName());
                        }
                    }
                }
            }
        }

        System.out.println("---------------------------");

        System.out.println("Player enters 4:");
        System.out.println("---------------------------");
        System.out.println("Display the user's assets");
        System.out.println("Your balance is: " + Lauren.getBalance() + " PCs");

        ArrayList<Integer> properties = new ArrayList<>();

        for (BoardSquare prop : board) {
            if (prop instanceof PropertySq) {
                if (((PropertySq) prop).getOwnerID() == Lauren.getPlayerID()) {
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
        System.out.println("-------------------------");
        System.out.println("-------------------------");

        System.out.println("Player enters 5: ");
        System.out.println("User ends their turn.");
        System.out.println("-------------------------");

        System.out.println("Player enters 6: ");
        System.out.println("The user has chosen option: *" + "6" + "*");
        System.out.println("-------------------------");

    }
    // 25
    @Test
    void buyBuildingTestTest() throws IOException {
        System.out.println("Tests to see if full set is owned");
        Player[] testplayers = new Player[2];
        testplayers[0] = new Player(0, "Adam");
        TechnopolySystem.setBoardSq();
        ((PropertySq)TechnopolySystem.board[1]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[2]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[1]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[2]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[1]).setFullSetOwned(true);
        ((PropertySq)TechnopolySystem.board[2]).setFullSetOwned(true);
        assertTrue(TechnopolySystem.buyBuildingTest(1) && !TechnopolySystem.buyBuildingTest(5));
    }
    // 26
    @Test
    void testBuyBuildingWith1Current()  throws IOException{
        System.out.println("Test for buying a building with one current buildings on the square");
        Player[] testplayers = new Player[2];
        testplayers[0] = new Player(0, "Adam");
        TechnopolySystem.setBoardSq();
        ((PropertySq)TechnopolySystem.board[1]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[2]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[1]).setFullSetOwned(true);
        ((PropertySq)TechnopolySystem.board[2]).setFullSetOwned(true);
        ((PropertySq)TechnopolySystem.board[1]).setNumOffices(1);
        TechnopolySystem.buyBuilding(1);
        assertTrue(((PropertySq)TechnopolySystem.board[1]).getNumOffices() == 2);
    }
    // 26
    @Test
    void testBuyBuildingHQ2()  throws IOException{
        System.out.println("Test for buying a building with 4 current buildings on the square");
        TechnopolySystem.setBoardSq();
        Player[] testplayers = new Player[2];
        testplayers[0] = new Player(0, "Adam");
        ((PropertySq)TechnopolySystem.board[1]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[2]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[1]).setFullSetOwned(true);
        ((PropertySq)TechnopolySystem.board[2]).setFullSetOwned(true);
        ((PropertySq)TechnopolySystem.board[1]).setNumOffices(4);
        TechnopolySystem.buyBuilding(1);
        assertTrue(((PropertySq)TechnopolySystem.board[1]).getNumHQs() == 1);
    }
    // 26
    @Test
    void testBuyFirstOffice() throws IOException{
        System.out.println("Test for buying a building with zero current buildings on the square");
        Player[] testplayers = new Player[2];
        testplayers[0] = new Player(0, "Adam");
        TechnopolySystem.setBoardSq();
        ((PropertySq)TechnopolySystem.board[1]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[2]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[1]).setFullSetOwned(true);
        ((PropertySq)TechnopolySystem.board[2]).setFullSetOwned(true);
        TechnopolySystem.buyBuilding(1);
        assertTrue(((PropertySq)TechnopolySystem.board[1]).getNumOffices() == 1);
    }
    // 27
    @Test
    void testBuyProperty() throws IOException {
        maxRoundLimit = 10;
        currentRound = 11;
        players = new Player[2];
        currentPosCounter = 2;
        endGame = false;
        currentPlayerturn = 2;
        TechnopolySystem.setBoardSq();
        Player tim1 = new Player(0,"tim1");
        Player tim2 = new Player(1,"tim2");
        players[0] = tim1;
        players[1] = tim2;

        TechnopolySystem.buyProperty(4);
        assertEquals(tim2.getPlayerID(),((PropertySq)(board[4])).getOwnerID());
    }
    // 28.1
    @Test
    void testDisplayUserAsset_1() throws IOException {
        System.out.println("+++ Test Case 28.1: Display User Assets - no Assets to display +++");

        Player olivia = new Player(0,"olivia");
        Player adam = new Player(1,"adam");

        TechnopolySystem.setBoardSq();

        TechnopolySystem.currentPlayerturn = 1;
        TechnopolySystem.players = new Player[2];
        TechnopolySystem.players[0] = olivia;
        TechnopolySystem.players[1] = adam;


        // should display nothing
        System.out.println("Player: " + TechnopolySystem.players[currentPlayerturn-1].getName());
        TechnopolySystem.displayUserAsset();
        System.out.println("\n+++ End Test Case ++++++++++++++++++++++++\n");

    }
    // 28.2
    @Test
    void testDisplayUserAsset_2() throws IOException {
        System.out.println("+++ Test Case 28.2: Display User Assets - Full Set Owned with Assets +++");

        Player olivia = new Player(0,"olivia");
        Player adam = new Player(1,"adam");

        TechnopolySystem.setBoardSq();

        TechnopolySystem.currentPlayerturn = 1;
        TechnopolySystem.players[0] = olivia;
        TechnopolySystem.players[1] = adam;

        ((PropertySq)TechnopolySystem.board[1]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[2]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[1]).setFullSetOwned(true);
        ((PropertySq)TechnopolySystem.board[2]).setFullSetOwned(true);

        ((PropertySq)TechnopolySystem.board[1]).setNumOffices(1);
        ((PropertySq)TechnopolySystem.board[2]).setNumOffices(2);

        olivia.updateBalance(-((PropertySq)TechnopolySystem.board[1]).calcOfficePrice());
        olivia.updateBalance(-((PropertySq)TechnopolySystem.board[2]).calcOfficePrice());
        olivia.updateBalance(-((PropertySq)TechnopolySystem.board[2]).calcOfficePrice());

        // should display 2 properties
        System.out.println("Player: " + TechnopolySystem.players[currentPlayerturn-1].getName());
        TechnopolySystem.displayUserAsset();
        System.out.println("\n+++ End Test Case ++++++++++++++++++++++++\n");

    }
    // 18
    @Test
    void testGainMoney() {
        System.out.println("Test for chance where player gains money");
        Player[] testplayers = new Player[1];
        testplayers[0] = new Player(0, "Adam");
        System.out.println("Balance Before Chance: "+testplayers[0].getBalance());
        int testVal = testplayers[0].getBalance();
        System.out.println("After Chance");
        TechnopolySystem.performChance(1, testplayers, 0);
        assertTrue(testplayers[0].getBalance() >= 1100 && testplayers[0].getBalance() <= 1500);
    }
    // 18
    @Test
    void testLooseMoney() {
        System.out.println("Test for chance where player looses money");
        Player[] testplayers = new Player[1];
        testplayers[0] = new Player(0, "Adam");
        System.out.println("Balance Before Chance: "+testplayers[0].getBalance());
        int testVal = testplayers[0].getBalance();
        System.out.println("After Chance");
        TechnopolySystem.performChance(2, testplayers, 0);
        assertTrue(testplayers[0].getBalance() >= 500 && testplayers[0].getBalance() <= 900);
    }
    // 18
    @Test
    void testLooseMoneyPerPlayer() {
        System.out.println("Test for chance where player gains money from every player");
        Player[] testplayers = new Player[2];
        testplayers[0] = new Player(0, "Adam");
        testplayers[1] = new Player(1, "Joe");
        System.out.println("Adam's Balance Before Chance: "+testplayers[0].getBalance());
        System.out.println("Joe's Balance Before Chance: "+testplayers[1].getBalance());
        int testVal = testplayers[0].getBalance();
        System.out.println("After Chance");
        TechnopolySystem.performChance(3, testplayers, 0);
        assertTrue(testplayers[0].getBalance() >= 1050 && testplayers[0].getBalance() <= 1250 && testplayers[1].getBalance() <= 950 && testplayers[1].getBalance() >= 750);
        System.out.println("Joe's Balance: " + testplayers[1].getBalance());
    }
    // 18
    @Test
    void testChanceMovePlayer() {
        Player p1 = new Player(0, "Mark");

        TechnopolySystem.currentPlayerturn = 1;
        TechnopolySystem.players[0] = p1;

        p1.setCurrentSquareID(6);

        System.out.println("Starting square: " + p1.getCurrentSquareID());

        int currentSquare = p1.getCurrentSquareID();
        TechnopolySystem.chanceMovePlayer();
        int newSquare = p1.getCurrentSquareID();
        System.out.println("New square: " + newSquare);
        assertTrue(currentSquare != newSquare);
    }
    // 29.1
    @Test
    void testSetBoardSq() throws IOException {
        System.out.println("+++ Test Case 29.1: Board for Game is initialised properly  +++");

        System.out.println("---Board before initialisation---");
        for(int i=0;i<23;i++){
            if(TechnopolySystem.board[i]!=null){
                System.out.println("Name: " + TechnopolySystem.board[i].getName() + ", Class: " + TechnopolySystem.board[i].getClass());
            } }
        System.out.println("---------------------------------");
        TechnopolySystem.setBoardSq();

        System.out.println("--- Board after initialisation---");
        for(int i=0;i<23;i++){
            if(TechnopolySystem.board[i]!=null){
                System.out.println("Name: " + TechnopolySystem.board[i].getName() + ", Class: " + TechnopolySystem.board[i].getClass());
            }
        }
        System.out.println("---------------------------------");

        System.out.println("\n+++ End Test Case ++++++++++++++++++++++++\n");

    }
    // 29.2
    @Test
    void testAddBoardSq_1(){
        System.out.println("+++ Test Case 29.2: Board for Game is initialised properly  +++");

        TechnopolySystem.addBoardSq("special", 0,0,"Go!", "None", "Collect 250 PCs as you pass Go!",0);

        // display details of that board position and if it is an instance of special sq
        System.out.println("Class of the board[0] = " + board[0].getClass());

        boolean classCorr = true;
        if(board[0] instanceof SpecialSquare){
            classCorr = true;
        } else{
            classCorr = false;
        }

        assertEquals(true,classCorr);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    }
    // 29.3
    @Test
    void testAddBoardSq_2(){
        System.out.println("+++ Test Case 29.3: Board for Game is initialised properly  +++");

        TechnopolySystem.addBoardSq("property", 1,1,"WAN", "Networks", "",60);

        // display details of that board position and if it is an instance of property sq

        // display details of that board position and if it is an instance of special sq
        System.out.println("Class of the board[1] = " + board[1].getClass());

        boolean classCorr = true;
        if(board[1] instanceof PropertySq){
            classCorr = true;
        } else{
            classCorr = false;
        }

        assertEquals(true,classCorr);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    }
    // 30.1
    @Test
    void testCountPlayerProperties_1() throws IOException {
        // if player properties are 0
        System.out.println("+++ Test Case 30.1: Count Number of Properties a Player Owns  +++");

        TechnopolySystem.setBoardSq();

        Player[] testplayers = new Player[2];
        testplayers[0] = new Player(0, "Adam");
        testplayers[1] = new Player(1, "Olivia");
        TechnopolySystem.players = testplayers;

        int numProps = TechnopolySystem.countPlayerProperties(0);

        System.out.println("The number of properties that " + TechnopolySystem.players[0].getName() + " owns is: " + numProps);
        assertEquals(0,numProps);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    }
    // 30.2
    @Test
    void testCountPlayerProperties_2() throws IOException {
        // if player owns 2 properties
        System.out.println("+++ Test Case 30.2: Count Number of Properties a Player Owns  +++");

        TechnopolySystem.setBoardSq();

        Player[] testplayers = new Player[2];
        testplayers[0] = new Player(0, "Adam");
        testplayers[1] = new Player(1, "Olivia");
        TechnopolySystem.players = testplayers;

        // set player ID = 0 to own properties
        ((PropertySq)TechnopolySystem.board[1]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[1]).setFullSetOwned(true);
        ((PropertySq)TechnopolySystem.board[2]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[2]).setFullSetOwned(true);

        int numProps = TechnopolySystem.countPlayerProperties(0);

        System.out.println("The number of properties that " + TechnopolySystem.players[0].getName() + " owns is: " + numProps);
        assertEquals(2,numProps);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    // 31.1
    @Test
    void testOwnsFullSet_1() throws IOException {
        // if player owns full set
        System.out.println("+++ Test Case 31.1: Player Owns a Full Set  +++");

        TechnopolySystem.setBoardSq();

        Player[] testplayers = new Player[2];
        testplayers[0] = new Player(0, "Adam");
        testplayers[1] = new Player(1, "Olivia");
        TechnopolySystem.players = testplayers;

        // set player ID = 0 to own properties
        ((PropertySq)TechnopolySystem.board[1]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[1]).setFullSetOwned(true);
        ((PropertySq)TechnopolySystem.board[2]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[2]).setFullSetOwned(true);

        boolean ownsFullSet = TechnopolySystem.ownsFullSet(0);

        System.out.println("Player " + TechnopolySystem.players[0].getName() + " owns a full set: " + ownsFullSet);
        assertEquals(true,ownsFullSet);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    // 31.2
    @Test
    void testOwnsFullSet_2() throws IOException {
        // if player does not own full set
        System.out.println("+++ Test Case 31.2: Player does not own a Full Set  +++");

        TechnopolySystem.setBoardSq();

        Player[] testplayers = new Player[2];
        testplayers[0] = new Player(0, "Adam");
        testplayers[1] = new Player(1, "Olivia");
        TechnopolySystem.players = testplayers;

        // set player ID = 0 to own properties
        ((PropertySq)TechnopolySystem.board[1]).setOwnerID(0);

        boolean ownsFullSet = TechnopolySystem.ownsFullSet(0);

        System.out.println("Player " + TechnopolySystem.players[0].getName() + " owns a full set: " + ownsFullSet);
        assertEquals(false,ownsFullSet);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
