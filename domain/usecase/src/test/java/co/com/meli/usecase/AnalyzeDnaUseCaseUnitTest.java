package co.com.meli.usecase;


import co.com.meli.model.DnaRecord;
import co.com.meli.model.gateways.DnaRecordRepository;
import co.com.meli.usecase.factory.DnaFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;

class AnalyzeDnaUseCaseUnitTest {
    @Mock
    DnaRecordRepository dnaRecordRepository;

    @InjectMocks
    AnalyzeDnaUseCase useCase;

    private DnaRecord dnaRecord;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(dnaRecordRepository.saveDnaRecord(any())).thenReturn(dnaRecord);
    }

    @Test
    void checkIsMutant() {
        String[] dna = DnaFactory.generateDna(6, true);
        dnaRecord = DnaRecord.builder()
                .dna(dna)
                .build();
        boolean isMutant = useCase.isMutant(dnaRecord);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void checkIsNotMutant() {
        String[] dna = DnaFactory.generateDna(6, false);
        dnaRecord = DnaRecord.builder()
                .dna(dna)
                .build();
        boolean isMutant = useCase.isMutant(dnaRecord);
        Assertions.assertFalse(isMutant);
    }

}
