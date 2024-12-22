// filepath: /c:/Users/LENOVO/IdeaProjects/project_javaee/projet-multiplateforme-e2425g4_5/service_sante/src/services/pages/plantes.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlantesService {
  private apiUrl = 'http://localhost:8080/plantes/'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  getPlantes(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
 

}