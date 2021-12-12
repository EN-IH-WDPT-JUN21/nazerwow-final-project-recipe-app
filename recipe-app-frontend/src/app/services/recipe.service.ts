import { RecipeDTO } from 'src/app/models/recipe.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  readonly baseUrl: string = "http://localhost:8080/api/v1/recipes"

  constructor(
    private http: HttpClient
  ) { }


  async getAllRecipes(): Promise<any> {
    return this.http.get<any>(this.baseUrl).toPromise();
  }

  async getRecipeById(id: number): Promise<any> {
    return this.http.get<any>(this.baseUrl + "/" + id).toPromise();
  }

  async getRecipesByUserId(userId:number): Promise<any> {
    return this.http.get<any>(this.baseUrl + "/user/" + userId).toPromise();
  }

  addRecipe(recipeDTO: RecipeDTO): Observable<any> {
    return this.http.post(`${this.baseUrl}/add`, recipeDTO);
  }

  deleteRecipe(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + "/" + id)
  }

  editRecipe(RecipeDTO: RecipeDTO): Observable<any> {
    return this.http.put(`${this.baseUrl + "/edit"}`, RecipeDTO)
  }

}
