package stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import csv.CsvReader;

public class KickstarterStats {
	
	private CsvReader csvReader;
	
	public KickstarterStats(CsvReader csvReader)
	{
		this.csvReader = csvReader;
	}
	
	
	public HashMap<String, Double> getDictOfAvgOfPledgesForAllYears() 
	{
		Set<String> allYears = csvReader.getPossibleYears();
		
		HashMap<String, Double> results = new HashMap<String, Double>();
		double yearlyPledgeAvg;
		for(String year : allYears)
		{
			yearlyPledgeAvg = getAvgOfPledgesForYear(year);
			results.put(year, yearlyPledgeAvg);
		}
		return results;
	}
	
	public double getAvgOfPledgesForYear(String year)
	{
		List<Map<String, String>> listOfKickstarterProjects = csvReader.getKickstartersForYear(year);
		
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
				
		return avgPledged; 
	}
	
	
	public HashMap<String, Double> getDictOfAvgOfPledgesForAllCategories() 
	{
		Set<String> allCategories = csvReader.getPossibleCategories();
		
		HashMap<String, Double> results = new HashMap<String, Double>();
		double categoryPledgeAvg = 0d;
		for(String category : allCategories)
		{
			categoryPledgeAvg = getAvgOfPledgesForCategory(category);
			results.put(category, categoryPledgeAvg);
		}
		return results;
	}
	
	public double getAvgOfPledgesForCategory(String category)
	{
		List<Map<String, String>> listOfKickstarterProjects = csvReader.getKickstartersForCategory(category);
		
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
				
		return avgPledged; 
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
						
		return avgPledged; 
	}
	
	public double getGlobalAvgOfFundingGoals()
	{
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
			total = goalAmount;
		}
		double avgGoal = total / listOfKickstarterProjects.size();
						
		return avgGoal; 
	}
	
	public double getPercentageThatReachedFundingGoal()
	{
		List<Map<String, String>> listOfKickstarterProjects = csvReader.getKickstartersForCategory("Design");
		
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
				
		return percentageSucceeded; 

	}
	
	
	

}




