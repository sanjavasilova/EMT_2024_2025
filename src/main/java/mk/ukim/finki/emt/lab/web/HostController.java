package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import mk.ukim.finki.emt.lab.dto.DisplayUserDto;
import mk.ukim.finki.emt.lab.service.application.HostApplicationService;
import mk.ukim.finki.emt.lab.service.application.UserApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/host")
public class HostController {
    private final HostApplicationService hostApplicationService;
    private final UserApplicationService userApplicationService;

    public HostController(HostApplicationService hostApplicationService, UserApplicationService userApplicationService) {
        this.hostApplicationService = hostApplicationService;
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Get all hosts", description = "Retrieve a list of all hosts")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved hosts")
    @GetMapping
//    public List<DisplayHostDto> getAllHosts() {
//        return hostApplicationService.findAll();
//    }
    public List<DisplayUserDto> getAllHosts() {
        return userApplicationService.findAll();
    }

//    @PreAuthorize("isAuthenticated()")
//    @Operation(summary = "Add a new host", description = "Create a new host")
//    @ApiResponse(responseCode = "200", description = "Successfully added the host")
//    @ApiResponse(responseCode = "400", description = "Bad Request")
//    @PostMapping("/add")
//    public ResponseEntity<DisplayHostDto> addHost(@RequestBody CreateHostDto host) {
//        return hostApplicationService.addHost(host).map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }

//    @PreAuthorize("isAuthenticated()")
//    @Operation(summary = "Delete a host", description = "Delete a host by ID")
//    @ApiResponse(responseCode = "204", description = "Successfully deleted the host")
//    @ApiResponse(responseCode = "404", description = "Host not found")
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteHost(@PathVariable Long id) {
//        if (hostApplicationService.findById(id).isPresent()) {
//            hostApplicationService.deleteById(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @PreAuthorize("isAuthenticated()")
//    @Operation(summary = "Edit a host", description = "Edit an existing host by ID")
//    @ApiResponse(responseCode = "200", description = "Successfully edited the host")
//    @ApiResponse(responseCode = "400", description = "Bad Request")
//    @PutMapping("/edit/{id}")
//    public ResponseEntity<DisplayHostDto> editHost(@PathVariable Long id, @RequestBody CreateHostDto hostDto) {
//        return hostApplicationService.editHost(id, hostDto).map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }
}
