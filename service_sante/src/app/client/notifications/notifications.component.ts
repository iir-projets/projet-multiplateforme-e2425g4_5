import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from "../navbar/navbar.component";
import { FooterComponent } from "../footer/footer.component";

@Component({
  selector: 'app-notifications',
  standalone: true,
  imports: [CommonModule, NavbarComponent, FooterComponent],
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css'],
})
export class NotificationsComponent {
  notifications = [
    { title: 'New article !!', description: 'This is an article, this is an article ...', type: 'new', isRead: false },
    { title: 'Recommendation list', description: 'Your recommendation list is available !!', type: 'recommendation', isRead: true },
    { title: 'New article !!', description: 'This is an article, this is an article ...', type: 'new', isRead: false },
    // Ajoutez plus de notifications ici
  ];

  markAsRead(notification: any) {
    notification.isRead = true;
  }
}
