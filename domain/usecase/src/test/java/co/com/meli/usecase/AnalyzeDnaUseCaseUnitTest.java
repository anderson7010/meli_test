package co.com.meli.usecase;


import co.com.meli.usecase.factory.DnaFactory;
import org.junit.Assert;
import org.junit.Test;

public class AnalyzeDnaUseCaseUnitTest {

    private final AnalyzeDnaUseCase useCase = new AnalyzeDnaUseCase();

    @Test
    public void checkIsMutant() {
        String[] dna = DnaFactory.generateDna(6, true);
        boolean isMutant = useCase.isMutant(dna);
        Assert.assertTrue(isMutant);
    }

    @Test
    public void checkIsNotMutant() {
        String[] dna = DnaFactory.generateDna(6, false);
        boolean isMutant = useCase.isMutant(dna);
        Assert.assertFalse(isMutant);
    }

}
