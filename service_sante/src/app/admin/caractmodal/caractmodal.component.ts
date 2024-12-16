import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';


@Component({
  selector: 'app-caractmodal',
  imports: [],
  templateUrl: './caractmodal.component.html',
  styleUrl: './caractmodal.component.css'
})
export class CaractmodalComponent {
constructor(public dialogRef: MatDialogRef<CaractmodalComponent>) {}

  // Méthode pour fermer le modal
  close(): void {
    this.dialogRef.close();
  }
}
