import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from "../navbar/navbar.component";
import { FooterComponent } from "../footer/footer.component";

export interface Article {
  id: number;
  title: string;
  description: string;
  image: string;
  isBookmarked: boolean;
}

@Component({
  selector: 'app-articles',
  standalone: true,
  imports: [CommonModule, FormsModule, NavbarComponent, FooterComponent],
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticleComponent {
  searchTerm: string = '';
  
  // Liste des articles
  articles: Article[] = [
    {
      id: 1,
      title: 'Chamomile',
      description: 'this is an article, this is an article, this is an article ...',
      image: 'assets/images/plante2.jpg',
      isBookmarked: false
    },
    {
      id: 2,
      title: 'Rosemary',
      description: 'this is an article, this is an article, this is an article ...',
      image: 'assets/images/plante8.jpg',
      isBookmarked: false
    },
    {
      id: 3,
      title: 'Chamomile',
      description: 'this is an article, this is an article, this is an article ...',
      image: 'assets/images/plante5.jpg',
      isBookmarked: false
    },
    {
      id: 4,
      title: 'Rosemary',
      description: 'this is an article, this is an article, this is an article ...',
      image: 'assets/images/plante3.jpg',
      isBookmarked: false
    }
  ];

  // Liste des articles sauvegardés
  savedArticles: Article[] = [];

  toggleBookmark(article: Article) {
    article.isBookmarked = !article.isBookmarked;
  }

  // Filtrer les articles en fonction du terme de recherche
  get filteredArticles() {
    return this.articles.filter(article =>
      article.title.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      article.description.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  // Sauvegarder tous les articles dans savedArticles
  saveAllArticles() {
    this.savedArticles = [...this.articles];  // Ajoute tous les articles à la liste des articles sauvegardés
  }
}
