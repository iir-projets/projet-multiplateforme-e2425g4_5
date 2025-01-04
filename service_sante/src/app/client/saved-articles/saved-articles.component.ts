import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../../services/article/article.service';  // Importer le service
import { Article } from '../../../services/article/article.service';// Importer le modèle Article
import { FooterComponent } from '../footer/footer.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedArticleService } from '../../../services/SharedArticleService/shared-article-service.service';

@Component({
  selector: 'app-saved-articles',
  templateUrl: './saved-articles.component.html',
  styleUrls: ['./saved-articles.component.css'],
  imports: [FooterComponent,NavbarComponent,CommonModule,FormsModule]
})
export class SavedArticlesComponent implements OnInit {
  savedArticles: Article[] = [];
  clientId: number = 2; // Client id fixe (ou récupéré d'une session/authentification)

  constructor(
    private articleService: ArticleService,
    private sharedArticleService: SharedArticleService
  ) {}
  
  ngOnInit(): void {
    this.loadSavedArticles();
  
    this.sharedArticleService.savedArticles$.subscribe((articles) => {
      this.savedArticles = articles;
    });
  }
  

  loadSavedArticles(): void {
    this.articleService.getArticleByClientId(this.clientId).subscribe({
      next: (articles) => {
        this.savedArticles = articles;
        this.sharedArticleService.updateSavedArticles(articles);
      },
      error: (err) => {
        console.error('Erreur lors du chargement des articles enregistrés :', err);
      }
    });
  }

 
  removeSavedArticle(articleId: number): void {
    this.articleService.deleteSavedArticle(this.clientId, articleId).subscribe({
      next: (response) => {
        console.log('Article removed successfully:', response);
        this.savedArticles = this.savedArticles.filter(article => article.id !== articleId);
        this.sharedArticleService.updateSavedArticles(this.savedArticles);
      },
      error: (err) => {
        console.error('Erreur lors de la suppression de l\'article :', err);
      },
    });
  }



  
}
