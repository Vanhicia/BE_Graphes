package org.insa.algo.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.BinaryHeap;
import org.insa.algo.utils.Label;
import org.insa.algo.utils.LabelStar;
import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.graph.Path;

public class AStarAlgorithm extends DijkstraAlgorithm {

	public AStarAlgorithm(ShortestPathData data) {
		super(data);
	}

	@Override
	protected ShortestPathSolution doRun() {
		boolean fin = false;
		ShortestPathData data = getInputData();
		Graph graph = data.getGraph();
		int tailleGraphe = graph.size();

		ShortestPathSolution solution = null;

		/* Tableau de Labels */
		/* Les Labels sont placés selon leur Id */
		LabelStar tabLabels[] = new LabelStar [tailleGraphe];

		/* Tas de Labels */
		BinaryHeap<Label> tas = new BinaryHeap<Label>();

		/* Tableau des prédecesseurs */
		Arc[] predecessorArcs = new Arc[tailleGraphe];


		/* Ajout du sommet de départ */
		LabelStar deb = new LabelStar(data.getOrigin(), data);
		tabLabels[deb.getNode().getId()] = deb;
		tas.insert(deb);
		deb.setInTas();
		deb.setCost(0);

		/* Notifie les observateurs du premier évènement (départ de l'origine) */
		notifyOriginProcessed(data.getOrigin());

		/* Tant qu'il existe des sommets non marqués */
		while(!tas.isEmpty() && !fin){
			nbSommets++;
			Label current= tas.deleteMin();
			/* On indique aux observateurs que le Node a été marqué */
			notifyNodeMarked(current.getNode());
			current.setMark();

			/* On cr�e une copie du Label current en un LabelStar */
			/* afin de pouvoir appeler par la suite la fonction getCostReal() */
			LabelStar currentStar = new LabelStar(current.getNode(),data);
			currentStar.setCostWithoutInf(current.getCost());

			/* Quand on a atteint la destination, on s'arrête */
			if (current.getNode() == data.getDestination()) {
				fin = true;
			}
			/* Parcours des successeurs du sommet courant */
			Iterator<Arc> arc = current.getNode().iterator();
			while (arc.hasNext()) {
				Arc arcIter = arc.next();

				// On vérifie que l'on peut réellement prendre cet arc
				if (!data.isAllowed(arcIter)) {
					continue;
				}

				Node successeur = arcIter.getDestination();

				/* On recupere le label correspondant au noeud dans le tableau de labels */
				LabelStar successeurLabel = tabLabels[successeur.getId()];

				/* Si le label n'existe pas encore*/
				/* Alors on le crée */
				if (successeurLabel == null) {
					/* On informe les observateurs que l'on atteint un Node pour la première fois */
					notifyNodeReached(arcIter.getDestination());
					successeurLabel = new LabelStar(successeur, data);
					tabLabels[successeurLabel.getNode().getId()] = successeurLabel;
				}

				/* Si le successeur n'est pas encore marqué */
				if (!successeurLabel.getMark()) {
					/* Si on obtient un meilleur coût */
					/* Alors on le met à jour */
					if((successeurLabel.getCost()>currentStar.getCostReal()+data.getCost(arcIter)+successeurLabel.getInf()) || (successeurLabel.getCostReal()==Float.POSITIVE_INFINITY)){
						successeurLabel.setCost(currentStar.getCostReal()+(float)data.getCost(arcIter));
						successeurLabel.setFather(current.getNode());
						/* Si le label est déjà dans le tas */
						/* Alors on met à jour sa position dans le tas */
						if(successeurLabel.getInTas()) {
							tas.remove(successeurLabel);
						}
						/* Sinon on l'ajoute dans le tas */
						else {
							successeurLabel.setInTas();
						}
						tas.insert(successeurLabel);
						predecessorArcs[arcIter.getDestination().getId()] = arcIter;
					}
				}

			}
		}

		// Destination has no predecessor, the solution is infeasible...
		if (predecessorArcs[data.getDestination().getId()] == null) {
			solution = new ShortestPathSolution(data, Status.INFEASIBLE);
		} else {

			// The destination has been found, notify the observers.
			notifyDestinationReached(data.getDestination());

			// Create the path from the array of predecessors...
			ArrayList<Arc> arcs = new ArrayList<>();
			Arc arc = predecessorArcs[data.getDestination().getId()];

			while (arc != null) {
				arcs.add(arc);
				arc = predecessorArcs[arc.getOrigin().getId()];
			}

			// Reverse the path...
			Collections.reverse(arcs);

			// Create the final solution.
			solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));

		}

		return solution;
	}

}
