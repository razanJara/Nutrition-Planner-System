package facts.nutrintion.project.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FactRequest {
    Double carbohydrate;
    Double protein;
    Double fat;
}
