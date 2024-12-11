import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-liste',
  imports : [CommonModule],
  templateUrl: './liste.component.html',
  styleUrls: ['./liste.component.css']
})
export class ListeComponent {
  items = [
    { nom: 'Allergie 1' },
    { nom: 'Maladie 2' },
    { nom: 'Médicament 3' }
  ];

  // Méthode pour supprimer un élément de la liste
  deleteItem(index: number): void {
    this.items.splice(index, 1);  // Supprime l'élément à l'index donné
  }
}
