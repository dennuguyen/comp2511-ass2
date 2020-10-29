package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

public class GloriaRomanusTest {

    @RunWith(Suite.class)
    @SuiteClasses({TurnTest.class, MoveTest.class, UnitTest.class, WorldTest.class})
    public class TestSuite {
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
