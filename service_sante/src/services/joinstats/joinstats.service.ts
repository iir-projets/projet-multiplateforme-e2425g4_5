import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators'; // Import de l'opérateur map

@Injectable({
  providedIn: 'root'
})
export class JoinstatsService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  /**
   * Récupère les statistiques des catégories avec un mapping des résultats.
   * @returns Observable avec les données formatées { maladies, medicaments, allergies }
   */
  getCounts(): Observable<{ maladies: number; medicaments: number; allergies: number }> {
    return this.http
      .get<Array<{ total: number; category: string }>>( // Typage explicite de la réponse
        `${this.apiUrl}/admin/stats/categories`,
        this.getHttpOptions()
      )
      .pipe(
        map((data) => {
          const result = { maladies: 0, medicaments: 0, allergies: 0 };
          data.forEach((item) => {
            if (item.category === 'Maladies') result.maladies = item.total;
            else if (item.category === 'Médicaments') result.medicaments = item.total;
            else if (item.category === 'Allergies') result.allergies = item.total;
          });
          return result;
        })
      );
  }

  /**
   * Obtient les options HTTP, y compris les en-têtes.
   * @returns Options HTTP avec en-têtes
   */
  private getHttpOptions() {
    const authToken = localStorage.getItem('authToken');
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${authToken}`
      })
    };
  }
}
