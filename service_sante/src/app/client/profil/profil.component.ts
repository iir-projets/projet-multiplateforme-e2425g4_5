import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [NavbarComponent, RouterModule, CommonModule, FormsModule],
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfileComponent implements OnInit {
  profileFields = [
    { label: 'Email', value: 'user@example.com', isEditing: false },
    { label: 'Mobile', value: '123-456-7890', isEditing: false },
    { label: 'Address', value: '1234 Main St', isEditing: false },
    { label: 'Password', value: '********', isEditing: false }
  ];

  medicalFields = [
    { 
      label: 'Allergy', 
      value: ['Pollen'], 
      isEditing: false,
      options: ['Pollen', 'Dust', 'Peanuts', 'Lactose', 'Gluten']
    },
    { 
      label: 'Medicine', 
      value: ['Ibuprofen'], 
      isEditing: false,
      options: ['Ibuprofen', 'Aspirin', 'Paracetamol', 'Amoxicillin', 'Omeprazole']
    },
    { 
      label: 'Disease', 
      value: ['None'], 
      isEditing: false,
      options: ['None', 'Asthma', 'Diabetes', 'Hypertension', 'Arthritis']
    }
  ];

  constructor() {}

  ngOnInit(): void {}

  editField(field: any): void {
    field.isEditing = !field.isEditing;
    if (!field.isEditing && field.label === 'Password') {
      field.value = '********';
    }
  }

  updateField(field: any): void {
    field.isEditing = false;
  }

  toggleOption(field: any, option: string): void {
    const index = field.value.indexOf(option);
    if (index > -1) {
      field.value.splice(index, 1);
    } else {
      field.value.push(option);
    }
  }
}

