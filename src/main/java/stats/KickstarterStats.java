package stats;

import java.util.List;
import java.util.Map;

import csv.CsvReader;

public class KickstarterStats {
	
	private CsvReader csvReader;
	
	public KickstarterStats(CsvReader csvReader)
	{
		this.csvReader = csvReader;
	}
	
	public double getAvgOfPledges()
	{
		CsvReader reader = new CsvReader("blah");
		List<Map<String, String>> listOfKickstarterProjects = csvReader.getListOfRecordsFromCsv();
		
		double total = 0d;
		for(Map<String, String> project : listOfKickstarterProjects)
		{
			String pledgedAsString = project.get("pledged");
			double pledgedAmount = 0d;
			try {
				pledgedAmount = Double.parseDouble(pledgedAsString);
			} catch (NumberFormatException e) {
				//e.printStackTrace(); 
				// TODO collect all records that are badly formatted (usually due to commas or quotes in title) 
			}
			total += pledgedAmount;
		}
		double avgPledged = total / listOfKickstarterProjects.size();
		
		System.out.println("avgPledged: "+avgPledged);
		
		return avgPledged; 
	}
		
}