package facts.nutrintion.project.controller;

import facts.nutrintion.project.dto.response.FavoriteRecipesDto;
import facts.nutrintion.project.service.FavoriteService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping("/add/{recipeId}")
    public ResponseEntity<String> addFavorite(@PathVariable Integer recipeId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(favoriteService.addToFavorite(authentication.getName(), recipeId));
    }

    @GetMapping("/list")
    public ResponseEntity<List<FavoriteRecipesDto>> showFavorite() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(favoriteService.showUserFavorites(authentication.getName()));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFavorite(Double recipeId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(favoriteService.removeFromFavorite(authentication.getName(), recipeId));
    }
}
