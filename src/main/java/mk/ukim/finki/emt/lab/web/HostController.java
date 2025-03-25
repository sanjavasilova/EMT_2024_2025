package mk.ukim.finki.emt.lab.web;

import mk.ukim.finki.emt.lab.model.dto.CreateHostDto;
import mk.ukim.finki.emt.lab.model.dto.DisplayHostDto;
import mk.ukim.finki.emt.lab.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/host")
public class HostController {
    private final HostApplicationService hostApplicationService;

    public HostController(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @GetMapping
    public List<DisplayHostDto> getAllHosts() {
        return hostApplicationService.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> addHost(@RequestBody CreateHostDto host) {
        return hostApplicationService.addHost(host).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHost(@PathVariable Long id) {
        if (hostApplicationService.findById(id).isPresent()) {
            hostApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDto> editAccommodation(@PathVariable Long id, @RequestBody CreateHostDto hostDto) {
        return hostApplicationService.editHost(id, hostDto).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
