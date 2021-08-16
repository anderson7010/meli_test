package co.com.meli.api.mutant;

import co.com.meli.api.mutant.request.DnaRequest;
import co.com.meli.api.mutant.response.Response;
import co.com.meli.model.Stat;
import co.com.meli.usecase.AnalyzeDnaUseCase;
import co.com.meli.usecase.GetStatsUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "REST API for analyze mutants")
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/mutant", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MutantController {
    private static final String MUTANT = "It's mutant!";
    private static final String HUMAN = "It's human!";
    private static final String SUCCESSFUL_VALIDATION = "Validation ended successfully";
    private static final String SUCCESSFUL_QUERY = "Query ended successfully";

    private final AnalyzeDnaUseCase analyzeDnaUseCase;
    private final GetStatsUseCase getStatsUseCase;

    @ApiOperation(value = "Analyze a mutant by DNA", notes = "If the DNA sequence belongs to a mutant, it will " +
            "return a message and a response code <b>200</b>. Otherwise the response code it will be <b>403</b>.",
            response = Response.class)
    @PostMapping
    public ResponseEntity<Response<String>> validateMutant(@RequestBody DnaRequest dnaRequest) {
        Response<String> response = new Response<>();
        try {
            dnaRequest.validate();
            if (analyzeDnaUseCase.isMutant(dnaRequest.toModel())) {
                response.setData(MUTANT);
                response.setMessage(SUCCESSFUL_VALIDATION);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setData(HUMAN);
                response.setMessage(SUCCESSFUL_VALIDATION);
                return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
            }
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Check stats of the mutant previous validations", response = Response.class)
    @GetMapping("/stats")
    public ResponseEntity<Response<Stat>> getStats() {
        Response<Stat> response = new Response<>();
        response.setData(getStatsUseCase.getStats());
        response.setMessage(SUCCESSFUL_QUERY);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
