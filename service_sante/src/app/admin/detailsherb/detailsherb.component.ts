import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { PlantesService, Plante } from '../../../services/plantes/plantes.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-detailsherb',
  imports : [CommonModule],
  templateUrl: './detailsherb.component.html',
  styleUrls: ['./detailsherb.component.css']
})
export class DetailsherbComponent implements OnInit {
  plante!: Plante;

  constructor(
    private plantesService: PlantesService,
    public dialogRef: MatDialogRef<DetailsherbComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { id: number } // Injectez les données passées
  ) {}

  ngOnInit(): void {
    this.loadPlanteDetails();
  }

  // Charger les détails de la plante
  loadPlanteDetails(): void {
    this.plantesService.getPlanteDetails(this.data.id).subscribe({
      next: (plante: Plante) => (this.plante = plante),
      error: (err: any) => console.error('Erreur lors de la récupération des détails de la plante :', err)
    });
  }

  // Méthode pour fermer la boîte de dialogue
  close(): void {
    this.dialogRef.close();
  }
}
