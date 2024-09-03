package facts.nutrintion.project.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeDto {
    String name;
    String description;
    Integer calories;
    Double rating;
    List<FactDto> facts;
}
