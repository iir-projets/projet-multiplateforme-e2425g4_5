package ma.plantes.backend.service;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.Notification;
import ma.plantes.backend.entities.User;
import ma.plantes.backend.repositories.NotificationRepository;
import ma.plantes.backend.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public List<Notification> getNotificationByUserId(Long id){
        User user = userRepository.findById(id).orElseThrow();

        return notificationRepository.findNotificationsByUser(user);
    }

    public Notification readNotification(Long id){
        Notification notification = notificationRepository.findById(id).orElseThrow();
        notification.setVu(true);
        return notificationRepository.save(notification);
    }
}
