package ma.plantes.backend.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientAllergieId implements Serializable {

    private Long clientId;
    private Long allergyId;
}
