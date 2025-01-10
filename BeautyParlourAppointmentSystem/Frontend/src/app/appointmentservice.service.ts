import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AppointmentserviceService {
  
  private apiUrl = 'http://localhost:9000/api'; 

  constructor(private http: HttpClient) {}
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*'
    }),
    observe: 'response' as 'response'
  };
 
  getAppointmentDetails(): Observable<any> {
    
    return this.http.get<any>(this.apiUrl + '/getInfo',this.httpOptions);
  }

  
}
