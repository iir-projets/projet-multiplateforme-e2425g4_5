import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { FormsModule , ReactiveFormsModule} from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { PlantesService, Propriete } from '../../../services/plantes/plantes.service';


@Component({
  selector: 'app-addmodal',
  standalone: true,
  imports: [
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './addmodal.component.html',
  styleUrl: './addmodal.component.css'
})
export class AddmodalComponent {
   // Propriétés pour les champs du formulaire
   plante = {
    nom: '',
    image: '',
    description: '',
    region: '',
    proprietes: '', // Tableau vide par défaut
    interaction: '',
    precaution: '',
    isFavorite: false // Boolean au lieu de string
  };
  

  constructor(public dialogRef: MatDialogRef<AddmodalComponent>,
    private planteService : PlantesService
  ) {}

  close(): void {
    this.dialogRef.close();
  }

  submit(): void {
    console.log('Données soumises :', this.plante); // Debug
    const plante = { ...this.plante, proprietes: [] }; // Remplacez `propriete` par un tableau.
    this.planteService.addHerb(plante).subscribe({
      next: (response) => {
        console.log('Plante créée avec succès :', response);
        this.dialogRef.close(response);
      },
      error: (err) => {
        console.error('Erreur lors de la création de la plante :', err);
      }
    });
  }
  
}
