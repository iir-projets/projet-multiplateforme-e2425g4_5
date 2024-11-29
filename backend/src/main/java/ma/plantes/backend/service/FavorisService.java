package ma.plantes.backend.service;

import ma.plantes.backend.repositories.FavorisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavorisService {

    @Autowired
    private FavorisRepository favorisRepository;


}
