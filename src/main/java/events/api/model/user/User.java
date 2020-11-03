package events.api.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties
@SQLDelete(sql = "UPDATE users SET is_deleted=true,status=false WHERE userid=?")
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR(15)")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "VARCHAR(15)")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false, columnDefinition = "VARCHAR(50)")
    private String email;

    /**
     * The username (String). Has same value as email.
     */
    @NotNull
    @Column
    private String username;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    private String password;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean status;


    @Column(name = "phone_no", nullable = false, columnDefinition = "VARCHAR(10)")
    private String phoneNo;

    @Column(name = "is_deleted", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isDeleted;

    @Column(name = "date_created", updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
     @Temporal(value = TemporalType.TIMESTAMP)
    private Date dateCreated;

    @PrePersist
    void dateCreatedAt() {
        this.dateCreated = new Date();
    }

    @Column(name = "date_updated", columnDefinition = "DATETIME ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dateUpdated;

    @PreUpdate
    void dateUpdatedAt() {
        this.dateUpdated = new Date();
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by", referencedColumnName = "email")
    private User userMaker;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "approved_by", referencedColumnName = "email")
    private User userChecker;


    /**
     * Part of the join relationship between user and role
     * connects users to the user role combination
     */
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "user",
            allowSetters = true)
    private Set<UserRoles> roles = new HashSet<>();


//    @JsonIgnore
//    @OneToOne(mappedBy="user")
//    private Account account;

    /**
     * Part of the join relationship between user and transactions
     * maps users to their set of transactions
     */
//    @OneToMany(mappedBy = "merchant",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true, fetch = FetchType.EAGER)
//    @JsonIgnoreProperties(value = "merchant",
//            allowSetters = true)
//    private List<Transaction> transactions = new ArrayList<>();



    // This field pertains to an agent: contains all terminals assigned to an agent
//    @JsonIgnore
//    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnoreProperties(value = "agent", allowSetters = true)
//    List<Terminal> assignedTerminals = new ArrayList<Terminal>();

    /**
     * Setter for user role combinations
     *
     * @param roles Change the list of user role combinations associated with this user to this one
     */
    public void setRoles(Set<UserRoles> roles)
    {
        this.roles = roles;
    }

    /**
     * A transient field, for mapping to the required role field of
     * incoming json formatted requests during user creation
     */
    @Transient
    private String role;

    /**
     * Ensured status and isDeleted values are also updated in the
     * current session
     */
    @PreRemove
    public void deleteUser () {
        this.isDeleted = true;
        this.status = false;
    }
}