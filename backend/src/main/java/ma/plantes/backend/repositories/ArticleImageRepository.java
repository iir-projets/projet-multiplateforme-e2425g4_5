package ma.plantes.backend.repositories;

import ma.plantes.backend.entities.ArticleImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleImageRepository extends JpaRepository<ArticleImage,Long> {
}
