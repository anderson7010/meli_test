package co.com.meli.api.mutant.request;

import co.com.meli.model.DnaRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DnaRequest {
    private String[] dna;

    public DnaRecord toModel() {
        return DnaRecord.builder()
                .dna(dna)
                .build();
    }
}
