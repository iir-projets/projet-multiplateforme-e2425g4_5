import { Component,OnInit } from '@angular/core';
import { DetailsherbComponent } from '../detailsherb/detailsherb.component';
import { MatDialog } from '@angular/material/dialog'; 
import { Plante, PlantesService } from '../../../services/plantes/plantes.service';
import { CommonModule } from '@angular/common';



@Component({
  selector: 'app-cardherb',
  standalone: true, 
  imports: [CommonModule],
  templateUrl: './cardherb.component.html',
  styleUrl: './cardherb.component.css'
})
export class CardherbComponent {
  herbs: Plante[] = [];
  isLoading = true;

  constructor(private planteService: PlantesService,private dialog: MatDialog) {}

  ngOnInit(): void {
    this.loadPlantes();
  }

  // Chargement des ambulances
  loadPlantes(): void {
    this.planteService.getAll().subscribe(
      data => {
        console.log('Ambulances reçues:', data); // Vérifier la structure des données
        this.herbs = data;
        this.isLoading = false;
      },
      error => {
        console.error('Erreur lors du chargement des plantes:', error);
        this.isLoading = false;
      }
    );
  }

  // Suppression d'une ambulance
  deletePlantes(id: number): void {
    this.planteService.deleteHerb(id).subscribe({
      next: () => {
        console.log('Plante supprimée avec succès');
        this.loadPlantes(); // Rechargez la liste
      },
      error: (err) => {
        console.error('Erreur lors de la suppression :', err);
      },
    });
  }
  
  details(id : number): void {
    this.dialog.open(DetailsherbComponent, {
      data: { id },
      width: '500px',
    });
  }
}
