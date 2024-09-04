package facts.nutrintion.project.config;

import facts.nutrintion.project.dto.response.FactResponse;
import facts.nutrintion.project.dto.response.RecipeResponse;
import facts.nutrintion.project.entity.Recipe;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        PropertyMap<Recipe, RecipeResponse> recipeDtoMap = new PropertyMap<>() {
            protected void configure() {
                map().setFacts(source.getFacts()
                        .stream().map(fact ->
                                new FactResponse(fact.getCarbohydrate(), fact.getProtein(), fact.getFat())
                        ).toList());
            }
        };

        modelMapper.getConfiguration().setCollectionsMergeEnabled(true);
        return modelMapper;
    }
}
