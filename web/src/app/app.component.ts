import { Component } from '@angular/core';
<<<<<<< HEAD
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
=======
//import { RouterOutlet } from '@angular/router';
import { DashboardComponent } from './service_sante/components/dashboard/dashboard.component';

@Component({
  selector: 'app-root',
  imports: [DashboardComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
>>>>>>> 972dbb3db886e259a54d38b408da4c1e86119419
})
export class AppComponent {
title = 'web';}
