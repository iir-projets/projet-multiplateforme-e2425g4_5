import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from "../navbar/navbar.component";
import { FooterComponent } from "../footer/footer.component";

@Component({
  selector: 'app-plant-details',
  standalone: true,
  imports: [CommonModule, NavbarComponent, FooterComponent],
  templateUrl: './details-plantes.component.html',
  styleUrls: ['./details-plantes.component.css']
})
export class DetailsPlantesComponent {
  plant = {
    name: "Chamomile",
    region: "Europe and Western Asia",
    properties: [
      "Anti-inflammatory",
      "Calming",
      "Aids sleep"
    ],
    precautions: "Avoid if allergic to daisies. Not recommended during pregnancy.",
    drugInteractions: "May interact with blood thinners and sedatives.",
    description: "Chamomile is a daisy-like plant known for its calming and soothing properties. It's commonly used in teas and has a pleasant, apple-like fragrance."
  };

  isFavorite: boolean = false;
  toggleFavorite() {
    this.isFavorite = !this.isFavorite;
    if (this.isFavorite) {
      console.log(`${this.plant.name} a été ajouté aux favoris.`);
    } else {
      console.log(`${this.plant.name} a été retiré des favoris.`);
    }
  }

  
  
}
