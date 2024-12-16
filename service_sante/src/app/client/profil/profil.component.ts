import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from "../navbar/navbar.component";
import { HeaderComponent } from "../../service_sante/header/header.component";


@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, RouterModule, NavbarComponent],
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css'],
})
export class ProfileComponent {
  avatarUrl: string = 'assets/images/default-avatar.png';
  profileDetails = [
    { label: 'email', icon: '>', url: '/profile/email' },
    { label: 'mobile', icon: '>', url: '/profile/mobile' },
    { label: 'address', icon: '>', url: '/profile/address' },
    { label: 'change password', icon: '🔒', url: '/profile/password' },
  ];
  medicalDetails = [
    { label: 'allergy', icon: '>', url: '/profile/allergy' },
    { label: 'medicine', icon: '>', url: '/profile/medicine' },
    { label: 'disease', icon: '>', url: '/profile/disease' },
  ];

  logOut() {
    console.log('Logged out');
  }
}
