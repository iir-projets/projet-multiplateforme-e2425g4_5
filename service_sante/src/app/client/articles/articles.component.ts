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
  imports: [FooterComponent, NavbarComponent, CommonModule, RouterModule, FormsModule],
})
export class ArticleComponent implements OnInit {
  articles: Article[] = [];
  savedArticles: Article[] = [];
  searchTerm: string = '';

  constructor(private articleService: ArticleService) {}

  ngOnInit(): void {
    this.articleService.getArticles().subscribe(
      (data) => {
        this.articles = data;
      },
      (error) => {
        console.error('Erreur lors de la récupération des articles', error);
      }
    );
  }

  get filteredArticles() {
    return this.articles.filter(article =>
      article.titre.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      article.contenu.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
}
