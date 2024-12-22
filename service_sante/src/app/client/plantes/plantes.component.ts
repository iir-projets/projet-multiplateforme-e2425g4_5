import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PlantesService } from '../../../services/pages/plantes.service';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-plantes',
  standalone: true,
  imports: [CommonModule, FormsModule, NavbarComponent, FooterComponent, RouterModule],
  templateUrl: './plantes.component.html',
  styleUrls: ['./plantes.component.css']
})
export class PlantesComponent implements OnInit {
  allPlantes: any[] = [];
  filteredPlantes: any[] = [];
  searchTerm: string = '';

  constructor(private plantesService: PlantesService) {}

  ngOnInit(): void {
    this.plantesService.getPlantes().subscribe(data => {
      this.allPlantes = data;
      this.filteredPlantes = data;
    });
  }

  toggleFavorite(plant: { nom: string; image: string; isFavorite: boolean }): void {
    plant.isFavorite = !plant.isFavorite;
  }

  viewDetails(plant: { nom: string; image: string }): void {
    console.log('View details for:', plant.nom);
  }

  searchPlants(): void {
    this.filteredPlantes = this.allPlantes.filter(plant =>
      plant.nom.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
}

