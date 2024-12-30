import { Component,OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from "../navbar/navbar.component";
import { FooterComponent } from "../footer/footer.component";
import { ActivatedRoute } from '@angular/router';
import { Plante, PlantesService, Propriete } from '../../../services/plantes/plantes.service';

@Component({
  selector: 'app-plant-details',
  standalone: true,
  imports: [CommonModule, NavbarComponent, FooterComponent],
  templateUrl: './details-plantes.component.html',
  styleUrls: ['./details-plantes.component.css']
})
export class DetailsPlantesComponent implements OnInit {

   plant: Plante | null = null;
   property: Propriete | null = null;

  constructor(private route: ActivatedRoute, private plantesService: PlantesService) {}

  ngOnInit(): void {
    const plantId = this.route.snapshot.paramMap.get('id');
    if (plantId) {
      this.plantesService.getPlanteDetails(parseInt(plantId)).subscribe(data => {
        this.plant = data;
      });
    } else {
      // Handle the case where plantId is null
      console.error('Plant ID is null');
    }
  }

  isFavorite: boolean = false;
  toggleFavorite() {
    this.isFavorite = !this.isFavorite;
    if (this.isFavorite) {
      console.log(`${this.plant?.nom} a été ajouté aux favoris.`);
    } else {
      console.log(`${this.plant?.nom} a été retiré des favoris.`);
    }
  }

  
  
}