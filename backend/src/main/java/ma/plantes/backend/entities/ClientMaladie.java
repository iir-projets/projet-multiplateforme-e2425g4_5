package ma.plantes.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClientMaladie {

    @EmbeddedId
    private ClientMaladieId id;

    @ManyToOne
    @MapsId("clientId")
    @JoinColumn(name="client_id")
    @JsonIgnoreProperties("maladies")
    private User user;

    @ManyToOne
    @MapsId("maladieId")
    @JoinColumn(name="maladie_id")
    @JsonIgnoreProperties("user")
    private Maladie maladie;


}
