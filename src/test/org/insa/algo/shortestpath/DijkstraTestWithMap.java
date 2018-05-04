package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.List;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.algo.utils.ArcInspectorDijkstra;
import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.graph.Path;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.BinaryPathReader;
import org.insa.graph.io.GraphReader;
import org.insa.graph.io.PathReader;

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
			System.out.println("type d'évaluation: temps");

			
			ArcInspector arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(2);
			
			
			ShortestPathData data = new ShortestPathData(graph, graph.get(0),graph.get(graph.size()-1), arcInspectorDijkstra);
	
			BellmanFordAlgorithm B = new BellmanFordAlgorithm(data);
			DijkstraAlgorithm D = new DijkstraAlgorithm(data);
			
			/* R�cup�ration des solutions de Bellman et Dijkstra pour comparer */
			ShortestPathSolution solution = D.run();
			ShortestPathSolution expected = B.run();
			
			System.out.println(mapName);
			/*System.out.println("origine : " + origine);
			System.out.println("destination : " + destination);*/
			System.out.println("origine : " + graph.get(0));
			System.out.println("destination : " + graph.get(graph.size()-1));
			
			/* Pas de chemin trouv� */
			if (solution.getPath() == null) {
				assertEquals(expected.getPath(), solution.getPath());
				System.out.print("(infini) ");
			}
			/* Un plus court chemin trouv� */
			else {
				/* Calcul du co�t de la solution */
				double costSolution = solution.getPath().getMinimumTravelTime();
				double costExpected = expected.getPath().getMinimumTravelTime();
				assertEquals(costExpected, costSolution, 0.001);
				
				System.out.println("cout solution: " + costSolution);
			}
        }			
		else if (typeEvaluation == 1) {
			System.out.println("type d'évaluation: distance");
						
			ArcInspector arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(0);
			
			ShortestPathData data = new ShortestPathData(graph, graph.get(0),graph.get(graph.size()-1), arcInspectorDijkstra);
	
			BellmanFordAlgorithm B = new BellmanFordAlgorithm(data);
			DijkstraAlgorithm D = new DijkstraAlgorithm(data);
			
			/* R�cup�ration des solutions de Bellman et Dijkstra pour comparer */
			ShortestPathSolution solution = D.run();
			ShortestPathSolution expected = B.run();
			
			System.out.println(mapName);
			/*System.out.println("origine : " + origine);
			System.out.println("destination : " + destination);*/
			System.out.println("origine : " + graph.get(0));
			System.out.println("destination : " + graph.get(graph.size()-1));
			
			/* Pas de chemin trouv� */
			if (solution.getPath() == null) {
				assertEquals(expected.getPath(), solution.getPath());
				System.out.print("(infini) ");
			}
			/* Un plus court chemin trouv� */
			else {				
				/* Calcul du co�t de la solution */
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
	// typeEvaluation : 0 = temps, 1 = distance
	public void testScenarioSansOracle(String mapName, int typeEvaluation) throws Exception {
	//public void testScenario(String mapName, int typeEvaluation, Node origine, Node destination) throws Exception {
  
        // Create a graph reader.
        GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

        // Read the graph.
        Graph graph = reader.read();
        
        if (typeEvaluation == 0) {
			System.out.println("type d'évaluation: temps");
			
			ArcInspector arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(2);
			
			ShortestPathData data = new ShortestPathData(graph, graph.get(0),graph.get(graph.size()-1), arcInspectorDijkstra);
	
			DijkstraAlgorithm D = new DijkstraAlgorithm(data);
			
			/* R�cup�ration de la solution de Dijkstra */
			ShortestPathSolution solution = D.run();

			System.out.println(mapName);
			/*System.out.println("origine : " + origine);
			System.out.println("destination : " + destination);*/
			System.out.println("origine : " + graph.get(0));
			System.out.println("destination : " + graph.get(graph.size()-1));
			
			/* Pas de chemin trouv� */
			if (solution.getPath() == null) {
				System.out.print("(infini) ");
			}
			/* Un plus court chemin trouv� */
			else {
				/* Calcul du co�t de la solution */
				double costSolution = solution.getPath().getLength();
				
				System.out.println("cout solution: " + costSolution);
			}
        }			
		else if (typeEvaluation == 1) {
			System.out.println("type d'évaluation: distance");
						
			ArcInspector arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(0);
			
			ShortestPathData data = new ShortestPathData(graph, graph.get(0),graph.get(graph.size()-1), arcInspectorDijkstra);
			
			DijkstraAlgorithm D = new DijkstraAlgorithm(data);
			
			/* R�cup�ration de la solution de Dijkstra */
			ShortestPathSolution solution = D.run();

			System.out.println(mapName);
			/*System.out.println("origine : " + origine);
			System.out.println("destination : " + destination);*/
			System.out.println("origine : " + graph.get(0));
			System.out.println("destination : " + graph.get(graph.size()-1));
			
			/* Pas de chemin trouv� */
			if (solution.getPath() == null) {
				System.out.print("(infini) ");
			}
			/* Un plus court chemin trouv� */
			else {
				/* Calcul du co�t de la solution */
				double costSolution = solution.getPath().getMinimumTravelTime();
				
				System.out.println("cout solution: " + costSolution);
			}
        }	
		else {
				System.out.println("Argument invalide");
		}
        System.out.println("");
	}
	
	
}
