package co.com.meli.jpa.mutant;

import co.com.meli.model.DnaRecord;
import co.com.meli.model.gateways.DnaRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DnaRecordRepositoryAdapter implements DnaRecordRepository {

    private final DnaRecordDataRepository repository;
    private final DnaRecordMapper mapper;

    @Override
    public DnaRecord saveDnaRecord(DnaRecord dnaRecord) {
        return mapper.toEntity(repository.save(mapper.toData(dnaRecord)));
    }

    @Override
    public Long mutantCount() {
        return repository.countByMutantTrue();
    }

    @Override
    public Long humanCount() {
        return repository.countByMutantFalse();
    }
}
