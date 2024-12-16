import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from "../footer/footer.component";
import { NavbarComponent } from "../navbar/navbar.component";  
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-accueil',
  standalone: true,
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css'],
  imports: [CommonModule, RouterModule,NavbarComponent,FooterComponent] 

})
export class AccueilComponent {
  plantes = [
    { nom: 'Aloe Vera', image: 'assets/images/plante1.jpeg' },
    { nom: 'Lavender', image: 'assets/images/plante2.jpg' },
    { nom: 'Chamomile', image: 'assets/images/plante3.jpg' },
    { nom: 'Mint', image: 'assets/images/plante4.jpg' },
    { nom: 'Rosemary', image: 'assets/images/plante5.jpg' },
    { nom: 'Thyme', image: 'assets/images/plante6.jpg' },
    { nom: 'Sage', image: 'assets/images/plante7.jpg' },
    { nom: 'Echinacea', image: 'assets/images/plante8.jpg' },
];

  viewDetails(plante: any) {
    console.log('Plante sélectionnée:', plante);
    // Vous pouvez rediriger l'utilisateur vers une page de détails ou afficher un modal
  }
}
