import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Article, ArticleService } from '../../../services/article/article.service';
import { CommonModule } from '@angular/common';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { AddarticleComponent } from '../addarticle/addarticle.component';
import { MatDialog } from '@angular/material/dialog';  // Import MatDialog
import { DetailsArticlesComponent } from '../details-articles/details-articles.component';
import { EditArticleComponent } from '../edit-article/edit-article.component';


@Component({
  selector: 'app-articles-admin',
  imports: [FormsModule, CommonModule, MatProgressSpinnerModule],
  templateUrl: './articles-admin.component.html',
  styleUrls: ['./articles-admin.component.css']
})
export class ArticlesAdminComponent implements OnInit {
  articles: Article[] = [];
  isLoading: boolean = true;
  selectedArticle: Article | null = null; // Pour afficher l'article sélectionné

  constructor(private articleService: ArticleService,private dialog: MatDialog) {}

  ngOnInit(): void {
    this.loadArticles();
  }

  loadArticles(): void {
    this.articleService.getArticles().subscribe(
      (data) => {
        this.articles = data;
        this.isLoading = false;
      },
      (error) => {
        console.error('Erreur lors du chargement des articles:', error);
        this.isLoading = false;
      }
    );
  }

  openAddArticle(): void {
      const dialogRef = this.dialog.open(AddarticleComponent);
  
      dialogRef.afterClosed().subscribe(result => {
        if (result) {
          console.log('Article added successfully :', result);
        }
      });
    }

  // Méthode pour afficher l'article complet lorsqu'on clique sur une carte
  selectArticle(article: Article): void {
    this.dialog.open(DetailsArticlesComponent, {
      data: article,
      width: '600px',
    });
  }

  updateArticle(article: Article): void {
      const dialogRef = this.dialog.open(EditArticleComponent, {
        data: { article },
        width: '600px',
      });
    
      dialogRef.afterClosed().subscribe((result) => {
        if (result) {
          console.log('Plante mise à jour:', result);
          this.loadArticles(); // Rechargez la liste des plantes après mise à jour
        }
      });
    }


  deleteArticle(id: number): void {
    this.articleService.deleteArticle(id).subscribe({
      next: () => {
        console.log('Article supprimé avec succès');
        this.loadArticles(); // Rechargez la liste
      },
      error: (err) => {
        console.error('Erreur lors de la suppression :', err);
      },
    });
  }
 
}
