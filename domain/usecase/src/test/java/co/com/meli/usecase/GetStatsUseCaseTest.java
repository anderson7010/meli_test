package co.com.meli.usecase;

import co.com.meli.model.Stat;
import co.com.meli.model.gateways.DnaRecordRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class GetStatsUseCaseTest {
    @Mock
    DnaRecordRepository dnaRecordRepository;

    @InjectMocks
    GetStatsUseCase useCase;

    Stat stat, noHumanStat;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        stat = Stat.builder()
                .countMutantDna(1L)
                .countHumanDna(2L)
                .ratio(0.5F)
                .build();
        noHumanStat = Stat.builder()
                .countMutantDna(1L)
                .countHumanDna(0L)
                .ratio(1F)
                .build();
    }

    @Test
    void getStatsTest() {
        Mockito.when(dnaRecordRepository.mutantCount()).thenReturn(1L);
        Mockito.when(dnaRecordRepository.humanCount()).thenReturn(2L);
        Assertions.assertEquals(stat, useCase.getStats());
    }

    @Test
    void givenNoHumanStatThenGetStatsTest() {
        Mockito.when(dnaRecordRepository.mutantCount()).thenReturn(1L);
        Mockito.when(dnaRecordRepository.humanCount()).thenReturn(0L);
        Assertions.assertEquals(noHumanStat, useCase.getStats());
    }
}
