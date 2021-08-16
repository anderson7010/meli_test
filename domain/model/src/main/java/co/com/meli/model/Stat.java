package co.com.meli.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Stat {
    private final Long countMutantDna;
    private final Long countHumanDna;
    private final Float ratio;
}
