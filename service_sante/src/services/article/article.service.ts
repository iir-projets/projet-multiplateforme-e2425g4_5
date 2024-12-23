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
  private apiUrl = 'http://localhost:8080/articles'; // Replace with your API URL

  constructor(private http: HttpClient) {}

  getArticles(): Observable<Article[]> {
    return this.http.get<Article[]>(`${this.apiUrl}`, this.getHttpOptions());
  }

  getArticleById(id: number): Observable<Article> {
    return this.http.get<Article>(`${this.apiUrl}/${id}`, this.getHttpOptions());
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
