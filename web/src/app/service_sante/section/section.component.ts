import { Component,Input } from '@angular/core';

@Component({
  selector: 'app-section',
  imports: [],
  standalone: true,
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent {
  @Input() title!: string;
}
