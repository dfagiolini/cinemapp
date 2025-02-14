import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import {CinemaListComponent} from './features/cinema-list/cinema-list.component';
import {FilmListComponent} from './features/film-list/film-list.component';
import {ProiezioneListComponent} from './features/proiezione-list/proiezione-list.component';
import {BookingComponent} from './features/booking/booking.component';
import {InsertFilmComponent} from './features/insert-film/insert-film.component';

export const routes: Routes = [
  { path: '', component: HomeComponent }, // Homepage caricata di default
  { path: 'home', component: HomeComponent },// Alternativa per /home
  { path: '', component: HomeComponent, pathMatch: 'full' },
  { path: 'cinema', component: CinemaListComponent },
  { path: 'film', component: FilmListComponent },
  { path: 'proiezioni', component: ProiezioneListComponent },
  { path: 'booking', component: BookingComponent },
  { path: 'insertTest', component: InsertFilmComponent}
];
