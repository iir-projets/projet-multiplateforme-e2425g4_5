package ma.plantes.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClientMedicamentId implements Serializable {

    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "medicament_id")
    private Long medicamentId;

}
