package org.insa.csv;

import java.io.*;
import java.util.*;


public class Lecture {
	
	private String mapName ;
	private ArrayList<Integer> listeOrigine;
	private ArrayList<Integer> listeDestination;
	
	public Lecture(String fileName) {
		listeOrigine = new ArrayList<Integer>();
		listeDestination = new ArrayList<Integer>();
		LectureFichier (fileName);
		
	}
	
	public void LectureFichier (String fileName) {
		try {
            Scanner scan = new Scanner(new File(fileName));
            if (scan.hasNext()) {
                mapName = scan.nextLine();
            }
			int origine;
			int destination;
			while (scan.hasNextInt()) {

				origine = scan.nextInt();
				this.listeOrigine.add(origine);
				 if (scan.hasNextInt()) {
					 destination = scan.nextInt();
					 this.listeDestination.add(destination);
				 } 
				 else {
					System.out.println("[WARNING]: ODD AMOUNT OF POINTS");
				 }
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("[EXCEPTION] FILE NOT FOUND");
		}
      //  points = new ArrayList<>();
        /*try {
            Scanner scanner = new Scanner(new File(fileName));
            if (scanner.hasNext()) {
                mapName = scanner.nextLine();
            }

            while (scanner.hasNextInt()) {
                int origin = scanner.nextInt();
                this.listeOrigine.add(origin);
                if (scanner.hasNextInt()) {
                    int destination = scanner.nextInt();
                    this.listeDestination.add(destination);
                    //points.add(new Points(origin, destination));
                } else {
                    System.out.println("WARNING: odd amount of points");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
	}
	
	public String getMapName() {
		return this.mapName;
	}

	public ArrayList<Integer> getlisteOrigine(){
		return this.listeOrigine;
	}
	
	public ArrayList<Integer> getlisteDestination(){
		return this.listeDestination;
	}
}

