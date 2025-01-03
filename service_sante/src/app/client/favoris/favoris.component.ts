import { Component, OnInit } from '@angular/core';
import { FavorisService } from '../../../services/favoris/favoris.service';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

interface Plant {
  id: number;
  name: string;
  description: string;
  image: string;
  isFavorite: boolean;
}

@Component({
  selector: 'app-favoris',
  templateUrl: './favoris.component.html',
  styleUrls: ['./favoris.component.css'],
  imports: [NavbarComponent,FooterComponent,CommonModule,FormsModule],
})
export class FavorisComponent implements OnInit {
  favoritePlants: Plant[] = [];
  clientId = 2; // Remplacez par l'ID du client connecté

  constructor(private favorisService: FavorisService) {}

  ngOnInit(): void {
    this.loadFavorites();
  }

  loadFavorites(): void {
    this.favorisService.getFavorisByClientId(this.clientId).subscribe({
      next: (plants) => {
        this.favoritePlants = plants.map((plant) => ({
          ...plant,
          isFavorite: true,
        }));
      },
      error: (err) => {
        console.error('Erreur lors du chargement des favoris :', err);
      },
    });
  }

  toggleFavorite(plant: Plant): void {
    plant.isFavorite = !plant.isFavorite;

    if (!plant.isFavorite) {
      this.removeFromFavorites(plant);
    }
  }

  removeFromFavorites(plant: Plant): void {
    this.favorisService
      .removeFromFavorites(this.clientId, plant.id)
      .subscribe(() => {
        this.favoritePlants = this.favoritePlants.filter(
          (p) => p.id !== plant.id
        );
      });
  }


  trackById(index: number, plant: Plant): number {
    return plant.id; // Utilisez l'ID de la plante comme clé unique
  }
  
}
