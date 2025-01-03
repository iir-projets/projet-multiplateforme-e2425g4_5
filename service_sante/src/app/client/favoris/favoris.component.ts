import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { trigger, style, animate, transition } from '@angular/animations';
import { NavbarComponent } from "../navbar/navbar.component";
import { FooterComponent } from "../footer/footer.component";

interface Plant {
  id: number;
  name: string;
  description: string;
  image: string;
  isFavorite: boolean;
}

@Component({
  selector: 'app-favoris',
  standalone: true,
  imports: [CommonModule, NavbarComponent,FooterComponent],
  templateUrl: './favoris.component.html',
  styleUrls: ['./favoris.component.css'],
  animations: [
    trigger('cardAnimation', [
      transition(':enter', [
        style({ opacity: 0, transform: 'scale(0.9)' }),
        animate('300ms ease-out', style({ opacity: 1, transform: 'scale(1)' })),
      ]),
      transition(':leave', [
        animate('300ms ease-in', style({ opacity: 0, transform: 'scale(0.8)' })),
      ]),
    ]),
  ],
})
export class FavorisComponent {
  favoritePlants: Plant[] = [
    {
      id: 1,
      name: "Aloe Vera",
      description: "Plante tropicale aux grandes feuilles perforées.",
      image: "assets/images/plante8.jpg",
      isFavorite: true,
    },
  
   
  ];

  toggleFavorite(plant: Plant) {
    plant.isFavorite = !plant.isFavorite;

    if (!plant.isFavorite) {
      this.removeFromFavorites(plant);
    }
  }

  removeFromFavorites(plant: Plant) {
    const index = this.favoritePlants.findIndex(p => p.id === plant.id);
    if (index !== -1) {
      this.favoritePlants.splice(index, 1);
    }
  }

  trackById(index: number, plant: Plant): number {
    return plant.id; // Utiliser l'ID comme identifiant unique
  }
}
