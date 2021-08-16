package co.com.meli.api.mutant.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Response<T> {
    private T data;
    private LocalDateTime date = LocalDateTime.now();
    private String message;
}
