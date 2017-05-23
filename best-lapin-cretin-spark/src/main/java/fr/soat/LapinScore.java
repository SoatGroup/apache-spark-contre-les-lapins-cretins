package fr.soat;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class LapinScore implements Comparable<LapinScore>, Serializable {
    // must be serializable to be sent to spark 
    
    private static final long serialVersionUID = 1L;
    
    String nom;
    Integer score;

    public LapinScore(String nom, Integer score) {
        this.nom = nom;
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public String getNom() {
        return nom;
    }

    private static List<Character> lettres = Arrays.asList('c', 'r', 'é', 't', 'i', 'n');
    
    /**
     * algorithme savant de calcul du niveau de crétinisme d'un lapin
     */
    public static Integer computeScore(String nom) {
        int score = 0;
    	for (int i = 0; i < nom.length(); i++) {
        	char l = nom.charAt(i);
        	if (lettres.contains(l)) {
        		score++;
        	}            	
        }
    	return score;
    }

    /**
     * Compare par score croisant
     * 
     * @param other
     * @return
     */
    @Override
    public int compareTo(LapinScore other) {
        int comparison = score.compareTo(other.score);
        if (comparison != 0) {
        	return comparison;
        } else {
        	return getNom().compareTo(other.getNom());
        }
    }

    public static LapinScore max(LapinScore l1, LapinScore l2) {
        return (l1.compareTo(l2) > 0) ? l1 : l2;
    }

}
