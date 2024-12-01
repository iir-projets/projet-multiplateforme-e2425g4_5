import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ListeserviceService {
  private apiUrl = 'http://';

  constructor(private http: HttpClient) { }

  // Methode pour obtenir les données
  getDate(): Observable<any>{
    return this.http.get<any>(`${this.apiUrl}/data`);
  }
}
