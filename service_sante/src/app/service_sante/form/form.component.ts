import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent {
  @Output() addEvent = new EventEmitter<string>();

  add(value: string): void {
    alert(`Form submitted with value:${value}`);
    this.addEvent.emit(value);
  }
}
