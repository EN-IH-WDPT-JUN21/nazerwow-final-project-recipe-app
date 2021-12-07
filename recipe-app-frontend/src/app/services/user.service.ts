import { UserDTO } from './../models/user-model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  readonly baseUrl: string = "http://localhost:8080/api/v1/users"

  constructor(
    private http: HttpClient
  ) { }


  getAllUsers(): Observable<any> {
    return this.http.get<any>(this.baseUrl);
  }

  getUserById(id: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + "/" + id);
  }

  addUser(userDTO: UserDTO): Observable<any> {
    return this.http.post(`${this.baseUrl}`, userDTO);
  }

  editUser(UserDTO: UserDTO): Observable<any> {
    return this.http.put(`${this.baseUrl + "/" + UserDTO.id}`, UserDTO)
  }

  userVerified(userId: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + "/" + userId + "/verify")
  }

  loadProfilePage(): Observable<any> {
    return this.http.get<any>(this.baseUrl + "/profile")
  }

}
