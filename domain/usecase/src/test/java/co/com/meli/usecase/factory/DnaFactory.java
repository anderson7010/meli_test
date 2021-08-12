package co.com.meli.usecase.factory;

import java.util.Random;

public class DnaFactory {

    private static final int SEQUENCE_LENGTH = 4;
    private static final String MUTANT_CHARACTERS = "ATCG";
    private static final String NO_MUTANT_CHARACTERS = "FFFF";

    public static String[] generateDna(int length, boolean isMutant) {

        String[] dna = createMatrix(length, isMutant);
        if (isMutant) {
            return addSequences(length, dna);
        }

        return dna;
    }

    private static String[] createMatrix(int length, boolean isMutant) {

        Random random = new Random();
        char[] characters = (isMutant ? MUTANT_CHARACTERS : NO_MUTANT_CHARACTERS).toCharArray();

        String[] dna = new String[length];
        for (int row = 0; row < length; row++) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append(characters[random.nextInt(SEQUENCE_LENGTH)]);
            }
            dna[row] = builder.toString();
        }
        return dna;
    }

    private static String[] addSequences(int n, String[] dna) {

        //Diagonal
        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            char[] s = dna[i].toCharArray();
            s[i] = 'A';
            dna[i] = String.valueOf(s);
        }

        //Horizontal
        char[] s = dna[n - 1].toCharArray();
        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            s[i] = 'G';
        }
        dna[n - 1] = String.valueOf(s);
        return dna;
    }
}
