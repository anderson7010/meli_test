package co.com.meli.usecase;


import co.com.meli.model.DnaRecord;
import co.com.meli.model.gateways.DnaRecordRepository;
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

    private final String[] verticalValidDna = {"ATGC", "ATGT", "ATAT", "ATAC"};
    private final String[] horizontalValidDna = {"GGGG", "CCCC", "TTAT", "AGAC"};
    private final String[] diagonalValidDna = {"ATGG", "CAGT", "TGAT", "GGAA"};
    private final String[] invalidDna = {"ATGC", "CAGT", "TTAT", "AGAC"};

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(dnaRecordRepository.saveDnaRecord(any())).thenReturn(dnaRecord);
    }

    @Test
    void givenVerticalValidDnaWhenCheckIsMutantTest() {
        dnaRecord = DnaRecord.builder()
                .dna(verticalValidDna)
                .build();
        Assertions.assertTrue(useCase.isMutant(dnaRecord));
    }

    @Test
    void givenHorizontalValidDnaWhenCheckIsMutantTest() {
        dnaRecord = DnaRecord.builder()
                .dna(horizontalValidDna)
                .build();
        Assertions.assertTrue(useCase.isMutant(dnaRecord));
    }

    @Test
    void givenDiagonalValidDnaWhenCheckIsMutantTest() {
        dnaRecord = DnaRecord.builder()
                .dna(diagonalValidDna)
                .build();
        Assertions.assertTrue(useCase.isMutant(dnaRecord));
    }

    @Test
    void givenInvalidDnaCheckIsNotMutantTest() {
        dnaRecord = DnaRecord.builder()
                .dna(invalidDna)
                .build();
        Assertions.assertFalse(useCase.isMutant(dnaRecord));
    }

}
