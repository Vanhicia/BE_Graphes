package org.insa.algo.utils;

import org.insa.graph.Node;

public class Label implements Comparable<Label> {
	private float cost;
	private boolean marked;
	private Node father;
	private Node node;
	private boolean inTas;
	
	public Label(Node noeud){
		this.node = noeud;
		this.marked = false;
		this.cost = -1.0f;
		this.father = null; 
		this.inTas = false;
	}
	
	public Node getNode() {
		return this.node;
	}
	
	public float getCost() {
		return this.cost;
	}
	
	public boolean getMark() {
		return this.marked;
	}
	
	public Node getFather() {
		return this.father;
	}
	
	public boolean getInTas() {
		return this.inTas;
	}	
	
	public void setMark() {
		this.marked = true;
	}
	
	public void setCost(float cout) {
		this.cost = cout;
	}
	
	public void setFather(Node father) {
		this.father = father;
	}
	
	public void setInTas() {
		this.inTas = true;
	}
	
	/* Compare les Labels selon leur coût */
	public int compareTo(Label autre) {
		int resultat;
		if (this.cost < autre.cost) {
			resultat = -1;
		}
		else if (this.cost == autre.cost) {
			resultat = 0;
		}
		else {
			resultat = 1;
		}
		return resultat;
	}
	
}
