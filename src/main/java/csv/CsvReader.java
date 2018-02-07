package csv;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * 
 * Csv Reader, adapted from: https://examples.javacodegeeks.com/core-java/apache/commons/csv-commons/writeread-csv-files-with-apache-commons-csv-example/
 *
 */
public class CsvReader {
	
	private String fileName;
	private List<Map<String, String>> allEntriesFromCsv = null;
	
	private HashMap<String, List<Map<String, String>>> hashedByCategory = null;
	private HashMap<String, List<Map<String, String>>> hashedByYear = null;
	private HashMap<String, List<Map<String, String>>> hashedByTitleLength = null;
	
	private static final String[] FILE_HEADER_MAPPING = {
			"ID", 
			"name", 
			"category", 
			"main_category", 
			"currency",
			"deadline", 
			"goal", 
			"launched", 
			"pledged", 
			"state", 
			"backers", 
			"country", 
			"usd pledged" 
			};

	/**
	 * 
	 * @param csvFilePath <-- only if you want to use a different file than the default repo one
	 */
	public CsvReader(String csvFilePath) 
	{
		fileName = csvFilePath;
	}
	
	/**
	 * 
	 * by default will use the csv that is located next to this class, (in the same folder)
	 * 
	 */
	public CsvReader() 
	{
		URL fileNameURL = CsvReader.class.getResource("ks-projects-201612-2.csv");
		fileName = fileNameURL.getPath();
		//System.out.println(fileName);
	}

	/**
	 * 
	 * @return basically the csv held in memory inside a List of Maps (each row is a map, you can access a row's property with row.get("main_category"))
	 */
	public List<Map<String, String>> getListOfAllKickstarterRecordsFromCsv() {
		
		if(allEntriesFromCsv != null)
		{
			return allEntriesFromCsv;
		}

		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);

		try {
			allEntriesFromCsv = new ArrayList<Map<String, String>>();
			fileReader = new FileReader(fileName);
			csvFileParser = new CSVParser(fileReader, csvFileFormat);
			List<CSVRecord> csvRecords = csvFileParser.getRecords();

			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord record = csvRecords.get(i);
				Map<String, String> recordAsMap = record.toMap();
				allEntriesFromCsv.add(recordAsMap);
			}

			// Print the new student list for debug:
			for (Map<String, String> entry : allEntriesFromCsv) {
				//System.out.println(entry.get("deadline"));
			}
			
			return allEntriesFromCsv;
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				csvFileParser.close();
			} catch (IOException e) {
				System.out.println("Error while closing fileReader/csvFileParser !!!");
				e.printStackTrace();
			}
		}
		return null;

	}
	
	/**
	 * 
	 * creates subsets from the data (basically like indexing) 
	 * 		- hashes the list to access easily by property
	 * 		- traverses the csv-as-List "cached" in this class
	 * 
	 */
	private void getSubsetsInData() 
	{
		if(allEntriesFromCsv == null)
		{
			getListOfAllKickstarterRecordsFromCsv();
		}
		if(hashedByCategory != null 
				&& hashedByYear != null
				&& hashedByTitleLength != null)
		{
			return;
		}
		
		for(Map<String, String> kickstarterProject : allEntriesFromCsv)
		{
			// hash by category:
			if(hashedByCategory == null){ hashedByCategory = new HashMap<String, List<Map<String, String>>>(); }
			String category = kickstarterProject.get("main_category");
			List<Map<String, String>> bucketForCategory = hashedByCategory.get(category);
			if(bucketForCategory == null) { bucketForCategory = new ArrayList<Map<String, String>>(); }
			bucketForCategory.add(kickstarterProject);
			hashedByCategory.put(category, bucketForCategory);
			
			// hash by year:
			if(hashedByYear == null){ hashedByYear = new HashMap<String, List<Map<String, String>>>(); }
			String year = kickstarterProject.get("launched").split("-")[0];
			double yearAsDouble = Double.parseDouble(year);
			if(yearAsDouble > 2008 && year.matches("20\\d{2}"))
			{
				List<Map<String, String>> bucketForYear = hashedByYear.get(year);
				if(bucketForYear == null) { bucketForYear = new ArrayList<Map<String, String>>(); }
				bucketForYear.add(kickstarterProject);
				hashedByYear.put(year, bucketForYear);	
			}
			
			// hash by title length:
			if(hashedByTitleLength == null){ hashedByTitleLength = new HashMap<String, List<Map<String, String>>>(); }
			int titleLength = kickstarterProject.get("name").length();
			int titleLengthGroup = titleLength / 5;
			List<Map<String, String>> bucketForTitleLengthGroup = hashedByTitleLength.get(titleLengthGroup);
			if(bucketForTitleLengthGroup == null) { bucketForTitleLengthGroup = new ArrayList<Map<String, String>>(); }
			bucketForTitleLengthGroup.add(kickstarterProject);
			hashedByTitleLength.put(""+titleLengthGroup, bucketForTitleLengthGroup);	
			
		}
		
	}
	
	/**
	 * 
	 * @return the Set of all different category found in the csv  
	 */
	public Set<String> getPossibleCategories() 
	{
		if(hashedByCategory == null)
		{
			getSubsetsInData();
		}
		return hashedByCategory.keySet();
	}
	
	/**
	 * 
	 * @param category (e.g. "Design", "Food", "Technology", etc.)
	 * @return the rows (Kickstarter projects) for that category, as List of Maps
	 */
	public List<Map<String, String>> getKickstartersForCategory(String category)
	{
		if(hashedByCategory == null)
		{
			getSubsetsInData();
		}
		return hashedByCategory.get(category);
	}
	
	/**
	 * 
	 * @return the Set of all different years with Kickstarters found in the csv  
	 */
	public Set<String> getPossibleYears() 
	{
		if(hashedByYear == null)
		{
			getSubsetsInData();
		}
		return hashedByYear.keySet();
	}
	
	/**
	 * 
	 * @param year (e.g. "Design", "Food", "Technology", etc.)
	 * @return the rows (Kickstarter projects) for that year, as List of Maps
	 */
	public List<Map<String, String>> getKickstartersForYear(String year) 
	{
		if(hashedByYear == null)
		{
			getSubsetsInData();
		}
		return hashedByYear.get(year);
	}
	
	/**
	 * 
	 * @return the Set of all different titlelengthGroups found in the csv
	 *   	- titlelengthGroups numbers are: 1,2,3,4,5 etc.
	 *   	- they represent the factor that, multiplied to 5, gives the title length (e.g. group 1 has less than 5 character titles, group 3 has less than 15 character titles)
	 */
	public Set<String> getPossibleTitleLengthGroups() 
	{
		if(hashedByTitleLength == null)
		{
			getSubsetsInData();
		}
		return hashedByTitleLength.keySet();
	}
	
	/**
	 * 
	 * @param titleLength (e.g. 1,2,3,4,5,6,7, etc.)
	 *   	- titlelengthGroups numbers are: 1,2,3,4,5 etc.
	 *   	- they represent the factor that, multiplied to 5, gives the title length (e.g. group 1 has less than 5 character titles, group 3 has less than 15 character titles)
	 * @return the rows (Kickstarter projects) for that title-length-group, as List of Maps
	 */
	public List<Map<String, String>> getKickstartersForTitleLengthGroup(String titleLength) 
	{
		if(hashedByTitleLength == null)
		{
			getSubsetsInData();
		}
		return hashedByTitleLength.get(titleLength);
	}


}
