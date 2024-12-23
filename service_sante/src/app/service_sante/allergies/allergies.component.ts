import { Component, OnInit } from '@angular/core';
import { FormComponent } from '../form/form.component';
import { ListComponent } from '../list/list.component';
import { Allergy, AllergyDTO, AllergyService } from '../../../services/allergie/allergy.service';

@Component({
  selector: 'app-allergies',
  imports: [FormComponent,ListComponent],
  templateUrl: './allergies.component.html',
  styleUrl: './allergies.component.css'
})
export class AllergiesComponent implements OnInit {


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
    alert(`Received allergy name from form:${value}`);
    const newAllergy: AllergyDTO = {
      nom: value
    };
    this.allergiesService.add(newAllergy).subscribe({
      next: (response) => {
          // Add to the list only after a successful response
          if( response === 'Allergie ajoutée avec succès !'){
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
