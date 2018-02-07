package stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import csv.CsvReader;

public class KickstarterStats {
	
	private CsvReader csvReader;
	
	/**
	 * 
	 * @param csvReader
	 * 
	 * Takes csvReader as dependency
	 * 
	 */
	public KickstarterStats(CsvReader csvReader)
	{
		this.csvReader = csvReader;
	}
	
	/**
	 * 
	 * @return Average Pledges for each title-length group (5-10-15 character titles, etc.)
	 * 
	 */
	public HashMap<String, Double> getDictOfAvgOfPledgesForAllTitleLengthGroups() 
	{
		Set<String> alltitleLengthGroups = csvReader.getPossibleTitleLengthGroups();
		
		HashMap<String, Double> results = new HashMap<String, Double>();
		double titleLengthGroupPledgeAvg;
		for(String titleLengthGroup : alltitleLengthGroups)
		{
			titleLengthGroupPledgeAvg = getAvgOfPledgesForTitleLengthGroup(titleLengthGroup);
			results.put(titleLengthGroup, titleLengthGroupPledgeAvg);
		}
		return results;
	}
	
	/**
	 * 
	 * @param titleLengthGroup
	 * @return  Average Pledges for one specific title-length group (e.g.-15 character titles)
	 * NB: titleLengthGroup is the factor of 5 to obtain the title length 
	 * 		- e.g. group 1 --> title is in the "less than 5 characters" group 
	 * 		- e.g. group 3 --> "less than 15 characters" group
	 * 
	 */
	public double getAvgOfPledgesForTitleLengthGroup(String titleLengthGroup)
	{
		List<Map<String, String>> listOfKickstarterProjects = csvReader.getKickstartersForTitleLengthGroup(titleLengthGroup);
		
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
	
	/**
	 * 
	 * @return Average Pledges for all years (2008 to 2016)
	 */
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
	
	/**
	 * 
	 * @param year
	 * @return Average Pledges for a single year (2008, 2009, 2010, etc.)
	 * 
	 */
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
	
	/**
	 * 
	 * @return Average Pledges for all categories (technology, design, food, documentaries, etc.)
	 */
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
	
	/**
	 * 
	 * @param category
	 * @return Average Pledges for one specific category (technology, design, food, documentaries, etc.)
	 * 
	 */
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
	
	/**
	 * 
	 * @return Average Pledged across all Kickstarters available in csv
	 */
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
	
	/**
	 * 
	 * @return Average "Funding Goal" across all Kickstarters available in csv
	 */
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
	
	/**
	 * 
	 * @return percentage of Kickstarters that Succeded
	 */
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




