package org.insa.algo.utils;

import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.graph.GraphStatistics;
import org.insa.graph.Node;
import org.insa.graph.Point;
import org.insa.algo.AbstractInputData;

public class LabelStar extends Label implements Comparable<Label>{
	private float inf;

	public LabelStar(Node noeud, ShortestPathData data) {
		super(noeud);

		if (data.getMode() == AbstractInputData.Mode.LENGTH) {
			this.inf = (float)Point.distance(noeud.getPoint(),data.getDestination().getPoint());
		}
		else {
			int vitesse = Math.max(data.getMaximumSpeed(), data.getGraph().getGraphInformation().getMaximumSpeed());
			this.inf = (float)Point.distance(noeud.getPoint(),data.getDestination().getPoint())/(vitesse*1000.0f/3600.0f);
		}
	}

	/* Met � jour le co�t */
	public void setCostWithoutInf(float cout) {
		this.cost = cout - this.inf; // enl�ve le vol d'oiseau indiqu� dans le co�t en argument
	}

	/* Renvoie le co�t avec le vol d'oiseau jusqu'� la destination */
	public float getCost() {
		return this.inf+this.cost;
	}

	/* Renvoie le vrai co�t (ie de l'origine au noeud */
	public float getCostReal() {
		return this.cost;
	}
	
	public float getInf() {
		return this.inf;
	}

}
