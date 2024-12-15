import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-addmodal',
  imports: [],
  templateUrl: './addmodal.component.html',
  styleUrl: './addmodal.component.css'
})
export class AddmodalComponent {
  constructor(public dialogRef: MatDialogRef<AddmodalComponent>) {}

  // Méthode pour fermer le modal
  close(): void {
    this.dialogRef.close();
  }
}
