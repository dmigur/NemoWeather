import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class WeatherService {

  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }


  getWeatherList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/weather`);
  }

  getWeatherNow(): Observable<any> {
    return this.http.get(`${this.baseUrl}/weather/now`);
  }

}