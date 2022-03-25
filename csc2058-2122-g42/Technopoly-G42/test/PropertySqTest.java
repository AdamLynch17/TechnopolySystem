package test;

import org.junit.jupiter.api.Test;
import src.Technopoly.BuildingInfo;
import src.Technopoly.Player;
import src.Technopoly.PropertySq;
import src.Technopoly.TechnopolySystem;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PropertySqTest {

    @Test
    void testConsturctorGetters(){
        System.out.println("--- Test Case 1: Set Property Sq Details, Constructor  ---");

        int price = 150;
        int[] rentVal = new int[]{(price / 2), price, (int) (price * 1.6), (int) (price * 1.7), (int) (price * 1.8), (int) (price * 1.9), (price * 2)};
        BuildingInfo newInfo = new BuildingInfo();
        // Constructor to be tested
        PropertySq test = new PropertySq(1, "testProperty", "testField", price,rentVal, newInfo);

        boolean passTest = true;
        System.out.println("ID of Property: " + test.getSqID());
        passTest = passTest && (test.getSqID()==1);
        System.out.println("Name of Property: " + test.getName());
        passTest = passTest && (test.getName().equals("testProperty"));
        System.out.println("Field of Property: " + test.getField());
        passTest = passTest && (test.getField().equals("testField"));
        System.out.println("Buy Value of Property: " + test.getBuyValue());
        passTest = passTest && (test.getBuyValue()== 150);
        System.out.println("Owner ID of Property: " + test.getOwnerID());
        passTest = passTest && (test.getOwnerID()==-1);
        System.out.println("Full Set Owned: " + test.getFullSetOwned());
        passTest = passTest && (test.getFullSetOwned()==false);
        System.out.println("Rent Value of Property: ");

        // Should return base rate
        System.out.println("Base Rate: " + test.getRentValue());
        passTest = passTest && (test.getRentValue()==75);
        // Add two house to the property - 170% of the card price
        test.setNumOffices(2);
        test.setFullSetOwned(true);
        System.out.println("Property with 2 offices: " + test.getRentValue());
        passTest = passTest && (test.getRentValue()==255);

        System.out.println("** Testing BuildingInfo will be done in the testBuildingInfo class **");

        assertEquals(true, passTest);
        System.out.println("-------------------------------------------------");

    }

    @Test
    void testGettersSetters(){
        System.out.println("--- Test Case 2: Set Property Sq Details, Setters  ---");

        int price = 150;
        int[] rentVal = new int[]{(price / 2), price, (int) (price * 1.6), (int) (price * 1.7), (int) (price * 1.8), (int) (price * 1.9), (price * 2)};
        BuildingInfo newInfo = new BuildingInfo();
        // Constructor to be tested
        PropertySq test = new PropertySq(1, "testProperty", "testField", price,rentVal, newInfo);

        System.out.println("--- BEFORE ---");
        System.out.println("Owner ID of Property: " + test.getOwnerID());
        System.out.println("Full Set Owned: " + test.getFullSetOwned());
        System.out.println("Number of Offices: " + test.getNumOffices());
        System.out.println("Number of HQs: " + test.getNumHQs());
        System.out.println("--------------");

        // setters are called to be tested
        boolean passTest = true;
        // set Owner ID
        test.setOwnerID(2);
        // set Full Set Owned
        test.setFullSetOwned(true);
        // set Num Offices
        test.setNumOffices(2);
        // set Num HQs
        test.setNumHQs(1);

        System.out.println("---- AFTER ---");
        System.out.println("Owner ID of Property: " + test.getOwnerID());
        passTest = passTest && (test.getOwnerID()==2);
        System.out.println("Full Set Owned: " + test.getFullSetOwned());
        passTest = passTest && (test.getFullSetOwned()==true);
        System.out.println("Number of Offices: " + test.getNumOffices());
        passTest = passTest && (test.getNumOffices() == 2);
        System.out.println("Number of HQs: " + test.getNumHQs());
        passTest = passTest && (test.getNumHQs() == 1);
        System.out.println("--------------");

        assertEquals(true, passTest);
        System.out.println("-------------------------------------------------");

    }

   @Test
    void testNumBuildings_1() throws IOException {
       System.out.println("--- Test Case 3.1: Calculating the number of buildings on a property  ---");
       TechnopolySystem.setBoardSq();

        // test with 0 buildings

       System.out.println("Property: " + TechnopolySystem.board[1].getName() + " has 0 buildings");

       System.out.println("Calculate Number of buildings: " + ((PropertySq)TechnopolySystem.board[1]).getNumBuildings() );

       assertEquals(0, ((PropertySq)TechnopolySystem.board[1]).getNumBuildings() );

       if( ((PropertySq)TechnopolySystem.board[1]).getNumBuildings() ==0){
           System.out.println("The test has passed.");
       } else{
           System.out.println("The test has failed.");
       }
       System.out.println("------------------------------------------------");

   }

   @Test
    void testNumBuildings_2() throws IOException {
        System.out.println("--- Test Case 3.2: Calculating the number of buildings on a property  ---");

        // test with 2 offices

        TechnopolySystem.setBoardSq();

        System.out.println("Property: " + TechnopolySystem.board[1].getName() + " has 2 offices");

        ((PropertySq)TechnopolySystem.board[1]).setNumOffices(2);

        System.out.println("Calculate Number of buildings: " + ((PropertySq)TechnopolySystem.board[1]).getNumBuildings() );

        assertEquals(2, ((PropertySq)TechnopolySystem.board[1]).getNumBuildings() );

        if( ((PropertySq)TechnopolySystem.board[1]).getNumBuildings() ==2){
            System.out.println("The test has passed.");
        } else{
            System.out.println("The test has failed.");
        }

        System.out.println("------------------------------------------------");

    }

    @Test
    void testNumBuildings_3() throws IOException {
        System.out.println("--- Test Case 3.3: Calculating the number of buildings on a property  ---");

        // test with 1 hq

        TechnopolySystem.setBoardSq();

        System.out.println("Property: " + TechnopolySystem.board[1].getName() + " has 1 HQ");

        ((PropertySq)TechnopolySystem.board[1]).setNumHQs(1);

        System.out.println("Calculate Number of buildings: " + ((PropertySq)TechnopolySystem.board[1]).getNumBuildings() );

        assertEquals(5, ((PropertySq)TechnopolySystem.board[1]).getNumBuildings() );

        if( ((PropertySq)TechnopolySystem.board[1]).getNumBuildings() ==5){
            System.out.println("The test has passed.");
        } else{
            System.out.println("The test has failed.");
        }

        System.out.println("------------------------------------------------");

    }

    @Test
    void testCalcOfficeHQPrice() throws IOException {
        System.out.println("--- Test Case 4: Calculating Office Price  ---");

        System.out.println("Office Price should be 1.25 * BuyProperty Value");
        System.out.println("HQ Price should be 1.5 * BuyProperty Value");
        System.out.println();

        TechnopolySystem.setBoardSq();

        boolean passTest = true;

        System.out.println("Board Square "+ TechnopolySystem.board[1].getName() + " Property Price: " + ((PropertySq)TechnopolySystem.board[1]).getBuyValue());
        System.out.println("Office Price "+ (((PropertySq) TechnopolySystem.board[1]).calcOfficePrice()) + ", HQ Price: " + (((PropertySq) TechnopolySystem.board[1]).calcHQPrice()));
        passTest = passTest && ((((PropertySq) TechnopolySystem.board[1]).calcOfficePrice())==80) && ((((PropertySq) TechnopolySystem.board[1]).calcHQPrice())==90);

        System.out.println("Board Square "+ TechnopolySystem.board[20].getName() + " Property Price: " + ((PropertySq)TechnopolySystem.board[20]).getBuyValue());
        System.out.println("Office Price "+ (((PropertySq) TechnopolySystem.board[02]).calcOfficePrice()) + ", HQ Price: " + (((PropertySq) TechnopolySystem.board[20]).calcHQPrice()));
        passTest = passTest && ((((PropertySq) TechnopolySystem.board[20]).calcOfficePrice())==360) && ((((PropertySq) TechnopolySystem.board[20]).calcHQPrice())==440);


        assertEquals(true,passTest);

        if(passTest){
            System.out.println("The test has passed");
        } else{
            System.out.println("The test has failed");
        }
        System.out.println("------------------------------------------");

    }

}