package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host, Long> {
}
