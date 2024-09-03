package facts.nutrintion.project.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeRequest {
    String name;
    String description;
    Integer calories;
    FactRequest facts;
    Double rating;
}
