import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Favoris{
  clientId?: number;
  hebrId?: number;
}

@Injectable({
  providedIn: 'root'
})
export class FavorisService {
  private apiUrl = 'http://localhost:8080'; // URL de votre API

  constructor(private http: HttpClient) {}

  // Récupérer les plantes favorites de l'utilisateur
  getFavoritePlantes(clientId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/favoris/plantes/${clientId}`);
  }

  // Ajouter une plante aux favoris
  addToFavorites(clientId: number, planteId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/favoris/ajouter/${clientId}/${planteId}`, {});
  }

  // Retirer une plante des favoris
  removeFromFavorites(clientId: number, planteId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/favoris/supprimer/${clientId}/${planteId}`);
  }


  getFavorisByClientId(clientId: number): Observable<Favoris[]> {
    return this.http.get<Favoris[]>(`${this.apiUrl}/admin/favoris/clientfavoris/${clientId}`,this.getHttpOptions());
  }

  getTop5Plantes(): Observable<Map<number, number>> {
    return this.http.get<Map<number, number>>(`${this.apiUrl}/admin/favoris/top5`,this.getHttpOptions());
  }


/**
   * Get http options, including headers
   * @returns HTTP options with headers
   */
private getHttpOptions() {
  return {
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('authToken')
    }
  };
}

}

