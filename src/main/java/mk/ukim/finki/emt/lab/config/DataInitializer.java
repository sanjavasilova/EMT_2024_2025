package mk.ukim.finki.emt.lab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.lab.model.*;
import mk.ukim.finki.emt.lab.repository.AccommodationRentRepository;
import mk.ukim.finki.emt.lab.repository.AccommodationRepository;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.repository.HostRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final AccommodationRepository accommodationRepository;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final AccommodationRentRepository accommodationRentRepository;

    public DataInitializer(AccommodationRepository accommodationRepository, CountryRepository countryRepository, HostRepository hostRepository, AccommodationRentRepository accommodationRentRepository) {
        this.accommodationRepository = accommodationRepository;
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.accommodationRentRepository = accommodationRentRepository;
    }

    @PostConstruct
    public void init() {
        countryRepository.save(new Country("USA", "North America"));
        countryRepository.save(new Country("Greece", "Europe"));
        countryRepository.save(new Country("Egypt", "Africa"));

        hostRepository.save(new Host("John", "Evans", countryRepository.findById(1L).get()));
        hostRepository.save(new Host("Athos", "Dimas", countryRepository.findById(2L).get()));
        hostRepository.save(new Host("Kasto", "Adel", countryRepository.findById(3L).get()));

        accommodationRepository.save(new Accommodation("The Highlands", AccommodationCategory.HOTEL, hostRepository.findById(1L).get(),100));
        accommodationRepository.save(new Accommodation("Lumi", AccommodationCategory.APARTMENT, hostRepository.findById(2L).get(),50));
        accommodationRepository.save(new Accommodation("Meraki", AccommodationCategory.HOTEL, hostRepository.findById(3L).get(),70));

        accommodationRentRepository.save(new AccommodationRent(accommodationRepository.findById(1L).get(), true));
        accommodationRentRepository.save(new AccommodationRent(accommodationRepository.findById(1L).get()));
        accommodationRentRepository.save(new AccommodationRent(accommodationRepository.findById(1L).get()));

        accommodationRentRepository.save(new AccommodationRent(accommodationRepository.findById(2L).get(), true));
        accommodationRentRepository.save(new AccommodationRent(accommodationRepository.findById(2L).get()));
        accommodationRentRepository.save(new AccommodationRent(accommodationRepository.findById(2L).get(), true));

        accommodationRentRepository.save(new AccommodationRent(accommodationRepository.findById(3L).get()));
        accommodationRentRepository.save(new AccommodationRent(accommodationRepository.findById(3L).get()));
        accommodationRentRepository.save(new AccommodationRent(accommodationRepository.findById(3L).get(), true));

    }
}
