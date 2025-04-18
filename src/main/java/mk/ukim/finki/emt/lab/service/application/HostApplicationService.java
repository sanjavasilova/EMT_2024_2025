package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.CreateHostDto;
import mk.ukim.finki.emt.lab.dto.DisplayHostDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface HostApplicationService {
    List<DisplayHostDto> findAll();

    Optional<DisplayHostDto> addHost(CreateHostDto host);

    Optional<DisplayHostDto> findById(Long id);

    void deleteById(Long id);

    Optional<DisplayHostDto> editHost(Long id, CreateHostDto hostDto);
}
