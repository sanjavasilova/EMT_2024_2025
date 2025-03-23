package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.model.AccommodationRent;
import mk.ukim.finki.emt.lab.model.Host;
import mk.ukim.finki.emt.lab.model.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.lab.model.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.lab.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AccommodationService;
import mk.ukim.finki.emt.lab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return DisplayAccommodationDto.from(accommodationService.findAll());
    }

    @Override
    public Optional<DisplayAccommodationDto> addAccommodation(CreateAccommodationDto createAccommodationDto) {
        Optional<Host> host = hostService.findById(createAccommodationDto.hostId());
        if (host.isPresent()) {
            return accommodationService.addAccommodation(createAccommodationDto.toAccommodation(host.get())).map(DisplayAccommodationDto::from);
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
        Optional<Host> host = hostService.findById(createAccommodationDto.hostId());
        if (host.isEmpty()) {
            return Optional.empty();
        }
        return accommodationService.editAccommodation(id, createAccommodationDto.toAccommodation(host.get())).map(DisplayAccommodationDto::from);
    }
}
