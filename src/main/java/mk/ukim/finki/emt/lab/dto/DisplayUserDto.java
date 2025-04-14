package mk.ukim.finki.emt.lab.dto;

import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.enumerations.Role;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayUserDto(String username, String name, String surname, Role role, Long countryId) {

    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole(),
                user.getCountry().getId()
        );
    }

    public static List<DisplayUserDto> from(List<User> users) {
        return users.stream().map(DisplayUserDto::from).collect(Collectors.toList());
    }

    public User toUser(Country country) {
        return new User(username, null, name, surname, role, country);
    }
}
