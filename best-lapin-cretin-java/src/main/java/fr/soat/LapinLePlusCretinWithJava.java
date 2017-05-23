package fr.soat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Un programme java recherchant le plus cretin des lapins
 * dans le fichier de >4M de lapins cretins
 */
public class LapinLePlusCretinWithJava {

    public static void main(String[] args) throws IOException {
        long deb = System.currentTimeMillis();
        Stream<String> lineStream = Files.lines(Paths.get("/home/my/dev/", "lapins-cretins.txt"));

        LapinScore best = lineStream
                            .parallel()
                            .map(line -> new LapinScore(line, LapinScore.computeScore(line)))
                            .reduce(LapinScore::max)
                            .get();

        long fin = System.currentTimeMillis();

        System.out.println(best.nom +  "\nscore : " + best.score);
        System.out.println("duration : " + (fin - deb) + " ms");
    }

}
