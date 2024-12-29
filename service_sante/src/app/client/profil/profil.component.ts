import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FooterComponent } from "../footer/footer.component";
import { AllergyService, Allergy } from '../../../services/allergie/allergy.service';
import { AuthService } from '../../../services/auth/auth.service';
import { MedicineService, Medicine } from '../../../services/medicament/medicine.service';
import { DeseaseService, Desease } from '../../../services/maladie/desease.service';
import { ClientService } from '../../../services/client/client.service';  // Assurez-vous que ce service est importé

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
    { label: 'password', value: '****', isEditing: false }  // Ne jamais exposer le mot de passe en clair
  ];

  clientId: any;

  medicalFields = [
    { label: 'Allergy', value: [] as string[], isEditing: false, options: [] as string[] },
    { label: 'Medicine', value: [] as string[], isEditing: false, options: [] as string[] },
    { label: 'Disease', value: [] as string[], isEditing: false, options: [] as string[] }
  ];

  constructor(
    private authService: AuthService,
    private clientService: ClientService,  // Ajoutez votre service utilisateur ici
    private allergyService: AllergyService,
    private medicineService: MedicineService,
    private deseaseService: DeseaseService
  ) {}

  ngOnInit(): void {
    // Charger les allergies, médicaments et maladies
    this.loadAllergies();
    this.loadMedicines();
    this.loadDiseases();

    // Récupérer l'ID de l'utilisateur connecté à partir du token
    const clientId = this.authService.getUserIdFromToken();

    if (clientId) {
      this.clientService.getClientById(Number(clientId)).subscribe({
        next: (data) => {
          this.clientId = data; // Assurez-vous de stocker les données récupérées dans la variable `user`
        },
        error: (error) => {
          console.error('Erreur lors de la récupération des informations de l\'utilisateur :', error);
        }
      });
    }
  }

  // Charger les allergies
  loadAllergies(): void {
    this.allergyService.getAll().subscribe({
      next: (allergies: Allergy[]) => {
        const allergyField = this.medicalFields.find(field => field.label === 'Allergy');
        if (allergyField) {
          allergyField.options = allergies.map(allergy => allergy.nom);
        }
      },
      error: (err) => console.error('Erreur lors du chargement des allergies :', err)
    });
  }

  // Charger les médicaments
  loadMedicines(): void {
    this.medicineService.getAll().subscribe({
      next: (medicines: Medicine[]) => {
        const medicineField = this.medicalFields.find(field => field.label === 'Medicine');
        if (medicineField) {
          medicineField.options = medicines.map(medicine => medicine.nom);
        }
      },
      error: (err) => console.error('Erreur lors du chargement des médicaments :', err)
    });
  }

  // Charger les maladies
  loadDiseases(): void {
    this.deseaseService.getAll().subscribe({
      next: (diseases: Desease[]) => {
        const diseaseField = this.medicalFields.find(field => field.label === 'Disease');
        if (diseaseField) {
          diseaseField.options = diseases.map(disease => disease.nom);
        }
      },
      error: (err) => console.error('Erreur lors du chargement des maladies :', err)
    });
  }

  // Modifier un champ (exemple : mobile, adresse)
  editField(field: any): void {
    field.isEditing = !field.isEditing;
    if (!field.isEditing && field.label === 'Password') {
      field.value = '********'; // Remettre '********' une fois que l'édition est terminée
    }
  }

  // Sauvegarder les modifications d'un champ (par exemple, email, téléphone, adresse)
  updateField(field: any): void {
    field.isEditing = false;
    console.log('Field updated:', field);
    // Appel d'un service pour mettre à jour les données côté serveur, si nécessaire
    // Exemple : this.profileService.updateEmail(field.value).subscribe();
  }

  // Toggle (ajouter ou retirer) une option (par exemple, allergies, médicaments, maladies)
  toggleOption(field: any, option: string): void {
    const index = field.value.indexOf(option);
    if (index > -1) {
      field.value.splice(index, 1);  // Supprimer l'option
    } else {
      field.value.push(option);  // Ajouter l'option
    }
  }
}
