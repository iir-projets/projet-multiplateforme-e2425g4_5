package ma.plantes.backend.controllers;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.User;
import ma.plantes.backend.service.UserDetailsServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.Param;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/admin/clients")
@RestController
@RequiredArgsConstructor
public class ClientController {
    private UserDetailsServiceImp userDetailsServiceImp;

    @GetMapping
    public ResponseEntity<List<User>> allClients(){
        return ResponseEntity.status(404).body(userDetailsServiceImp.allClients());
    }
    @GetMapping("{id}")
    public ResponseEntity<Optional<User>> getClientById(@PathVariable Long id){
        return ResponseEntity.status(404).body(userDetailsServiceImp.getUserById(id));
    }
}
