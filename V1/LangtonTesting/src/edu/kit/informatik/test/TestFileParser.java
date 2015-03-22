package edu.kit.informatik.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Parses a given file and creates the testcases accordingly
 * 
 * @version 0.1
 * @author jan
 *
 */
public class TestFileParser {

    private final StreamTokenizer st;

    private final List<String> args;

    private final Deque<TestCase> testCases;

    private int lookahead;

    /**
     * Constructs a new TestFileParser with the given filepath
     * 
     * @param pathToFile
     *            the path to the test file
     * @throws FileNotFoundException
     *             if the file was not found
     */
    public TestFileParser(String pathToFile) throws FileNotFoundException {

	st = new StreamTokenizer(new FileReader(pathToFile));

	st.quoteChar('"');

	st.slashSlashComments(true);
	st.slashStarComments(true);

	st.ordinaryChars('0', '9');
	st.wordChars('0', '9');

	args = new ArrayList<String>();
	testCases = new LinkedList<TestCase>();
    }

    /**
     * Returns the command line arguments that were parsed in the testfile
     * 
     * @return cmd line arguments for you main program routine
     */
    public String[] getArgs() {

	String[] arguments = new String[args.size()];
	return args.toArray(arguments);
    }

    /**
     * Parses the given input test file and returns a deque with all test cases
     * 
     * @return a deque with all parsed test cases
     * @throws IOException
     *             if a io error occurs
     * @throws ParseException
     *             if a parsing error occurs
     */
    public Deque<TestCase> parseAndReturn() throws IOException, ParseException {

	next();

	parseAndCreateFile();

	parseStartConditions();

	parseTestCases();

	return testCases;

    }

    private void next() throws IOException {
	lookahead = st.nextToken();
    }

    private void match(int expected) throws ParseException, IOException {

	if (lookahead != expected) {
	    throw new ParseException("fail parsing, expected: "
		    + (char) expected + " got: " + (char) lookahead,
		    st.lineno());
	}
	next();

    }

    private void matchString(String expected) throws ParseException,
	    IOException {

	if (!expected.equals(st.sval)) {
	    throw new ParseException("fail parsing, expected: " + expected
		    + " got: " + st.sval, st.lineno());
	}
	next();

    }

    private void parseTestCases() throws ParseException, IOException {

	while (st.ttype != StreamTokenizer.TT_EOF) {

	    testCases.addLast(parseTestCase());
	}

    }

    private TestCase parseTestCase() throws ParseException, IOException {

	String input;
	String output = null;

	matchString("input");
	match('=');
	input = st.sval;

	next();
	matchString("output");
	match('=');

	if (lookahead == '<') {

	    next();

	    if (st.sval.equals("ERROR")) {
		return parseFailCase(input);
	    } else {

		matchString("NONE");
		match('>');

		return new SuccessCase(input, null);
	    }

	} else {

	    if (lookahead == '{') {
		output = parseBoard();
	    } else {
		output = st.sval;
		next();
	    }
	    return new SuccessCase(input, output);
	}

    }

    private TestCase parseFailCase(String input) throws ParseException,
	    IOException {

	next();
	match(',');
	matchString("cause");
	match('=');

	String cause = st.sval;

	next();
	match('>');

	return new FailCase(input, cause);

    }

    private void parseNone() throws ParseException, IOException {
	match('<');
	matchString("NONE");
	match('>');
    }

    private void parseStartConditions() throws IOException, ParseException {

	matchString("start");
	match('=');

	if (lookahead == '<') {
	    parseNone();
	    match(',');
	    parseNone();
	    return;
	} else {
	    System.out.println(st.sval);
	    args.add(st.sval);
	    next();
	    match(',');

	    if (lookahead == '<') {
		parseNone();
		return;
	    } else {
		args.add(st.sval);
		next();
		return;

	    }

	}
    }

    private void parseAndCreateFile() throws ParseException, IOException {

	PrintWriter writer = new PrintWriter("testing.txt", "UTF-8");

	args.add("testing.txt");

	matchString("board");
	match('=');

	writer.write(parseBoard());

	writer.close();
    }

    private String parseBoard() throws IOException, ParseException {

	StringBuilder sb = new StringBuilder();

	match('{');

	while (lookahead != '}') {

	    sb.append(st.sval);

	    next();

	    if (lookahead != '}') {
		sb.append('\n');
	    }

	}
	match('}');

	return sb.toString();
    }

}
