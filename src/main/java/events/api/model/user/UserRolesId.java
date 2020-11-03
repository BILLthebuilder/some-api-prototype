package events.api.model.user;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

/**
 * Class to represent the complex primary key for UserRoles
 */
@Embeddable
public class UserRolesId
        implements Serializable
{
    /**
     * The id of the user
     */
    private UUID user;

    /**
     * The id of the role
     */
    private UUID role;

    /**
     * The default constructor required by JPA
     */
    public UserRolesId()
    {
    }

    /**
     * Getter for the user id
     *
     * @return UUIDthe user id
     */
    public UUID getUser()
    {
        return user;
    }

    /**
     * Setter for the user id
     *
     * @param user the new user id for this object
     */
    public void setUser(UUID user)
    {
        this.user = user;
    }

    /**
     * Getter for the role id
     *
     * @return UUID the role id
     */
    public UUID getRole()
    {
        return role;
    }

    /**
     * The setter for the role id
     *
     * @param role the new role id for this object
     */
    public void setRole(UUID role)
    {
        this.role = role;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        // boolean temp = (o.getClass() instanceof Class);
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        UserRolesId that = (UserRolesId) o;
        return user == that.user &&
                role == that.role;
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}