import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Article, ArticleService } from '../../../services/article/article.service';
import { CommonModule } from '@angular/common';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { AddarticleComponent } from '../addarticle/addarticle.component';
import { MatDialog } from '@angular/material/dialog';  // Import MatDialog


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
    this.selectedArticle = article;
  }

  // Méthode pour réinitialiser la sélection de l'article
  closeArticle(): void {
    this.selectedArticle = null;
  }
}
