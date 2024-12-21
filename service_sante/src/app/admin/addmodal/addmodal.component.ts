import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-addmodal',
  standalone: true,
  imports: [
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
    CommonModule
  ],
  templateUrl: './addmodal.component.html',
  styleUrl: './addmodal.component.css'
})
export class AddmodalComponent {
   // Propriétés pour les champs du formulaire
   herb = {
    nom: '',
    description: '',
    precaution: '',
    interaction: '',
    region: '',
    propriete: '',
    image: ''
  };

  constructor(public dialogRef: MatDialogRef<AddmodalComponent>) {}

  close(): void {
    this.dialogRef.close();
  }

  submit(): void {
    console.log('Données de la nouvelle herbe :', this.herb);
    this.dialogRef.close(this.herb);
  }
}
