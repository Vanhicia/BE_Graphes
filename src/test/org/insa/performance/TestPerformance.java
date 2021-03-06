package org.insa.performance;


import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class TestPerformance {
	private ArrayList<ResultatExecutionAlgos> listeResultatPerformance;

	public TestPerformance() {
		this.listeResultatPerformance = new ArrayList<ResultatExecutionAlgos>();
	}


	public void doRun(String fileNameRead, String fileNameWrite, int typeEvaluation) {
		//Lecture lect = new Lecture("/home/katran/Bureau/Maison/Graphes/BE_Graphes/input/haute-garonne.txt");
		//Lecture lect = new Lecture("C:/Users/Utilisateur/Desktop/BE_Graphes/input/haute-garonne.txt");
		
		Lecture lect = new Lecture(fileNameRead);

		String mapName = lect.getMapName();

		Iterator<Integer> origineIter = lect.getListeOrigine().iterator();
		Iterator<Integer> destinationIter = lect.getListeDestination().iterator();


		while(origineIter.hasNext()) {
			ResultatExecutionAlgos  resultat = new ResultatExecutionAlgos ("C:/Users/Utilisateur/Desktop/3A MIC/Kimi/graphe/Maps/bretagne.mapgr", typeEvaluation, origineIter.next(), destinationIter.next());
			//ResultatExecutionAlgos resultat = new ResultatExecutionAlgos(lect.getMapName(), typeEvaluation, origineIter.next(), destinationIter.next());

			this.listeResultatPerformance.add(resultat);
			//System.out.println("Temps execution : Dijkstra = " + resultat.getTempsExecutionDijkstra() + "/ AStar = " + resultat.getTempsExecutionAStar());
			//System.out.println("Nb sommets visités: Dijkstra = " + resultat.getNbSommetsVisitesDijkstra() + " / AStar = " + resultat.getNbSommetsVisitesAStar());
		}

	
		Ecriture write = new Ecriture(fileNameWrite, mapName, this.listeResultatPerformance);
	}


	@Test
	public void testLecture() {
		//Lecture lect = new Lecture("/home/katran/Bureau/Maison/Graphes/BE_Graphes/input/haute-garonne.txt");
		//Lecture lect = new Lecture("C:/Users/Alicia/eclipse-workspace/BE_Graphes/input/haute-garonne.txt");
		//Lecture lect = new Lecture("C:/Users/Utilisateur/Desktop/BE_Graphes/input/haute-garonne.txt");
		Lecture lect = new Lecture("C:/Users/Utilisateur/Desktop/BE_Graphes/input/bretagne_15_25.txt");
		System.out.println("la carte : "+lect.getMapName());
		System.out.println("1ère origine : "+lect.getListeOrigine().get(0));
	}


}
