package ma.plantes.backend.controllers;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.Notification;
import ma.plantes.backend.service.NotificationService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getNotificationsByUserId(@Param("id") Long id){
        return ResponseEntity.ok(notificationService.getNotificationByUserId(id));
    }

    @PostMapping("/notifications/{id}")
    public ResponseEntity<Notification> readNotification(@PathVariable Long id){
        return ResponseEntity.ok(notificationService.readNotification(id));
    }
}
