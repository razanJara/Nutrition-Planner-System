package facts.nutrintion.project.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FactDto {
    Double carbohydrate;
    Double protein;
    Double fat;
}
