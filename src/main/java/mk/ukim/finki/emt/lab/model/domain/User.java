package mk.ukim.finki.emt.lab.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt.lab.model.enumerations.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "accommodation_user")
@Data
public class User implements UserDetails {

    @Id
    private String username;

    @JsonIgnore
    private String password;

    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    private Country country;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;


    public User() {}

    public User(String username, String password, String name, String surname, Role role, Country country) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.country = country;
    }

    public User(UserDetails userDetails) {
        this.username = userDetails.getUsername();
        this.password = userDetails.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }
}