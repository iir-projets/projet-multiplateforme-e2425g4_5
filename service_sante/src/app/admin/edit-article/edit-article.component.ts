import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Article, ArticleService } from '../../../services/article/article.service';

@Component({
  selector: 'app-edit-article',
  imports: [
        MatDialogModule,
        MatFormFieldModule,
        MatInputModule,
        MatButtonModule,
        FormsModule,
        ReactiveFormsModule,
        CommonModule
      ],
  templateUrl: './edit-article.component.html',
  styleUrl: './edit-article.component.css'
})
export class EditArticleComponent {

  article : Article;

  constructor(
    private dialogRef: MatDialogRef<EditArticleComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { article: Article },
    private articleServie : ArticleService
  ) {
    this.article = { ...data.article }; // Clone de l'objet pour éviter les modifications directes
  }

  submit(): void {
    this.articleServie.updateArticle(this.article).subscribe({
      next: (updateArticle) => {
        console.log('Article mise à jour avec succès:', updateArticle);
        this.dialogRef.close(updateArticle); // Ferme le popup et renvoie les données mises à jour
      },
      error: (err) => {
        console.error('Erreur lors de la mise à jour:', err);
      },
    });
  }

  close(): void {
    this.dialogRef.close();
  }
}
