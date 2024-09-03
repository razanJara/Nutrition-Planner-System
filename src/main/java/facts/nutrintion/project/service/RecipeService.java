package facts.nutrintion.project.service;

import facts.nutrintion.project.dto.request.RecipeRequest;
import facts.nutrintion.project.dto.response.RecipeDto;
import facts.nutrintion.project.entity.Fact;
import facts.nutrintion.project.entity.Recipe;
import facts.nutrintion.project.repository.RecipeRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeService {
    RecipeRepository recipiesRepository;
    ModelMapper modelMapper;

    public List<RecipeDto> getAllRecipes() {
        return recipiesRepository.findAll().stream()
                .map(recipe -> modelMapper.map(recipe, RecipeDto.class))
                .collect(Collectors.toList());
    }

    public RecipeDto addRecipe(RecipeRequest request) {
        Recipe recipy = modelMapper.map(request, Recipe.class);
        Fact mappedFact = modelMapper.map(request.getFacts(), Fact.class);
        mappedFact.setRecipe(recipy);
        recipy.getFacts().add(mappedFact);
        Recipe savedRecipy = recipiesRepository.save(recipy);
        return modelMapper.map(savedRecipy, RecipeDto.class);
    }

    @Transactional
    public void updateRate(Integer recipeId, Double rate) {
        recipiesRepository.updateRecipeById(recipeId, rate);
        recipiesRepository.findAll();
    }
}
