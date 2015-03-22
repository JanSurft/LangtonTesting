package edu.kit.informatik;

import java.util.Deque;

import edu.kit.informatik.test.FailCase;
import edu.kit.informatik.test.SuccessCase;
import edu.kit.informatik.test.TestCase;

public class Terminal {

    public static Deque<TestCase> testCases;

    private static TestCase currentCase;

    private Terminal() {
    }

    public static String readLine() {

	currentCase = testCases.removeFirst();
	System.out.println(currentCase.readLine());

	return currentCase.readLine();
    }

    public static void printLine(String line) {

	if (currentCase instanceof FailCase) {

	    failTest((FailCase) currentCase, line);

	} else if (currentCase instanceof SuccessCase) {

	    successTest((SuccessCase) currentCase, line);

	} else {
	    throw new IllegalStateException("the test case is not a legal type");
	}
    }

    private static void successTest(SuccessCase successCase, String line) {

	if (!line.equals(successCase.printLine())) {
	    System.out.println("error, expected: \n" + successCase.printLine()
		    + "\n given: \n" + line);
	    System.exit(1);
	} else {
	    System.out.println(line);
	}

	if (testCases.isEmpty()) {
	    System.out.println("all tests were successfull");
	    System.exit(0);
	}
    }

    private static void failTest(FailCase failCase, String line) {

	if (line.startsWith(failCase.printLine())) {
	    System.out.println("looks like an error message, good");
	} else {
	    System.out.println("there was an error expected, cause: "
		    + failCase.getCause());
	}

	if (testCases.isEmpty()) {
	    System.out.println("all tests were successfull");
	    System.exit(0);
	}

    }

}
