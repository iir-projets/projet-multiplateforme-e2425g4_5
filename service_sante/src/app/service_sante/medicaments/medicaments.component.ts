import { Component } from '@angular/core';
import { FormComponent } from '../form/form.component';
import { ListComponent } from '../list/list.component';

@Component({
  selector: 'app-medicaments',
  imports: [FormComponent,ListComponent],
  templateUrl: './medicaments.component.html',
  styleUrl: './medicaments.component.css'
})
export class MedicamentsComponent {

}
