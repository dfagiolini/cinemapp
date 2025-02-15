import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Proiezione} from '../model/proiezione';

@Injectable({
  providedIn: 'root'
})
export class ProiezioneService {
  url = "http://localhost:8080"
  constructor(private http: HttpClient) { }

  getProiezioni(): Observable<Proiezione[]> {
    return this.http.get<Proiezione[]>(`${this.url}/proiezioni`);
  }

  getProiezioneById(id: number): Observable<Proiezione> {
    return this.http.get<Proiezione>(`${this.url}/proiezione/${id}`)
  }

  saveProiezione(proiezione: Proiezione): Observable<HttpResponse<void>> {
    return this.http.post<HttpResponse<void>>(`${this.url}/insertProiezione`,proiezione);
  }

  updateProiezione(proiezione: Proiezione): Observable<HttpResponse<void>> {
    return this.http.put<HttpResponse<void>>(`${this.url}/updateProiezione`,proiezione);
  }

  getDisponibilita(proiezioneId: number): Observable<number>{
    return this.http.get<number>(`${this.url}/getDisponibilita/${proiezioneId}`);
  }
}
