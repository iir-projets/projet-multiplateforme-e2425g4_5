import { Component } from '@angular/core';
import { CardsComponent } from '../cards/cards.component';
import { ChartsComponent } from '../charts/charts.component';

@Component({
  selector: 'app-stats',
  imports: [CardsComponent,ChartsComponent],
  templateUrl: './stats.component.html',
  styleUrl: './stats.component.css'
})
export class StatsComponent {

}
