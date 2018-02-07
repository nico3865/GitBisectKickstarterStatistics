package main;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import csv.CsvReader;
import stats.KickstarterStats;

public class PrintKickstarterStats {

	public static void main(String[] args) {
		
		System.out.println("========================================== KICKSTARTER STATS ==========================================");
		
		CsvReader csvReader = new CsvReader();
		KickstarterStats classUnderTest = new KickstarterStats(csvReader);
        double globalAvgPledged = classUnderTest.getGlobalAvgOfPledges();
        double globalAvgFundingGoal = classUnderTest.getGlobalAvgOfFundingGoals();
        double percentageSucceeded = classUnderTest.getPercentageThatReachedFundingGoal();
        
        System.out.println("globalAvgPledged ==> "+globalAvgPledged);
        System.out.println("globalAvgFundingGoal ==> "+globalAvgFundingGoal);
        System.out.println("percentageSucceeded ==> "+percentageSucceeded);
        System.out.println();
        
        HashMap<String, Double> getAvgOfPledgesForAllCategories = classUnderTest.getDictOfAvgOfPledgesForAllCategories();
        for(String category : getAvgOfPledgesForAllCategories.keySet())
        {
        	Double avgForCategory = getAvgOfPledgesForAllCategories.get(category);
        	System.out.println("avgPledgedForCategory: "+ category + " ==> " +avgForCategory);
        }
        System.out.println();
        
        HashMap<String, Double> getAvgOfPledgesForAllYears = classUnderTest.getDictOfAvgOfPledgesForAllYears();
        for(String year : getAvgOfPledgesForAllYears.keySet())
        {
        	Double avgForYear = getAvgOfPledgesForAllYears.get(year);
        	System.out.println("avgPledgedForYear: "+ year + " ==> " +avgForYear);
        }
        System.out.println();
        
        HashMap<String, Double> getAvgOfPledgesForAllTitleLengthGroups = classUnderTest.getDictOfAvgOfPledgesForAllTitleLengthGroups();
        for(String titleLengthGroup : getAvgOfPledgesForAllTitleLengthGroups.keySet())
        {
        	Double avgForTitleLengthGroup = getAvgOfPledgesForAllTitleLengthGroups.get(titleLengthGroup);
        	System.out.println("avgPledgedForTitleLengthGroup: "+ titleLengthGroup + " ==> " +avgForTitleLengthGroup);
        }
        System.out.println();
        
        System.out.println("========================================== /KICKSTARTER STATS ==========================================");

	}

}
