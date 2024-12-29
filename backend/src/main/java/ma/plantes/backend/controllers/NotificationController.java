package ma.plantes.backend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.Notification;
import ma.plantes.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")

public class NotificationController {
    private final NotificationService notificationService;



    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getNotificationsByUser(@PathVariable Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("L'ID de l'utilisateur ne peut pas être null.");
        }
        List<Notification> notifications = notificationService.findByUserId(userId);
        return ResponseEntity.ok(notifications);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Notification> addNotificationToUser(
            @PathVariable Long userId,
            @RequestBody Notification notification) {
        Notification newNotification = notificationService.addNotificationToUser(userId, notification);
        return ResponseEntity.ok(newNotification);
    }



}
