package ma.plantes.backend.controllers;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.service.FavorisService;
import ma.plantes.backend.service.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stats")
public class StatsController {


    private final StatsService statsService;


    @GetMapping("/categories")
    public ResponseEntity<List<Map<String, Object>>> getCountByCategories() {
        return ResponseEntity.ok(statsService.getCountByCategories());
    }
}
