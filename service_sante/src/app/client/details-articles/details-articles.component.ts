import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { ArticleService, Article } from '../../../services/article/article.service';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-details-articles',
  standalone: true,
  templateUrl: './details-articles.component.html',
  styleUrls: ['./details-articles.component.css'],
  imports: [NavbarComponent,  CommonModule, FormsModule, RouterModule],
})
export class ArticleDetailsComponent implements OnInit {
  article: Article | null = null;
  newComment: string = '';
  loading = true;
  errorMessage: string | null = null;

  constructor(private route: ActivatedRoute, private articleService: ArticleService) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.articleService.getArticleById(Number(id)).subscribe(
        (data) => {
          this.article = data;
          this.loading = false;
        },
        (error) => {
          this.errorMessage = 'Unable to fetch article details. Please try again later.';
          this.loading = false;
          console.error('Error fetching article details:', error);
        }
      );
    }
  }

  addComment() {
    if (this.newComment.trim()) {
      const comment = {
        id: (this.article?.commentaires?.length ?? 0) + 1,
        utilisateur: { id: 1, username: 'FlowerPower' }, // Remplacez par l'utilisateur actuel
        contenu: this.newComment,
        date: new Date().toISOString().split('T')[0]
      };
      this.article?.commentaires.push(comment);
      this.newComment = '';
    }
  }

}

