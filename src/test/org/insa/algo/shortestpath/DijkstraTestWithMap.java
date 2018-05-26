package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.graph.Path;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;

import org.junit.Test;

public class DijkstraTestWithMap {

	@Test
	// typeEvaluation : 0 = temps, 1 = distance
	public void testScenario(String mapName, int typeEvaluation) throws Exception {
		//public void testScenario(String mapName, int typeEvaluation, Node origine, Node destination) throws Exception {

		// Create a graph reader.
		GraphReader reader = new BinaryGraphReader(
				new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

		// Read the graph.
		Graph graph = reader.read();

		if (typeEvaluation == 0) {
			System.out.println("type d'evaluation: temps");


			ArcInspector arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(2);


			ShortestPathData data = new ShortestPathData(graph, graph.get(0),graph.get(graph.size()-1), arcInspectorDijkstra);

			BellmanFordAlgorithm B = new BellmanFordAlgorithm(data);
			DijkstraAlgorithm D = new DijkstraAlgorithm(data);

			/* Recuperation des solutions de Bellman et Dijkstra pour comparer */
			ShortestPathSolution solution = D.run();
			ShortestPathSolution expected = B.run();

			System.out.println(mapName);
			/*System.out.println("origine : " + origine);
			System.out.println("destination : " + destination);*/
			System.out.println("origine : " + graph.get(0));
			System.out.println("destination : " + graph.get(graph.size()-1));

			/* Pas de chemin trouve */
			if (solution.getPath() == null) {
				assertEquals(expected.getPath(), solution.getPath());
				System.out.print("(infini) ");
			}
			/* Un plus court chemin trouve */
			else {
				/* Calcul du cout de la solution */
				double costSolution = solution.getPath().getMinimumTravelTime();
				double costExpected = expected.getPath().getMinimumTravelTime();
				assertEquals(costExpected, costSolution, 0.001);

				System.out.println("cout solution: " + costSolution);
			}
		}			
		else if (typeEvaluation == 1) {
			System.out.println("type d'evaluation: distance");

			ArcInspector arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(0);

			ShortestPathData data = new ShortestPathData(graph, graph.get(0),graph.get(graph.size()-1), arcInspectorDijkstra);

			BellmanFordAlgorithm B = new BellmanFordAlgorithm(data);
			DijkstraAlgorithm D = new DijkstraAlgorithm(data);

			/* Recuperation des solutions de Bellman et Dijkstra pour comparer */
			ShortestPathSolution solution = D.run();
			ShortestPathSolution expected = B.run();

			System.out.println(mapName);
			/*System.out.println("origine : " + origine);
			System.out.println("destination : " + destination);*/
			System.out.println("origine : " + graph.get(0));
			System.out.println("destination : " + graph.get(graph.size()-1));

			/* Pas de chemin trouve */
			if (solution.getPath() == null) {
				assertEquals(expected.getPath(), solution.getPath());
				System.out.print("(infini) ");
			}
			/* Un plus court chemin trouve */
			else {				
				/* Calcul du cout de la solution */
				float costSolution = solution.getPath().getLength();
				float costExpected = expected.getPath().getLength();
				assertEquals(costExpected, costSolution, 0.001);

				System.out.println("cout solution: " + costSolution);
			}
		}
		else {
			System.out.println("Argument invalide");
		}
		System.out.println("");
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
