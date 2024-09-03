package facts.nutrintion.project.repository;

import facts.nutrintion.project.Favorite;
import facts.nutrintion.project.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository <Favorite, Integer>{

    @Query("select r.recipe from Favorite r where r.username.username = :username")
    List<Recipe> findByUsername(@Param("username") String username);

    @Transactional
    @Modifying
    @Query("DELETE FROM Favorite f WHERE f.username.username = :username AND f.recipe.id = :id")
    int deleteByUsernameAndRecipe(@Param("username") String name, @Param("id") Double recipeId);
}
