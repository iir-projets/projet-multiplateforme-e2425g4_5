import { Component,Input } from '@angular/core';
import { FormulaireComponent } from '../formulaire/formulaire.component';
import { ListeComponent } from '../liste/liste.component';

@Component({
  selector: 'app-section',
  imports: [FormulaireComponent,ListeComponent],
  standalone: true,
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent {
  @Input() title!: string;
}
