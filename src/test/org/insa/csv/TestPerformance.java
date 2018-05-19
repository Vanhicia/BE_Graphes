package org.insa.csv;


import java.util.ArrayList;
import java.util.Iterator;
import org.junit.*;

public class TestPerformance {
	private ArrayList<TempsExecutionAlgos> listeResultatPerformance;

	public TestPerformance() {
		this.listeResultatPerformance = new ArrayList<TempsExecutionAlgos>();
	}


	public void doRun(String fileNameRead, String fileNameWrite, int typeEvaluation) {
		//Lecture lect = new Lecture("/home/katran/Bureau/Maison/Graphes/BE_Graphes/input/haute-garonne.txt");
		//Lecture lect = new Lecture("C:/Users/Utilisateur/Desktop/BE_Graphes/input/haute-garonne.txt");
		Lecture lect = new Lecture(fileNameRead);

		String mapName = lect.getMapName();

		Iterator<Integer> origineIter = lect.getListeOrigine().iterator();
		Iterator<Integer> destinationIter = lect.getListeDestination().iterator();


		while(origineIter.hasNext()) {
			TempsExecutionAlgos resultat = new TempsExecutionAlgos("C:/Users/Utilisateur/Desktop/3A MIC/Kimi/graphe/Maps/haute-garonne.mapgr", typeEvaluation, origineIter.next(), destinationIter.next());
			//TempsExecutionAlgos resultat = new TempsExecutionAlgos(lect.getMapName(), typeEvaluation, origineIter.next(), destinationIter.next());
			this.listeResultatPerformance.add(resultat);
		}

		//appel de la classe Ecriture (classe à implémenter) avec en argument fileNameWrit
		Ecriture write = new Ecriture(fileNameWrite, mapName, this.listeResultatPerformance);
	}


	@Test
	public void testLecture() {
		//Lecture lect = new Lecture("/home/katran/Bureau/Maison/Graphes/BE_Graphes/input/haute-garonne.txt");
		//Lecture lect = new Lecture("C:/Users/Alicia/eclipse-workspace/BE_Graphes/input/haute-garonne.txt");
		Lecture lect = new Lecture("C:/Users/Utilisateur/Desktop/BE_Graphes/input/haute-garonne.txt");
		System.out.println("la carte : "+lect.getMapName());
		System.out.println("1ère origine : "+lect.getListeOrigine().get(0));
	}


}
