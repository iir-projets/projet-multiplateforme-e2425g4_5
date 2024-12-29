import { Component, OnInit } from '@angular/core';
import { Article, ArticleService } from '../../../services/article/article.service';
import { FooterComponent } from '../footer/footer.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css'],
  standalone: true,
  imports: [FooterComponent, NavbarComponent, CommonModule, RouterModule, FormsModule],
})
export class ArticleComponent implements OnInit {
  articles: Article[] = [];
  savedArticles: Article[] = [];
  searchTerm: string = '';
  filteredArticles: Article[] = [];

  constructor(private articleService: ArticleService) {}

  ngOnInit(): void {
    this.articleService.getArticles().subscribe(
      (data) => {
        this.articles = data;
        this.filterArticles();
      },
      (error) => {
        console.error('Erreur lors de la récupération des articles', error);
      }
    );
  }

  filterArticles(): void {
    this.filteredArticles = this.articles.filter(article =>
      article.titre.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      article.contenu.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  onSearchChange(): void {
    this.filterArticles();
  }
}

