package ma.plantes.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public ResponseEntity<String> demo(){
        return ResponseEntity.ok("Hello from CLIENT secured url");
    }

    @GetMapping("/admin/demo")
    public ResponseEntity<String> admiDemo(){
        return ResponseEntity.ok("Hello from ADMIN secured url");
    }
}
