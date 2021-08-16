package co.com.meli.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class DnaRecord {
    private final String[] dna;
    private final boolean mutant;
}
