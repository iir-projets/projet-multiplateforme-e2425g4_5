import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ArticleService } from '../../../services/article/article.service';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-addarticle',
  imports: [
      MatDialogModule,
      MatFormFieldModule,
      MatInputModule,
      MatButtonModule,
      FormsModule,
      ReactiveFormsModule,
      CommonModule
    ],
  templateUrl: './addarticle.component.html',
  styleUrl: './addarticle.component.css'
})
export class AddarticleComponent {
// Propriétés pour les champs du formulaire
   article = {
    titre: '',
    image: '',
    contenu: '',
    
  };
  

  constructor(public dialogRef: MatDialogRef<AddarticleComponent>,
    private articleService : ArticleService
  ) {}

  close(): void {
    this.dialogRef.close();
  }

  submit(): void {
    console.log('Données soumises :', this.article); // Debug
    this.articleService.addArticle(this.article).subscribe({
      next: (response) => {
        console.log('Article créée avec succès :', response);
        this.dialogRef.close(response);
      },
      error: (err) => {
        console.error('Erreur lors de la création de larticle :', err);
      }
    });
  }
}
