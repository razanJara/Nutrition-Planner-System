package facts.nutrintion.project.service;

import facts.nutrintion.project.Favorite;
import facts.nutrintion.project.dto.response.FavoriteRecipesResponse;
import facts.nutrintion.project.entity.Recipe;
import facts.nutrintion.project.entity.User;
import facts.nutrintion.project.repository.FavoriteRepository;
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
public class FavoriteService {

    FavoriteRepository favoriteRepository;
    RecipeRepository recipiesRepository;
    ModelMapper modelMapper;

    public String addToFavorite(String username, Integer recipeId) {
        Favorite favorite = new Favorite();
        User user = new User();
        user.setUsername(username);
        Optional<Recipe> recipe = recipiesRepository.findByRecipeId(recipeId);
        if (recipe.isEmpty()) {
            return "no recipe with this id!";
        }
        favorite.setRecipe(recipe.get());
        favorite.setUsername(user);
        favoriteRepository.save(favorite);
        return "added to favorite";
    }

    public List<FavoriteRecipesResponse> showUserFavorites(String username) {
        List<Recipe>favoriteRecipes = favoriteRepository.findByUsername(username);
        if (favoriteRecipes.isEmpty()) {
            return null;
        }
        return favoriteRecipes.stream()
                .map(recipe -> modelMapper.map(recipe, FavoriteRecipesResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public String removeFromFavorite(String name, Double recipeId) {
        favoriteRepository.deleteByUsernameAndRecipe(name, recipeId);
        return "removed from favorite";
    }
}
