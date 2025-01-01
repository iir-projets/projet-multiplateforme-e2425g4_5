package ma.plantes.backend.controllers;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.service.ArticleEnregistreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequiredArgsConstructor
@RequestMapping("/savedarticle")
public class ArticleEnregistreController {

    private final ArticleEnregistreService articleEnregistreService;

    // Afficher les top 5 plantes
    @GetMapping("/top5")
    public Map<Long, Long> getTop5Plantes() {
        return articleEnregistreService.getTop5Articles();
    }
}
