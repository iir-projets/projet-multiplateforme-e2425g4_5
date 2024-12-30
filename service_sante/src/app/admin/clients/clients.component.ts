import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ClientService, Client } from '../../../services/client/client.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-clients',
  imports: [CommonModule],
  templateUrl: './clients.component.html',
  styleUrl: './clients.component.css'
})
export class ClientsComponent{
  clients: Client[] = [];
  isLoading = true;

  constructor(private clientService: ClientService, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.loadClients();
  }

  // Charger les clients
  loadClients(): void {
    this.clientService.getAll().subscribe(
      data => {
        console.log('Clients reçus:', data); // Vérifier la structure des données
        this.clients = data;
        this.isLoading = false;
      },
      error => {
        console.error('Erreur lors du chargement des clients:', error);
        this.isLoading = false;
      }
    );
  }
}
