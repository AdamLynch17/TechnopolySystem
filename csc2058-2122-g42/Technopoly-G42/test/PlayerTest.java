package test;

import org.junit.jupiter.api.Test;
import src.Technopoly.BoardSquare;
import src.Technopoly.Player;
import src.Technopoly.PropertySq;
import src.Technopoly.TechnopolySystem;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static src.Technopoly.TechnopolySystem.players;
import static src.Technopoly.TechnopolySystem.setBoardSq;

class PlayerTest {

    // 1
    @Test
    void testGetPlayerDetails() {
        System.out.println("--- Test Case 1: Set Player Details, Getters  ---");
        Player olivia = new Player(4,"olivia");

        boolean passTest = true;
        System.out.println("ID of Player: " + olivia.getPlayerID());
        passTest = passTest && (olivia.getPlayerID()==4);
        System.out.println("Name of Player: " + olivia.getName());
        passTest = passTest && (olivia.getName().equals("olivia"));
        System.out.println("Balance of Player: " + olivia.getBalance());
        passTest = passTest && (olivia.getBalance()==1000);
        System.out.println("CurrentSqID of Player: " + olivia.getCurrentSquareID());
        passTest = passTest && (olivia.getCurrentSquareID()==0);
        System.out.println("Active Player: " + olivia.isActivePlayer());
        passTest = passTest && (olivia.isActivePlayer());
        System.out.println("Ranking of Player: " + olivia.getRanking());
        passTest = passTest && (olivia.getRanking()==-1);

        assertEquals(true, passTest);
        System.out.println("-------------------------------------------------");

    }
    // 2
    @Test
    void testSetters() {
        System.out.println("--- Test Case 2: Set Player Details, Setters  ---");

        boolean passTest = true;

        Player olivia = new Player(4,"liv");
        olivia.setBalance(900);
        olivia.setCurrentSquareID(5);

        System.out.println("Balance of Player: " + olivia.getBalance());
        passTest = passTest && (olivia.getBalance()==900);

        System.out.println("CurrentSq ID of Player: " + olivia.getCurrentSquareID());
        passTest = passTest && (olivia.getCurrentSquareID()==5);

        assertEquals(true, passTest);
        System.out.println("-------------------------------------------------");

    }
    // 3
    @Test
    void testUpdateBalance() {
        System.out.println("--- Test Case 3: Update Player's Balance   ---");
        Player Lauren = new Player(0,"Lauren");
        TechnopolySystem.players = new Player[1];
        TechnopolySystem.players[0] = Lauren;

        System.out.println("Player's balance before: ");
        System.out.println(Lauren.getBalance());
        System.out.println("-------------------------");

        System.out.println("Player's balance after: ");
        assertTrue(Lauren.updateBalance(100));
        System.out.println(Lauren.getBalance());
        System.out.println("-------------------------");

        System.out.println("Updated balance is less than 0: ");
        assertFalse(Lauren.updateBalance(-1200));
        System.out.println(Lauren.getBalance());
        System.out.println("-------------------------");
    }
    // 4
    @Test
    void testCalcTotalAsset() throws IOException{
        System.out.println("--- Test Case 4: Calculate the Total Assets of Player   ---");
        Player Lauren = new Player(0,"Lauren");

        TechnopolySystem.setBoardSq();
        TechnopolySystem.players = new Player[1];
        TechnopolySystem.players[0] = Lauren;

        System.out.println("Before buying any assets: ");
        System.out.println(Lauren.calcTotalAsset(TechnopolySystem.board));
        System.out.println("-------------------------");

        System.out.println("After buying a property: ");
        if(TechnopolySystem.board[3] instanceof PropertySq){
            ((PropertySq) TechnopolySystem.board[3]).setOwnerID(0);
            Lauren.updateBalance(-120);
        }
        System.out.println(Lauren.calcTotalAsset(TechnopolySystem.board));
        System.out.println("-------------------------");

        System.out.println("After buying a full set and an office on one property: ");
        if(TechnopolySystem.board[4] instanceof PropertySq){
            ((PropertySq) TechnopolySystem.board[4]).setOwnerID(0);
            Lauren.updateBalance(-120);
        }
        if(TechnopolySystem.board[5] instanceof PropertySq){
            ((PropertySq) TechnopolySystem.board[5]).setOwnerID(0);
            Lauren.updateBalance(-130);
        }

        Lauren.updateBalance(-((PropertySq)TechnopolySystem.board[4]).calcOfficePrice());
        ((PropertySq)TechnopolySystem.board[4]).setNumOffices(((PropertySq)TechnopolySystem.board[4]).getNumOffices() + 1);

        System.out.println(Lauren.calcTotalAsset(TechnopolySystem.board));
        System.out.println("------------------------------------------------------");

    }
    // 5
    @Test
    void testSellAsset() throws IOException {
        System.out.println("--- Test Case 5: Selling Assets  ---");
        TechnopolySystem.players = new Player[1];
        Player Lauren = new Player(0,"Lauren");

        TechnopolySystem.setBoardSq();
        TechnopolySystem.players[0] = Lauren;

        Lauren.updateBalance(2000);
        System.out.println("Player's starting balance for testing purposes: " + Lauren.getBalance());

        System.out.println("Player owns no properties: ");
        Lauren.sellAsset(TechnopolySystem.board);
        System.out.println("-------------------------");

        System.out.println("Player cannot sell a property if they do not own it: ");
        if(TechnopolySystem.board[3] instanceof PropertySq){
            ((PropertySq) TechnopolySystem.board[3]).setOwnerID(0);
            Lauren.updateBalance(-120);
        }
        if (((PropertySq) TechnopolySystem.board[4]).getOwnerID() != Lauren.getPlayerID()) {
            System.out.println("You don't own this property.");
        }
        System.out.println("-------------------------------------------------------------------------");

        System.out.println("Player can sell a property if it has no buildings: ");
        System.out.println("Balance before selling a property: " + Lauren.getBalance() + "\n");
        if (((PropertySq)TechnopolySystem.board[3]).getNumBuildings() == 0) {
            //return property to the bank and remove from the user's assets
            ((PropertySq)TechnopolySystem.board[3]).setOwnerID(-1);
            Lauren.updateBalance(((PropertySq)TechnopolySystem.board[3]).getBuyValue());
            System.out.println("You have sold a property.");
            System.out.println("Your balance is now: " + Lauren.getBalance() + " PC's.");
        }

        System.out.println("-------------------------------------------------");

        System.out.println("Player cannot sell a property if it has buildings: ");
        if(TechnopolySystem.board[3] instanceof PropertySq){
            ((PropertySq) TechnopolySystem.board[3]).setOwnerID(0);
            Lauren.updateBalance(-120);
        }
        if(TechnopolySystem.board[4] instanceof PropertySq){
            ((PropertySq) TechnopolySystem.board[4]).setOwnerID(0);
            Lauren.updateBalance(-120);
        }
        if(TechnopolySystem.board[5] instanceof PropertySq){
            ((PropertySq) TechnopolySystem.board[5]).setOwnerID(0);
            Lauren.updateBalance(-130);
        }
        Lauren.updateBalance(-((PropertySq)TechnopolySystem.board[4]).calcOfficePrice());
        ((PropertySq)TechnopolySystem.board[4]).setNumOffices(((PropertySq)TechnopolySystem.board[4]).getNumOffices() + 1);

        if (((PropertySq) TechnopolySystem.board[4]).getNumBuildings() > 0) {
            System.out.println("You must sell Offices and HQs first.");
        }
        System.out.println("----------------------------------------------");

        System.out.println("Player cannot sell an office from a property that doesn't have at least 1 office: ");

        if (((PropertySq) TechnopolySystem.board[3]).getOwnerID() == Lauren.getPlayerID()) {
            if (((PropertySq) TechnopolySystem.board[3]).getDetails().getNumOffice() > 0) {
                ((PropertySq) TechnopolySystem.board[3]).getDetails().setNumOffice(((PropertySq) TechnopolySystem.board[4]).getDetails().getNumOffice() - 1);
                Lauren.updateBalance(10);
                System.out.println("You have sold an office.");
                System.out.println("Your balance is now: " + Lauren.getBalance() + " PC's.");
            } else {
                System.out.println("There are no offices on this property.");
            }
        }

        System.out.println("----------------------------------------------");

        System.out.println("Player can sell an office: ");
        if (((PropertySq) TechnopolySystem.board[4]).getOwnerID() == Lauren.getPlayerID()) {
            if (((PropertySq) TechnopolySystem.board[4]).getDetails().getNumOffice() > 0) {
                ((PropertySq) TechnopolySystem.board[4]).getDetails().setNumOffice(((PropertySq) TechnopolySystem.board[4]).getDetails().getNumOffice() - 1);
                Lauren.updateBalance(10);
                System.out.println("You have sold an office.");
                System.out.println("Your balance is now: " + Lauren.getBalance() + " PC's.");
            } else {
                System.out.println("There are no offices on this property.");
            }
        }
        System.out.println("------------------------------");

        System.out.println("Player cannot sell an office if they do not own any: ");
        int numPropertiesWithOffices = 0;
        for (BoardSquare square : TechnopolySystem.board) {
            if (square instanceof PropertySq) {
                if (((PropertySq) square).getOwnerID() == Lauren.getPlayerID() && ((PropertySq) square).getDetails().getNumOffice() > 0) {
                    numPropertiesWithOffices++;
                    System.out.println(square.getSqID() + ": " + square.getName());
                }
            }
        }
        if(numPropertiesWithOffices == 0){
            System.out.println("You have no properties with offices.");
        }
        System.out.println("----------------------------------------------");


        //buy an HQ
        Lauren.updateBalance(-((PropertySq)TechnopolySystem.board[4]).calcOfficePrice());
        ((PropertySq)TechnopolySystem.board[4]).setNumOffices(((PropertySq)TechnopolySystem.board[4]).getNumOffices() + 1);
        Lauren.updateBalance(-((PropertySq)TechnopolySystem.board[4]).calcOfficePrice());
        ((PropertySq)TechnopolySystem.board[4]).setNumOffices(((PropertySq)TechnopolySystem.board[4]).getNumOffices() + 1);
        Lauren.updateBalance(-((PropertySq)TechnopolySystem.board[4]).calcOfficePrice());
        ((PropertySq)TechnopolySystem.board[4]).setNumOffices(((PropertySq)TechnopolySystem.board[4]).getNumOffices() + 1);
        Lauren.updateBalance(-((PropertySq)TechnopolySystem.board[4]).calcOfficePrice());
        ((PropertySq)TechnopolySystem.board[4]).setNumOffices(((PropertySq)TechnopolySystem.board[4]).getNumOffices() + 1);

        Lauren.updateBalance(-((PropertySq)TechnopolySystem.board[4]).calcHQPrice());
        ((PropertySq) TechnopolySystem.board[4]).setNumOffices(0);
        ((PropertySq) TechnopolySystem.board[4]).setNumHQs(1);

        System.out.println("Player cannot sell an HQ from a property that doesn't have an HQ: ");
        if (((PropertySq) TechnopolySystem.board[3]).getDetails().getNumHq() > 0) {
            //return property to the bank and remove from the user's assets
            ((PropertySq) TechnopolySystem.board[3]).getDetails().setNumHq(((PropertySq) TechnopolySystem.board[4]).getDetails().getNumHq() - 1);
            Lauren.updateBalance(10); // needs changed to sell value
            System.out.println("You have sold an HQ.");
            System.out.println("Your balance is now: " + Lauren.getBalance() + " PC's");
        } else {
            System.out.println("There are no HQ's on this property.");
        }
        System.out.println("----------------------------------------------");

        System.out.println("Player can sell an HQ: ");
        if (((PropertySq) TechnopolySystem.board[4]).getDetails().getNumHq() > 0) {
            //return property to the bank and remove from the user's assets
            ((PropertySq) TechnopolySystem.board[4]).getDetails().setNumHq(((PropertySq) TechnopolySystem.board[4]).getDetails().getNumHq() - 1);
            Lauren.updateBalance(10); // needs changed to sell value
            System.out.println("You have sold an HQ.");
            System.out.println("Your balance is now: " + Lauren.getBalance() + " PC's");
        } else {
            System.out.println("There are no HQ's on this property.");
        }
        System.out.println("----------------------------------------------");

        System.out.println("Player cannot sell an HQ if they do not own any: ");
        int numPropertiesWithHQs = 0;
        for (BoardSquare square : TechnopolySystem.board) {
            if (square instanceof PropertySq) {
                if (((PropertySq) square).getOwnerID() == Lauren.getPlayerID() && ((PropertySq) square).getDetails().getNumHq() > 0) {
                    numPropertiesWithHQs++;
                    System.out.println(square.getSqID() + ": " + square.getName());
                }
            }
        }
        if(numPropertiesWithHQs == 0){
            System.out.println("You have no properties with HQs.");
        }
        System.out.println("----------------------------------------------");

        System.out.println("User input is out of range/ number doesn't correlate to a property square: ");
        if (TechnopolySystem.board[0] instanceof PropertySq) {

            //check property has an HQ to sell
            if (((PropertySq) TechnopolySystem.board[0]).getOwnerID() == Lauren.getPlayerID()) {
                if (((PropertySq) TechnopolySystem.board[0]).getDetails().getNumHq() > 0) {
                    //return property to the bank and remove from the user's assets
                    ((PropertySq) TechnopolySystem.board[0]).getDetails().setNumHq(((PropertySq) TechnopolySystem.board[0]).getDetails().getNumHq() - 1);
                    Lauren.updateBalance(10); // needs changed to sell value
                    System.out.println("You have sold an HQ.");
                    System.out.println("Your balance is now: " + Lauren.getBalance() + " PC's");
                } else {
                    System.out.println("There are no HQ's on this property.");
                }

            } else {
                System.out.println("You don't own this property.");
            }
        } else {
            System.out.println("This is not a valid property.");
        }
        System.out.println("----------------------------------------------");

        System.out.println("Testing that the full set owned boolean changes when a player sells a property: ");
        if(TechnopolySystem.board[21] instanceof PropertySq){
            ((PropertySq) TechnopolySystem.board[21]).setOwnerID(0);
            Lauren.updateBalance(-320);
        }
        if(TechnopolySystem.board[22] instanceof PropertySq){
            ((PropertySq) TechnopolySystem.board[22]).setOwnerID(0);
            Lauren.updateBalance(-340);
        }

        ((PropertySq) TechnopolySystem.board[21]).setFullSetOwned(true);

        System.out.println("getFullSetOwned before selling a property: " + ((PropertySq) TechnopolySystem.board[21]).getFullSetOwned() + "\n");

        if (((PropertySq)TechnopolySystem.board[21]).getNumBuildings() == 0) {
            //return property to the bank and remove from the user's assets
            ((PropertySq)TechnopolySystem.board[21]).setOwnerID(-1);
            Lauren.updateBalance(((PropertySq)TechnopolySystem.board[3]).getBuyValue());
            System.out.println("You have sold a property.");
            System.out.println("Your balance is now: " + Lauren.getBalance() + " PC's.");

            if (((PropertySq) TechnopolySystem.board[21]).getFullSetOwned()){
                ((PropertySq) TechnopolySystem.board[21]).setFullSetOwned(false);
            }
        }

        System.out.println("getFullSetOwned after selling a property: " + ((PropertySq) TechnopolySystem.board[21]).getFullSetOwned() + "\n");
        System.out.println("-------------------------------------------------");

    }
    // 6
    @Test
    void testDeclareBankruptcy1() throws IOException {
        System.out.println("--- Test Case 6: Declare Bankruptcy ActivePlayer & OwnerID  ---");
        Player olivia = new Player(0,"olivia");

        TechnopolySystem.setBoardSq();
        TechnopolySystem.players = new Player[1];
        TechnopolySystem.players[0] = olivia;


        if(TechnopolySystem.board[3] instanceof PropertySq){
            ((PropertySq) TechnopolySystem.board[3]).setOwnerID(0);
        }
        if(TechnopolySystem.board[7] instanceof PropertySq){
            ((PropertySq) TechnopolySystem.board[7]).setOwnerID(0);
        }

        System.out.println("Player assets owned before declare bankruptcy: ");
        TechnopolySystem.currentPlayerturn = 1;
        TechnopolySystem.displayUserAsset();
        System.out.println("-----------------------------------------------");

        olivia.declareBankruptcy(1,TechnopolySystem.board);

        System.out.println("\nPlayer assets owned after declare bankruptcy: ");
        TechnopolySystem.displayUserAsset();
        System.out.println("-----------------------------------------------");

        System.out.println("--- Check the Owner ID of the two properties: ");
        System.out.println("Property with ID = 3, OwnerID = " + ((PropertySq) TechnopolySystem.board[3]).getOwnerID());
        System.out.println("Property with ID = 7, OwnerID = " + ((PropertySq) TechnopolySystem.board[7]).getOwnerID());
        System.out.println("----------------------------------------------");

        System.out.println("---------------------------------------------------------------");

    }
    // 7
    @Test
    void testDeclareBankruptcy2() throws IOException {
        System.out.println("--- Test Case 7: Declare Bankruptcy NumHQs & NumOffices  ---");
        Player olivia = new Player(0,"olivia");

        TechnopolySystem.setBoardSq();
        TechnopolySystem.players = new Player[1];
        TechnopolySystem.players[0] = olivia;

        // Player owns the full set
        ((PropertySq) TechnopolySystem.board[3]).setOwnerID(0);
        ((PropertySq) TechnopolySystem.board[4]).setOwnerID(0);
        ((PropertySq) TechnopolySystem.board[5]).setOwnerID(0);

        // Player has 1 HQ on Property with ID = 4
        ((PropertySq) TechnopolySystem.board[4]).setNumHQs(1);

        // Player has 1 Office on Property with ID = 5
        ((PropertySq) TechnopolySystem.board[5]).setNumOffices(1);

        System.out.println("Properties displayed before declare bankruptcy: ");
        System.out.println("Property ID 3, Num Offices = " + ((PropertySq) TechnopolySystem.board[3]).getNumOffices());
        System.out.println("Property ID 3, Num HQs = " + ((PropertySq) TechnopolySystem.board[3]).getNumHQs());
        System.out.println("Property ID 4, Num Offices = " + ((PropertySq) TechnopolySystem.board[4]).getNumOffices());
        System.out.println("Property ID 4, Num HQs = " + ((PropertySq) TechnopolySystem.board[4]).getNumHQs());
        System.out.println("Property ID 5, Num Offices = " + ((PropertySq) TechnopolySystem.board[5]).getNumOffices());
        System.out.println("Property ID 5, Num HQs = " + ((PropertySq) TechnopolySystem.board[5]).getNumHQs());
        System.out.println("---------------------------------------------------------------");

        olivia.declareBankruptcy(1,TechnopolySystem.board);

        System.out.println("Properties displayed before after bankruptcy: ");
        System.out.println("Property ID 3, Num Offices = " + ((PropertySq) TechnopolySystem.board[3]).getNumOffices());
        System.out.println("Property ID 3, Num HQs = " + ((PropertySq) TechnopolySystem.board[3]).getNumHQs());
        System.out.println("Property ID 4, Num Offices = " + ((PropertySq) TechnopolySystem.board[4]).getNumOffices());
        System.out.println("Property ID 4, Num HQs = " + ((PropertySq) TechnopolySystem.board[4]).getNumHQs());
        System.out.println("Property ID 5, Num Offices = " + ((PropertySq) TechnopolySystem.board[5]).getNumOffices());
        System.out.println("Property ID 5, Num HQs = " + ((PropertySq) TechnopolySystem.board[5]).getNumHQs());
        System.out.println("---------------------------------------------------------------");

    }

    // 8
    @Test
    void testDeclareBankruptcyEndGame() throws IOException {
        System.out.println("--- Test Case 6: Declare Bankruptcy ActivePlayer & OwnerID  ---");
        Player olivia = new Player(0,"olivia");

        TechnopolySystem.setBoardSq();
        TechnopolySystem.players = new Player[1];

        TechnopolySystem.players[0] = olivia;


        if(TechnopolySystem.board[3] instanceof PropertySq){
            ((PropertySq) TechnopolySystem.board[3]).setOwnerID(0);
        }
        if(TechnopolySystem.board[7] instanceof PropertySq){
            ((PropertySq) TechnopolySystem.board[7]).setOwnerID(0);
        }

        System.out.println("Player assets owned before declare bankruptcy: ");
        TechnopolySystem.currentPlayerturn = 1;
        TechnopolySystem.displayUserAsset();
        System.out.println("-----------------------------------------------");

        olivia.declareBankruptcyEndGame(1,TechnopolySystem.board);

        System.out.println("\nPlayer assets owned after declare bankruptcy: ");
        TechnopolySystem.displayUserAsset();
        System.out.println("-----------------------------------------------");

        System.out.println("--- Check the Owner ID of the two properties: ");
        System.out.println("Property with ID = 3, OwnerID = " + ((PropertySq) TechnopolySystem.board[3]).getOwnerID());
        System.out.println("Property with ID = 7, OwnerID = " + ((PropertySq) TechnopolySystem.board[7]).getOwnerID());
        System.out.println("----------------------------------------------");

        System.out.println("---------------------------------------------------------------");

    }

}