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
        
        HashMap<String, Double> getAvgOfPledgesForAllCategories = classUnderTest.getDictOfAvgOfPledgesForAllCategories();
        for(String category : getAvgOfPledgesForAllCategories.keySet())
        {
        	Double avgForCategory = getAvgOfPledgesForAllCategories.get(category);
        	System.out.println("avgForCategory: "+ category + " ==> " +avgForCategory);
        }
        
        HashMap<String, Double> getAvgOfPledgesForAllYears = classUnderTest.getDictOfAvgOfPledgesForAllYears();
        for(String year : getAvgOfPledgesForAllYears.keySet())
        {
        	Double avgForYear = getAvgOfPledgesForAllYears.get(year);
        	System.out.println("avgForYear: "+ year + " ==> " +avgForYear);
        }
        
        System.out.println("========================================== /KICKSTARTER STATS ==========================================");

	}

}
