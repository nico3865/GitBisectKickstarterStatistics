package csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.URL;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * 
 * Csv Reader, adapted from: https://examples.javacodegeeks.com/core-java/apache/commons/csv-commons/writeread-csv-files-with-apache-commons-csv-example/
 *
 */
public class CsvReader {
	
	private static String fileName;
	
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

	public CsvReader(String csvFilePath) {
		fileName = "/Users/nicolasg-chausseau/Documents/workspace/GitBisectKickstarterStatistics/data/ks-projects-201612-2.csv";
	}

	
	public static List<CSVRecord> getListOfRecordsFromCsv() {

		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);

		try {
			List<CSVRecord> allEntriesFromCsv = new ArrayList<CSVRecord>();
			fileReader = new FileReader(fileName);
			csvFileParser = new CSVParser(fileReader, csvFileFormat);
			List<CSVRecord> csvRecords = csvFileParser.getRecords();

			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord record = csvRecords.get(i);
				allEntriesFromCsv.add(record);
			}

			// Print the new student list for debug:
			for (CSVRecord entry : allEntriesFromCsv) {
				System.out.println(entry.toMap().get("deadline"));
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

}
