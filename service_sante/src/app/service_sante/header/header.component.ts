import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service';

@Component({
  selector: 'app-header',
  imports: [],
  standalone: true,
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
  
})
export class HeaderComponent {
  constructor(private authService: AuthService) {}

  logout(): void {
    this.authService.logout();
  }

}
