import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-plantes',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, RouterModule, CommonModule],
  templateUrl: './plantes.component.html',
  styleUrls: ['./plantes.component.css']
})
export class PlantesComponent implements OnInit {
  // Exemple de données avec une propriété isFavorite pour chaque plante
  plantes = [
    { nom: 'Aloe Vera', image: 'assets/images/plante1.jpeg', isFavorite: false },
    { nom: 'Lavender', image: 'assets/images/plante2.jpg', isFavorite: false },
    { nom: 'Mint', image: 'assets/images/plante3.jpg', isFavorite: false },
    { nom: 'Rosemary', image: 'assets/images/plante4.jpg', isFavorite: false },
    { nom: 'Basil', image: 'assets/images/plante5.jpg', isFavorite: false },
    { nom: 'Cactus', image: 'assets/images/plante6.jpg', isFavorite: false },
    { nom: 'Orchid', image: 'assets/images/plante7.jpg', isFavorite: false },
    { nom: 'Snake Plant', image: 'assets/images/plante8.jpg', isFavorite: false }
  ];

  constructor() {}

  ngOnInit(): void {}

  // Fonction pour basculer l'état de favori d'une plante
  toggleFavorite(plant: { nom: string; image: string; isFavorite: boolean }): void {
    plant.isFavorite = !plant.isFavorite;  // Bascule l'état du favori
  }

  // Fonction pour afficher les détails de la plante (optionnelle)
  viewDetails(plant: { nom: string; image: string }): void {
    console.log('View details for:', plant.nom);
    // Vous pouvez utiliser le routeur Angular pour naviguer vers une page de détails
    // Exemple : this.router.navigate(['/plant-details', plant.nom]);
  }
}
