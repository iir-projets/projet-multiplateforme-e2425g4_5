import { Component } from '@angular/core';
import { SectionComponent } from '../section/section.component';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [HeaderComponent, SectionComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  allergies = [{ nom: 'Pollen' }, { nom: 'Gluten' }];
  maladies = [{ nom: 'Diabète' }, { nom: 'Hypertension' }];
  medicaments = [{ nom: 'Aspirine' }, { nom: 'Paracétamol' }];
}
