import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PlantesService, Plante } from '../../../services/plantes/plantes.service';
import { MatDialogRef } from '@angular/material/dialog';
@Component({
  selector: 'app-detailsherb',
  templateUrl: './detailsherb.component.html',
  styleUrls: ['./detailsherb.component.css']
})
export class DetailsherbComponent implements OnInit {
  plante!: Plante;

  constructor(
    private plantesService: PlantesService,
    private route: ActivatedRoute,
    public dialogRef: MatDialogRef<DetailsherbComponent>
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.plantesService.getPlanteDetails(id).subscribe({
        next: (plante: Plante) => (this.plante = plante),
        error: (err: any) => console.error('Erreur lors de la récupération des détails de la plante :', err)
      });
    }
  }

  // Méthode pour fermer le modal
  close(): void {
    this.dialogRef.close();
  }
}
