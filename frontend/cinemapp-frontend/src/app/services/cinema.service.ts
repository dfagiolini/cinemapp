import { Injectable } from '@angular/core';
import { Cinema } from '../model/cinema';
import {HttpClient, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CinemaService {
  url = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getCinemas(): Observable<Cinema[]> {
    return this.http.get<Cinema[]>(this.url+"/cinema");
  }

  getCinemaById(id: number): Observable<Cinema> {
    return this.http.get<Cinema>(`${this.url}/cinema/${id}`);
  }

  postCinema(cinema: Cinema): Observable<HttpResponse<void>> {
   return this.http.post<HttpResponse<void>>(`${this.url}/insertCinema`, cinema);
  }

  putCinema(cinema: Cinema): Observable<HttpResponse<void>> {
    return this.http.put<HttpResponse<void>>(`${this.url}/updateCinema`, cinema);
  }

  deleteCinema(id: number): Observable<HttpResponse<void>> {
    return this.http.delete<HttpResponse<void>>(`${this.url}/deleteCinema/${id}`);
  }

}

