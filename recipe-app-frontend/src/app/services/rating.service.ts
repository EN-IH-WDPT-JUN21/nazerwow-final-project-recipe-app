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

  async getAverageRatingForRecipe(recipeId: any): Promise<any> {
    return this.http.get<any>(this.baseUrl +"/recipe/" + recipeId).toPromise();
  }

  async getTop10Recipes(): Promise<any> {
    return this.http.get<any>(this.baseUrl + "/top10recipes").toPromise();
  }

  async getTop10RecipesForUser(userId: number): Promise<any> {
    return this.http.get<any>(this.baseUrl + "/top10recipes/" + userId).toPromise();
  }

  rateRecipe(ratingDTO: RatingDTO): Observable<any> {
    return this.http.put(`${this.baseUrl}/raterecipe`, ratingDTO)
  }

  getUsersRating(ratingDTO: RatingDTO): Observable<any> {
    return this.http.put(`${this.baseUrl}/usersrating`, ratingDTO)
  }
}
