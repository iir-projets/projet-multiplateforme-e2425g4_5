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

  getTotal(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/admin/maladies/total`,this.getHttpOptions());
  }

  /**
   * Add a disease
   * @param {Disease} disease
   * @returns String
   * @memberof DiseaseService
   * @method add
   */
  add(disease: Desease): Observable<String> {
    return this.http.post<String>(`${this.baseUrl}/sante/maladies/add`, disease,this.getHttpOptions());
    }

  /**
   * Delete a disease
   * @param {number} id
   * @returns String
   * @memberof DiseaseService
   * @method delete
   */
  delete(id: number): Observable<String> {
    return this.http.delete<String>(`${this.baseUrl}/sante/maladies/delete/${id}`,this.getHttpOptions());
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
