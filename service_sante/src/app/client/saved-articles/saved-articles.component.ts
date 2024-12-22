import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from "../navbar/navbar.component";
import { FooterComponent } from "../footer/footer.component";

interface SavedArticle {
  id: number;
  title: string;
  description: string;
  image: string;
  isSaved: boolean;
}

@Component({
  selector: 'app-saved-articles',
  standalone: true,
  imports: [CommonModule, NavbarComponent, FooterComponent],
  templateUrl: './saved-articles.component.html',
  styleUrls: ['./saved-articles.component.css']
})
export class SavedArticlesComponent {
  savedArticles: SavedArticle[] = [
    {
      id: 1,
      title: 'Chamomile',
      description: 'This is an article about chamomile, a gentle herb known for its calming properties.',
      image: 'assets/images/plante4.jpg',
      isSaved: true
    },
    {
      id: 2,
      title: 'Rosemary',
      description: 'Discover the aromatic wonders of rosemary, a versatile herb used in cooking and aromatherapy.',
      image: 'assets/images/plante5.jpg',
      isSaved: true
    },
    {
      id: 3,
      title: 'Lavender',
      description: 'Explore the soothing world of lavender, known for its relaxing scent and beautiful purple flowers.',
      image: 'assets/images/plante6.jpg',
      isSaved: true
    },
    {
      id: 4,
      title: 'Mint',
      description: 'Learn about the refreshing qualities of mint, a popular herb used in teas, desserts, and more.',
      image: 'assets/images/plante7.jpg',
      isSaved: true
    },
    {
      id: 5,
      title: 'Basil',
      description: 'Dive into the world of basil, a fragrant herb essential in many cuisines around the globe.',
      image: 'assets/images/plante8.jpg',
      isSaved: true
    }
  ];

  removeFromSaved(article: SavedArticle) {
    article.isSaved = false;
    this.savedArticles = this.savedArticles.filter(a => a.isSaved);
  }
}

