import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent {
  newElement = '';
  @Output() addEvent = new EventEmitter<string>();

  add(): void {
    if (this.newElement !== '') {
      //alert(`Form submitted with value: ${this.newElement}`);
      this.addEvent.emit(this.newElement);
      this.newElement = ''; // Clear the input field after submission
    } else {
      alert(`Please enter a valid allergy. the value: ${this.newElement}`);
    }
  }
}
