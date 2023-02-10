package az.company.kafkatutorialproject.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {

    private String code;
    private String message;

}
