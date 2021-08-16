package co.com.meli.api.mutant;

import co.com.meli.api.mutant.request.DnaRequest;
import co.com.meli.model.Stat;
import co.com.meli.usecase.AnalyzeDnaUseCase;
import co.com.meli.usecase.GetStatsUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest(classes = MutantController.class)
class MutantControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    AnalyzeDnaUseCase analyzeDnaUseCase;

    @MockBean
    GetStatsUseCase getStatsUseCase;

    ObjectWriter objectWriter = new ObjectMapper().writer();
    DnaRequest validMutantDnaRequest = new DnaRequest();
    DnaRequest validHumanDnaRequest = new DnaRequest();
    DnaRequest nullDnaRequest = new DnaRequest();
    DnaRequest emptyDnaRequest = new DnaRequest();
    DnaRequest invalidDnaRequest = new DnaRequest();
    Stat stat;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        validHumanDnaRequest.setDna(new String[]{"ATGC", "CAGT", "TTAT", "AGAC"});
        Mockito.when(analyzeDnaUseCase.isMutant(validHumanDnaRequest.toModel())).thenReturn(Boolean.FALSE);

        validMutantDnaRequest.setDna(new String[]{"GGGG", "CCCC", "TTAT", "AGAC"});
        Mockito.when(analyzeDnaUseCase.isMutant(validMutantDnaRequest.toModel())).thenReturn(Boolean.TRUE);

        emptyDnaRequest.setDna(new String[]{});
        invalidDnaRequest.setDna(new String[]{"AAAA", "AAAA", "AAAA", "A"});

        stat = Stat.builder()
                .countHumanDna(2L)
                .countMutantDna(1L)
                .ratio(0.5F)
                .build();
        Mockito.when(getStatsUseCase.getStats()).thenReturn(stat);
    }

    @Test
    void givenValidMutantDnaThenValidateMutantThenOkTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/mutant")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectWriter.writeValueAsString(validMutantDnaRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void givenValidHumanDnaThenValidateMutantThenForbiddenTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/mutant")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectWriter.writeValueAsString(validHumanDnaRequest)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void givenNullDnaThenValidateMutantThenBadRequestTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/mutant")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectWriter.writeValueAsString(nullDnaRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void givenEmptyDnaThenValidateMutantThenBadRequestTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/mutant")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectWriter.writeValueAsString(emptyDnaRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void givenInvalidDnaThenValidateMutantThenBadRequestTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/mutant")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectWriter.writeValueAsString(invalidDnaRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void getStatsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/mutant/stats")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.countMutantDna",
                        Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.countHumanDna",
                        Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.ratio",
                        Matchers.is(0.5)));
    }
}
