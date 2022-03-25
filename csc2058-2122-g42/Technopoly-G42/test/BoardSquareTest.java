package test;

import org.junit.jupiter.api.Test;
import src.Technopoly.BoardSquare;

import static org.junit.jupiter.api.Assertions.*;

class BoardSquareTest {

    @Test
    void testGetters(){
        System.out.println("--- Test Case 1: Set Board Sq Details, Constructor & Getters  ---");

        // uses the constructor
        BoardSquare test = new BoardSquare(0,"testName","testField");
        boolean passTest = true;
        System.out.println("ID of Board Sq: " + test.getSqID());
        passTest = passTest && (test.getSqID()==0);
        System.out.println("Name of Board Sq: " + test.getName());
        passTest = passTest && (test.getName().equals("testName"));
        System.out.println("Field of Board Sq: " + test.getField());
        passTest = passTest && (test.getField().equals("testField"));

        assertEquals(true, passTest);
        System.out.println("-------------------------------------------------");

    }


}