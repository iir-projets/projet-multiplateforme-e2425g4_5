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

export interface User {
  id: number;
  lastName: string;
  username: string;
  phoneNumber: string;
  password: string;
  allergies: string[];
  medicaments: string[];
  maladies: string[];
}

export interface Allergy {
  id?: number;
  nom: string;
}

export interface Medicine {
  id?: number;
  nom: string;
}

export interface Disease {
  id?: number;
  nom: string;
}

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private baseUrl = 'http://localhost:8080';  // Assurez-vous que l'URL de base est correcte

  constructor(private http: HttpClient) { }

  /**
   * Get all clients
   * @returns Client[]
   */
  getAll(): Observable<Client[]> {
    return this.http.get<Client[]>(`${this.baseUrl}/admin/clients`, this.getHttpOptions());
  }

  getTotal(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/admin/clients/total`, this.getHttpOptions());
  }

  getClientDetails(id: number): Observable<Client> {
      return this.http.get<Client>(`${this.baseUrl}/admin/clients/${id}`,this.getHttpOptions());
    }

  /**
   * Get a client by their ID
   * @param id Client ID
   * @returns User
   */
  getClientById(id: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/admin/clients/${id}`, this.getHttpOptions());
  }

  /**
   * Edit client profile (e.g., phoneNumber, lastName, etc.)
   * @param id Client ID
   * @param data Data to be updated
   * @returns Observable<Object>
   */
  editProfile(id: number, data: any): Observable<Object> {
    return this.http.patch(`${this.baseUrl}/clients/${id}`, data, this.getHttpOptions());
  }



  
  

  /**
   * Add an allergy to the client's profile
   * @param id Client ID
   * @param allergyId Allergy ID to be added
   * @returns Observable<any>
   */
  addAllergy(id: number, allergyId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/clients/${id}/allergie`, null, {
      params: { allergie_id: allergyId.toString() },
      headers: this.getHttpOptions().headers
    });
  }

  /**
   * Add a disease to the client's profile
   * @param id Client ID
   * @param diseaseId Disease ID to be added
   * @returns Observable<any>
   */
  addDisease(id: number, diseaseId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/clients/${id}/maladie`, null, {
      params: { maladie_id: diseaseId.toString() },
      headers: this.getHttpOptions().headers
    });
  }

  /**
   * Add a medicine to the client's profile
   * @param id Client ID
   * @param medicineId Medicine ID to be added
   * @returns Observable<any>
   */
  addMedicine(id: number, medicineId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/clients/${id}/medicament`, null, {
      params: { medicament_id: medicineId.toString() },
      headers: this.getHttpOptions().headers
    });
  }

  /**
   * Get HTTP options, including headers
   * @returns HTTP options with headers
   */
  private getHttpOptions() {
    return {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('authToken')  // Assurez-vous d'avoir un token valide dans le localStorage
      }
    };
  }

}
