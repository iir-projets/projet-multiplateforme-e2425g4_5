// src/app/client/plantes/plantes.component.ts
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { PlantesService } from '../../../services/plantes/plantes.service';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

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

  constructor(private plantesService: PlantesService, private router: Router) {}

  ngOnInit(): void {
    this.plantesService.getPlantes().subscribe(data => {
      this.allPlantes = data;
      this.filteredPlantes = data;
    });
  }

  toggleFavorite(plant: { nom: string; image: string; isFavorite: boolean }): void {
    plant.isFavorite = !plant.isFavorite;
  }

  viewDetails(plant: { id: string }): void {
    this.router.navigate(['/details_plantes', plant.id]);
  }

  searchPlants(): void {
    this.filteredPlantes = this.allPlantes.filter(plant =>
      plant.nom.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
}