import { Component,OnInit } from '@angular/core';
import { DetailsherbComponent } from '../detailsherb/detailsherb.component';
import { MatDialog } from '@angular/material/dialog'; 
import { Router, RouterModule } from '@angular/router';
import { Plante, PlantesService } from '../../../services/plantes/plantes.service';
import { CommonModule } from '@angular/common';



@Component({
  selector: 'app-cardherb',
  standalone: true, 
  imports: [CommonModule],
  templateUrl: './cardherb.component.html',
  styleUrl: './cardherb.component.css'
})
export class CardherbComponent implements OnInit {
  allHerbs: Plante[] = [];
  isLoading = true;

  constructor(
    private dialog: MatDialog,
    private plantesService: PlantesService,
    private router: Router
  ) {}

  details(): void {
    const dialogRef = this.dialog.open(DetailsherbComponent);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log('Details herb:', result);
      }
    });
  }

  ngOnInit(): void {
    this.plantesService.getAll().subscribe(
      data => {
        console.log('Plantes reçues:', data); // Vérifier la structure des données
        this.allHerbs = data;
        this.isLoading = false;
      },
      error => {
        console.error('Erreur lors du chargement des plantes:', error);
        this.isLoading = false;
      }
    );
  }
}
