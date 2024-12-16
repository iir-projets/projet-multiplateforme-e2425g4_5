import { Component } from '@angular/core';
import { CaractmodalComponent } from '../caractmodal/caractmodal.component';
import { MatDialog } from '@angular/material/dialog'; 

@Component({
  selector: 'app-cardherb',
  imports: [],
  templateUrl: './cardherb.component.html',
  styleUrl: './cardherb.component.css'
})
export class CardherbComponent {
constructor(public dialog: MatDialog) {}  // Inject MatDialog service

  // Méthode pour ouvrir le modal
  openModal(): void {
    const dialogRef = this.dialog.open(CaractmodalComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
