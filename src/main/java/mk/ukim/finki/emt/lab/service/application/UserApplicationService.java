package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.model.dto.DisplayHostDto;
import mk.ukim.finki.emt.lab.model.dto.DisplayUserDto;
import mk.ukim.finki.emt.lab.model.dto.LoginDto;
import mk.ukim.finki.emt.lab.model.dto.RegisterDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserApplicationService {
    List<DisplayUserDto> findAll();

    Optional<DisplayUserDto> register(RegisterDto createUserDto);

    Optional<DisplayUserDto> login(LoginDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}


