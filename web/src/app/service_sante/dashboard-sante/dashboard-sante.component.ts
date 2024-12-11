import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SectionComponent } from '../section/section.component'; // Mettez le chemin correct

@Component({
  selector: 'app-dashboard-sante',
  standalone: true,
  imports: [CommonModule, SectionComponent], // Ajoutez SectionComponent ici
  templateUrl: './dashboard-sante.component.html',
  styleUrls: ['./dashboard-sante.component.css']
})
export class DashboardSanteComponent {}
