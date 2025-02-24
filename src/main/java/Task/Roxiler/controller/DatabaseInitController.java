package Task.Roxiler.controller;


import Task.Roxiler.service.DatabaseInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/initialize")
public class DatabaseInitController {

    @Autowired
    private DatabaseInitService initService;

    @PostMapping
    public ResponseEntity<String> initializeDB() {
        initService.initializeDatabase();
        return ResponseEntity.ok("Database initialized successfully");
    }
}