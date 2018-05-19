package org.insa.csv;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.algo.shortestpath.AStarAlgorithm;
import org.insa.algo.shortestpath.DijkstraAlgorithm;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.graph.Graph;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;

public class TempsExecutionAlgos {
	private int origine;
	private int destination;
	private long tempsExecutionDijkstra;
	private long tempsExecutionAStar;
	
	public TempsExecutionAlgos(String mapName, int typeEvaluation, int origine, int destination) {

		this.origine = origine;
		this.destination = destination;
		
		try {
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
			tempsDeb = System.nanoTime();
			DijkstraAlgorithm D = new DijkstraAlgorithm(data);
			tempsFin = System.nanoTime();
			this.tempsExecutionDijkstra = tempsFin-tempsDeb;

			/* Calcul du temps d'exécution de Dijkstra */
			tempsDeb = System.nanoTime();
			AStarAlgorithm A = new AStarAlgorithm(data);
			tempsFin = System.nanoTime();
			this.tempsExecutionAStar = tempsFin-tempsDeb;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public long getTempsExecutionDijkstra() {
		return this.tempsExecutionDijkstra;
	}
	
	public long getTempsExecutionAStar() {
		return this.tempsExecutionAStar;
	}
	
	public int getOrigine() {
		return this.origine;
	}
	
	public int getDestination() {
		return this.destination;
	}
}
