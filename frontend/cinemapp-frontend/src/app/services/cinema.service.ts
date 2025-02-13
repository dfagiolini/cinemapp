import { Injectable } from '@angular/core';
import { Cinema } from '../model/cinema';
import { HttpClient } from '@angular/common/http';
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

  postCinema(cinema: Cinema) {
    this.http.post<Cinema>(`${this.url}/cinema`, cinema);
  }


}

