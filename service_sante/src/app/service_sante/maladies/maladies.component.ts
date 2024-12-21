import { Component } from '@angular/core';
import { FormComponent } from '../form/form.component';
import { ListComponent } from '../list/list.component';

@Component({
  selector: 'app-maladies',
  imports: [FormComponent,ListComponent],
  templateUrl: './maladies.component.html',
  styleUrl: './maladies.component.css'
})
export class MaladiesComponent {

}
