import { Component } from '@angular/core';
import { CardherbComponent } from '../cardherb/cardherb.component';
import { MatDialog } from '@angular/material/dialog';  // Import MatDialog
import { AddmodalComponent } from '../addmodal/addmodal.component';  // Import your modal component


@Component({
  selector: 'app-herbs',
  imports: [CardherbComponent],
  templateUrl: './herbs.component.html',
  styleUrls: ['./herbs.component.css']
})
export class HerbsComponent {
  constructor(private dialog: MatDialog) {}

  openModal(): void {
    const dialogRef = this.dialog.open(AddmodalComponent);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log('Herb added successfully :', result);
      }
    });
  }
}
