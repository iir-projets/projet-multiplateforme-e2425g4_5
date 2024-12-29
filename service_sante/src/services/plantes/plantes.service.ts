// filepath: /c:/Users/LENOVO/IdeaProjects/project_javaee/projet-multiplateforme-e2425g4_5/service_sante/src/services/pages/plantes.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Propriete{
  id?: number;
  nom: string;
}
export interface Plante{
  id?: number;
  nom: string;
  image: string;
  description: string;
  region: string;
  proprietes: Propriete[];
  interaction: string;
  precaution: string;

}
@Injectable({
  providedIn: 'root'
})
export class PlantesService {
  private apiUrl = 'http://localhost:8080'; 

  constructor(private http: HttpClient) {}

  getPlantes(): Observable<Plante[]> {
    return this.http.get<Plante[]>(this.apiUrl,this.getHttpOptions());
  }
 

  getPlanteDetails(id: string): Observable<Plante> {
    return this.http.get<Plante>(`${this.apiUrl}${id}`,this.getHttpOptions());
  }

  /**
   * Get all herbs
   * @returns Plante[]
   * @memberof Plante
   * @method getAll
   */

  getAll(): Observable<Plante[]> {
    return this.http.get<Plante[]>(`${this.apiUrl}/plantes/`,this.getHttpOptions());
  }

  getTotal(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/admin/plantes/total`,this.getHttpOptions());
  }

  addHerb(): Observable<Plante[]> {
    return this.http.post<Plante[]>(`${this.apiUrl}/admin/plantes`,this.getHttpOptions());
  }

  deleteHerb(): Observable<Plante[]> {
    return this.http.delete<Plante[]>(`${this.apiUrl}/admin/plantes`,this.getHttpOptions());
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