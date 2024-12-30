import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


export interface Client {
  id?: number;
  username: string;
  password: string;
  role: string;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  
}

interface User {
  id: number;
  email: string;
  mobile: string;
  address: string;
  password: string;
}

@Injectable({
  providedIn: 'root'
})
export class ClientService {


  private baseUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  /**
   * Get all allergies
   * @returns Allergy[]
   * @memberof ClientService
   * @method getAll
   */

  getAll(): Observable<Client[]> {
    return this.http.get<Client[]>(`${this.baseUrl}/admin/clients`,this.getHttpOptions());
  }

  getTotal(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/admin/clients/total`,this.getHttpOptions());
  }

  /**
   * Get http options, including headers
   * @returns HTTP options with headers
   */

  // Récupérer un utilisateur par son ID
  getClientById(id: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/admin/clients/${id}`,this.getHttpOptions());
  }


  private getHttpOptions() {
    return {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('authToken')
        }
    };
  }

}
