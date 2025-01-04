import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Article } from '../../services/article/article.service';

@Injectable({
  providedIn: 'root',
})
export class SharedArticleService {
  private savedArticlesSource = new BehaviorSubject<Article[]>([]);
  savedArticles$ = this.savedArticlesSource.asObservable();

  updateSavedArticles(articles: Article[]): void {
    this.savedArticlesSource.next(articles);
  }
}
