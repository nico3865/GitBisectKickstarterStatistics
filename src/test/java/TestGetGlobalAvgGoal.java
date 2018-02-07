import static org.hamcrest.CoreMatchers.is;
 import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
 import org.junit.Test;
 
 import csv.CsvReader;
 import stats.KickstarterStats;
 
 public class TestGetGlobalAvgGoal {
 
 	@Test
 	public void testNotNull() {
 				
		CsvReader dummyCsvReader = mock(CsvReader.class); //new CsvReader();
		List<Map<String, String>> listOfDummyKickstarterProjects = new ArrayList<Map<String, String>>();
		
//		ID ,name ,category ,main_category ,currency ,deadline ,goal ,launched ,pledged ,state ,backers ,country ,usd pledged ,,,,
//		1000002330,The Songs of Adelaide & Abullah,Poetry,Publishing,GBP,2015-10-09 11:36:00,1000,2015-08-11 12:12:28,0,failed,0,GB,0,,,,
//		1000004038,Where is Hank?,Narrative Film,Film & Video,USD,2013-02-26 00:20:50,45000,2013-01-12 00:20:50,220,failed,3,US,220,,,,
//		1000007540,ToshiCapital Rekordz Needs Help to Complete Album,Music,Music,USD,2012-04-16 04:24:11,5000,2012-03-17 03:24:11,1,failed,1,US,1,,,,
//		1000011046,Community Film Project: The Art of Neighborhood Filmmaking,Film & Video,Film & Video,USD,2015-08-29 01:00:00,19500,2015-07-04 08:35:03,1283,canceled,14,US,1283,,,,

		HashMap<String, String> dummyKickstarter1 = new HashMap<String, String>();
		HashMap<String, String> dummyKickstarter2 = new HashMap<String, String>();
		HashMap<String, String> dummyKickstarter3 = new HashMap<String, String>();
		HashMap<String, String> dummyKickstarter4 = new HashMap<String, String>();
		
		dummyKickstarter1.put("goal", "1000");
		dummyKickstarter2.put("goal", "2000");
		dummyKickstarter3.put("goal", "3000");
		dummyKickstarter4.put("goal", "4000");
		
		listOfDummyKickstarterProjects.add(dummyKickstarter1);
		listOfDummyKickstarterProjects.add(dummyKickstarter2);
		listOfDummyKickstarterProjects.add(dummyKickstarter3);
		listOfDummyKickstarterProjects.add(dummyKickstarter4);
		
		when(dummyCsvReader.getListOfAllKickstarterRecordsFromCsv()).thenReturn(listOfDummyKickstarterProjects);
		
		KickstarterStats classUnderTest = new KickstarterStats(dummyCsvReader);
		double avg = classUnderTest.getGlobalAvgOfFundingGoals();
		
		verify(dummyCsvReader).getListOfAllKickstarterRecordsFromCsv();
		
		assertThat(avg,is(2500d));

 		
 	}
 	
 	
 	
 	
 	
 
 }
