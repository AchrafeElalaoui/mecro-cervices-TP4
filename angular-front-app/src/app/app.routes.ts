import { Routes } from '@angular/router';
import {KeynoteComponent} from './keynote/keynote.component';
import {ConferenceReviewsComponent} from './conference-reviews/conference-reviews.component';
import {ConferencesComponent} from './conferences/conferences.component';

export const routes: Routes = [
  {path :"keynotes",component: KeynoteComponent},
  { path: '', redirectTo: '/keynotes', pathMatch: 'full' },
  { path: 'conferences/:id/reviews', component: ConferenceReviewsComponent },
  { path: 'conferences', component: ConferencesComponent }
];
