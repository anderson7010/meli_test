package co.com.meli.api.mutant;

import co.com.meli.api.mutant.request.DnaRequest;
import co.com.meli.usecase.AnalyzeDnaUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/mutant", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MutantController {

    private final AnalyzeDnaUseCase useCase;

    @PostMapping
    public ResponseEntity<String> validateMutant(@RequestBody DnaRequest dnaRequest) {
        boolean isMutant = useCase.isMutant(dnaRequest.getDna());
        if (isMutant) {
            return new ResponseEntity<>("Es mutante", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No es mutante", HttpStatus.FORBIDDEN);
        }
    }
}
