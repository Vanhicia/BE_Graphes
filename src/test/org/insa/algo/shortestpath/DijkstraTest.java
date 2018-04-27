package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.insa.algo.shortestpath.BellmanFordAlgorithm;
import org.insa.algo.shortestpath.DijkstraAlgorithm;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.algo.shortestpath.ShortestPathSolution;
import org.insa.algo.utils.ArcInspectorDijkstra;
import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.graph.RoadInformation;
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
    		System.out.print("x"+(nodes[i].getId()+1) + ":");
    		
    		for (int j=0;  j < nodes.length; ++j) {
    			
    			if(nodes[i]==nodes[j]) {
    				System.out.print("- ");
    			}
    			else{
    			
	    			ArcInspectorDijkstra arcInspectorDijkstra = new ArcInspectorDijkstra();
	    			ShortestPathData data = new ShortestPathData(graph, nodes[i],nodes[j], arcInspectorDijkstra);
	    	
	    			BellmanFordAlgorithm B = new BellmanFordAlgorithm(data);
	    			DijkstraAlgorithm D = new DijkstraAlgorithm(data);
	    			
	    			/* Récupération des solutions de Bellman et Dijkstra pour comparer */
	    			ShortestPathSolution solution = D.run();
	    			ShortestPathSolution expected = B.run();
	    			
	    			/* Pas de chemin trouvé */
	    			if (solution.getPath() == null) {
	    				assertEquals(expected.getPath(), solution.getPath());
	    				System.out.print("(infini) ");
	    			}
	    			/* Un plus court chemin trouvé */
	    			else {
	    			
		    			/* Calcul du coût de la solution */
		    			float costSolution = solution.getPath().getLength();
		    			float costExpected = expected.getPath().getLength();
		    			assertEquals(costExpected, costSolution, 0);
		    			
		    			/* On récupère l'avant dernier sommet du chemin de la solution (=sommet père de la destination) */
		    			List<Arc> arcs = solution.getPath().getArcs();
		    			Node originOfLastArc = arcs.get(arcs.size()-1).getOrigin();
	    			
	    			/* Affiche le couple (coût, sommet père du Dest) */
	    			System.out.print("("+costSolution+ ", x" + (originOfLastArc.getId()+1) + ") ");
	    			}
    			}
    		}
    		
    		/* Retour à la ligne */ 
    		System.out.println("");
    	}
    }

}