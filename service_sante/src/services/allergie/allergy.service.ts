import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


export interface Allergy {
  id?: number;
  nom: string;
}
export interface AllergyDTO {
  nom: string;
}

@Injectable({
  providedIn: 'root'
})
export class AllergyService {

  private baseUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  /**
   * Get all allergies
   * @returns Allergy[]
   * @memberof AllergyService
   * @method getAll
   */

  getAll(): Observable<Allergy[]> {
    return this.http.get<Allergy[]>(`${this.baseUrl}/allergies/getall`,this.getHttpOptions());
  }

  /**
   * Add an allergy
   * @param {AllergyDTO} allergy
   * @returns String
   * @memberof AllergyService
   * @method add
   */
  add(allergy: AllergyDTO): Observable<Object> {
    return this.http.post(`${this.baseUrl}/sante/allergies/add`, allergy, {
      ...this.getHttpOptions(),
      responseType: 'text' as 'json', // Indicate that the response is plain text
    });
  }
  

  /**
   * Delete an allergy
   * @param {number} id
   * @returns String
   * @memberof AllergyService
   * @method delete
   */
  delete(id: number): Observable<Object> {
    return this.http.delete(`${this.baseUrl}/sante/allergies/delete/${id}`, {
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
