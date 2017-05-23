package fr.soat;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * Un programme spark recherchant le plus cretin des lapins 
 * dans le fichier de >7M de lapins cretins
 *
 * Cette implementation est basee sur spark en mode "cluster standalone"
 */
public class LapinLePlusCretinWithSparkCluster {

    public static void main(String[] args) throws IOException {
        long deb = System.currentTimeMillis();

        // configuration pour une execution sur un cluster standalone
        SparkConf conf = new SparkConf().setAppName("programme spark recherchant le Lapin crétin le plus crétin");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // init un RDD depuis un fichier txt
        String lapinsFile = "/home/my/dev/lapins-cretins.txt";
        JavaRDD<String> lapinsData = sc.textFile(lapinsFile); // .cache();

        // execute un job spark (transformation puis action)
        LapinScore best = lapinsData.map(nom -> new LapinScore(nom, LapinScore.computeScore(nom)))
                                    .reduce(LapinScore::max);

        long fin = System.currentTimeMillis();

        System.out.println("Le plus crétin des lapins crétins est :\n" + best.nom + "\navec un score de : " + best.score);
        System.out.println("duration : " + (fin - deb) + " ms");
    }

}
