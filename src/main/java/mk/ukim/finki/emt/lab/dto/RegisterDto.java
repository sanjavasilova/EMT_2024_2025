package mk.ukim.finki.emt.lab.dto;

import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.enumerations.Role;
import mk.ukim.finki.emt.lab.model.exceptions.PasswordsDoNotMatchException;

public record RegisterDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role,
        Long countryId
) {
    public boolean checkPassword(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }

    public User toUser(Country country) {
        if (checkPassword(password, repeatPassword)) {
            return new User(username, password, name, surname, role, country);
        }
        throw new PasswordsDoNotMatchException();
    }
}
