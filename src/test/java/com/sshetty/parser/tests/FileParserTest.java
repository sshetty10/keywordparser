package com.sshetty.parser.tests;

import java.io.File;


import com.sshetty.parse.util.Names;
import com.sshetty.parser.main.FileParser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for FileParser.
 */
public class FileParserTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FileParserTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FileParserTest.class );
    }


    /**
     * True positive case
     */
    public void testParserPositive()
    {
    	FileParser fp = new FileParser();
    	File file = new File("src/test/resources/input.txt");
    	assertTrue(fp.parseFile(file));
    }
    
    /**
     * input is a directory or file does not exist at the location
     */
    public void testParserFileNotFound()
    {
    	FileParser fp = new FileParser();
    	File file = new File("/src/test/resources");
    	assertFalse(fp.parseFile(file));
    }
    
    
    /**
     * validate the count of entries for the given category and line of text
     */
    public void testParserValidateTokenCount()
    {
    	String input = "PERSON     is a good man";
    	String output = "PERSON is a good man";
    	FileParser fp = new FileParser();
    	String methoutOut= fp.tokenizeAndParseLine(input);
    	assertEquals(output.trim(), methoutOut.trim());
    	int count = fp.getCategoryMap().get(Names.PERSON);
    	assertEquals(count,1);
    }
    
    /**
     * Test Success even if file does not contains invalid characters
     */
    public void testParserValidateTokenInvalid()
    {
    	FileParser fp = new FileParser();
    	File file = new File("src/test/resources/inputInvalid.txt");
    	assertTrue(fp.parseFile(file));
    }
    
    /**
     * Check whether the count is set to 1 for n number of same category, subcategory pairs
     */
    public void testParserValidateTokenUnique()
    {
    	FileParser fp = new FileParser();
    	File file = new File("src/test/resources/inputUniQue.txt");
    	assertTrue(fp.parseFile(file));
    	int count = fp.getCategoryMap().get(Names.PERSON);
    	assertEquals(count,1);
    }
   
}
