package facts.nutrintion.project.repository;

import facts.nutrintion.project.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository <Recipe, Integer>{
    @Query("select r from Recipe r where r.id = :id")
    Optional<Recipe> findByRecipeId(@Param("id") Integer recipeId);

    @Transactional
    @Modifying
    @Query("update Recipe r set r.rating = :rate where r.id = :id ")
    void updateRecipeById(@Param("id")Integer recipeId,@Param("rate") Double rate);
}
