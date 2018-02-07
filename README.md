# GitBisectKickstarterStatistics

This program compiles various statistics about Kickstarter projects.

### Clone and Setup instructions:

- clone it from: https://github.com/nico3865/GitBisectKickstarterStatistics.git
- import it in Eclipse as a Gradle project
- Run Gradle 
- Run the Main: CollectKickstarterStats.java
- Run JUnit (some very bad dummy tests are in the src/test/java folder) 

You will use Git Bisect to find two commits that introduced two bugs in this program.

### We know that:

- The two bugs are: 
	1. wrong output for percentage of successful Kickstarters.
	2. wrong output for average for funding goals.
- Those two numbers are returned to the main by two functions in the KickstarterStats class: 
	1. getGlobalAvgOfFundingGoals()
	2. getPercentageThatReachedFundingGoal() 
- We know that those functions used to work correctly at those commits:
	1. getGlobalAvgOfFundingGoals() --> d29668c2b20bb87c8793e0c0c0338c8314f9b6ab
	2. getPercentageThatReachedFundingGoal() --> 56945aced56e4fa07b36dbe32a7a63f28a4a4135

### Here are the steps to find the bugs with Git Bisect:

1. Make two Mockito tests for the two buggy functions (This will allow you to isolate the calculations from the csv reader logic a-d thus determine where each bug comes from).
	- import Mockito:
		- add to build.gradle: ```testCompile "org.mockito:mockito-core:1.+"```
		- run gradle
	- then test the 2 faulty functions with Mockito:
		- mock the csv reader, and a relevant function (make it return a Map<String, String> of kickstarter entries)
		- run your test on the bad and the good commits (d29668c2b20bb87c8793e0c0c0338c8314f9b6ab and 56945aced56e4fa07b36dbe32a7a63f28a4a4135)
			- make sure that it fails on the bad commits and succeeds on the good commits
		- make a copy of your two test files on your Desktop (you will need it as you checkout different commits that didn't have that test yet)
2. Then run Git Bisect, 
	- open a terminal in your local git repo's folder:
	- ```git bisect start```
	- ```git bisect bad``` to indicate that the current head is bad
	- ```git bisect good 56945aced56e4fa07b36dbe32a7a63f28a4a4135``` to indicate last known good commit for this percentage feature. (For the avg goal feature: ```d29668c2b20bb87c8793e0c0c0338c8314f9b6ab```)
	- then, paste your test back in the test folder --> and run it. 
		- Depending on the result of the test, type ```git bisect bad``` or ```git bisect good```
	- Repeat the last step, until git finds the first bad commit.
	- NB, use: ```git bisect reset``` if you made a mistake and want to start over.

Of course since it's a small repo, with a short history of commit, it would be relatively easy to find the bugs using 

- Git-grep, or 
- Visualizing a file's history with git-blame (see feature accessible on GitHub's website)

But the goal here is to get familiar with Git Bisect.