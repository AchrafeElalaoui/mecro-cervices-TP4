import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {NgForOf, NgIf} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-keynotes',
  standalone: true,
  imports: [
    NgForOf,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './keynote.component.html',
  styleUrl: './keynote.component.css'
})
export class KeynoteComponent implements OnInit {

  public keynotes: any[] = [];
  // 🔁 adapte le port si ton keynote-service tourne ailleurs
  private url: string = 'http://localhost:8081/api/keynotes';

  // ---- Update modal state ----
  showUpdateForm = false;
  selectedKeynote: any = null;
  updatedFunctionTitle: string = '';

  // ---- New keynote form model ----
  newKeynote: any = {
    lastName: '',
    firstName: '',
    email: '',
    functionTitle: ''
  };

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.loadKeynotes();
  }

  // Charger tous les keynotes
  loadKeynotes() {
    this.http.get(this.url).subscribe({
      next: (data: any) => {
        this.keynotes = data;
      },
      error: err => {
        console.error(err);
        alert('Failed to load keynotes');
      }
    });
  }

  // Ajouter un keynote (POST)
  addKeynote() {
    const body = {
      lastName: this.newKeynote.lastName,
      firstName: this.newKeynote.firstName,
      email: this.newKeynote.email,
      functionTitle: this.newKeynote.functionTitle
    };

    this.http.post(this.url, body)
      .subscribe({
        next: (created: any) => {
          alert('Keynote added successfully');
          this.keynotes.push(created);
          this.newKeynote = {lastName: '', firstName: '', email: '', functionTitle: ''};
        },
        error: err => {
          console.error(err);
          alert('Failed to add keynote');
        }
      });
  }

  // Ouvrir le formulaire d’update de la fonction
  openUpdateFunctionForm(keynote: any) {
    this.selectedKeynote = keynote;
    this.updatedFunctionTitle = keynote.functionTitle;
    this.showUpdateForm = true;
  }

  closeUpdateForm() {
    this.showUpdateForm = false;
    this.selectedKeynote = null;
    this.updatedFunctionTitle = '';
  }

  // Update functionTitle (PUT /api/keynotes/{id})
  updateKeynoteFunction(keynoteId: number) {
    // ⚠ ton backend attend un KeynoteRequestDTO complet
    const body = {
      lastName: this.selectedKeynote.lastName,
      firstName: this.selectedKeynote.firstName,
      email: this.selectedKeynote.email,
      functionTitle: this.updatedFunctionTitle
    };

    this.http.put(this.url + `/${keynoteId}`, body)
      .subscribe({
        next: (updated: any) => {
          alert('Function updated successfully');

          const index = this.keynotes.findIndex(k => k.id === keynoteId);
          if (index !== -1) {
            this.keynotes[index].functionTitle = updated.functionTitle;
          }

          this.closeUpdateForm();
        },
        error: err => {
          console.error(err);
          alert('Failed to update function');
        }
      });
  }

  // Delete keynote
  deleteKeynote(id: number) {
    if (!confirm('Are you sure you want to delete this keynote?')) {
      return;
    }

    this.http.delete(this.url + `/${id}`)
      .subscribe({
        next: () => {
          this.keynotes = this.keynotes.filter(k => k.id !== id);
          alert('Keynote deleted successfully!');
        },
        error: err => {
          console.error(err);
          alert('Failed to delete keynote');
        }
      });
  }
}
