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

    @JsonProperty("id")
    private Long id;
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

    // Le constructeur personnalisé pour accepter les 3 paramètres
    public PlanteDto(Long id, String nom, String description, String image) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }


    // Constructeur avec tous les champs
    public PlanteDto(Long id, String nom, String description, String image, String precaution, String interaction, String region, List<ProprieteDto> proprietes) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.precaution = precaution;
        this.interaction = interaction;
        this.region = region;
        this.proprietes = proprietes;
    }


    @Override
    public String toString() {
        return "PlanteDto{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", precaution='" + precaution + '\'' +
                ", interaction='" + interaction + '\'' +
                ", image='" + image + '\'' +
                ", region='" + region + '\'' +
                ", proprietes=" + proprietes +
                '}';
    }
}
