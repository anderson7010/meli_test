package co.com.meli.usecase;

import co.com.meli.model.Stat;
import co.com.meli.model.gateways.DnaRecordRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetStatsUseCase {
    private final DnaRecordRepository dnaRecordRepository;

    public Stat getStats() {
        Long countMutantDna = dnaRecordRepository.mutantCount();
        Long countHumanDna = dnaRecordRepository.humanCount();

        return Stat.builder()
                .countMutantDna(countMutantDna)
                .countHumanDna(countHumanDna)
                .ratio(countHumanDna == 0 ? countMutantDna.floatValue() :
                        countMutantDna.floatValue() / countHumanDna.floatValue())
                .build();
    }
}
