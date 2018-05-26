package org.insa.performance;

import org.junit.Test;

public class AllPerformanceTests {
	
	@Test
	public void testsPerformance() {
		TestPerformance test1Temps = new TestPerformance();
		test1Temps.doRun("C:/Users/Utilisateur/Desktop/BE_Graphes/input/bretagne_15_25.txt", "resultat_performance_bretagne_15_25_temps_bis.csv", 0);
		
		TestPerformance test1Distance = new TestPerformance();
		test1Distance.doRun("C:/Users/Utilisateur/Desktop/BE_Graphes/input/bretagne_15_25.txt", "resultat_performance_bretagne_15_25_distance_bis.csv", 1);
		
		/*TestPerformance test2Temps = new TestPerformance();
		test2Temps.doRun("C:/Users/Utilisateur/Desktop/BE_Graphes/input/bretagne_50_100.txt", "resultat_performance_bretagne_50_100_temps.csv", 0);
		
		TestPerformance test2Distance = new TestPerformance();
		test2Distance.doRun("C:/Users/Utilisateur/Desktop/BE_Graphes/input/bretagne_50_100.txt", "resultat_performance_bretagne_50_100_distance.csv", 1);

		TestPerformance test3Temps = new TestPerformance();
		test3Temps.doRun("C:/Users/Utilisateur/Desktop/BE_Graphes/input/bretagne_150_170.txt", "resultat_performance_bretagne_150_170_temps.csv", 0);
		
		TestPerformance test3Distance = new TestPerformance();
		test3Distance.doRun("C:/Users/Utilisateur/Desktop/BE_Graphes/input/bretagne_150_170.txt", "resultat_performance_bretagne_150_170_distance.csv", 1);
	}*/

}
