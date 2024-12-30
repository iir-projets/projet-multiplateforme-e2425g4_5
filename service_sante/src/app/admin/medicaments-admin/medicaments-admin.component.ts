import { Component, OnInit } from '@angular/core';
import { Medicine, MedicineService } from '../../../services/medicament/medicine.service';
import { FormComponent } from '../../service_sante/form/form.component';
import { ListComponent } from '../../service_sante/list/list.component';
@Component({
  selector: 'app-medicaments-admin',
  imports: [FormComponent,ListComponent],
  templateUrl: './medicaments-admin.component.html',
  styleUrl: './medicaments-admin.component.css'
})
export class MedicamentsAdminComponent implements OnInit{
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
