package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.dto.CreateHostDto;
import mk.ukim.finki.emt.lab.dto.DisplayHostDto;
import mk.ukim.finki.emt.lab.service.application.HostApplicationService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import mk.ukim.finki.emt.lab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {
    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return DisplayHostDto.from(hostService.findAll());
    }

    @Override
    public Optional<DisplayHostDto> addHost(CreateHostDto host) {
        Optional<Country> country = countryService.findById(host.countryId());
        if (country.isPresent()) {
            return hostService.addHost(host.toHost(country.get())).map(DisplayHostDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
    }

    @Override
    public Optional<DisplayHostDto> editHost(Long id, CreateHostDto hostDto) {
        Optional<Country> country = countryService.findById(hostDto.countryId());
        if (country.isEmpty()) {
            return Optional.empty();
        }
        return hostService.editHost(id, hostDto.toHost(country.get())).map(DisplayHostDto::from);
    }
}
