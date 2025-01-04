package ma.plantes.backend.dto;

public class ArticleDTO {

    private Long id; // ID de l'article enregistré (optionnel)
    private Long clientId; // ID du client
    private Long articleId; // ID de l'article
    private String titre;
    private String contenu;
    private String image;

    // Constructeur par défaut
    public ArticleDTO() {
    }

    // Constructeur avec paramètres
    public ArticleDTO(Long id, Long clientId, Long articleId, String titre, String contenu, String image) {
        this.id = id;
        this.clientId = clientId;
        this.articleId = articleId;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
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
                ", clientId=" + clientId +
                ", articleId=" + articleId +
                ", titre='" + titre + '\'' +
                ", contenu='" + contenu + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
