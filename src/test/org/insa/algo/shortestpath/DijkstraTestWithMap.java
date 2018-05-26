package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.graph.Graph;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;

import org.junit.Test;

public class DijkstraTestWithMap {

	@Test
	// typeEvaluation : 0 = temps, 1 = distance
	public void testScenario(String mapName, int typeEvaluation, int origine, int destination) throws Exception {
		//public void testScenario(String mapName, int typeEvaluation, Node origine, Node destination) throws Exception {

		// Create a graph reader.
		GraphReader reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

		// Read the graph.
		Graph graph = reader.read();

		if (typeEvaluation!=0 && typeEvaluation!=1) {
			System.out.println("Argument invalide");
		} else {
			if (origine<0 || destination<0 || origine>=(graph.size()-1) || destination>=(graph.size()-1)) { // On est hors du graphe. / Sommets inexistants
				System.out.println("ERREUR : Paramètres invalides ");
				
			} else {
				ArcInspector arcInspectorDijkstra;
				
				if (typeEvaluation == 0) { //Temps
					System.out.println("Mode : Temps");
					arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(2);
				} else {
					System.out.println("Mode : Distance");
					arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(0);
				}
				
				
				//System.out.println("Chemin de la carte : "+mapName);
				System.out.println("Origine : " + origine);
				System.out.println("Destination : " + destination);
				
				if(origine==destination) {
					System.out.println("Origine et Destination identiques");
					System.out.println("Cout solution: 0");
					
				} else {			
					ShortestPathData data = new ShortestPathData(graph, graph.get(origine),graph.get(destination), arcInspectorDijkstra);
		
					BellmanFordAlgorithm B = new BellmanFordAlgorithm(data);
					DijkstraAlgorithm D = new DijkstraAlgorithm(data);
					
					// Recuperation des solutions de Bellman et Dijkstra pour comparer 
					ShortestPathSolution solution = D.run();
					ShortestPathSolution expected = B.run();
	
					
					if (solution.getPath() == null) {
						assertEquals(expected.getPath(), solution.getPath());
						System.out.println("PAS DE SOLUTION");
						System.out.println("(infini) ");
					}
					// Un plus court chemin trouve 
					else {
						double costSolution;
						double costExpected;
						if(typeEvaluation == 0) { //Temps
							//Calcul du cout de la solution 
							costSolution = solution.getPath().getMinimumTravelTime();
							costExpected = expected.getPath().getMinimumTravelTime();
						} else {
							costSolution = solution.getPath().getLength();
							costExpected = expected.getPath().getLength();
						}
						assertEquals(costExpected, costSolution, 0.001);
						System.out.println("Cout solution: " + costSolution);
					}
				}
			}
		}
/*		if (typeEvaluation == 0) {
			//System.out.println("type d'evaluation: temps");


			ArcInspector arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(2);


			ShortestPathData data = new ShortestPathData(graph, graph.get(origine),graph.get(destination), arcInspectorDijkstra);

			BellmanFordAlgorithm B = new BellmanFordAlgorithm(data);
			DijkstraAlgorithm D = new DijkstraAlgorithm(data);

			// Recuperation des solutions de Bellman et Dijkstra pour comparer 
			ShortestPathSolution solution = D.run();
			ShortestPathSolution expected = B.run();

			System.out.println(mapName);
			System.out.println("origine : " + origine);
			System.out.println("destination : " + destination);
			System.out.println("origine : " + graph.get(origine));
			System.out.println("destination : " + graph.get(destination));

			// Pas de chemin trouve 
			if (solution.getPath() == null) {
				assertEquals(expected.getPath(), solution.getPath());
				System.out.print("(infini) ");
			}
			// Un plus court chemin trouve 
			else {
				// Calcul du cout de la solution 
				double costSolution = solution.getPath().getMinimumTravelTime();
				double costExpected = expected.getPath().getMinimumTravelTime();
				
				assertEquals(costExpected, costSolution, 0.001);

				System.out.println("Cout solution: " + costSolution);
			}
		}			
		else if (typeEvaluation == 1) {
			//System.out.println("type d'evaluation: distance");

			ArcInspector arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(0);

			ShortestPathData data = new ShortestPathData(graph, graph.get(origine),graph.get(destination), arcInspectorDijkstra);

			BellmanFordAlgorithm B = new BellmanFordAlgorithm(data);
			DijkstraAlgorithm D = new DijkstraAlgorithm(data);

			//Recuperation des solutions de Bellman et Dijkstra pour comparer 
			ShortestPathSolution solution = D.run();
			ShortestPathSolution expected = B.run();

			//System.out.println(mapName);
			System.out.println("Origine : " + origine);
			System.out.println("Destination : " + destination);
			System.out.println("origine : " + origine);
			System.out.println("destination : " + destination);
			System.out.println("origine : " + graph.get(origine));
			System.out.println("destination : " + graph.get(destination));

			// Pas de chemin trouve 
			if (solution.getPath() == null) {
				assertEquals(expected.getPath(), solution.getPath());
				System.out.println("PAS DE SOLUTION TROUVEE");
				System.out.print("Cout solution: (infini) ");
			}
			// Un plus court chemin trouve 
			else {				
				//Calcul du cout de la solution 
				float costSolution = solution.getPath().getLength();
				float costExpected = expected.getPath().getLength();
				assertEquals(costExpected, costSolution, 0.001);

				System.out.println("Cout solution: " + costSolution);
			}
		}
		else {
			System.out.println("Argument invalide");
		}*/
		System.out.println();
		System.out.println();
	}


	@Test
	/* Verifie que le temps du chemin le plus rapide est inferieur au temps du chemin le plus court */
	/* Et verifie que la distance du chemin le plus rapide est superieur a la distance du chemin le plus court */
	public void testScenarioSansOracle(String mapName) throws Exception {
		//public void testScenario(String mapName, int typeEvaluation, Node origine, Node destination) throws Exception {

		double costFastestSolutionInTime = -1f;
		double costFastestSolutionInDistance = -1f;
		double costShortestSolutionInTime = -1f;
		double costShortestSolutionInDistance = -1f;

		// Create a graph reader.
		GraphReader reader = new BinaryGraphReader(
				new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

		// Read the graph.
		Graph graph = reader.read();

		/** Recherche du chemin le plus rapide **/

		System.out.println("type d'evaluation: temps");

		ArcInspector arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(2);

		ShortestPathData data = new ShortestPathData(graph, graph.get(0),graph.get(graph.size()-1), arcInspectorDijkstra);

		DijkstraAlgorithm D = new DijkstraAlgorithm(data);

		/* Recuperation de la solution de Dijkstra */
		ShortestPathSolution solution = D.run();

		System.out.println(mapName);
		/*System.out.println("origine : " + origine);
			System.out.println("destination : " + destination);*/
		System.out.println("origine : " + graph.get(0));
		System.out.println("destination : " + graph.get(graph.size()-1));

		/* Pas de chemin trouve */
		if (solution.getPath() == null) {
			System.out.print("(infini) ");
		}
		/* Un plus court chemin trouve */
		else {
			/* Calcul du cout de la solution (en temps et en distance) */
			costFastestSolutionInTime = solution.getPath().getMinimumTravelTime();
			costFastestSolutionInDistance = solution.getPath().getLength();
		}


		/** Recherche du chemin le plus court **/

		arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(0);

		data = new ShortestPathData(graph, graph.get(0),graph.get(graph.size()-1), arcInspectorDijkstra);

		D = new DijkstraAlgorithm(data);

		/* Recuperation de la solution de Dijkstra */
		solution = D.run();

		System.out.println(mapName);
		/*System.out.println("origine : " + origine);
			System.out.println("destination : " + destination);*/
		System.out.println("origine : " + graph.get(0));
		System.out.println("destination : " + graph.get(graph.size()-1));

		/* Pas de chemin trouve */
		if (solution.getPath() == null) {
			System.out.print("(infini) ");
		}
		/* Un plus court chemin trouve */
		else {				
			/* Calcul du cout de la solution (en temps et en distance) */
			costShortestSolutionInTime = solution.getPath().getMinimumTravelTime();
			costShortestSolutionInDistance = solution.getPath().getLength();
		}

		/* Verifie que le temps du chemin le plus rapide est inferieur au temps du chemin le plus court */
		assertTrue(costFastestSolutionInTime <= costShortestSolutionInTime);
		System.out.println("cout en temps du chemin le plus rapide : " + costFastestSolutionInTime);
		System.out.println("cout en temps du chemin le plus court : " + costShortestSolutionInTime);

		/* Et verifie que la distance du chemin le plus rapide est superieur a la distance du chemin le plus court */
		assertTrue(costFastestSolutionInDistance >= costShortestSolutionInDistance);
		System.out.println("cout en distance du chemin le plus rapide : " + costFastestSolutionInDistance);
		System.out.println("cout en distance du chemin le plus court : " + costShortestSolutionInDistance);

		System.out.println("");

	}
}
