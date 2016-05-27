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

}
