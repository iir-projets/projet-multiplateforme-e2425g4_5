import { Component, OnInit } from '@angular/core';
import { Allergy, AllergyDTO, AllergyService } from '../../../services/allergie/allergy.service';
import { FormComponent } from '../../service_sante/form/form.component';
import { ListComponent } from '../../service_sante/list/list.component';

@Component({
  selector: 'app-allergies-admin',
  imports: [FormComponent,ListComponent],
  templateUrl: './allergies-admin.component.html',
  styleUrl: './allergies-admin.component.css'
})
export class AllergiesAdminComponent {
  allAllergies: Allergy[] = [];
  constructor(private allergiesService: AllergyService) { }
  ngOnInit(): void {
    this.allergiesService.getAll().subscribe(data => {
      this.allAllergies = data;
    });
    
  }

  onDelete(id: number): void {
    // Call the delete method in the service
    this.allergiesService.delete(id).subscribe(() => {
      // Update the list after deletion
      this.allAllergies = this.allAllergies.filter(allergy => allergy.id !== id);
    });
  }
  onAdd(value: string): void{
    
    const newAllergy: AllergyDTO = {
      nom: value
    };
    this.allergiesService.add(newAllergy).subscribe({
      next: (response) => {
          // Add to the list only after a successful response
          if( response === 'Allergie ajoutée avec succès !'){
            alert('Allergy added successfully');
            this.allergiesService.getAll().subscribe(data => {
            this.allAllergies = data;
            });
          }
          else console.log(response.toString());
          
      },
      error: (err) => {
          console.error('Failed to add allergy:', err);
      }
  });
  }
}
