package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("/example")
@PreAuthorize("denyAll()")
public class ExampleController {

    Collection<String> users = Arrays.asList("JOSE", "PEPE");
    Collection<String> privateUsers = Arrays.asList("MEPHISTO", "CRISTOFORO");

    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public Collection<String> getAll() {
        return users;
    }

    @GetMapping("/private")
    @PreAuthorize("hasAuthority('CREATE')")
    public Collection<String> getPrivateNames() {
        return privateUsers;
    }

}
