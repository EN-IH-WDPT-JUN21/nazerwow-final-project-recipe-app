import { FavouriteDTO } from './../models/favourites-model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

type NewType = Observable<any>;

@Injectable({
  providedIn: 'root'
})
export class FavouritesService {

  readonly baseUrl: string = "http://localhost:7000/api/v1/favourites"

  constructor(
    private http: HttpClient
  ) { }


  getFavouriteRecipesByUserId(userId: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + "/userid/" + userId);
  }

  addToFavourites(favouriteDTO: FavouriteDTO): Observable<any> {
    return this.http.post(`${this.baseUrl}`, favouriteDTO);
  }

  removeFromFavourites(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + "/" + id)
  }


}
