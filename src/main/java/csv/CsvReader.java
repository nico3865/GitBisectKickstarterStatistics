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

	public CsvReader(String csvFilePath) 
	{
		fileName = csvFilePath;
	}
	
	public CsvReader() 
	{
		URL fileNameURL = CsvReader.class.getResource("ks-projects-201612-2.csv");
		fileName = fileNameURL.getPath();
		//System.out.println(fileName);
	}

	
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
	
	
	private void getSubsetsInData() 
	{
		// TODO: check cache first before doing all this work:
		
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
			double yearDouble = Double.parseDouble(year);
			if(yearDouble > 2008 && year.matches("20\\d{2}"))
			{
				List<Map<String, String>> bucketForYear = hashedByYear.get(year);
				if(bucketForYear == null) { bucketForYear = new ArrayList<Map<String, String>>(); }
				bucketForYear.add(kickstarterProject);
				hashedByYear.put(year, bucketForYear);	
			}
			
		}
		
	}
	
	public Set<String> getPossibleCategories() 
	{
		if(hashedByCategory == null)
		{
			getSubsetsInData();
		}
		return hashedByCategory.keySet();
	}
	
	public List<Map<String, String>> getKickstartersForCategory(String category) 
	{
		if(hashedByCategory == null)
		{
			getSubsetsInData();
		}
		return hashedByCategory.get(category);
	}
	
	public Set<String> getPossibleYears() 
	{
		if(hashedByYear == null)
		{
			getSubsetsInData();
		}
		return hashedByYear.keySet();
	}
	
	public List<Map<String, String>> getKickstartersForYear(String year) 
	{
		if(hashedByYear == null)
		{
			getSubsetsInData();
		}
		return hashedByYear.get(year);
	}


}
