package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.AccommodationRent;
import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.enumerations.Role;
import mk.ukim.finki.emt.lab.model.exceptions.*;
import mk.ukim.finki.emt.lab.repository.UserRepository;
import mk.ukim.finki.emt.lab.service.domain.AccommodationRentService;
import mk.ukim.finki.emt.lab.service.domain.AccommodationService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import mk.ukim.finki.emt.lab.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CountryService countryService;
    private final AccommodationRentService accommodationRentService;
    private final AccommodationService accommodationService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CountryService countryService, AccommodationRentService accommodationRentService, AccommodationService accommodationService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.countryService = countryService;
        this.accommodationRentService = accommodationRentService;
        this.accommodationService = accommodationService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User mostPopularHost() {
        List<User> hosts = userRepository.findAll().stream().filter(user -> user.getRole().equals(Role.ROLE_HOST)).collect(Collectors.toList());
        int max = 0;
        User mostPopularHost = hosts.get(0);
        for (User host : hosts) {
            List<Accommodation> accommodations = accommodationService.findByHost(host);
            int count = 0;
            for(Accommodation accommodation : accommodations) {
                List<AccommodationRent> accommodationRents = accommodationRentService.findByAccommodation(accommodation);
                count += (int) accommodationRents.stream().filter(AccommodationRent::isRent).count();
            }
            if (count > max){
                max = count;
                mostPopularHost = host;
            }
        }
        return mostPopularHost;
    }

    @Override
    public User register(
            String username,
            String password,
            String repeatPassword,
            String name,
            String surname,
            Role userRole,
            Long countryId
    ) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword)) throw new PasswordsDoNotMatchException();
        if (userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        Optional<Country> country = countryService.findById(countryId);
        if (country.isEmpty())
            throw new CountryDoesNotExist();
        User user = new User(username, passwordEncoder.encode(password), name, surname, userRole, country.get());
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(
                InvalidUserCredentialsException::new);
    }
}
