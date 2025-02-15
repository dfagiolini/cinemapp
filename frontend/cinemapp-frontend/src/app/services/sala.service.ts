import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Sala} from '../model/sala';

@Injectable({
  providedIn: 'root'
})
export class SalaService {
  url = 'http://localhost:8080';


  constructor(private http: HttpClient) { }
  getSale(): Observable<Sala[]> {
    return this.http.get<Sala[]>(`${this.url}/sale`);
  }

  getSalaById(id: number): Observable<Sala> {
    return this.http.get<Sala>(`${this.url}/sala/{$id}`);
  }

  deleteSala(id: number): Observable<HttpResponse<void>> {
    return this.http.delete<HttpResponse<void>>(`${this.url}/deleteSala/{$id}`)
  }

  saveSala(sala: Sala): Observable<HttpResponse<void>> {
    return this.http.post<HttpResponse<void>>(`${this.url}/insertSala`, sala);
  }

  updateSala(sala: Sala): Observable<HttpResponse<void>> {
    return this.http.put<HttpResponse<void>>(`${this.url}/updateSala`, sala);
  }
}
