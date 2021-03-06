import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private baseUrl = '//localhost:8080/api/v1/game';

  constructor(private http: HttpClient) { }

  getGame(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createGame(game: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, game);
  }

  updateGame(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteGame(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getGameList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
