import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent {
  @Input() paramList: any[] = [];
  @Output() deleteEvent = new EventEmitter<number>(); // Emit delete event

  delete(id: number): void {
    this.deleteEvent.emit(id); // Emit the ID of the item to be deleted
  }
}
