package edu.kit.informatik.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import edu.kit.informatik.Terminal;

/**
 * This is the main utilty method to start the test enviornment
 * 
 * @author jan
 * @version 0.1
 */
public class Tester {

    private Tester() {

    }

    /**
     * Takes one argument path to the test file
     * 
     * @param args
     *            path to the textfile
     */
    public static void main(String[] args) {

	if (args.length != 1) {
	    System.out
		    .println("error takes exactly one argument, the path to the test file");
	    System.exit(1);
	}

	TestFileParser parser = null;

	try {
	    parser = new TestFileParser(args[0]);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

	// feed the specific Terminal class with the static test cases
	try {
	    Terminal.testCases = parser.parseAndReturn();
	} catch (IOException | ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	// Here you call your main langton program routine and the main method
	// so if your main starting class is not called Main you should rename
	// it here
	Main.main(parser.getArgs());

    }
}
