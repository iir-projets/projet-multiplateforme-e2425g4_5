import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FooterComponent } from "../footer/footer.component";
import { AllergyService} from '../../../services/allergie/allergy.service';
import { AuthService } from '../../../services/auth/auth.service';
import { MedicineService, Medicine } from '../../../services/medicament/medicine.service';
import { DeseaseService, Desease } from '../../../services/maladie/desease.service';
import { ClientService, User, Allergy } from '../../../services/client/client.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [NavbarComponent, RouterModule, CommonModule, FormsModule, FooterComponent],
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfileComponent implements OnInit {
  profileFields = [
    { label: 'phoneNumber', value: '', isEditing: false },
    { label: 'lastName', value: '', isEditing: false },
    { label: 'password', value: '****', isEditing: false }
  ];

  medicalFields = [
    { label: 'Allergy', value: [] as string[], isEditing: false, options: [] as string[] },
    { label: 'Medicine', value: [] as string[], isEditing: false, options: [] as string[] },
    { label: 'Disease', value: [] as string[], isEditing: false, options: [] as string[] }
  ];

  clientId!: number;

  constructor(
    private authService: AuthService,
    private clientService: ClientService,
    private allergyService: AllergyService,
    private medicineService: MedicineService,
    private deseaseService: DeseaseService
  ) {}

  ngOnInit(): void {
    // Charger les allergies, médicaments et maladies
    this.loadAllergies([]);
    this.loadMedicines([]);
    this.loadDiseases([]);
  
    // Récupérer l'ID de l'utilisateur connecté à partir du token
    const clientId = this.authService.getUserIdFromToken();
  
    if (clientId) {
      this.clientService.getClientById(Number(clientId)).subscribe({
        next: (data) => {
          this.clientId = data.id;
          this.populateProfileFields(data);  // Récupérer et remplir les champs du profil
        },
        error: (error) => {
          console.error('Erreur lors de la récupération des informations de l\'utilisateur :', error);
          if (error instanceof HttpErrorResponse) {
            console.error('Statut de l\'erreur:', error.status);
            console.error('Message:', error.message);
            if (error.status === 200) {
              console.error('Le format JSON retourné par le serveur est incorrect.');
            }
          }
        }
      });
    }
  }
  
  // Méthode pour remplir les champs du profil avec les données récupérées
  populateProfileFields(data: User): void {
    this.profileFields = [
      { label: 'phoneNumber', value: data.phoneNumber, isEditing: false },
      { label: 'lastName', value: data.lastName, isEditing: false },
      { label: 'password', value: '****', isEditing: false }  // Ne jamais exposer le mot de passe en clair
    ];

 
    this.medicalFields.forEach(field => {
      if (field.label === 'Allergy') {
        field.value = Array.isArray(data.allergies) 
          ? data.allergies.map((allergy: any) => allergy.allergie?.nom) 
          : [];
      } else if (field.label === 'Medicine') {
        field.value = Array.isArray(data.medicaments) 
          ? data.medicaments.map((medicine: any) => medicine.medicament?.nom) 
          : [];
      } else if (field.label === 'Disease') {
        field.value = Array.isArray(data.maladies) 
          ? data.maladies.map((disease: any) => disease.maladie?.nom) 
          : [];
      }
    });
  }

 // Exemple d'allergie sous forme d'objet
 loadAllergies(allergies: any[]): void {
  this.allergyService.getAll().subscribe({
    next: (allergiesList: Allergy[]) => {
      const allergyField = this.medicalFields.find(field => field.label === 'Allergy');
      if (allergyField) {
        // Mettre à jour les options et les valeurs avec les noms
        allergyField.options = allergiesList.map(allergy => allergy.nom);
        allergyField.value = allergies.map((allergy: any) => allergy.allergie?.nom) || [];
      }
    },
    error: (err) => console.error('Erreur lors du chargement des allergies :', err)
  });
}





 // Charger les médicaments et les sélectionner
  loadMedicines(medicaments: any[]): void {
    this.medicineService.getAll().subscribe({
      next: (medicinesList: Medicine[]) => {
        const medicineField = this.medicalFields.find(field => field.label === 'Medicine');
        if (medicineField) {
          medicineField.options = medicinesList.map(medicine => medicine.nom);
          medicineField.value = medicaments.map((medicine: any) => medicine.medicament?.nom) || [];
        }
      },
      error: (err) => console.error('Erreur lors du chargement des médicaments :', err)
    });
  }
  

   // Charger les maladies et les sélectionner
   loadDiseases(maladies: any[]): void {
    this.deseaseService.getAll().subscribe({
      next: (diseasesList: Desease[]) => {
        const diseaseField = this.medicalFields.find(field => field.label === 'Disease');
        if (diseaseField) {
          diseaseField.options = diseasesList.map(disease => disease.nom);
          diseaseField.value = maladies.map((disease: any) => disease.maladie?.nom) || [];
        }
      },
      error: (err) => console.error('Erreur lors du chargement des maladies :', err)
    });
  }
  

  updateField(field: any): void {
    if (!field) {
      console.error('Le champ est undefined!');
      return;
    }

    // Pour les champs médicaux, s'assurer qu'il y a des options disponibles avant de continuer
    if (field.label === 'Allergy' || field.label === 'Medicine' || field.label === 'Disease') {
      if (!field.options || field.options.length === 0) {
        console.error('Aucune option disponible pour ce champ.');
        return;
      }
    }

    // Mettre à jour les champs de profil
    switch (field.label) {
      case 'phoneNumber':
        this.profileFields.find(f => f.label === 'phoneNumber')!.value = field.value;
        break;
      case 'lastName':
        this.profileFields.find(f => f.label === 'lastName')!.value = field.value;
        break;
      case 'password':
        console.log('Mot de passe modifié');
        break;
      default:
        console.log('Champ non traité');
    }

    field.isEditing = false;
    console.log('Champ mis à jour:', field);
    this.editProfile();  // Appel à l'API pour sauvegarder les changements
  }

  // Fonction de bascule des options (ajout ou suppression)
  toggleOption(field: any, option: string): void {
    const index = field.value.indexOf(option);
    if (index > -1) {
      field.value.splice(index, 1);  // Retirer l'option
    } else {
      field.value.push(option);  // Ajouter l'option
    }
  }

  // Méthode pour ajouter une allergie
  addAllergy(option: string): void {
    this.clientService.addAllergy(this.clientId,Number(option)).subscribe({
      next: () => {
        console.log('Allergie ajoutée avec succès.');
        this.loadAllergies(this.medicalFields.find(f => f.label === 'Allergy')?.value || []);
      },
      error: (err) => {
        console.error('Erreur lors de l\'ajout de l\'allergie:', err);
      }
    });
  }

  // Méthode pour ajouter un médicament
  addMedicine(option: string): void {
    this.clientService.addMedicine(this.clientId, Number(option)).subscribe({
      next: () => {
        console.log('Médicament ajouté avec succès.');
        this.loadMedicines(this.medicalFields.find(f => f.label === 'Medicine')?.value || []);
      },
      error: (err) => {
        console.error('Erreur lors de l\'ajout du médicament:', err);
      }
    });
  }

  // Méthode pour ajouter une maladie
  addDisease(option: string): void {
    this.clientService.addDisease(this.clientId, Number(option)).subscribe({
      next: () => {
        console.log('Maladie ajoutée avec succès.');
        this.loadDiseases(this.medicalFields.find(f => f.label === 'Disease')?.value || []);
      },
      error: (err) => {
        console.error('Erreur lors de l\'ajout de la maladie:', err);
      }
    });
  }

  // Méthode pour sauvegarder les changements via l'API
  editProfile(): void {
    const updatedData = {
      phoneNumber: this.profileFields.find(f => f.label === 'phoneNumber')?.value,
      lastName: this.profileFields.find(f => f.label === 'lastName')?.value,
      allergies: this.medicalFields.find(f => f.label === 'Allergy')?.value,
      medicines: this.medicalFields.find(f => f.label === 'Medicine')?.value,
      diseases: this.medicalFields.find(f => f.label === 'Disease')?.value
    };

    this.clientService.editProfile(this.clientId, updatedData).subscribe({
      next: (response) => {
        console.log('Profil mis à jour avec succès:', response);
        alert('Les informations ont été mises à jour avec succès.');
      },
      error: (error) => {
        console.error('Erreur lors de la mise à jour du profil:', error);
        alert('Une erreur est survenue lors de la mise à jour.');
      }
    });
  }
}
