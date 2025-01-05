package ma.plantes.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FavorisDTO {

    @JsonProperty("clientId")
    private Long clientId;

    @JsonProperty("emailUser")
    private String clientEmail;

    @JsonProperty("planteId")
    private Long planteId;

    @JsonProperty("planteName")
    private String planteName;

    // Constructor that matches the arguments in the convertToFavorisDTO method
    public FavorisDTO(Long clientId, String clientEmail, Long planteId, String planteName) {
        this.clientId = clientId;
        this.clientEmail = clientEmail;
        this.planteId = planteId;
        this.planteName = planteName;
    }
}
