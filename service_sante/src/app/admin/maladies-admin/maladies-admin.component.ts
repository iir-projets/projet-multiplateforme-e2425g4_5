import { Component } from '@angular/core';
import { FormComponent } from '../../service_sante/form/form.component';
import { ListComponent } from '../../service_sante/list/list.component';
import { Desease, DeseaseService } from '../../../services/maladie/desease.service';

@Component({
  selector: 'app-maladies-admin',
  imports: [FormComponent,ListComponent],
  templateUrl: './maladies-admin.component.html',
  styleUrl: './maladies-admin.component.css'
})
export class MaladiesAdminComponent {
allMaladies: Desease[] = [];
  constructor(private maladiesService: DeseaseService) { }
  ngOnInit(): void {
    this.maladiesService.getAll().subscribe(data => {
      this.allMaladies = data;
    });
    
  }

  onDelete(id: number): void {
    // Call the delete method in the service
    this.maladiesService.delete(id).subscribe(() => {
      // Update the list after deletion
      this.allMaladies = this.allMaladies.filter(desease => desease.id !== id);
    });
  }

  onAdd(nom: string): void{
    const newDesease: Desease = { nom };
    this.maladiesService.add(newDesease).subscribe(() => {
      // Update the list after adding
      this.allMaladies.push(newDesease);
    });
  }

}