package ma.plantes.backend.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.Notification;
import ma.plantes.backend.entities.User;
import ma.plantes.backend.repositories.NotificationRepository;
import ma.plantes.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;


    public List<Notification> findByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }


    public Notification addNotificationToUser(Long userId, Notification notification) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID : " + userId));

        notification.setUser(user);
        return notificationRepository.save(notification);
    }



}
