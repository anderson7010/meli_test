package co.com.meli.jpa.mutant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DnaRecordDataRepository extends JpaRepository<DnaRecordData, Long> {
    Long countByMutantTrue();

    Long countByMutantFalse();
}
