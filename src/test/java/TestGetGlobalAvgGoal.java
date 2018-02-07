import static org.junit.Assert.*;

import org.junit.Test;

import csv.CsvReader;
import stats.KickstarterStats;

public class TestGetGlobalAvgGoal {

	@Test
	public void testNotNull() {
		
		CsvReader csvReader = new CsvReader();
		KickstarterStats classUnderTest = new KickstarterStats(csvReader);
        assertNotNull("making sure that a non-null avg goal is returned", classUnderTest.getGlobalAvgOfFundingGoals());
		
	}
	
	
	
	
	

}


