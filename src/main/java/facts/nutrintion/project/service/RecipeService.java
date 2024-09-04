package facts.nutrintion.project.service;

import facts.nutrintion.project.dto.request.RecipeCalculateRequest;
import facts.nutrintion.project.dto.request.RecipeRequest;
import facts.nutrintion.project.dto.response.RecipeCalculateResponse;
import facts.nutrintion.project.dto.response.RecipeResponse;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeService {
    RecipeRepository recipiesRepository;
    ModelMapper modelMapper;

    public List<RecipeResponse> getAllRecipes() {
        return recipiesRepository.findAll().stream()
                .map(recipe -> modelMapper.map(recipe, RecipeResponse.class))
                .collect(Collectors.toList());
    }

    public RecipeResponse addRecipe(RecipeRequest request) {
        Recipe recipy = modelMapper.map(request, Recipe.class);
        Fact mappedFact = modelMapper.map(request.getFacts(), Fact.class);
        mappedFact.setRecipe(recipy);
        recipy.getFacts().add(mappedFact);
        Recipe savedRecipy = recipiesRepository.save(recipy);
        return modelMapper.map(savedRecipy, RecipeResponse.class);
    }

    @Transactional
    public void updateRate(Integer recipeId, Double rate) {
        recipiesRepository.updateRecipeById(recipeId, rate);
        recipiesRepository.findAll();
    }

    public RecipeCalculateResponse calculateRecipe(RecipeCalculateRequest request) {
        List<Recipe> recipeList = recipiesRepository.findAllById(request.getRecipeId());
        RecipeCalculateResponse recipeCalculateResponse = new RecipeCalculateResponse();
        recipeList.forEach(recipe -> {
            if(recipeCalculateResponse.getCalories() == null){
                recipeCalculateResponse.setCalories(recipe.getCalories());
            } else{
                recipeCalculateResponse.setCalories(recipeCalculateResponse.getCalories() + recipe.getCalories());
            }
            recipe.getFacts().forEach(fact -> {
                if(recipeCalculateResponse.getFat() == null){
                    recipeCalculateResponse.setFat(fact.getFat());
                } else {
                    recipeCalculateResponse.setFat(recipeCalculateResponse.getFat() + fact.getFat());
                }
                if(recipeCalculateResponse.getProtein() == null){
                    recipeCalculateResponse.setProtein(fact.getProtein());
                } else{
                    recipeCalculateResponse.setProtein(recipeCalculateResponse.getProtein() + fact.getProtein());
                }
                if(recipeCalculateResponse.getCarbohydrate() == null) {
                    recipeCalculateResponse.setCarbohydrate(fact.getCarbohydrate());
                } else{
                    recipeCalculateResponse.setCarbohydrate(recipeCalculateResponse.getCarbohydrate() + fact.getCarbohydrate());
                }
            });
        });
        return recipeCalculateResponse;
    }

    public RecipeResponse getRecipe(Integer recipeId) {
        Optional<Recipe> recipe = recipiesRepository.findById(recipeId);
        return recipe.map(value -> modelMapper.map(value, RecipeResponse.class)).orElse(null);
    }
}
