import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { PlantesService, Plante } from '../../../services/plantes/plantes.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-herb',
  imports: [
      MatDialogModule,
      MatFormFieldModule,
      MatInputModule,
      MatButtonModule,
      FormsModule,
      ReactiveFormsModule,
      CommonModule
    ],
  templateUrl: './edit-herb.component.html',
  styleUrls: ['./edit-herb.component.css'],
})
export class EditHerbComponent {
  plante: Plante;

  constructor(
    private dialogRef: MatDialogRef<EditHerbComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { plante: Plante },
    private planteService: PlantesService
  ) {
    this.plante = { ...data.plante }; // Clone de l'objet pour éviter les modifications directes
  }

  submit(): void {
    this.planteService.updateHerb(this.plante).subscribe({
      next: (updatedPlante) => {
        console.log('Plante mise à jour avec succès:', updatedPlante);
        this.dialogRef.close(updatedPlante); // Ferme le popup et renvoie les données mises à jour
      },
      error: (err) => {
        console.error('Erreur lors de la mise à jour:', err);
      },
    });
  }

  close(): void {
    this.dialogRef.close();
  }
}
