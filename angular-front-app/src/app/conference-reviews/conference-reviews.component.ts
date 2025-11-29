import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {DatePipe, NgForOf, NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Review} from '../conferences/conference.model';

@Component({
  selector: 'app-conference-reviews',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    HttpClientModule,
    FormsModule,
    DatePipe
  ],
  templateUrl: './conference-reviews.component.html',
  styleUrl: './conference-reviews.component.css'
})
export class ConferenceReviewsComponent implements OnChanges {

  @Input() conferenceId!: number;

  reviews: Review[] = [];

  // adapte le port / baseUrl
  private baseUrl = 'http://localhost:8082/api/conferences';

  // new review form
  newReview: any = {
    date: '',
    text: '',
    stars: 5
  };

  constructor(private http: HttpClient) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['conferenceId'] && this.conferenceId) {
      this.loadReviews();
    }
  }

  loadReviews() {
    this.http.get<Review[]>(`${this.baseUrl}/${this.conferenceId}/reviews`).subscribe({
      next: data => {
        this.reviews = data;
      },
      error: err => {
        console.error(err);
        this.reviews = [];
      }
    });
  }

  addReview() {
    const body = {
      // backend ReviewRequestDTO : Date, text, stars
      date: new Date(this.newReview.date).toISOString(),
      text: this.newReview.text,
      stars: this.newReview.stars
    };

    this.http.post<Review>(
      `${this.baseUrl}/${this.conferenceId}/reviews`, body
    ).subscribe({
      next: created => {
        alert('Review added successfully');
        this.reviews.push(created);
        this.newReview = {date: '', text: '', stars: 5};
      },
      error: err => {
        console.error(err);
        alert('Failed to add review');
      }
    });
  }
}
