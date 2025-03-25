package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.Accommodation;
import mk.ukim.finki.emt.lab.model.AccommodationCategory;
import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.model.Host;
import mk.ukim.finki.emt.lab.repository.HostRepository;
import mk.ukim.finki.emt.lab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> addHost(Host host) {
        if (host.getName() == null || host.getName().isEmpty()) {
            return Optional.empty();
        }
        if (host.getSurname() == null || host.getSurname().isEmpty()) {
            return Optional.empty();
        }
        if (host.getCountry() == null) {
            return Optional.empty();
        }
        Country country = host.getCountry();
        return Optional.of(hostRepository.save(new Host(host.getName(), host.getSurname(), country)));
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }

    @Override
    public Optional<Host> editHost(Long id, Host host) {
        if (findById(id).isEmpty()) {
            return Optional.empty();
        }
        Host hostToEdit = findById(id).get();
        if (host.getName() != null) {
            hostToEdit.setName(host.getName());
        }
        if (host.getSurname() != null) {
            hostToEdit.setSurname(host.getSurname());
        }
        if (host.getCountry() != null) {
            hostToEdit.setCountry(host.getCountry());
        }
        return Optional.of(hostRepository.save(hostToEdit));
    }
}
