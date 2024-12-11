import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ListeserviceService {
  private apiUrl = 'http://votre-api-url.com/data';  // Remplacez par votre URL d'API réelle

  constructor(private http: HttpClient) {}

  // Méthode pour obtenir les données depuis l'API
  getData(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
