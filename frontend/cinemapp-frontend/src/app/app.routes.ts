import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';

export const routes: Routes = [
  { path: '', component: HomeComponent }, // Homepage caricata di default
  { path: 'home', component: HomeComponent } // Alternativa per /home
];
