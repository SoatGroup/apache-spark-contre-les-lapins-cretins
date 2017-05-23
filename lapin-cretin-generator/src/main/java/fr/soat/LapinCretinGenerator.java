package fr.soat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

/**
 * Created by my on 20/03/16.
 */
public class LapinCretinGenerator {

    static String lapinTemplate1 = "%s lapin crétin %s déguisé en %s avec %s %s";
    static String lapinTemplate2 = "%s lapin crétin %s déguisé en %s avec %s %s et %s %s";

    static List<String> adjectifAvants = Arrays.asList("", "petit", "grand");
    static List<String> adjectifApres = Arrays.asList("", "hurlant", "qui fait bwaaaa", "qui fait bwaaaaaaaaa", "qui fait daaaaa", "qui fait daaaaaaaaaaa");

    static List<String> deguisements = Arrays.asList(
            "indien", "cow boy", "pompier", "zombie", "momie", "tortue ninja", "mon petit poney", "dark vador",
            "danseuse étoile", "skieur", "jockey", "surfeur", "pilote d'essai", "couvreur",
            "d'jeuns", "quater back", "boxeur", "super-lapin", "footballeur", "catcheur",
            "kayakiste", "cycliste", "judoka", "pilote de tricycle", "sprinteur", "tennis-man",
            "golfeur", "halterophile", "chevalier", "business-man", "rabbit des bois", "rabbit jackob",
            "bunny-girl", "lapin de ménage", "tutu", "canard", "ornithorinque", "fée clochette"
    );

    static List<String> ouCas = Arrays.asList(
            "sous le bras", "sur la tête", "dans la main droite", "dans la main gauche", "entre les dents"
    );

    static List<String> objets = Arrays.asList(
            "un débouche-évier", "une pelle", "un marteau", "une balise de chantier", "une passoire",
            "une truelle", "un rateau", "du PQ", "un plat à tartes", "une raquette", "un club de golf",
            "une échelle", "un casque de mobilette", "un canard en plastique", "une tronconneuse", "une poele à crepes",
            "une passoire", "une casserole", "des menottes", "une rose rouge", "une clef à molette",
            "une brosse à chiotte", "un balon", "le livre blanc du big data"
    );

    static List<String> lapins = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        for (String adjAvant : adjectifAvants) {
            for (String adjApres : adjectifApres) {
                for (String deguisement : deguisements) {
                    // un seul accessoire
                    for (String obj : objets) {
                        for (String ouCa : ouCas) {
                            String lapin = String.format(lapinTemplate1, adjAvant, adjApres, deguisement, obj, ouCa).trim().replaceAll("\\s\\s", " ");
                            // fw.write(lapin + "\n");
                            lapins.add(lapin + "\n");
                            logLapiins();
                        }
                    }

                    // 2 accessoires
                    for (String obj1 : objets) {
                        for (String ouCa1 : ouCas) {
                            for (String obj2 : objets) {
                                for (String ouCa2 : ouCas) {
                                    if (!obj1.equals(obj2) && !ouCa1.equals(ouCa2)) {
                                        String lapin = String.format(lapinTemplate2, adjAvant, adjApres, deguisement, obj1, ouCa1, obj2, ouCa2).trim().replaceAll("\\s\\s", " ");
                                        // fw.write(lapin + "\n");
                                        lapins.add(lapin + "\n");
                                    }
                                    logLapiins();
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("shuffling lapins...");
        Collections.shuffle(lapins);
        try (FileWriter                                                             fw = new FileWriter(new File("lapins-cretins.txt"))) {
            for (String lapin : lapins) {
                fw.write(lapin);
            }
        }
    }

    private static void logLapiins() {
        if (lapins.size() % 1000 == 0) {
            System.out.println(lapins.size() + " lapins ont été créés");
        }
    }

}

