package az.company.kafkatutorialproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

    private String name;
    private String author;
    private Integer publishYear;
    private String isbn;

}
