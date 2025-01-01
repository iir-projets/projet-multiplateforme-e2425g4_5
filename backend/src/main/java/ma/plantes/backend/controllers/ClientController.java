package ma.plantes.backend.controllers;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.dto.UserDto;
import ma.plantes.backend.entities.*;
import ma.plantes.backend.service.UserDetailsServiceImp;
import ma.plantes.backend.service.security.AuthenticationService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ClientController {


    private final UserDetailsServiceImp userDetailsServiceImp;
    private final AuthenticationService authenticationService;





    @GetMapping("/admin/clients")
    public ResponseEntity<List<User>> allClients(){

        return ResponseEntity.ok(userDetailsServiceImp.allClients());
    }
    @GetMapping("/admin/clients/{id}")
    public ResponseEntity<Optional<User>> getClientById(@PathVariable Long id){
        return ResponseEntity.ok(userDetailsServiceImp.getUserById(id));
    }

    @PatchMapping("/clients/{id}")
    public ResponseEntity<User> editProfile(@PathVariable Long id, @RequestBody UserDto userDto){
        if (userDto.getPassword() != null){
            userDetailsServiceImp.editProfile(userDto,id);
            return ResponseEntity.ok(authenticationService.changePassword(id,userDto.getPassword()));
        }
        return ResponseEntity.ok(userDetailsServiceImp.editProfile(userDto,id));

    }

    @PostMapping("/clients/{id}/maladie")
    public  ResponseEntity<List<ClientMaladie>> addMaladie(@PathVariable Long id,
                                                           @Param("maladie_id") Long maladie_id){
        return ResponseEntity.ok(userDetailsServiceImp.addMaladie(id,maladie_id));
    }

    @DeleteMapping("/clients/{id}/maladie")
    public  ResponseEntity<List<ClientMaladie>> deleteMaladie(@PathVariable Long id,
                                                           @Param("maladie_id") Long maladie_id){
        return ResponseEntity.ok(userDetailsServiceImp.deleteMaladie(id,maladie_id));
    }
    ///////////////////////////////////////////////////////////
    @PostMapping("/clients/{id}/allergie")
    public  ResponseEntity<List<ClientAllergie>> addAllergie(@PathVariable Long id,
                                                            @Param("allergie_id") Long allergie_id){
        return ResponseEntity.ok(userDetailsServiceImp.addAllergie(id,allergie_id));
    }

    @DeleteMapping("/clients/{id}/allergie")
    public  ResponseEntity<List<ClientAllergie>> deleteAllergie(@PathVariable Long id,
                                                             @Param("allergie_id") Long allergie_id){
        return ResponseEntity.ok(userDetailsServiceImp.deleteAllergie(id,allergie_id));
    }
    ///////////////////////////////////////////////////////////
    @PostMapping("/clients/{id}/medicament")
    public  ResponseEntity<List<ClientMedicament>> addMedicament(@PathVariable Long id,
                                                               @Param("medicament_id") Long medicament_id){
        return ResponseEntity.ok(userDetailsServiceImp.addMedicament(id,medicament_id));
    }

    @DeleteMapping("/clients/{id}/medicament")
    public  ResponseEntity<List<ClientMedicament>> deleteMedicament(@PathVariable Long id,
                                                                 @Param("medicament_id") Long medicament_id){
        return ResponseEntity.ok(userDetailsServiceImp.deleteMedicament(id,medicament_id));
    }



}
