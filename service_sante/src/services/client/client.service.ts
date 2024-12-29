import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

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

  private apiUrl = 'http://localhost:8080/admin/clients';  // Hardcoder l'URL de l'API

  constructor(private http: HttpClient) {}

  // Récupérer un utilisateur par son ID
  getClientById(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${id}`,this.getHttpOptions());
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
