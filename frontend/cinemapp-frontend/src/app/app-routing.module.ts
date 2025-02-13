import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { CinemaListComponent } from './features/cinema-list/cinema-list.component';
import { FilmListComponent } from './features/film-list/film-list.component';
import { ProiezioneListComponent } from './features/proiezione-list/proiezione-list.component';
import { BookingComponent } from './features/booking/booking.component';

const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full' },
  { path: 'cinema', component: CinemaListComponent },
  { path: 'film', component: FilmListComponent },
  { path: 'proiezioni', component: ProiezioneListComponent },
  { path: 'booking', component: BookingComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
