package ma.plantes.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class PlanteDto {

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("description")
    private String description;

    @JsonProperty("precaution")
    private String precaution;

    @JsonProperty("interaction")
    private String interaction;

    @JsonProperty("image")
    private String image;

    @JsonProperty("region")
    private String region;

    @JsonProperty("proprietes")
    private List<ProprieteDto> proprietes;

    @Override
    public String toString() {
        return "PlanteDto{" +
                "nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", precaution='" + precaution + '\'' +
                ", interaction='" + interaction + '\'' +
                ", image='" + image + '\'' +
                ", region='" + region + '\'' +
                ", proprietes=" + proprietes +
                '}';
    }
}
