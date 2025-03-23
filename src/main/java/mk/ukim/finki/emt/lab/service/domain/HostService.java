package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.Host;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface HostService {
    Optional<Host> findById(Long id);
}
