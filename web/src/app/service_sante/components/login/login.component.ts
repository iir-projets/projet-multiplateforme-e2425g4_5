import { Component } from '@angular/core';
import { AuthService } from '../../services/login/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username = '';
  password = '';

  constructor(private authService: AuthService) {}

  login() {
    this.authService.login(this.username, this.password).subscribe(
      (response) => {
        localStorage.setItem('token', response.token);
        // Redirection vers le dashboard
      },
      (error) => {
        console.error('Erreur de connexion', error);
      }
    );
  }
}
