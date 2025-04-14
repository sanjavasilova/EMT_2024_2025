package mk.ukim.finki.emt.lab.model.dto;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record CreateHostDto(
        String name,
        String surname,
        Long countryId
) {
    public static CreateHostDto from(Host host) {
        return new CreateHostDto(
                host.getName(),
                host.getSurname(),
                host.getCountry().getId()
        );
    }

    public static List<CreateHostDto> from(List<Host> hosts) {
        return hosts.stream().map(CreateHostDto::from).collect(Collectors.toList());
    }

    public Host toHost(Country country) {
        return new Host(name, surname, country);
    }
}
