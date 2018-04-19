package org.insa.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.insa.algo.ArcInspector;
import org.insa.algo.shortestpath.BellmanFordAlgorithm;
import org.insa.algo.shortestpath.DijkstraAlgorithm;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.algo.shortestpath.ShortestPathSolution;
import org.insa.graph.RoadInformation.RoadType;

import org.junit.BeforeClass;
import org.junit.Test;

public class DijkstraTest {

    // Small graph use for tests
    private static Graph graph;

    // List of nodes
    private static Node[] nodes;

    // List of arcs in the graph, a2b is the arc from node A (0) to B (1).
    @SuppressWarnings("unused")
    private static Arc a2b, a2c, b2d, b2e, b2f, c2a, c2b, c2f, e2c, e2d, e2f, f2e;

    // Some paths...
   /* private static Path emptyPath, singleNodePath, shortPath, longPath, loopPath, longLoopPath,
            invalidPath;*/

    @BeforeClass
    public static void initAll() throws IOException {

        // Define roads
        RoadInformation RoadInfo = new RoadInformation(RoadType.UNCLASSIFIED, null, true, 1, null);

        // Create nodes
        nodes = new Node[6];
        for (int i = 0; i < nodes.length; ++i) {
            nodes[i] = new Node(i, null);
        }

        // Add arcs...
        a2b = Node.linkNodes(nodes[0], nodes[1], 7, RoadInfo, null);
        a2c = Node.linkNodes(nodes[0], nodes[2], 8, RoadInfo, null);
        b2d = Node.linkNodes(nodes[1], nodes[3], 4, RoadInfo, null);
        b2e = Node.linkNodes(nodes[1], nodes[4], 1, RoadInfo, null);
        b2f = Node.linkNodes(nodes[1], nodes[5], 5, RoadInfo, null);
        c2a = Node.linkNodes(nodes[2], nodes[0], 7, RoadInfo, null);
        c2b = Node.linkNodes(nodes[2], nodes[1], 2, RoadInfo, null);
        c2f = Node.linkNodes(nodes[2], nodes[5], 2, RoadInfo, null);
        e2c = Node.linkNodes(nodes[4], nodes[2], 2, RoadInfo, null);
        e2d = Node.linkNodes(nodes[4], nodes[3], 2, RoadInfo, null);
        e2f = Node.linkNodes(nodes[4], nodes[5], 3, RoadInfo, null);
        f2e = Node.linkNodes(nodes[5], nodes[4], 3, RoadInfo, null);

        // Initialize the graph
        graph = new Graph("ID", "", Arrays.asList(nodes), null);

    }

    @Test
    public void testDoRun() {
    	
    	/* Tableau contenant les arcs*/
    	//Arc[] arcs = new Arc[] { a2b, a2c, b2d, b2e, b2f, c2a, c2b, c2f, e2c, e2d, e2f, f2e };
    	
    	for (int i=0;  i < nodes.length; ++i) {
    		
    		/* Affichage du point de départ */
    		System.out.print("x"+nodes[i].getId() + ":");
    		
    		for (int j=0;  j < nodes.length; ++j) {
    			
    			if(nodes[i]==nodes[j]) {
    				System.out.print(" - ");
    			}
    			else{
    			
    			// TODO : Trouver un moyen d'initialiser un ArcInspector 
    			ShortestPathData data = new ShortestPathData(graph, nodes[i],nodes[j], ArcInspector);
    	
    			
    			BellmanFordAlgorithm B = new BellmanFordAlgorithm(data);
    			DijkstraAlgorithm D = new DijkstraAlgorithm(data);
    			
    			/* Récupération des solutions de Bellman et Dijkstra pour comparer */
    			ShortestPathSolution solution = D.run();
    			ShortestPathSolution expected = B.run();
    			assertEquals(expected,solution);
    			
    			/* Calcul du coût de la solution */
    			int cost=0;
    			Node OriginOfLastArc;
    			for (Arc arc: solution.getPath().getArcs()) {
    				cost+=solution.getInputData().getCost(arc);
    				
    				// TODO : Trouver un moyen d'optimiser la recherche du sommet père du Dest
    				OriginOfLastArc=arc.getOrigin();
    			}
    			
    			/* Affiche le couple (coût, sommet père du Dest) */
    			System.out.print("("+cost+ "," + OriginOfLastArc.getId() + ") ");
    			
    			}
    		}
    		
    		/* Retour à la ligne */ 
    		System.out.println("");
    	}
    }

}