import { Component,OnInit } from '@angular/core';
import { DetailsherbComponent } from '../detailsherb/detailsherb.component';
import { MatDialog } from '@angular/material/dialog'; 
import { Plante, PlantesService } from '../../../services/plantes/plantes.service';
import { CommonModule } from '@angular/common';
import { EditHerbComponent } from '../edit-herb/edit-herb.component';
import { FormsModule } from '@angular/forms';



@Component({
  selector: 'app-cardherb',
  standalone: true, 
  imports: [CommonModule,FormsModule],
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
        console.log('Plantes reçues:', data); // Vérifier la structure des données
        this.herbs = data;
        this.isLoading = false;
      },
      error => {
        console.error('Erreur lors du chargement des plantes:', error);
        this.isLoading = false;
      }
    );
  }

  // Modification d'une plante

  editPlante(plante: Plante): void {
    const dialogRef = this.dialog.open(EditHerbComponent, {
      data: { plante },
      width: '600px',
    });
  
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        console.log('Plante mise à jour:', result);
        this.loadPlantes(); // Rechargez la liste des plantes après mise à jour
      }
    });
  }

  // Suppression d'une plante
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
