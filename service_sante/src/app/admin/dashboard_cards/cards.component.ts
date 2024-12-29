import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { PlantesService } from '../../../services/plantes/plantes.service';
import { ArticleService } from '../../../services/article/article.service';
import { ClientService } from '../../../services/client/client.service';
import { AllergyService } from '../../../services/allergie/allergy.service';
import { DeseaseService } from '../../../services/maladie/desease.service';
import { MedicineService } from '../../../services/medicament/medicine.service';

@Component({
  selector: 'app-cards',
  imports: [CommonModule],
  templateUrl: './cards.component.html',
  styleUrl: './cards.component.css'
})
export class CardsComponent implements OnInit{
  totalHerbs : number | null = null;
  totalArticles : number | null = null;
  totalClients : number | null = null;
  totalAllergies : number | null = null;
  totalMedicines : number | null = null;
  totalDiseases : number | null = null;

  constructor(private planteService : PlantesService, private articleService : ArticleService,private clientService : ClientService,private allergieService : AllergyService,private  deseaseService:  DeseaseService, private medicament:  MedicineService) {}

  ngOnInit(): void {
    this.loadTotalHerbs();
    this.loadTotalArticles();
    this.loadTotalClients();
    this.loadTotalAllergies();
    this.loadTotalMedicines();
    this.loadTotalDiseases();
  }

  // Charger le total des plantes depuis le service
  loadTotalHerbs(): void {
    this.planteService.getTotal().subscribe({
      next: (total) => (this.totalHerbs = total),
      error: (err) => console.error('Erreur lors de la récupération des plantes :', err),
    });
  }
  // Charger le total des articles depuis le service
  loadTotalArticles(): void {
    this.articleService.getTotal().subscribe({
      next: (total) => (this.totalArticles = total),
      error: (err) => console.error('Erreur lors de la récupération des articles :', err),
    });
  }
  // Charger le total des clients depuis le service
  loadTotalClients(): void {
    this.clientService.getTotal().subscribe({
      next: (total) => (this.totalClients = total),
      error: (err) => console.error('Erreur lors de la récupération des clients :', err),
    });
  }
  // Charger le total des allergies depuis le service
  loadTotalAllergies(): void {
    this.allergieService.getTotal().subscribe({
      next: (total) => (this.totalAllergies = total),
      error: (err) => console.error('Erreur lors de la récupération des allergies :', err),
    });
  }
  // Charger le total des medicaments depuis le service
  loadTotalMedicines(): void {
    this.medicament.getTotal().subscribe({
      next: (total) => (this.totalMedicines = total),
      error: (err) => console.error('Erreur lors de la récupération des medicaments :', err),
    });
  }
  // Charger le total des maladies depuis le service
  loadTotalDiseases(): void {
    this.deseaseService.getTotal().subscribe({
      next: (total) => (this.totalDiseases = total),
      error: (err) => console.error('Erreur lors de la récupération des maladies :', err),
    });
  }
}
