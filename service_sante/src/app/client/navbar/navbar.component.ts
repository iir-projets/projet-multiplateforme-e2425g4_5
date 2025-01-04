import { Component } from '@angular/core';
import { ChatbotComponent } from "../chatbot/chatbot.component";
import { AuthService } from '../../../services/auth/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  imports: [ChatbotComponent],
})
export class NavbarComponent {
  constructor(private authService: AuthService) {}

  logout(): void {
    // Reset Botpress session
    
    window.botpress.sendEvent({ type: 'createConversation' });
    
    this.authService.logout();
  }
}
