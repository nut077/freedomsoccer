package com.freedomsoccer.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import sun.plugin.util.UserProfile;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;
    @NotEmpty
    @Size(min = 8)
    private String password;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name="user_username")},
            inverseJoinColumns = {@JoinColumn(name = "role_name")}
    )
    private List<Role> roles;

    public String toString() {
        return "User [username=" + username + "]";
    }
}
