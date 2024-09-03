package facts.nutrintion.project.entity;

import facts.nutrintion.project.Favorite;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Size(max = 50)
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Size(max = 100)
    @NotNull
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @NotNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled = false;

    @OneToMany(mappedBy = "username")
    private Set<Authority> authorities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "username", cascade = CascadeType.ALL)
    private Set<Favorite> favorites = new LinkedHashSet<>();

}