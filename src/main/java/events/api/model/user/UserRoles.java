package events.api.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * The entity allowing interaction with the user_roles table.
 * The join table between users and roles.
 * <p>
 * Table enforces a unique constraint of the combination of userid and role_id.
 * These two together form the primary key.
 * <p>
 * When you have a compound primary key, you must implement Serializable for Hibernate
 * When you implement Serializable you must implement equals and hash code
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_roles")
@IdClass(UserRolesId.class)
public class UserRoles
        implements Serializable
{
    /**
     * 1/2 of the primary key (long) for userroles.
     * Also is a foreign key into the users table
     */
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = "roles",
            allowSetters = true)
    private User user;

    /**
     * 1/2 of the primary key (long) for userroles.
     * Also is a foreign key into the roles table
     */
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "role_id")
    @JsonIgnoreProperties(value = "users",
            allowSetters = true)
    private Role role;


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof UserRoles))
        {
            return false;
        }
        UserRoles that = (UserRoles) o;
        return ((user == null) ? 0 : user.getUserId()) == ((that.user == null) ? 0 : that.user.getUserId()) &&
                ((role == null) ? 0 : role.getRoleId()) == ((that.role == null) ? 0 : that.role.getRoleId());
    }

    @Override
    public int hashCode()
    {
        // return Objects.hash(user.getUserId(), role.getRoleId());
        return 37;
    }
}
