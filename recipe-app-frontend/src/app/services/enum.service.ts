import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EnumService {

  readonly baseUrl:string = "http://localhost:8100/api/v1/"

  constructor(private http: HttpClient) { }

  getAllCuisines(): Observable<any> {
    return this.http.get<any>(this.baseUrl + "cuisines");
  }

  getAllDiets(): Observable<any> {
    return this.http.get<any>(this.baseUrl + "diets");
  }

  getAllMeasurements(): Observable<any> {
    return this.http.get<any>(this.baseUrl + "measurements");
  }
  
}
