import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';


@Component({
  selector: 'app-detailsherb',
  imports: [],
  templateUrl: './detailsherb.component.html',
  styleUrl: './detailsherb.component.css'
})
export class DetailsherbComponent {
constructor(public dialogRef: MatDialogRef<DetailsherbComponent>) {}

  // Méthode pour fermer le modal
  close(): void {
    this.dialogRef.close();
  }
}
