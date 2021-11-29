import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  readonly baseUrl: string = "http://localhost:7000/api/v1/users"

  constructor(
    private http: HttpClient
  ) { }


  getAllUsers(): Observable<any> {
    return this.http.get<any>(this.baseUrl);
  }

  getUserById(id: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + "/" + id);
  }

}
