import { Component } from '@angular/core';
import { FormComponent } from '../form/form.component';
import { ListComponent } from '../list/list.component';

@Component({
  selector: 'app-allergies',
  imports: [FormComponent,ListComponent],
  templateUrl: './allergies.component.html',
  styleUrl: './allergies.component.css'
})
export class AllergiesComponent {

}
