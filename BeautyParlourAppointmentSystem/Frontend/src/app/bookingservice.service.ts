import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookingserviceService {
  private apiUrl = 'http://localhost:9000/api';
  constructor(private http: HttpClient) { }
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*'
    }),
    observe: 'response' as 'response'
  };
  getbookingDetails(): Observable<any> {
  
    return this.http.get<any>(this.apiUrl + '/getdata',this.httpOptions);
  }
}
