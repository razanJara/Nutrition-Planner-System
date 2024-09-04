package facts.nutrintion.project.controller;

import facts.nutrintion.project.dto.request.RecipeCalculateRequest;
import facts.nutrintion.project.dto.request.RecipeRequest;
import facts.nutrintion.project.dto.response.RecipeCalculateResponse;
import facts.nutrintion.project.dto.response.RecipeResponse;
import facts.nutrintion.project.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RecipeControllerTest {
    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testRecipeList() {
        // Arrange
        List<RecipeResponse> mockRecipes = Arrays.asList(new RecipeResponse(), new RecipeResponse());
        when(recipeService.getAllRecipes()).thenReturn(mockRecipes);

        // Act
        ResponseEntity<List<RecipeResponse>> response = recipeController.recipeList();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        verify(recipeService, times(1)).getAllRecipes();
    }
    @Test
    void testAddRecipe() {
        // Arrange
        RecipeRequest recipeRequest = new RecipeRequest();
        RecipeResponse mockResponse = new RecipeResponse();
        when(recipeService.addRecipe(recipeRequest)).thenReturn(mockResponse);

        // Act
        ResponseEntity<RecipeResponse> response = recipeController.addRecipe(recipeRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(recipeService, times(1)).addRecipe(recipeRequest);
    }

    @Test
    void testUpdateRate() {
        // Arrange
        Integer recipeId = 1;
        Double rate = 4.5;

        // Act
        ResponseEntity<String> response = recipeController.updateRate(recipeId, rate);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("updated successfully", response.getBody());
        verify(recipeService, times(1)).updateRate(recipeId, rate);
    }

    @Test
    void testCalculateRecipe() {
        // Arrange
        RecipeCalculateRequest request = new RecipeCalculateRequest();
        RecipeCalculateResponse mockResponse = new RecipeCalculateResponse();
        when(recipeService.calculateRecipe(request)).thenReturn(mockResponse);

        // Act
        ResponseEntity<RecipeCalculateResponse> response = recipeController.calculate(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(recipeService, times(1)).calculateRecipe(request);
    }

    @Test
    void testGetRecipe() {
        // Arrange
        Integer recipeId = 1;
        RecipeResponse mockResponse = new RecipeResponse();
        when(recipeService.getRecipe(recipeId)).thenReturn(mockResponse);

        // Act
        ResponseEntity<RecipeResponse> response = recipeController.getRecipe(recipeId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(recipeService, times(1)).getRecipe(recipeId);
    }
}
