package facts.nutrintion.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authorities")
public class Authority {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User username;

    @Size(max = 50)
    @Column(name = "authority", length = 50)
    private String authority;
    @Id
    private Long id;
}