package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.lab.model.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.lab.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AccommodationService;
import mk.ukim.finki.emt.lab.service.domain.HostService;
import mk.ukim.finki.emt.lab.service.domain.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final UserService userService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService, UserService userService) {
        this.accommodationService = accommodationService;
        this.userService = userService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return DisplayAccommodationDto.from(accommodationService.findAll());
    }

    @Override
    public Optional<DisplayAccommodationDto> addAccommodation(CreateAccommodationDto createAccommodationDto) {
        User host = userService.findByUsername(createAccommodationDto.hostUsername());
        if (host.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_HOST"))) {
            return accommodationService.addAccommodation(createAccommodationDto.toAccommodation(host)).map(DisplayAccommodationDto::from);
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        accommodationService.deleteById(id);
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> editAccommodation(Long id, CreateAccommodationDto createAccommodationDto) {
        User host = userService.findByUsername(createAccommodationDto.hostUsername());
        if (host.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_HOST"))) {
            return Optional.empty();
        }
        return accommodationService.editAccommodation(id, createAccommodationDto.toAccommodation(host)).map(DisplayAccommodationDto::from);
    }
}
