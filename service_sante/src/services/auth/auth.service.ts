import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private TOKEN_KEY = 'authToken';
  private ROLE_KEY = 'userRole';

  constructor(private router: Router) {}

  // Save Token and Role to LocalStorage
  saveToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
    const role = this.extractRoleFromToken(token);
    if (role) {
      localStorage.setItem(this.ROLE_KEY, role);
    }
  }

  // Decode Token Manually
  private decodeToken(token: string): any {
    try {
      const base64Url = token.split('.')[1];
      const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
      return JSON.parse(window.atob(base64));
    } catch (error) {
      console.error('Error decoding token:', error);
      return null;
    }
  }

  // Extract Role
  private extractRoleFromToken(token: string): string | null {
    const decoded = this.decodeToken(token);
    if (decoded && decoded.authorities && decoded.authorities.length > 0) {
      return decoded.authorities[0].authority;
    }
    return null;
  }

  // Get Role
  getRole(): string | null {
    return localStorage.getItem(this.ROLE_KEY);
  }

  // Check if User is Logged In
  isLoggedIn(): boolean {
    return !!localStorage.getItem(this.TOKEN_KEY);
  }

  // Logout User
  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.ROLE_KEY);
    this.router.navigate(['/login']);
  }


  getUserIdFromToken(): string | null {
    const token = localStorage.getItem(this.TOKEN_KEY);
    if (token) {
      const decoded = this.decodeToken(token);
      return decoded?.id || null;  // Assurez-vous que l'ID est dans le token
    }
    return null;
  }
  


}
