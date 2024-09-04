package facts.nutrintion.project.controller;

import facts.nutrintion.project.dto.request.RecipeCalculateRequest;
import facts.nutrintion.project.dto.request.RecipeRequest;
import facts.nutrintion.project.dto.response.RecipeCalculateResponse;
import facts.nutrintion.project.dto.response.RecipeResponse;
import facts.nutrintion.project.service.RecipeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/list")
    public ResponseEntity<List<RecipeResponse>> recipeList() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }
    @PostMapping("/add")
    public ResponseEntity<RecipeResponse> addRecipe(@RequestBody RecipeRequest recipeRequest) {
        RecipeResponse recipe = recipeService.addRecipe(recipeRequest);
        return ResponseEntity.ok(recipe);
    }
    @PutMapping("/rate_update/{recipeId}/{rate}")
    public ResponseEntity<String> updateRate(@PathVariable Integer recipeId, @PathVariable Double rate) {
        recipeService.updateRate(recipeId, rate);
        return ResponseEntity.ok("updated successfully");
    }

    @PostMapping("/calculate")
    public ResponseEntity<RecipeCalculateResponse> calculate(@RequestBody RecipeCalculateRequest request) {
        return ResponseEntity.ok(recipeService.calculateRecipe(request));
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeResponse> getRecipe(@PathVariable(name = "recipeId") Integer recipeId) {
        return ResponseEntity.ok(recipeService.getRecipe(recipeId));
    }

}
