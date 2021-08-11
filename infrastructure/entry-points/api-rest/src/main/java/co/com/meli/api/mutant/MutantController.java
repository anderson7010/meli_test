package co.com.meli.api.mutant;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/mutant", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MutantController {

    @PostMapping
    public ResponseEntity<String> validateMutant() {
        return new ResponseEntity<>("Validacion en construccion!", HttpStatus.OK);
    }
}
