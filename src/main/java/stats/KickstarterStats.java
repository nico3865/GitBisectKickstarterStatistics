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
	
	public double getGlobalAvgOfPledges()
	{
		List<Map<String, String>> listOfKickstarterProjects = csvReader.getListOfAllKickstarterRecordsFromCsv();
		
		double total = 0d;
		for(Map<String, String> project : listOfKickstarterProjects)
		{
			String pledgedAsString = project.get("pledged");
			double pledgedAmount = 0d;
			try {
				pledgedAmount = Double.parseDouble(pledgedAsString);
			} catch (NumberFormatException e) {
				// TODO collect all records that are badly formatted (usually due to commas or quotes in title) 
			}
			total += pledgedAmount;
		}
		double avgPledged = total / listOfKickstarterProjects.size();
		
		System.out.println("GlobalAvgPledged: "+avgPledged);
		
		return avgPledged; 
	}
	
	public double getGlobalAvgOfFundingGoals()
	{
		// TODO: create helper avg function that takes List<Map<String, String>> as input so it can be tested more easily
		List<Map<String, String>> listOfKickstarterProjects = csvReader.getListOfAllKickstarterRecordsFromCsv();
		
		double total = 0d;
		for(Map<String, String> project : listOfKickstarterProjects)
		{
			String pledgedAsString = project.get("goal");
			double goalAmount = 0d;
			try {
				goalAmount = Double.parseDouble(pledgedAsString);
			} catch (NumberFormatException e) {
				// TODO collect all records that are badly formatted (usually due to commas or quotes in title) 
			}
			total += goalAmount;
		}
		double avgGoal = total / listOfKickstarterProjects.size();
		
		System.out.println("GlobalAvgGoal: "+avgGoal);
		
		return avgGoal; 
	}
	
	public double getPercentageThatReachedFundingGoal()
	{
		// TODO: create helper avg function that takes List<Map<String, String>> as input so it can be tested more easily
		List<Map<String, String>> listOfKickstarterProjects = csvReader.getListOfAllKickstarterRecordsFromCsv();
		
		double numSuccesses = 0d;
		for(Map<String, String> project : listOfKickstarterProjects)
		{
			String projectStateAsString = project.get("state");
			if(projectStateAsString.equalsIgnoreCase("successful"))
			{
				numSuccesses++;
			}
		}
		double percentageSucceeded = numSuccesses / listOfKickstarterProjects.size();
		
		System.out.println("PercentageThatReachedFundingGoal: "+percentageSucceeded);
		
		return percentageSucceeded; 

	}
	
	

}




