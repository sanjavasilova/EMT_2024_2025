package mk.ukim.finki.emt.lab.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_HOST,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
