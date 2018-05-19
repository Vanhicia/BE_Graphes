package org.insa.csv;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.algo.shortestpath.AStarAlgorithm;
import org.insa.algo.shortestpath.DijkstraAlgorithm;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.algo.shortestpath.ShortestPathSolution;
import org.insa.graph.Graph;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;

public class ResultatPerformanceAlgo {
	private long tempsExecutionDijkstra;
	private long tempsExecutionAStar;
	
	public ResultatPerformanceAlgo(String mapName, int typeEvaluation, int origine, int destination) throws Exception {
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

			long tempsDeb;
			long tempsFin;
			
			/* Calcul du temps d'exécution de Dijkstra */
			tempsDeb = System.currentTimeMillis();
			DijkstraAlgorithm D = new DijkstraAlgorithm(data);
			tempsFin = System.currentTimeMillis();
			this.tempsExecutionDijkstra = tempsFin-tempsDeb;
			
			/* Calcul du temps d'exécution de Dijkstra */
			tempsDeb = System.currentTimeMillis();
			AStarAlgorithm A = new AStarAlgorithm(data);
			tempsFin = System.currentTimeMillis();
			this.tempsExecutionAStar = tempsFin-tempsDeb;
	}
}
