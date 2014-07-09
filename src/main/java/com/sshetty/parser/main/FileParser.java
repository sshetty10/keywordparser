/**
 * 
 */
package com.sshetty.parser.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.sshetty.parse.util.Names;

/**
 * @author Sameeksha
 *
 *FileParse class which holds the business Logic
 */
public class FileParser {

	private static final Logger logger = Logger.getLogger(FileParser.class);
	private static final String[] TOKENS = {Names.PERSON, Names.PLACE, Names.ANIMAL, Names.COMPUTER, Names.OTHER};
	
	private Map<String,Integer> categoryMap = new HashMap<String,Integer>(); //Hashmap with key,value= category,count
	Set<String> uniqueComboSet = new HashSet<String>(); //HashSet to maintain uniqueness of key,value combo
	StringBuffer output = new StringBuffer(); // used for printing the accepted and read values
	
	public FileParser(){
		initializeMap();
	}
	/**
	 * @param map
	 * Prints the counts for each category
	 */
	private void printMapCounts(Map<String,Integer> map){
		System.out.printf("\t%10s\t%10s","CATEGORY","COUNT");
		for (Entry<String, Integer> entry : map.entrySet())
		{
			System.out.println();
			System.out.printf("\t%10s\t%10s", entry.getKey().toString(),entry.getValue().toString());
			logger.info(entry.getKey().toString() + "\t" + entry.getValue().toString());
		}

	}
	/**
	 * @return Map of categories and count
	 * getter for the category Map
	 */
	public Map<String, Integer> getCategoryMap() {
		return categoryMap;
	}

	/**
	 * 
	 * initialize the counts of all categories to 0
	 */
	private void initializeMap(){
		for(int i=0; i<TOKENS.length;i++){
			categoryMap.put(TOKENS[i], 0);
		}
	}

	/**
	 * @param file
	 * @return true if parsing and printing is successful.False in case of exceptions or negative cases
	 */
	public boolean parseFile(File file){
		if(file.exists()){ //check if file exists
			try {
				Scanner scanner = new Scanner(file);
				try
				{
					while(scanner.hasNext()){//for each line
						tokenizeAndParseLine(scanner.nextLine());
					}
					printOutput();
					return true;
				}
				catch(Exception e){
					logger.error(e);
					System.out.println("Invalid file content");
				}
				finally{
					scanner.close();
				}
			}
			catch (FileNotFoundException e) {
				logger.error(e);
				System.out.println("File does not exist.");
			}
		}
		else{
			System.out.println("File does not exist");
		}
		return false;
	}
	
	/**
	 * @param line
	 * @return output value to be printed
	 * Method runs for each line of the file
	 */
	public String tokenizeAndParseLine(String line){
		StringTokenizer st = new StringTokenizer(line);
		String category = st.nextToken();
		if(categoryMap.get(category)!=null){
			StringBuffer subCategory = new StringBuffer();
			while(st.hasMoreTokens()){			//for each token in the line
				subCategory.append(" "+st.nextToken());
			}
			addIfUnique(category, subCategory);
		}
		return output.toString();
	}

	/**
	 * @param category
	 * @param subCategory
	 * To fulfill the requirement to process/print/update count only if the combination(category,subcategory) is unqiue
	 */
	private void addIfUnique(String category, StringBuffer subCategory){
		if(uniqueComboSet.add(category.trim()+"|"+subCategory.toString().trim())){
			categoryMap.put(category, categoryMap.get(category) + 1);
			output.append(Names.NEWLINE);
			output.append(category + subCategory.toString());
		}
	}
	/**
	 * Print Output
	 */
	private void printOutput(){
		printMapCounts(categoryMap);
		System.out.println();
		System.out.println(output.toString());
		logger.info(output.toString());
	}
}
