package facts.nutrintion.project.entity;

import facts.nutrintion.project.Favorite;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "calories")
    private Integer calories;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<Fact> facts = new LinkedHashSet<>();

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 5)
    @Column(name = "rating")
    private Double rating;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<Favorite> favorites = new LinkedHashSet<>();

}