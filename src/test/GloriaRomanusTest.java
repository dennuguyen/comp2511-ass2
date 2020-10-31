package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import unsw.gloriaromanus.Turn;

public class GloriaRomanusTest {

    @RunWith(Suite.class)
    @SuiteClasses({ TurnTest.class, StatsTest.class, MoveTest.class, UnitTest.class, PubSubTest.class,
            ProvinceTest.class })
    public class TestSuite {
        Turn turn = Turn.getInstance();
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestSuite.class);
        for (Failure failure : result.getFailures()) {
            System.out.println();
            System.out.println("----------");
            System.out.println("Test Case: " + failure.toString());
            System.out.println("----------");
        }
        System.out.println();
        System.out.println("=========================");
        System.out.println("Test Suite Result: " + result.wasSuccessful());
        System.out.println("=========================");
        System.out.println();
    }
}
