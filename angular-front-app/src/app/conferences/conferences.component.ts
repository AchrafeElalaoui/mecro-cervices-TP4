import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {DatePipe, NgForOf, NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {ConferenceReviewsComponent} from '../conference-reviews/conference-reviews.component';
import {Conference, ConferenceType} from './conference.model';

@Component({
  selector: 'app-conferences',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    HttpClientModule,
    FormsModule,
    ConferenceReviewsComponent,
    DatePipe
  ],
  templateUrl: './conferences.component.html',
  styleUrl: './conferences.component.css'
})
export class ConferencesComponent implements OnInit {

  conferences: Conference[] = [];

  // adapte le port / chemin à ton conference-service
  private baseUrl = 'http://localhost:8082/api/conferences';

  // Form new conference
  newConference: any = {
    title: '',
    type: 'ACADEMIQUE' as ConferenceType,
    keynoteId: null,
    date: '',
    duration: 60,
    numberOfRegistrations: 0,
    score: 0
  };

  // selected conference for detail + reviews
  selectedConference: Conference | null = null;

  conferenceTypes: ConferenceType[] = ['ACADEMIQUE', 'COMMERCIALE'];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadConferences();
  }

  loadConferences() {
    this.http.get<Conference[]>(this.baseUrl).subscribe({
      next: data => {
        this.conferences = data;
      },
      error: err => {
        console.error(err);
        alert('Failed to load conferences');
      }
    });
  }

  addConference() {
    const body = {
      title: this.newConference.title,
      type: this.newConference.type,
      keynoteId: this.newConference.keynoteId,
      // backend = java.util.Date, JSON string OK
      date: new Date(this.newConference.date).toISOString(),
      duration: this.newConference.duration,
      numberOfRegistrations: this.newConference.numberOfRegistrations,
      score: this.newConference.score
    };

    this.http.post<Conference>(this.baseUrl, body).subscribe({
      next: created => {
        alert('Conference added successfully');
        this.conferences.push(created);
        // reset form
        this.newConference = {
          title: '',
          type: 'ACADEMIQUE' as ConferenceType,
          keynoteId: null,
          date: '',
          duration: 60,
          numberOfRegistrations: 0,
          score: 0
        };
      },
      error: err => {
        console.error(err);
        alert('Failed to add conference');
      }
    });
  }

  deleteConference(id: number) {
    if (!confirm('Are you sure you want to delete this conference?')) {
      return;
    }
    this.http.delete(this.baseUrl + `/${id}`).subscribe({
      next: () => {
        this.conferences = this.conferences.filter(c => c.id !== id);
        if (this.selectedConference?.id === id) {
          this.selectedConference = null;
        }
        alert('Conference deleted successfully');
      },
      error: err => {
        console.error(err);
        alert('Failed to delete conference');
      }
    });
  }

  selectConference(conference: Conference) {
    this.selectedConference = conference;
  }
}

