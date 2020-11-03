package events.api.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Data
@Table(name = "roles")
public class Role implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(nullable = false, name = "role_id")
    private UUID roleId;

    @Column(length = 100, unique = true, nullable = false)
    @NotNull
    private String roleName;

    /**
     * Part of the join relationship between user and role
     * connects roles to the user role combination
     */
    @OneToMany(mappedBy = "role",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = "role",
            allowSetters = true)
    private Set<UserRoles> users = new HashSet<>();

}
