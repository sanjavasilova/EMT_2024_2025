package mk.ukim.finki.emt.lab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.lab.model.domain.*;
import mk.ukim.finki.emt.lab.model.enumerations.AccommodationCategory;
import mk.ukim.finki.emt.lab.model.enumerations.Role;
import mk.ukim.finki.emt.lab.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final AccommodationRepository accommodationRepository;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final AccommodationRentRepository accommodationRentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AccommodationRepository accommodationRepository, CountryRepository countryRepository, HostRepository hostRepository, AccommodationRentRepository accommodationRentRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.accommodationRepository = accommodationRepository;
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.accommodationRentRepository = accommodationRentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        countryRepository.save(new Country("USA", "North America"));
        countryRepository.save(new Country("Greece", "Europe"));
        countryRepository.save(new Country("Egypt", "Africa"));

        userRepository.save(new User("john_evans", passwordEncoder.encode("john"),"John", "Evans", Role.ROLE_HOST,countryRepository.findById(1L).get()));
        userRepository.save(new User("athos_dimas", passwordEncoder.encode("athos"), "Athos", "Dimas", Role.ROLE_HOST, countryRepository.findById(2L).get()));
        userRepository.save(new User("kasto_adel", passwordEncoder.encode("kasto-"), "Kasto", "Adel", Role.ROLE_HOST, countryRepository.findById(3L).get()));

        accommodationRepository.save(new Accommodation("The Highlands", AccommodationCategory.HOTEL, userRepository.findByUsername("john_evans").get(),100));
        accommodationRepository.save(new Accommodation("Lumi", AccommodationCategory.APARTMENT, userRepository.findByUsername("athos_dimas").get(),50));
        accommodationRepository.save(new Accommodation("Meraki", AccommodationCategory.HOTEL, userRepository.findByUsername("kasto_adel").get(),3));

        accommodationRentRepository.save(new AccommodationRent(accommodationRepository.findById(1L).get(), true));

        accommodationRentRepository.save(new AccommodationRent(accommodationRepository.findById(2L).get(), true));
        accommodationRentRepository.save(new AccommodationRent(accommodationRepository.findById(2L).get(), true));

        accommodationRentRepository.save(new AccommodationRent(accommodationRepository.findById(3L).get(), true));

    }
}
