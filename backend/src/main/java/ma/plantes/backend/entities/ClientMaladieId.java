package ma.plantes.backend.entities;

import jakarta.annotation.security.DenyAll;
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
public class ClientMaladieId implements Serializable {

    private Long clientId;
    private Long maladieId;
}
