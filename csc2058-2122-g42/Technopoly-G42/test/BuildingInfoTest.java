package test;

import org.junit.jupiter.api.Test;
import src.Technopoly.BuildingInfo;

import static org.junit.jupiter.api.Assertions.*;

class BuildingInfoTest {

    // 1
    @Test
    void testConstructorGetters(){
        System.out.println("--- Test Case 1: Building Info, Constructor, Getters ---");

        BuildingInfo detailsTest = new BuildingInfo();
        boolean passTest = true;
        System.out.println("The number of offices: " + detailsTest.getNumOffice());
        passTest = passTest && (detailsTest.getNumOffice()==0);
        System.out.println("The number of HQs: " + detailsTest.getNumHq());
        passTest = passTest && (detailsTest.getNumHq()==0);

        assertEquals(true, passTest);
        System.out.println("-------------------------------------------------");

    }
    // 2
    @Test
    void testSetters(){
        System.out.println("--- Test Case 2: Building Info, Setters ---");

        BuildingInfo detailsTest = new BuildingInfo();
        boolean passTest = true;

        System.out.println("-----------BEFORE-----------");
        System.out.println("The number of offices: " + detailsTest.getNumOffice());
        System.out.println("The number of HQs: " + detailsTest.getNumHq());
        System.out.println("----------------------------");

        // call the setters
        detailsTest.setNumOffice(2);
        detailsTest.setNumHq(1);

        System.out.println("-----------AFTER-----------");
        System.out.println("The number of offices: " + detailsTest.getNumOffice());
        passTest = passTest && (detailsTest.getNumOffice()==2);
        System.out.println("The number of HQs: " + detailsTest.getNumHq());
        passTest = passTest && (detailsTest.getNumHq()==1);
        System.out.println("---------------------------");
        assertEquals(true, passTest);
        System.out.println("-------------------------------------------------");

    }

}