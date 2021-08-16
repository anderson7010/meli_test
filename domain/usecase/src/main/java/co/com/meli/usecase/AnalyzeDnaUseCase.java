package co.com.meli.usecase;

import co.com.meli.model.DnaRecord;
import co.com.meli.model.gateways.DnaRecordRepository;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class AnalyzeDnaUseCase {
    private final DnaRecordRepository dnaRecordRepository;

    private static final List<Character> VALID_CHARACTERS = Arrays.asList('G', 'A', 'C', 'T');
    private static final int MIN_SEQUENCES = 2;
    private static final int LENGTH_SEQUENCE = 4;

    public boolean isMutant(DnaRecord dnaRecord) {
        String[] dna = dnaRecord.getDna();

        char[][] matrix = new char[dna.length][dna.length];

        for (int i = 0; i < dna.length; i++) {
            matrix[i] = dna[i].toCharArray();
        }

        int count = countLinearSequences(matrix);
        if (count >= MIN_SEQUENCES) {
            saveDna(dnaRecord, Boolean.TRUE);
            return Boolean.TRUE;
        }
        count += countDiagonalSequences(matrix);

        boolean isMutant = count >= MIN_SEQUENCES;
        saveDna(dnaRecord, isMutant);

        return isMutant;
    }

    private int countLinearSequences(char[][] matrix) {

        int counter = 0;

        for (int i = 0; i < matrix.length; i++) {

            SequenceCounterStatus horizontalStatus = new SequenceCounterStatus();
            SequenceCounterStatus verticalStatus = new SequenceCounterStatus();

            for (int j = 0; j < matrix.length; j++) {
                char nextChar = matrix[i][j];
                if (processCharacter(nextChar, horizontalStatus)) {
                    counter++;
                }

                nextChar = matrix[j][i];
                if (processCharacter(nextChar, verticalStatus)) {
                    counter++;
                }

                if (counter >= MIN_SEQUENCES) {
                    break;
                }
            }
        }
        return counter;
    }

    private int countDiagonalSequences(char[][] matrix) {
        int counter = 0;

        for (int i = 0; i < matrix.length / 2; i++) {
            SequenceCounterStatus diagonalMajorTopStatus = new SequenceCounterStatus();
            SequenceCounterStatus diagonalMajorBottomStatus = new SequenceCounterStatus();

            // Diagonals from the top left to the bottom right direction \\\\
            for (int j = 0; j < matrix.length - i; j++) {
                char nextChar = matrix[j][j + i];
                if (processCharacter(nextChar, diagonalMajorTopStatus)) {
                    counter++;
                }
                if (i == 0) {
                    continue;
                }

                nextChar = matrix[i + j][j];
                if (processCharacter(nextChar, diagonalMajorBottomStatus)) {
                    counter++;
                }

            }
            SequenceCounterStatus diagonalMinorTopStatus = new SequenceCounterStatus();
            SequenceCounterStatus diagonalMinorBottomStatus = new SequenceCounterStatus();

            // Diagonals from the bottom left to the top right corner direction ////
            for (int j = 0; j < matrix.length - i; j++) {

                char nextChar = matrix[matrix.length - 1 - (i + j)][j];
                if (processCharacter(nextChar, diagonalMinorTopStatus)) {
                    counter++;
                }

                if (i == 0) {
                    continue;
                }

                nextChar = matrix[matrix.length - 1 - j][j + i];
                if (processCharacter(nextChar, diagonalMinorBottomStatus)) {
                    counter++;
                }

            }
        }
        return counter;
    }

    /**
     * @return true if this finds a sequence
     */
    private boolean processCharacter(char nextChar, SequenceCounterStatus status) {
        if (characterIsValid(nextChar) && nextChar == status.currentChar) {
            status.count++;
            if (status.count == LENGTH_SEQUENCE) {
                status.count = 0;
                status.currentChar = ' ';
                return true;
            }
        } else {
            status.currentChar = nextChar;
            status.count = 1;
        }
        return false;
    }

    /**
     * @return true if contains G,A,C or T
     */
    private static boolean characterIsValid(char character) {
        return VALID_CHARACTERS.contains(character);
    }

    private static class SequenceCounterStatus {
        int count = 0;
        char currentChar = ' ';
    }

    private DnaRecord saveDna(DnaRecord dnaRecord, boolean isMutant) {
        return dnaRecordRepository.saveDnaRecord(dnaRecord.toBuilder()
                .mutant(isMutant)
                .build());
    }
}
