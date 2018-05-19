package org.insa.csv;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.algo.shortestpath.AStarAlgorithm;
import org.insa.algo.shortestpath.BellmanFordAlgorithm;
import org.insa.algo.shortestpath.DijkstraAlgorithm;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.algo.shortestpath.ShortestPathSolution;
import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.junit.*;

public class TestPerformance {

	@Test
	public void testLecture() {
		//Lecture lect = new Lecture("/home/katran/Bureau/Maison/Graphes/BE_Graphes/input/haute-garonne.txt");
		Lecture lect = new Lecture("C:/Users/Alicia/eclipse-workspace/BE_Graphes/input/haute-garonne.txt");
		System.out.println("la carte : "+lect.getMapName());
		System.out.println("1ère origine : "+lect.getListeOrigine().get(0));
	}

	private void testScenario (String mapName, int typeEvaluation, int origine, int destination) throws Exception {
		// Create a graph reader.
		GraphReader reader = new BinaryGraphReader(
				new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

		// Read the graph.
		Graph graph = reader.read();

		/* Création de l'arcInspector */
		ArcInspector arcInspector;
		/* Si évaluation en temps */
		if (typeEvaluation == 0) {
			arcInspector = ArcInspectorFactory.getAllFilters().get(2);
		}
		/* Sinon évaluation en distance */
		else {
			arcInspector = ArcInspectorFactory.getAllFilters().get(0);
		}

		ShortestPathData data = new ShortestPathData(graph, graph.get(origine),graph.get(destination), arcInspector);

		DijkstraAlgorithm D = new DijkstraAlgorithm(data);
		AStarAlgorithm A = new AStarAlgorithm(data);

		/* Recuperation des solutions de Dijkstra et AStar */
		ShortestPathSolution solutionD = D.run();
		ShortestPathSolution solutionA = A.run();

		/* Pas de chemin trouve */
		if (solutionD.getPath() == null) {
			System.out.print("(infini) ");
		}
		/* Un plus court chemin trouve */
		else {
			/* Calcul du cout de la solution */
			/* Evaluation en temps */
			if (typeEvaluation == 0) {
				double costSolutionD = solutionD.getPath().getMinimumTravelTime();
				double costSolutionA = solutionA.getPath().getMinimumTravelTime();
			}
			/* Evaluation en distance */
			else {
				float costSolutionD = solutionD.getPath().getLength();
				float costSolutionA = solutionA.getPath().getLength();
			}
		}
	}


	@Test
	public void testsPerformance() {
		//Lecture lect = new Lecture("/home/katran/Bureau/Maison/Graphes/BE_Graphes/input/haute-garonne.txt");
		Lecture lect = new Lecture("C:/Users/Alicia/eclipse-workspace/BE_Graphes/input/haute-garonne.txt");

		String mapName = lect.getMapName();

		Iterator<Integer> origineIter = lect.getListeOrigine().iterator();
		Iterator<Integer> destinationIter = lect.getListeDestination().iterator();

		for (int i=0; i<2; i++) {
			while(origineIter.hasNext()) {
				TestPerformance test = new TestPerformance();
				test.testScenario(lect.getMapName(), i, origineIter.next(), destinationIter.next());
			}
		}

	}

}
