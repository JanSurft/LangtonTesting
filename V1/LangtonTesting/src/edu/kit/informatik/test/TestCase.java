package edu.kit.informatik.test;

/**
 * basic test case, every test case need an input argument
 * 
 * @version 0.1
 * @author jan
 *
 */
public abstract class TestCase {

    final String input;

    /**
     * Constructs a new testcase iwth the given input
     * 
     * @param input
     *            input for this test case
     */
    public TestCase(String input) {
	this.input = input;
    }

    /**
     * returns the given input for this test case
     * 
     * @return given input
     */
    public String readLine() {
	return input;
    }

    /**
     * Returns the output of this testcase, null if there is no output.
     * 
     * @return given ouput for the test case, {@code null} if there is no output
     *         (for exapmle for move cmd)
     */
    public abstract String printLine();
}
