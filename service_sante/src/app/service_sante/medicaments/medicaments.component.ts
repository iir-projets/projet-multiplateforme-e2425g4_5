import { Component, OnInit } from '@angular/core';
import { FormComponent } from '../form/form.component';
import { ListComponent } from '../list/list.component';
import { Medicine, MedicineService } from '../../../services/medicament/medicine.service';

@Component({
  selector: 'app-medicaments',
  imports: [FormComponent,ListComponent],
  templateUrl: './medicaments.component.html',
  styleUrl: './medicaments.component.css'
})
export class MedicamentsComponent implements OnInit {

  allMedicaments: Medicine[] = [];
  constructor(private medicineService: MedicineService) { }
  ngOnInit(): void {
    this.medicineService.getAll().subscribe(data => {
      this.allMedicaments = data;
    });
    
  }

  onDelete(id: number): void {
    // Call the delete method in the service
    this.medicineService.delete(id).subscribe(() => {
      // Update the list after deletion
      this.allMedicaments = this.allMedicaments.filter(medicine => medicine.id !== id);
    });
  }

  onAdd(nom: string): void{
    const newMedicine: Medicine = { nom };
    this.medicineService.add(newMedicine).subscribe(() => {
      // Update the list after adding
      this.allMedicaments.push(newMedicine);
    });
  }

}
