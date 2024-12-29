import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from "../navbar/navbar.component";
import { FooterComponent } from "../footer/footer.component";
import { NotificationService, Notification } from '../../../services/notifications/notification.service';

@Component({
  selector: 'app-notifications',
  standalone: true,
  imports: [CommonModule, NavbarComponent, FooterComponent],
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css'],
})
export class NotificationsComponent implements OnInit {
  notifications: Notification[] = [];
  userId: number | null = null;

  constructor(private notificationService: NotificationService) {}

  ngOnInit() {
    // Fixez l'ID utilisateur à 2
    this.userId = 2; 
    this.loadNotifications();
  }

  loadNotifications() {
    if (this.userId) {
      this.notificationService.getNotifications(this.userId).subscribe({
        next: (notifications) => {
          this.notifications = notifications;
        },
        error: (error) => {
          console.error('Error fetching notifications:', error);
        }
      });
    }
  }

  markAsRead(notification: Notification) {
    // Mise à jour immédiate dans l'UI
    const index = this.notifications.findIndex(n => n.id === notification.id);
    if (index !== -1) {
      this.notifications[index].vu = true;
    }

    // Appel à l'API pour synchroniser avec le backend
    this.notificationService.markAsRead(notification.id).subscribe({
      next: (updatedNotification) => {
        const index = this.notifications.findIndex(n => n.id === updatedNotification.id);
        if (index !== -1) {
          this.notifications[index] = updatedNotification;  // Mettre à jour l'état du backend si nécessaire
        }
      },
      error: (error) => {
        console.error('Error marking notification as read:', error);
      }
    });
  }
}
