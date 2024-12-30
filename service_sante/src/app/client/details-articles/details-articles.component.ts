import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { ArticleService, Article, Commentaire } from '../../../services/article/article.service';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../services/auth/auth.service';

@Component({
  selector: 'app-details-articles',
  standalone: true,
  templateUrl: './details-articles.component.html',
  styleUrls: ['./details-articles.component.css'],
  imports: [NavbarComponent, CommonModule, FormsModule, RouterModule],
})
export class ArticleDetailsComponent implements OnInit {
  article: Article | null = null;
  newComment: string = '';
  loading = true;
  errorMessage: string | null = null;

  constructor(private route: ActivatedRoute, private articleService: ArticleService, private auth:AuthService) {}

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

  ajouterCommentaire() {
    if (this.newComment.trim() && this.article) {

      const userId = this.auth.getUser();
      if (userId)
        {
          const id = parseInt(userId);
          this.articleService.ajouterCommentaire(this.article.id, id, this.newComment).subscribe(
        (newComment: Commentaire) => {
          if (this.article) {
            this.article.commentaires.push(newComment);
            this.newComment = '';
          }
        },
        error => {
          this.errorMessage = 'Erreur lors de l\'ajout du commentaire';
          console.error('Erreur :', error);
        }
      );
        } // Remplacez par l'ID utilisateur actuel (dynamique si possible)
      
    }
  }

  supprimerCommentaire(commentId: number) {
    if (this.article) {
      // Suppression optimiste du commentaire de l'interface utilisateur
      this.article.commentaires = this.article.commentaires.filter(c => c.id !== commentId);

      this.articleService.supprimerCommentaire(commentId).subscribe(
        () => {
          // La suppression a réussi, pas besoin de faire quoi que ce soit d'autre
          console.log('Commentaire supprimé avec succès');
        },
        error => {
          // En cas d'erreur, on remet le commentaire dans la liste
          console.error('Erreur lors de la suppression du commentaire:', error);
          this.errorMessage = 'Erreur lors de la suppression du commentaire. Veuillez réessayer.';
          
          // Récupérer à nouveau les détails de l'article pour s'assurer que nous avons les données les plus récentes
          if (this.article) {
            this.articleService.getArticleById(this.article.id).subscribe(
              (updatedArticle) => {
                this.article = updatedArticle;
              },
              (fetchError) => {
                console.error('Erreur lors de la récupération des détails de l\'article:', fetchError);
              }
            );
          }
        }
      );
    }
  }
}

