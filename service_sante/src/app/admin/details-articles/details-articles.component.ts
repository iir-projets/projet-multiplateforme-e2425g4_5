import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Article } from '../../../services/article/article.service';
import { CommonModule } from '@angular/common';  // Assurez-vous que CommonModule est importé
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-details-articles',
  standalone: true,
  imports: [MatDialogModule,
        MatFormFieldModule,
        MatInputModule,
        MatButtonModule,
        FormsModule,
        ReactiveFormsModule,
        CommonModule],
  templateUrl: './details-articles.component.html',
  styleUrls: ['./details-articles.component.css']
})
export class DetailsArticlesComponent {
  constructor(
    public dialogRef: MatDialogRef<DetailsArticlesComponent>,
    @Inject(MAT_DIALOG_DATA) public article: Article
  ) {}

  close(): void {
    this.dialogRef.close();  // Ferme la boîte de dialogue
  }
}
