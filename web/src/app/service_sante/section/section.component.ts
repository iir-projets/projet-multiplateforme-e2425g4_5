import { Component, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-section',
  standalone: true,
  //imports: [FormsModule], // Permet d'utiliser ngModel
  templateUrl: './section.component.html',  
  styleUrls: ['./section.component.css']
})
export class SectionComponent {
  @Input() title!: string;
  newElement = '';
}
