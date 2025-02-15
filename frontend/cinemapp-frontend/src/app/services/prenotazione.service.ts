import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Prenotazione} from '../model/prenotazione';

@Injectable({
  providedIn: 'root'
})
export class PrenotazioneService {
  url = 'http://localhost:8080'
  constructor(private http: HttpClient) { }

  getPrenotazioni(): Observable<Prenotazione[]> {
    return this.http.get<Prenotazione[]>(`${this.url}/proiezioni`);
  }

  getPrenotazioneById(id: number): Observable<Prenotazione> {
    return this.http.get<Prenotazione>(`${this.url}/prenotazione/${id}`);
  }

  savePrenotazione(prenotazione: Prenotazione): Observable<HttpResponse<void>> {
    return this.http.post<HttpResponse<void>>(`${this.url}/insertPrenotazione`,prenotazione);
  }
  updatePrenotazione(prenotazione: Prenotazione): Observable<HttpResponse<void>> {
    return this.http.put<HttpResponse<void>>(`${this.url}/updatePrenotazione`, prenotazione);
  }

  deletePrenotazioneById(id: number): Observable<HttpResponse<void>> {
    return this.http.delete<HttpResponse<void>>(`${this.url}/deletePrenotazione/${id}`);
  }
}
