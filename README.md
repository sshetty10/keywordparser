#File Parsing application

1. Algorithm:
	1.Fetch absolute Path of the file from 
	2.Instantiate a Map<Category,count> with count 0 for each Category(case sensistive currently. Making it case insensitve just would require us to say map.get(category.toUpper() instead of map.get(category))
	3.For each line in the file
		i. Get the first Token.
		ii. Check if its a valid category. 
			If yes
				a. append the rest of the tokens to form a subcategory
				b. check if this combination is unique by saving the combinations in a hashset(case sensitive currently)
					if yes
					- Increment the map count for this category 
					- Add it to the output stringbuffer
					else
					-ignore the line
			else
				ignore the line


2. Project Architecture
	a. src/main/java
		i. com.sshetty.parser.main
			-ParserMain.java (Main class)
			-FileParser(Business logic)
		ii.com.sshetty.parser.util
			-Names.java (All the constants declared in this class)
	b. src/main/resources
		-log4j.properties(Currently has the file appender only to avoid duplicate outputs on the console)	
	c.src/test/java
		i.com.sshetty.parser.tests
			-FileParserTest.java (Class comprising of all the Unit tests)
	d. src/test/resources
		i.  input.txt
		ii. inputInvalid.txt
		iii. inputUnique.txt
		
3. Enhanced features
	Logging using log4j
	
5. Coding Approach: 
	1. Preferred Scanner over BufferedReader as BufferedReader is synchronized and we do not need it in a single threaded application.
	   (preferred performance over thread safety)
	2. Used string split function over string tokenizer. The Java API documentation promotes use of split over tokenizer and
	   mentions that tokenizer is included in latest classes only for backward compatibility.
	3. Separated file reading from processing of the each line (String). Good Junit practice to separate IO intensive operations
	 	from the method implementing business logic to improve test coverage for application specific business logic. (parseFile: IO and 
	 	tokenizeAndParseLine does the parsing)
	4. Used a hashSet to implement uniqueness of a category, subcategory pair.
	5. Used a map to maintain the count for each category. As fetching map.get(category) and map. 
		Preferred key as a String object because it is not only immutable but also
	   	the hashcode method of the String is quite foolproof and would return map.get(category) in a constant time.
	5. Extra whitespaces are trimmed while printing the output.
	 	
4. How to run:
	-Unzip the project file or get the src from github
	-New -> Java Project -> Unzipped file Location -> Finish
	-Build the Project: preferrably using maven - mvn clean install
	-Run the application
		a.Using the jar
			i. cd ${your target} folder
			ii. java -jar keywordparser-1.0.1.jar ${absolute path of file}
		b. From Eclipse
			i. ParserMain.java - > Run as Java Application



Maven goals:
mvn clean compile
mvn test
mvn install

