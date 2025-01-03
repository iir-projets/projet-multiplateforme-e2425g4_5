import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { forkJoin } from 'rxjs';
import { ClientService, Client } from '../../../services/client/client.service';
import { FavorisService } from '../../../services/favoris/favoris.service';
import { ArticleService, Article } from '../../../services/article/article.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-details-client',
  imports: [CommonModule],
  templateUrl: './details-client.component.html',
  styleUrls: ['./details-client.component.css'],
})
export class DetailsClientComponent implements OnInit {
  client!: Client;
  favoris: any[] = [];
  articles: Article[] = [];

  constructor(
    private clientService: ClientService,
    private favorisService: FavorisService,
    private articleService: ArticleService,
    public dialogRef: MatDialogRef<DetailsClientComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { id: number }
  ) {}

  ngOnInit(): void {
    this.loadClientDetails();
    this.loadClientData();
  }

  // Charger les détails du client
  loadClientDetails(): void {
    this.clientService.getClientDetails(this.data.id).subscribe({
      next: (client: Client) => (this.client = client),
      error: (err: any) =>
        console.error('Erreur lors de la récupération des détails du client :', err),
    });
  }

  // Charger les favoris et les articles
  loadClientData(): void {
    forkJoin({
      favoris: this.favorisService.getFavorisByClientId(this.data.id),
      articles: this.articleService.getArticleByClientId(this.data.id),
    }).subscribe({
      next: (results) => {
        console.log('Favoris:', results.favoris); // Log favoris
        console.log('Articles:', results.articles); // Log articles
        this.favoris = results.favoris;
        this.articles = results.articles;
      },
      error: (err) =>
        console.error('Erreur lors de la récupération des données du client :', err),
    });
  }

  // Méthode pour fermer la boîte de dialogue
  close(): void {
    this.dialogRef.close();
  }
}
