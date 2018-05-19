package org.insa.csv;

import org.junit.Test;

public class AllPerformanceTests {
	
	@Test
	public static void essai() {
		TestPerformance test1Temps = new TestPerformance();
		test1Temps.doRun("C:/Users/Alicia/eclipse-workspace/BE_Graphes/input/haute-garonne.txt", "resultat_performance_haute_garonne_temps.csv", 0);
		
		TestPerformance test1Distance = new TestPerformance();
		test1Distance.doRun("C:/Users/Alicia/eclipse-workspace/BE_Graphes/input/haute-garonne.txt", "resultat_performance_haute_garonne_distance.csv", 1);
		
		TestPerformance test2Temps = new TestPerformance();
		test2Temps.doRun("C:/Users/Alicia/eclipse-workspace/BE_Graphes/input/belgium.txt", "resultat_performance_belgium_temps.csv", 0);
		
		TestPerformance test2Distance = new TestPerformance();
		test2Distance.doRun("C:/Users/Alicia/eclipse-workspace/BE_Graphes/input/belgium.txt", "resultat_performance_belgium_distance.csv", 1);
	}

}
