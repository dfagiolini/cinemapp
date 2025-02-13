import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Film} from '../model/film';

@Injectable({
  providedIn: 'root'
})
export class FilmService {
  url = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getFilms(): Observable<Film[]> {
    return this.http.get<Film[]>(`${this.url}/film`);
  }

  getFilmById(id: number): Observable<Film> {
    return this.http.get<Film>(`${this.url}/film/${id}`);
  }

  postFilm(film: Film) {
    this.http.post<Film>(`${this.url}/film`, film);
  }

  deleteFilmById(id: number) {
    this.http.delete<Film>(`${this.url}/film/${id}`);
  }

  updateFilm(film: Film) {
    this.http.put<Film>(`${this.url}/film`, film);
  }
}
