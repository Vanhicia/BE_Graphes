package org.insa.csv;

import java.util.ArrayList;
import java.io.*;
public class Ecriture {
 public Ecriture(String fileNameWrite, String mapName, ArrayList<ResultatExecutionAlgos>listeResultatPerformance) {
	 String commaDelimiter = ",";
	 String newLineSeparator ="\n";
	 String fileHeader ="Carte,Origine,Destination,Temps CPU Dijkstra (en ms), Nb Sommets, Temps CPU A* (en ms), Nb Sommets"; // Nb sommets
	 try {
	 FileWriter fileWriter = new FileWriter("src\\test\\"+fileNameWrite);
	 fileWriter.append(fileHeader);
	 	for (ResultatExecutionAlgos p : listeResultatPerformance) {
	 		fileWriter.append(newLineSeparator);
	 		fileWriter.append(mapName);
	 		fileWriter.append(commaDelimiter);
	 		fileWriter.append(String.valueOf(p.getOrigine()));
	 		fileWriter.append(commaDelimiter);
	 		fileWriter.append(String.valueOf(p.getDestination()));
	 		fileWriter.append(commaDelimiter);
	 		fileWriter.append(String.valueOf(p.getTempsExecutionDijkstra()));
	 		fileWriter.append(commaDelimiter);
	 		fileWriter.append(String.valueOf(p.getNbSommetsVisitesDijkstra()));
	 		fileWriter.append(commaDelimiter);
	 		fileWriter.append(String.valueOf(p.getTempsExecutionAStar()));
	 		fileWriter.append(commaDelimiter);
	 		fileWriter.append(String.valueOf(p.getNbSommetsVisitesAStar()));
	 	}
 		fileWriter.flush();
 		fileWriter.close();
 		System.out.println("Done");	
 		} 
	 catch (Exception e){
		 System.out.println(e.getMessage());
	 }
 }
}
