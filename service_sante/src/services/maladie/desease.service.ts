import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Desease {
  id?: number;
  nom: string;
}
@Injectable({
  providedIn: 'root'
})
export class DeseaseService {

  private baseUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  /**
   * Get all diseases
   * @returns Disease[]
   * @memberof DiseaseService
   * @method getAll
   */
  getAll(): Observable<Desease[]> {

    return this.http.get<Desease[]>(`${this.baseUrl}/maladies/getall`,this.getHttpOptions());
  }

  /**
   * Add a disease
   * @param {Disease} disease
   * @returns String
   * @memberof DiseaseService
   * @method add
   */
  add(disease: Desease): Observable<Object> {
    return this.http.post(`${this.baseUrl}/sante/maladies/add`, disease, {
      ...this.getHttpOptions(),
      responseType: 'text' as 'json', // Indicate that the response is plain text
    });
    }

  /**
   * Delete a disease
   * @param {number} id
   * @returns String
   * @memberof DiseaseService
   * @method delete
   */
  delete(id: number): Observable<Object> {
    return this.http.delete(`${this.baseUrl}/sante/maladies/delete/${id}`, {
      ...this.getHttpOptions(),
      responseType: 'text' as 'json', // Indicate that the response is plain text
    });
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
