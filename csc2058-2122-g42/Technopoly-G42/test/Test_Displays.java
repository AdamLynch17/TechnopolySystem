package test;


import org.junit.jupiter.api.Test;
import src.Technopoly.Player;
import src.Technopoly.PropertySq;
import src.Technopoly.TechnopolySystem;

import java.io.IOException;
import java.util.Scanner;

import static src.Technopoly.TechnopolySystem.*;
import static src.Technopoly.TechnopolySystem.gamePlay;

public class Test_Displays {

    public static void main(String[] args) throws IOException {
        //testResetBoard();
        //testCalcStats();
        //testDisplayStats();
        //gamePlay_2();
        //testPerformTurn_1();
        //testPerformTurn_2();
        //testCheckSquare_1();
        //testCheckSquare_2();
        //testCheckSquare_3();
        //testCheckSquare_4();
        //testCalcDebts_2();
        //testCalcDebts_3();
        //testResetBoard();
        //testTakeOptionalAction_1();
        //testTakeOptionalAction_2();
        //testTakeOptionalAction_3();
        //testTakeOptionalAction_4();
        //testTakeOptionalAction_5();

    }
    // 2
    static void testResetBoard() throws IOException {
        System.out.println("+++ Test Case 2: Board for Game is initialised properly  +++");

        Player liv = new Player(0,"liv");
        Player lucy = new Player(1,"lucy");
        TechnopolySystem.players = new Player[2];
        TechnopolySystem.players[0] = liv;
        TechnopolySystem.players[1] = lucy;

        // sets the board
        TechnopolySystem.setBoardSq();
        // example of changing

        // full properties owned for Liv
        ((PropertySq)TechnopolySystem.board[3]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[3]).setFullSetOwned(true);
        ((PropertySq)TechnopolySystem.board[3]).setNumOffices(1);
        ((PropertySq)TechnopolySystem.board[4]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[4]).setFullSetOwned(true);
        ((PropertySq)TechnopolySystem.board[5]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[5]).setFullSetOwned(true);

        TechnopolySystem.players[0].setBalance(630);

        // full properties owned for Lucy
        ((PropertySq)TechnopolySystem.board[9]).setOwnerID(1);
        TechnopolySystem.players[1].setBalance(840);

        // display user assets before and after
        System.out.println("--------USER ASSETS BEFORE -----------");
        TechnopolySystem.currentPlayerturn = 1;
        TechnopolySystem.displayUserAsset();
        TechnopolySystem.currentPlayerturn = 2;
        TechnopolySystem.displayUserAsset();
        System.out.println("---------------------------------------");

        Scanner s = new Scanner(System.in);
        TechnopolySystem.playAgainSetVariables(s);
        s.close();

        // after
        System.out.println("--------USER ASSETS AFTER -----------");
        TechnopolySystem.currentPlayerturn = 1;
        TechnopolySystem.displayUserAsset();
        TechnopolySystem.currentPlayerturn = 2;
        TechnopolySystem.displayUserAsset();
        System.out.println("---------------------------------------");


        System.out.println("\n+++ End Test Case ++++++++++++++++++++++++\n");

    }
    // 4
    static void testStartGame() {
        System.out.println("+++ Test Case 4: test overall input and output for StartGame +++");
        Scanner s = new Scanner(System.in);
        TechnopolySystem.startGame(s);
    }
    // 5
    static void testSetNumPlayers() {
        System.out.println("+++ Test Case 5: Test input for number of players +++");
        System.out.println("---Before---");
        System.out.println("Number of players: " + TechnopolySystem.players.length);
        Scanner s = new Scanner(System.in);
        int numPlayers = TechnopolySystem.setNumPlayers(s);
        TechnopolySystem.players = new Player[numPlayers];
        s.close();
        System.out.println("--- After ----");
        System.out.println("Number of players:" + TechnopolySystem.players.length);
    }
    // 6
    static void testSetPlayerNames() {
        System.out.println("+++ Test Case 6: Test input for Player Names +++");
        Scanner s = new Scanner(System.in);
        int numPlayers = 3;
        TechnopolySystem.players = new Player[numPlayers];
        TechnopolySystem.setPlayerNames(s, numPlayers);
        System.out.println("--- After ---");
        System.out.println("Player names: \n");
    }
    // 8
    static void testSetNumRounds() {
        System.out.println("+++ Test Case 8: Test input for number of rounds +++");
        System.out.println("---- Before---");
        System.out.println("Max round limit: " + TechnopolySystem.maxRoundLimit);
        Scanner s = new Scanner(System.in);
        TechnopolySystem.setNumRounds(s);
        s.close();
        System.out.println("---- After---");
        System.out.println("Max round limit: " + TechnopolySystem.maxRoundLimit);
    }
    // 10.1
    static void gamePlay_1() throws IOException {
        //To test if the gameplay method will correctly call other methods within in
        //providing normal parameters with keep the game running
        //Calculating the turn number when we are not at the maximum turn number for 2 players. i.e 1
        currentPlayerturn = 1;
        Player tim1 = new Player(0,"tim1");
        Player tim2 = new Player(1,"tim2");
        TechnopolySystem.players[0] = tim1;
        TechnopolySystem.players[1] = tim2;
        maxRoundLimit = 10;
        currentRound = 1;
        setBoardSq();
        Scanner s = new Scanner(System.in);
        endGame = false;
        gamePlay(s);
        //gamePlay_1();

    }
    // 10.2
    static void gamePlay_2() throws IOException {
        //To test if gameplay loop with stop if the endgame boolean is true
        currentPlayerturn = 2;
        Player tim1 = new Player(0,"tim1");
        Player tim2 = new Player(1,"tim2");
        TechnopolySystem.players = new Player[2];
        TechnopolySystem.players[0] = tim1;
        TechnopolySystem.players[1] = tim2;
        maxRoundLimit = 5;
        currentRound = 5;
        currentPosCounter = 2;
        setBoardSq();
        Scanner s = new Scanner(System.in);
        TechnopolySystem.setBoardSq();
        Player p1 = new Player(0, "Mark");
        Player p2 = new Player(0, "Tim");
        TechnopolySystem.maxRoundLimit = 2;
        TechnopolySystem.currentRound = 1;
        TechnopolySystem.currentPlayerturn = 1;
        TechnopolySystem.players[0] = p1;
        TechnopolySystem.players[1] = p2;
        p1.setCurrentSquareID(23);
        endGame = true;
        gamePlay(s);
    }
    // 12.1
    static void testPerformTurn_1() throws IOException {
        System.out.println("+++ Test Case 12.1: Perform Turn - without passing Go +++");

        TechnopolySystem.players = new Player[2];
        Scanner s = new Scanner(System.in);
        TechnopolySystem.setBoardSq();
        Player p1 = new Player(0, "Mark");
        Player p2 = new Player(0, "Tim");
        TechnopolySystem.maxRoundLimit = 2;
        TechnopolySystem.currentRound = 1;
        TechnopolySystem.currentPlayerturn = 1;
        TechnopolySystem.players[0] = p1;
        TechnopolySystem.players[1] = p2;

        System.out.println("Starting square: " + p1.getCurrentSquareID());

        TechnopolySystem.performTurn(s);

        System.out.println("New square: " + p1.getCurrentSquareID());
        s.close();
    }
    // 12.2
    static void testPerformTurn_2() throws IOException {
        System.out.println("+++ Test Case 12.2: Perform Turn - Passing Go +++");

        TechnopolySystem.players = new Player[2];
        Scanner s = new Scanner(System.in);
        TechnopolySystem.setBoardSq();
        Player p1 = new Player(0, "Mark");
        Player p2 = new Player(0, "Tim");
        TechnopolySystem.maxRoundLimit = 2;
        TechnopolySystem.currentRound = 1;
        TechnopolySystem.currentPlayerturn = 1;
        TechnopolySystem.players[0] = p1;
        TechnopolySystem.players[1] = p2;
        p1.setCurrentSquareID(23);

        System.out.println("Starting square: " + p1.getCurrentSquareID());

        TechnopolySystem.performTurn(s);

        System.out.println("New square: " + p1.getCurrentSquareID());
        s.close();
    }
    // 12.3
    static void testCheckSquare_1() throws IOException {
        System.out.println("+++ Test Case 12.3: Check Square - Property Square +++");

        Scanner s = new Scanner(System.in);

        TechnopolySystem.setBoardSq();
        Player p1 = new Player(0, "Mark");
        Player p2 = new Player(1, "Mark2");

        TechnopolySystem.players = new Player[2];

        TechnopolySystem.currentPlayerturn = 1;
        TechnopolySystem.players[0] = p1;
        TechnopolySystem.players[1] = p2;

        p1.setCurrentSquareID(0);

        System.out.println("Current square: "+p1.getCurrentSquareID());
        int newSquare = 1;

        System.out.println("New square:" +newSquare);

        TechnopolySystem.checkSquare(newSquare, s);

    }
    // 12.4
    static void testCheckSquare_2() throws IOException {
        System.out.println("+++ Test Case 12.4: Check Square - Property Square - Square Owned +++");

        Scanner s = new Scanner(System.in);

        TechnopolySystem.setBoardSq();
        Player p1 = new Player(0, "Mark");
        Player p2 = new Player(1, "Mark2");

        TechnopolySystem.players = new Player[2];

        TechnopolySystem.currentPlayerturn = 1;
        TechnopolySystem.players[0] = p1;
        TechnopolySystem.players[1] = p2;

        p1.setCurrentSquareID(0);

        System.out.println("Current square: "+p1.getCurrentSquareID());
        int newSquare = 1;
        ((PropertySq) board[newSquare]).setOwnerID(1);

        System.out.println("New square:" +newSquare);

        TechnopolySystem.checkSquare(newSquare, s);

    }
    // 12.5
    static void testCheckSquare_3() throws IOException {
        System.out.println("+++ Test Case 12.5: Check Square - Special Square - Press Release +++");
        Scanner s = new Scanner(System.in);

        TechnopolySystem.setBoardSq();
        Player p1 = new Player(0, "Mark");
        Player p2 = new Player(1, "Mark2");

        TechnopolySystem.players = new Player[2];

        TechnopolySystem.currentPlayerturn = 1;
        TechnopolySystem.players[0] = p1;
        TechnopolySystem.players[1] = p2;

        p1.setCurrentSquareID(0);

        System.out.println("Current square: "+p1.getCurrentSquareID());
        int newSquare = 6;

        System.out.println("New square:" +newSquare);

        TechnopolySystem.checkSquare(newSquare, s);

    }
    // 12.6
    static void testCheckSquare_4() throws IOException {
        System.out.println("+++ Test Case 12.6: Check Square - Special Square - Power Cut +++");
        Scanner s = new Scanner(System.in);

        TechnopolySystem.setBoardSq();
        Player p1 = new Player(0, "Mark");
        Player p2 = new Player(1, "Mark2");

        TechnopolySystem.players = new Player[2];

        TechnopolySystem.currentPlayerturn = 1;
        TechnopolySystem.players[0] = p1;
        TechnopolySystem.players[1] = p2;

        p1.setCurrentSquareID(0);

        System.out.println("Current square: "+p1.getCurrentSquareID());
        int newSquare = 12;

        System.out.println("New square:" +newSquare);

        TechnopolySystem.checkSquare(newSquare, s);

    }
    // 16
    static void testDisplayStats(){
        System.out.println("+++ Test Case 16: Display Stats through leaderboard  +++");

        TechnopolySystem.players = new Player[3];
        TechnopolySystem.players[0] = new Player(0,"Olivia");
        TechnopolySystem.players[0].setBalance(700);
        TechnopolySystem.players[1] = new Player(1,"Lucy");
        TechnopolySystem.players[1].setBalance(1200);
        TechnopolySystem.players[2] = new Player(2,"Adam");
        TechnopolySystem.players[2].setBalance(950);

        TechnopolySystem.currentPosCounter = 3;

        System.out.println("--------Rankings before --------");
        System.out.println("Player 1: " + TechnopolySystem.players[0].getName() + ", Ranking: " + TechnopolySystem.players[0].getRanking() + ", Active Player: "+ TechnopolySystem.players[0].isActivePlayer() + ", Balance: " + TechnopolySystem.players[0].getBalance());
        System.out.println("Player 2: " + TechnopolySystem.players[1].getName() + ", Ranking: " + TechnopolySystem.players[1].getRanking() + ", Active Player: "+ TechnopolySystem.players[1].isActivePlayer() + ", Balance: " + TechnopolySystem.players[1].getBalance());
        System.out.println("Player 3: " + TechnopolySystem.players[2].getName() + ", Ranking: " + TechnopolySystem.players[2].getRanking() + ", Active Player: "+ TechnopolySystem.players[2].isActivePlayer() + ", Balance: " + TechnopolySystem.players[2].getBalance());

        System.out.println();
        TechnopolySystem.displayStats();

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");


    }
    // 17
    static void testCalcStats() {
        System.out.println("+++ Test Case 17: Test Calculating Stats  +++");

        TechnopolySystem.players = new Player[3];
        TechnopolySystem.players[0] = new Player(0,"Olivia");
        TechnopolySystem.players[0].setBalance(700);
        TechnopolySystem.players[1] = new Player(1,"Lucy");
        TechnopolySystem.players[1].setBalance(1200);
        TechnopolySystem.players[2] = new Player(2,"Adam");
        TechnopolySystem.players[2].setBalance(950);

        TechnopolySystem.currentPosCounter = 3;

        System.out.println("--------Rankings before --------");
        System.out.println("Player 1: " + TechnopolySystem.players[0].getName() + ", Ranking: " + TechnopolySystem.players[0].getRanking() + ", Active Player: "+ TechnopolySystem.players[0].isActivePlayer() + ", Balance: " + TechnopolySystem.players[0].getBalance());
        System.out.println("Player 2: " + TechnopolySystem.players[1].getName() + ", Ranking: " + TechnopolySystem.players[1].getRanking() + ", Active Player: "+ TechnopolySystem.players[1].isActivePlayer() + ", Balance: " + TechnopolySystem.players[1].getBalance());
        System.out.println("Player 3: " + TechnopolySystem.players[2].getName() + ", Ranking: " + TechnopolySystem.players[2].getRanking() + ", Active Player: "+ TechnopolySystem.players[2].isActivePlayer() + ", Balance: " + TechnopolySystem.players[2].getBalance());

        // should get players ranking for correct place
        System.out.println();
        TechnopolySystem.calcStats();
        System.out.println();

        System.out.println("--------Rankings after --------");
        System.out.println("Player 1: " + TechnopolySystem.players[0].getName() + ", Ranking: " + TechnopolySystem.players[0].getRanking() + ", Active Player: "+ TechnopolySystem.players[0].isActivePlayer() + ", Balance: " + TechnopolySystem.players[0].getBalance());
        System.out.println("Player 2: " + TechnopolySystem.players[1].getName() + ", Ranking: " + TechnopolySystem.players[1].getRanking() + ", Active Player: "+ TechnopolySystem.players[1].isActivePlayer() + ", Balance: " + TechnopolySystem.players[1].getBalance());
        System.out.println("Player 3: " + TechnopolySystem.players[2].getName() + ", Ranking: " + TechnopolySystem.players[2].getRanking() + ", Active Player: "+ TechnopolySystem.players[2].isActivePlayer() + ", Balance: " + TechnopolySystem.players[2].getBalance());

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");


    }
    // 23.1
    static void testCalcDebts_2() throws IOException {
        System.out.println("+++ Test Case 23.2: calcDebts - Insufficient funds with property +++");

        TechnopolySystem.setBoardSq();
        Scanner s = new Scanner(System.in);

        TechnopolySystem.setBoardSq();
        Player p1 = new Player(0, "Mark");

        TechnopolySystem.players = new Player[1];

        TechnopolySystem.currentPlayerturn = 1;
        TechnopolySystem.players[0] = p1;

        int rentOwed = 950;
        TechnopolySystem.buyProperty(1);
        System.out.println("Player balance: " + p1.getBalance());
        System.out.println("Debt owed: " + rentOwed);
        displayUserAsset();


        if(calcDebts(rentOwed, p1)){
            System.out.println("CalcDebts returns true");
            displayUserAsset();
            System.out.println("Test passed");
        }
        else{
            System.out.println("Test Failed");
        }
    }
    // 23.3
    static void testCalcDebts_3() throws IOException {
        System.out.println("+++ Test Case 23.3: calcDebts - Insufficient funds with property +++");

        Scanner s = new Scanner(System.in);

        TechnopolySystem.setBoardSq();
        Player p1 = new Player(0, "Mark");

        TechnopolySystem.players = new Player[2];

        TechnopolySystem.currentPlayerturn = 1;
        TechnopolySystem.players[0] = p1;

        int rentOwed = 1001;

        System.out.println("Player balance: " + p1.getBalance());
        System.out.println("Debt owed: " + rentOwed);
        displayUserAsset();

        if(calcDebts(rentOwed, p1)){
            System.out.println("Test failed");
        }
        else{
            System.out.println("CalcDebts returns false");
            System.out.println("Test passed");
        }
    }
    // 24.2
    static void testTakeOptionalAction_1() throws IOException {
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
    // 24.3
    static void testTakeOptionalAction_2() throws IOException {
        Scanner s = new Scanner(System.in);

        // starts the game and sets the variables accordingly
        startGame(s);

        // sets the board squares and property squares
        setBoardSq();

        ((PropertySq)TechnopolySystem.board[3]).setOwnerID(1);

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
    // 24.4
    static void testTakeOptionalAction_3() throws IOException {
        Scanner s = new Scanner(System.in);

        // starts the game and sets the variables accordingly
        startGame(s);

        // sets the board squares and property squares
        setBoardSq();

        ((PropertySq)TechnopolySystem.board[2]).setOwnerID(0);

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
    // 24.5
    static void testTakeOptionalAction_4() throws IOException {
        Scanner s = new Scanner(System.in);

        // starts the game and sets the variables accordingly
        startGame(s);

        // sets the board squares and property squares
        setBoardSq();

        ((PropertySq)TechnopolySystem.board[1]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[1]).setFullSetOwned(true);
        ((PropertySq)TechnopolySystem.board[2]).setOwnerID(0);
        ((PropertySq)TechnopolySystem.board[2]).setFullSetOwned(true);

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
    // 24.6
    static void testTakeOptionalAction_5() throws IOException {
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

}
