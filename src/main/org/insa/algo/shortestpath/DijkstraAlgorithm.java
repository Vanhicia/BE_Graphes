package org.insa.algo.shortestpath;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.insa.algo.utils.*;
import org.insa.graph.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
      	int nbmarques = 0;
      	int tailleGraphe = data.getGraph().size();
      	
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        
        /* Tableau de Labels */
        /* Les Labels sont placés selon leur Id */
        Label tabLabels[] = new Label [data.getGraph().size()];

        /* Tas de Labels */
        BinaryHeap<Label> tas = new BinaryHeap<Label>();
        
        /* Tableau de Nodes dans l'ordre de marquage */
        Label tabLabelsSolution[] = new Label [data.getGraph().size()];
        
        /* Ajout du sommet de départ */
        Label deb = new Label(data.getOrigin());
        tabLabels[deb.getNode().getId()] = deb;
        tas.insert(deb);
        deb.setInTas();
        
        /* Tant qu'il existe des sommets non marqués */
        while(nbmarques < tailleGraphe){
        	boolean labelTrouve = false ;
        	Label current= tas.deleteMin();
        	current.setMark();
        	tabLabelsSolution[nbmarques] = current;
        	nbmarques++;
        	/* Parcours des successeurs du sommet courant */
        	Iterator<Arc> arc = current.getNode().iterator();
        	while (arc.hasNext()) {
        		Arc arcIter = arc.next();
        		Node successeur = arcIter.getDestination();
        		
        		/* On recupere le label correspondant au noeud dans le tableau de labels */
        		Label successeurLabel = tabLabels[successeur.getId()];
        		
        		/* Si le label n'existe pas encore*/
        		/* Alors on le crée */
        		if (successeurLabel == null) {
        			successeurLabel = new Label(data.getOrigin());
        	        tabLabels[successeurLabel.getNode().getId()] = successeurLabel;
        		}
        		
        		/* Si le successeur n'est pas encore marqué */
        		if (!successeurLabel.getMark()) {
        			/* Si on obtient un meilleur coût */
        			/* Alors on le met à jour */
        			if((successeurLabel.getCost()>current.getCost()+arcIter.getLength())|| (successeurLabel.getCost()==-1.0f)){
        				successeurLabel.setCost(current.getCost()+arcIter.getLength());
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
        			}
        		}
        		
        	}
        }
        List<Arc> arcs = new ArrayList<Arc>();
        for (Label label : tabLabelsSolution) {
        	Node father = label.getFather();
        	if (father != null) {
        		
        	}
        }

        return solution;
    }

}
