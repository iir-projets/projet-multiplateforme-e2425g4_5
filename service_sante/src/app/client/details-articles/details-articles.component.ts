import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from "../navbar/navbar.component";
import { FooterComponent } from "../footer/footer.component";

interface Comment {
  id: number;
  user: string;
  text: string;
  date: string;
}

@Component({
  selector: 'app-details-articles',
  standalone: true,
  imports: [CommonModule, FormsModule, NavbarComponent, FooterComponent],
  templateUrl: './details-articles.component.html',
  styleUrls: ['./details-articles.component.css']
})
export class ArticleDetailsComponent {
  article = {
    title: 'Chamomile: Nature\'s Soothing Wonder',
    image: 'assets/images/plante8.jpg',
    sections: [
      {
        title: 'The Magic of Chamomile',
        content: 'Chamomile, with its delicate white petals and sunny yellow center, is more than just a pretty flower. This charming little bloom has been cherished for centuries for its soothing properties and gentle nature. Whether you\'re sipping it in a warm cup of tea or using it in skincare, chamomile brings a touch of natural magic to our lives.'
      },
      {
        title: 'A Cup of Calm',
        content: 'Imagine curling up with a soft blanket and a steaming mug of chamomile tea. As you take that first sip, you can almost feel the day\'s stress melting away. That\'s the beauty of chamomile - it\'s like a warm hug for your insides, helping to calm your mind and prepare your body for a restful night\'s sleep. It\'s no wonder this humble herb has been a bedtime favorite for generations!'
      }
    ]
  };

  comments: Comment[] = [
    {
      id: 1,
      user: 'HappyHerbLover',
      text: 'I absolutely adore chamomile! It\'s my go-to tea for relaxing evenings. Does anyone else feel like they\'re wrapped in a cozy cloud when they drink it? ☁️☕',
      date: '2024-01-20'
    },
    {
      id: 2,
      user: 'GardenGuru',
      text: 'Great article! I\'ve been growing chamomile in my garden, and the flowers are so cheerful. Plus, the bees love them! 🐝🌼 Any tips for drying the flowers for tea?',
      date: '2024-01-21'
    }
  ];

  newComment: string = '';

  addComment() {
    if (this.newComment.trim()) {
      const comment: Comment = {
        id: this.comments.length + 1,
        user: 'FlowerPower',
        text: this.newComment,
        date: new Date().toISOString().split('T')[0]
      };
      this.comments.push(comment);
      this.newComment = '';
    }
  }
}

