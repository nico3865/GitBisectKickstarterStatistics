import static org.junit.Assert.*;

import org.junit.Test;

import csv.CsvReader;
import stats.KickstarterStats;

public class TestGetGlobalAvgPledged {

	@Test
	public void testNotNull() {
		
		CsvReader csvReader = new CsvReader("bla");
		KickstarterStats classUnderTest = new KickstarterStats(csvReader);
        assertNotNull("making sure that a non-null avg pledged is returned", classUnderTest.getGlobalAvgOfPledges());
		
	}
	
	
	

}


