import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Commentaire {
  id: number;
  contenu: string;
  date: string;
  utilisateur: User;
}
export interface User {
  id: number;
  username: string;
}

export interface Article {
  id: number;
  titre: string;
  contenu: string;
  images: { id: number; imageUrl: string }[];
  commentaires: Commentaire[];
}

@Injectable({
  providedIn: 'root',
})
export class ArticleService {
  private apiUrl = 'http://localhost:8080'; // Replace with your API URL

  constructor(private http: HttpClient) {}

  getArticles(): Observable<Article[]> {
    return this.http.get<Article[]>(`${this.apiUrl}/articles`, this.getHttpOptions());
  }

  getArticleById(id: number): Observable<Article> {
    return this.http.get<Article>(`${this.apiUrl}/articles/${id}`, this.getHttpOptions());
  }

  ajouterCommentaire(articleId: number, userId: number, contenu: string): Observable<Commentaire> {
    const body = {
      contenu,
      articleId,
      userId
    };
    return this.http.post<Commentaire>(`${this.apiUrl}/commentaires`, body, this.getHttpOptions());
  }

  supprimerCommentaire(commentId: number): Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/commentaires/${commentId}`, this.getHttpOptions());
  }
  
  private getHttpOptions() {
    return {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('authToken')
      }
    };
  }
}

