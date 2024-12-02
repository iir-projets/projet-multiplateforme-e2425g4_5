package ma.plantes.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ClientAllergie {

    @EmbeddedId
    private ClientAllergieId id;

    @ManyToOne
    @MapsId("clientId")
    @JoinColumn( name = "client_id")
    @JsonIgnoreProperties("allergie")
    private User user;

    @ManyToOne
    @MapsId("allergieId")
    @JoinColumn( name = "allergie_id")
    @JsonIgnoreProperties("user")
    private Allergie allergie;
}
