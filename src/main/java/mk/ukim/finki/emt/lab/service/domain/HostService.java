package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Host;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface HostService {
    Optional<Host> findById(Long id);

    List<Host> findAll();

    Optional<Host> addHost(Host host);

    void deleteById(Long id);

    Optional<Host> editHost(Long id, Host host);
}
