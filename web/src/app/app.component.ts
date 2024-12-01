import { Component } from '@angular/core';
import { NavbarComponent } from './client/navbar/navbar.component'; // Adjust path
import { FooterComponent } from './client/footer/footer.component'; // Adjust path
import { ProfileComponent } from './client/profil/profil.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [NavbarComponent, FooterComponent,ProfileComponent], // Import all components
  template: `
    <div>
      <app-navbar></app-navbar>
   <app-profile></app-profile>
      <app-footer></app-footer>
    </div>
  `
})
export class AppComponent {
title = 'web';}
