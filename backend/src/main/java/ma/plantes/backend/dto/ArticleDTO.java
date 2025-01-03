package ma.plantes.backend.dto;



public class ArticleDTO {

    private Long id;
    private String titre;
    private String contenu;
    private String image;

    // Constructeur par défaut
    public ArticleDTO() {
    }

    // Constructeur avec paramètres
    public ArticleDTO(Long id, String titre, String contenu, String image) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.image = image;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // Méthode toString (optionnelle, utile pour débogage)
    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", contenu='" + contenu + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}


