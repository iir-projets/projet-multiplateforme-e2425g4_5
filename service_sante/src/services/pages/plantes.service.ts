import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlantesService {
  private apiUrl = 'http://localhost:8080/plantes'; // Remplacez par l'URL de votre backend

  constructor(private http: HttpClient) {}


  getPlantes(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }


}
