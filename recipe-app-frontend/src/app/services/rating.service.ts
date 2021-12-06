import { RatingDTO } from './../models/rating-model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  readonly baseUrl: string = "http://localhost:8080/api/v1/ratings"

  constructor(
    private http: HttpClient
  ) { }

  getByUserId(userId: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + "/" + userId);
  }

  checkIfPreviouslyRated(recipeId: number, userId:number): Observable<any> {
    return this.http.get<any>(this.baseUrl + "/" + recipeId + "/" + userId)
  }

  getAverageRatingForRecipe(recipeId: any): Observable<any> {
    return this.http.get<any>(this.baseUrl +"/recipe/" + recipeId)
  }

  getTop10Recipes(): Observable<any> {
    return this.http.get<any>(this.baseUrl + "/top10recipes")
  }

  getTop10RecipesForUser(userId: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + "/top10recipes/" + userId)
  }

  rateRecipe(ratingDTO: RatingDTO): Observable<any> {
    return this.http.put(`${this.baseUrl}`, ratingDTO)
  }

  

}
