package com.sshetty.parser.main;


import java.io.File;
import org.apache.log4j.Logger;

/**
 * @author Sameeksha
 * This is the Main class
 * Enter the absolute path of the input file to be parsed.
 */
public class ParserMain
{
	private static final Logger logger = Logger.getLogger(ParserMain.class);
	public static void main( String[] args )
	{
		if(args.length > 0){
			File file = new File(args[0]);
			FileParser fp = new FileParser();
			fp.parseFile(file);
		}
		else{
			System.out.println("Please pass the file name as the first argument.");
		}
	}


}

