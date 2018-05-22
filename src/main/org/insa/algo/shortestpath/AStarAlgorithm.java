package org.insa.algo.shortestpath;

import org.insa.algo.utils.Label;
import org.insa.algo.utils.LabelStar;
import org.insa.graph.Node;

public class AStarAlgorithm extends DijkstraAlgorithm {

	public AStarAlgorithm(ShortestPathData data) {
		super(data);
	}
	
	@Override
	/* Réécriture de la méthode newLabel */
	/* afin d'utiliser LabelStar au lieu de Label dans l'algo */
	protected Label newLabel(Node node, ShortestPathData data) {
		return new LabelStar(node, data);
	}

}
