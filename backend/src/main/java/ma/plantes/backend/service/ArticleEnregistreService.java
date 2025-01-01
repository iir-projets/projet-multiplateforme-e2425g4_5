package ma.plantes.backend.service;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.repositories.ArticleEnregistreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleEnregistreService {

    private final ArticleEnregistreRepository articleEnregistreRepository;

    public Map<Long, Long> getTop5Articles() {
        List<Object[]> results = articleEnregistreRepository.findTop5ArticlesSaved();
        Map<Long, Long> topPlantes = new LinkedHashMap<>();

        for (Object[] result : results) {
            Long articleId = ((Number) result[0]).longValue();
            Long count = ((Number) result[1]).longValue();
            topPlantes.put(articleId, count);
        }

        return topPlantes;
    }
}
