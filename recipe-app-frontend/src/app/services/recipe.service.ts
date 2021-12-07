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


  getAllRecipes(): Observable<any> {
    return this.http.get<any>(this.baseUrl);
  }

  getRecipeById(id: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + "/" + id);
  }

  getRecipesByUserId(userId:number): Observable<any> {
    return this.http.get<any>(this.baseUrl + "/user/" + userId);
  }

  addRecipe(recipeDTO: RecipeDTO): Observable<any> {
    return this.http.post(`${this.baseUrl}`, recipeDTO);
  }

  deleteRecipe(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + "/" + id)
  }

  editRecipe(RecipeDTO: RecipeDTO): Observable<any> {
    return this.http.put(`${this.baseUrl + "/" + RecipeDTO.id}`, RecipeDTO)
  }


  recipeList!:RecipeDTO[];
  returnAllRecipes(): RecipeDTO[] {
    this.getAllRecipes().subscribe(result => {
      this.recipeList = result;
    });
    return this.recipeList;    
  }

}
