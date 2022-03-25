package test;

import org.junit.jupiter.api.Test;
import src.Technopoly.SpecialSquare;

import static org.junit.jupiter.api.Assertions.*;

class SpecialSquareTest {
    // 1
    @Test
    void testGetAction() {
        System.out.println("--- Test Case 1: Building Info, Constructor, Getters ---");

        SpecialSquare test = new SpecialSquare(1, "Go!", "None", "Pick up 200 PCs");

        assertTrue(test.getAction().equals("Pick up 200 PCs"));

        if(test.getAction().equals("Pick up 200 PCs")){
            System.out.println("The Actions are equal and the test has passed.");
        } else{
            System.out.println("The Actions are not equal and the test has failed.");
        }
        System.out.println("-------------------------------------------------");

    }
}