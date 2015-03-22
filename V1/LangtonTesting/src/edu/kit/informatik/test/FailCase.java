package edu.kit.informatik.test;

/**
 * FailCase, represent fail test cases for the test frame work
 * 
 * @version 0.1
 * @author jan
 *
 */
public class FailCase extends TestCase {

    private final String cause;

    /**
     * Constructs a new Fail case takin an input that should cause an error in
     * your programm and the cause of this error
     * 
     * @param input
     *            the input that should cause an error in your program
     * @param cause
     *            the cause of this error
     */
    public FailCase(String input, String cause) {
	super(input);
	this.cause = cause;
    }

    /**
     * Returns the cause of the error
     * 
     * @return cause of the error
     */
    public String getCause() {
	return cause;
    }

    @Override
    public String printLine() {
	return "Error, ";
    }

    @Override
    public String toString() {
	return input + ":" + cause;
    }

}
