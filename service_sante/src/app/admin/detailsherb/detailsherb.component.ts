import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { PlantesService } from '../../../services/plantes/plantes.service';


@Component({
  selector: 'app-detailsherb',
  imports: [],
  templateUrl: './detailsherb.component.html',
  styleUrl: './detailsherb.component.css'
})
export class DetailsherbComponent {
constructor(public dialogRef: MatDialogRef<DetailsherbComponent>, herbsService : PlantesService) {}

  // Méthode pour fermer le modal
  close(): void {
    this.dialogRef.close();
  }
}
