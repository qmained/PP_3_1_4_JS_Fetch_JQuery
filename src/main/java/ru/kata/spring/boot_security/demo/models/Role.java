package ru.kata.spring.boot_security.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Entity
@Data
@Table(name = "t_role")
public class Role implements GrantedAuthority {

    @Id
    private Long id;

    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {

    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
