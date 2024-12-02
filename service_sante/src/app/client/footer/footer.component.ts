import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [CommonModule], 
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {
  currentYear: number = new Date().getFullYear();
  siteName: string = 'Catalogue de Plantes Médicinales';
  links = [
    { name: 'Accueil', url: '/' },
    { name: 'À propos', url: '/about' },
    { name: 'Contact', url: '/contact' },
    { name: 'Mentions légales', url: '/legal' }
  ];
}
