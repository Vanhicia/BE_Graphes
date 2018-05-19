package org.insa.csv;

import org.junit.*;

public class TestPerformance {
	
	@Test
	public void testLecture() {
		//Lecture lect = new Lecture("/home/katran/Bureau/Maison/Graphes/BE_Graphes/input/haute-garonne.txt");
		Lecture lect = new Lecture("C:/Users/Alicia/eclipse-workspace/BE_Graphes/input/haute-garonne.txt");
		System.out.println("ici "+lect.getMapName());
		System.out.println("ici O "+lect.getlisteOrigine().get(0));
		
	}
}
