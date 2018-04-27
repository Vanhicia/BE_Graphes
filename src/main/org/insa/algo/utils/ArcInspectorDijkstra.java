package org.insa.algo.utils;

import org.insa.algo.AbstractInputData;
import org.insa.algo.ArcInspector;
import org.insa.graph.Arc;
import org.insa.graph.GraphStatistics;

public class ArcInspectorDijkstra implements ArcInspector {
	
	public double getCost (Arc arc) {
		return arc.getLength();
	}
	
	public int getMaximumSpeed() {
		return GraphStatistics.NO_MAXIMUM_SPEED;
	}
	
	public AbstractInputData.Mode getMode() {
		return AbstractInputData.Mode.LENGTH;
	}
	
	public boolean isAllowed (Arc arc) {
		if (arc.getLength() >= 0.0f) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
