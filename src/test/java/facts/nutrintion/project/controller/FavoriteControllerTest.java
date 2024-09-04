package facts.nutrintion.project.controller;

import facts.nutrintion.project.dto.response.FavoriteRecipesResponse;
import facts.nutrintion.project.service.FavoriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FavoriteControllerTest {
    @Mock
    private FavoriteService favoriteService;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private FavoriteController favoriteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testUser");
    }
    @Test
    void testAddFavorite() {
        // Arrange
        Integer recipeId = 1;
        when(favoriteService.addToFavorite("testUser", recipeId)).thenReturn("Favorite added successfully");

        // Act
        ResponseEntity<String> response = favoriteController.addFavorite(recipeId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Favorite added successfully", response.getBody());
        verify(favoriteService, times(1)).addToFavorite("testUser", recipeId);
    }

    @Test
    void testShowFavorite() {
        // Arrange
        List<FavoriteRecipesResponse> mockFavorites = Arrays.asList(new FavoriteRecipesResponse(), new FavoriteRecipesResponse());
        when(favoriteService.showUserFavorites("testUser")).thenReturn(mockFavorites);

        // Act
        ResponseEntity<List<FavoriteRecipesResponse>> response = favoriteController.showFavorite();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockFavorites.size(), Objects.requireNonNull(response.getBody()).size());
        verify(favoriteService, times(1)).showUserFavorites("testUser");
    }

    @Test
    void testRemoveFavorite() {
        // Arrange
        Double recipeId = 1.0;
        when(favoriteService.removeFromFavorite("testUser", recipeId)).thenReturn("Favorite removed successfully");

        // Act
        ResponseEntity<String> response = favoriteController.removeFavorite(recipeId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Favorite removed successfully", response.getBody());
        verify(favoriteService, times(1)).removeFromFavorite("testUser", recipeId);
    }
}
