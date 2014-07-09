#File Parsing Application

Algorithm:
1. Fetch absolute Path of the file from user input
2. Instantiate a Map<Category,count> with count 0 for each Category(case sensistive currently. Making it case insensitve just would require us to say map.get(category.toUpper() instead of map.get(category))
2. For each line in the file
	i. Get the first Token.
	ii. Check if its a valid category. 
		If yes
			a. append the rest of the tokens to form a subcategory
			b. check if this combination is unique by saving the combinations in a hashset(case sensitive currently)
				if yes
				- Increment the map count for this category 
				- Add it to the output stringbuffer
				if no
				-ignore the line
		If no
			ignore the line


1. Project Architecture
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
		
2.Enhanced features
Logging using log4j


Maven goals:
mvn clean compile
mvn test
mvn install

