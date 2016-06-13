package com.freedomsoccer.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private String name;
    private String role;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
    public String toString() {
        return name;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role role = (Role) obj;
        if (name == null) {
            if (role.name != null)
                return false;
        } else if (!name.equals(role.name))
            return false;
        return true;
    }
}
