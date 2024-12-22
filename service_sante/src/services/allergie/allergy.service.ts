import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


export interface Allergy {
  id?: number;
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
   * @param {Allergy} allergy
   * @returns String
   * @memberof AllergyService
   * @method add
   */
  add(allergy: Allergy): Observable<String> {
    return this.http.post<String>(`${this.baseUrl}/sante/allergies/add`, allergy,this.getHttpOptions());
  }

  /**
   * Delete an allergy
   * @param {number} id
   * @returns String
   * @memberof AllergyService
   * @method delete
   */
  delete(id: number): Observable<String> {
    return this.http.delete<String>(`${this.baseUrl}/sante/allergies/delete/${id}`,this.getHttpOptions());
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
