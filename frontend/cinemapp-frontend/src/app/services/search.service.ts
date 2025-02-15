import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SearchModel} from '../model/search-model';
import {Observable} from 'rxjs';
import {SearchResultModel} from '../model/search-result-model';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  url = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  search(searchModel: SearchModel): Observable<SearchResultModel[]> {
    return this.http.post<SearchResultModel[]>(`${this.url}/search`,searchModel);
  }
}
