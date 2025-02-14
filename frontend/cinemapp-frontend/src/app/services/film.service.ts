import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
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

  postFilm(film: Film): Observable<HttpResponse<void>> {
    return this.http.post<HttpResponse<void>>(`${this.url}/insertFilm`, film);
  }

  deleteFilmById(id: number): Observable<HttpResponse<void>> {
    return this.http.delete<HttpResponse<void>>(`${this.url}/deleteFilm/${id}`);
  }

  updateFilm(film: Film):Observable<HttpResponse<void>> {
    return this.http.put<HttpResponse<void>>(`${this.url}/updateFilm`, film);
  }
}
