package edu.kit.informatik.test;

/**
 * Represents Success test cases for the testing framework
 * 
 * @version 0.1
 * @author jan
 *
 */
public class SuccessCase extends TestCase {

    private final String output;

    /**
     * Constructs a new Success test Case with given input and expected output
     * 
     * @param input
     *            the given input
     * @param output
     *            the expected output
     */
    public SuccessCase(String input, String output) {
	super(input);
	this.output = output;
    }

    @Override
    public String printLine() {
	return output;
    }

    @Override
    public String toString() {
	return input + ":" + output;
    }

}
