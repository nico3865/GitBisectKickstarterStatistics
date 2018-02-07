package main;

import static org.junit.Assert.assertNotNull;

import csv.CsvReader;
import stats.KickstarterStats;

public class CollectKickstarterStats {

	public static void main(String[] args) {
		
		System.out.println("========================================== KICKSTARTER STATS ==========================================");
		
		CsvReader csvReader = new CsvReader("bla");
		KickstarterStats classUnderTest = new KickstarterStats(csvReader);
        double globalAvgPledged = classUnderTest.getGlobalAvgOfPledges();
        double globalAvgFundingGoal = classUnderTest.getGlobalAvgOfFundingGoals();
        
        System.out.println("========================================== /KICKSTARTER STATS ==========================================");

	}

}
