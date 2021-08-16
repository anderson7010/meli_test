package co.com.meli.api.mutant.request;

import co.com.meli.model.DnaRecord;
import lombok.Data;

import java.util.Arrays;

@Data
public class DnaRequest {
    private String[] dna;

    public DnaRecord toModel() {
        return DnaRecord.builder()
                .dna(dna)
                .build();
    }

    public void validate() {
        if (dna == null || dna.length < 4 || Arrays.stream(dna)
                .anyMatch(str -> str.length() != dna.length)) {
            throw new IllegalArgumentException("It's necessary a valid DNA sequence.");
        }
    }
}
