export interface Keynote {
  id: number;
  lastName: string;
  firstName: string;
  email: string;
  functionTitle: string;
}

export type ConferenceType = 'ACADEMIQUE' | 'COMMERCIALE';

export interface Review {
  id: number;
  date: string;       // JSON date string
  text: string;
  stars: number;
}

export interface Conference {
  id: number;
  title: string;
  type: ConferenceType;
  keynoteId: number;
  date: string;                 // JSON date string
  duration: number;
  numberOfRegistrations: number;
  score: number;
  keynote?: Keynote | null;
  reviews?: Review[];
}
