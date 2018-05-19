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

public class ResultatExecutionAlgos {
	private int origine;
	private int destination;
	private long tempsExecutionDijkstra;
	private long tempsExecutionAStar;
	private int nbSommetsDijsktra;
	private int nbSommetsAStar;

	public ResultatExecutionAlgos(String mapName, int typeEvaluation, int origine, int destination) {

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
			DijkstraAlgorithm D = new DijkstraAlgorithm(data);
			tempsDeb = System.currentTimeMillis();
			D.run();
			tempsFin = System.currentTimeMillis();
			this.tempsExecutionDijkstra = tempsFin-tempsDeb;
			this.nbSommetsDijsktra = D.getNbSommetsVisites();

			tempsDeb = 0;
			tempsFin = 0;
			
			/* Calcul du temps d'exécution d'AStar */
			AStarAlgorithm A = new AStarAlgorithm(data);
			tempsDeb = System.currentTimeMillis();
			A.run();
			tempsFin = System.currentTimeMillis();
			this.tempsExecutionAStar = tempsFin-tempsDeb;
			this.nbSommetsAStar = A.getNbSommetsVisites();

			this.nbSommetsDijsktra=D.getNbSommetsVisites();
			this.nbSommetsAStar=A.getNbSommetsVisites();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getOrigine() {
		return this.origine;
	}
	
	public int getDestination() {
		return this.destination;
	}
	
	public float getTempsExecutionDijkstra() {

		return this.tempsExecutionDijkstra;
	}
	
	public float getTempsExecutionAStar() {
		return this.tempsExecutionAStar;
	}
	
	public int getNbSommetsVisitesDijkstra() {
		return this.nbSommetsDijsktra;
	}
	
	public int getNbSommetsVisitesAStar() {
		return this.nbSommetsAStar;
	}

}
