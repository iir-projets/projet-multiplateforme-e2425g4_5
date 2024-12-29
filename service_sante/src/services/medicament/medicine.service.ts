import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Medicine {
  id?: number;
  nom: string;
  
}
@Injectable({
  providedIn: 'root'
})
export class MedicineService {

  private baseUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  /**
   * Get all medicines
   * @returns Medicine[]
   * @memberof MedicineService
   * @method getAll
   */

  getAll(): Observable<Medicine[]> {
    return this.http.get<Medicine[]>(`${this.baseUrl}/medicaments/getall`,this.getHttpOptions());
  }

  getTotal(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/admin/medicaments/total`,this.getHttpOptions());
  }

  
  /**
   * Add a medicine
   * @param {Medicine} medicine
   * @returns String
   * @memberof MedicineService
   * @method add
   */
  add(medicine: Medicine): Observable<String> {
    return this.http.post<String>(`${this.baseUrl}/sante/medicaments/add`, medicine,this.getHttpOptions());
  }
  
  /**
   * Delete a medicine
   * @param {number} id
   * @returns String
   * @memberof MedicineService
   * @method delete
   */
  delete(id: number): Observable<String> {
    return this.http.delete<String>(`${this.baseUrl}/sante/medicaments/delete/${id}`,this.getHttpOptions());
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
