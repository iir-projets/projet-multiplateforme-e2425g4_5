import { Component ,OnInit} from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { FooterComponent } from "../footer/footer.component";
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-plantes',
  imports: [NavbarComponent, FooterComponent,RouterModule,CommonModule],
  templateUrl: './plantes.component.html',
  styleUrl: './plantes.component.css'
})
export class PlantesComponent implements OnInit {
  plantes = [
    { nom: 'Aloe Vera', image: 'assets/images/plante1.jpeg' },
    { nom: 'Lavender', image: 'assets/images/lavender.jpg' },
    { nom: 'Mint', image: 'assets/images/mint.jpg' },
    { nom: 'Rosemary', image: 'assets/images/rosemary.jpg' }
  ];

  constructor() {}

  ngOnInit(): void {}
}