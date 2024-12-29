import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FavorisService {
  private apiUrl = 'http://localhost:8080/favoris'; // URL de votre API

  constructor(private http: HttpClient) {}

  // Récupérer les plantes favorites de l'utilisateur
  getFavoritePlantes(clientId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/plantes/${clientId}`);
  }

  // Ajouter une plante aux favoris
  addToFavorites(clientId: number, planteId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/ajouter/${clientId}/${planteId}`, {});
  }

  // Retirer une plante des favoris
  removeFromFavorites(clientId: number, planteId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/supprimer/${clientId}/${planteId}`);
  }


  // src/services/pages/plantes.service.ts
getFavorisByClientId(clientId: number): Observable<any[]> {
  return this.http.get<any[]>(`http://localhost:8080/favoris/${clientId}`);
}



}

