import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../../services/article/article.service';  // Importer le service
import { Article } from '../../../services/article/article.service';// Importer le modèle Article
import { FooterComponent } from '../footer/footer.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-saved-articles',
  templateUrl: './saved-articles.component.html',
  styleUrls: ['./saved-articles.component.css'],
  imports: [FooterComponent,NavbarComponent,CommonModule,FormsModule]
})
export class SavedArticlesComponent implements OnInit {
  savedArticles: Article[] = [];
  clientId: number = 2; // Client id fixe (ou récupéré d'une session/authentification)

  constructor(private articleService: ArticleService) {}

  ngOnInit(): void {
    this.loadSavedArticles();
  }

  loadSavedArticles(): void {
    this.articleService.getArticleByClientId(this.clientId).subscribe({
      next: (articles) => {
        this.savedArticles = articles;  // Stocker les articles récupérés dans la variable
      },
      error: (err) => {
        console.error('Erreur lors du chargement des articles enregistrés :', err);
      }
    });
  }

  removeFromSaved(article: Article): void {
    article.isSaved = false;
    this.savedArticles = this.savedArticles.filter(a => a.isSaved);  // Met à jour la liste d'articles
  }



  toggleSaveState(article: Article): void {
    article.isSaved = !article.isSaved; // Inverse l'état de sauvegarde
    
    // Appelez un service pour enregistrer ou supprimer l'article sur le backend
    if (article.isSaved) {
      this.articleService.saveArticle(article).subscribe({
        next: () => console.log(`Article ${article.titre} sauvegardé`),
        error: (err) => console.error(`Erreur lors de la sauvegarde de l'article ${article.titre} :`, err)
      });
    } else {
      this.articleService.unsaveArticle(article).subscribe({
        next: () => console.log(`Article ${article.titre} supprimé des sauvegardes`),
        error: (err) => console.error(`Erreur lors de la suppression de l'article ${article.titre} :`, err)
      });
    }
  }
  
}
