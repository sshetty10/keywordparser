package com.sshetty.parser.main;


import java.io.File;
import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * @author Sameeksha
 * This is the Main class
 * Enter the file name of the input file to be parsed.
 * Keep processing new files unless the user does not wish to continue.
 */
public class ParserMain
{
	private static final Logger logger = Logger.getLogger(ParserMain.class);
	public static void main( String[] args )
	{
		Scanner scan = new Scanner(System.in);
		boolean repeat= false;
		do{
				try{
					System.out.println("Enter input file name (absolute path):");
					String str = scan.next();
					if(!str.trim().equals("")){
						File file = new File(str);
						FileParser fp = new FileParser();
						fp.parseFile(file);
					}
					else{
						logger.error("Invalid Input");
					}
					System.out.println("Do you want to continue? ");
					str = scan.next();
					if(str.equalsIgnoreCase("y")){
						repeat=true;
					}
					else{
						repeat=false;
					}
				}
				catch(Exception e){
					logger.error(e);
				}
		}while(repeat);
		scan.close();
	}


}

