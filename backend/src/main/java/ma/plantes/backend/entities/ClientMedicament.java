package ma.plantes.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ClientMedicament {

    @EmbeddedId
    private ClientMedicamentId id;

    @ManyToOne
    @MapsId("clientId")
    @JoinColumn(name="client_id")
    @JsonIgnoreProperties("medicaments")
    private User user;

    @ManyToOne
    @MapsId("medicamentId")
    @JoinColumn(name="medicament_id")
    @JsonIgnoreProperties("user")
    private Medicament medicament;



}
