import { FormsModule } from "@angular/forms";
import { Ingredient, IngredientDTO } from "./ingredient.model";

export class Recipe {

     constructor(
        private _name: string,
        private _ingredients: Ingredient[],
        private _method: string[],
        private _prepTime: number,
        private _cookingTime: number,
        private _authorId: number,
        private _cuisine: string,
        private _diets: string[],
    ){}


    public get cookingTime(): number {
        return this._cookingTime;
    }
    public set cookingTime(value: number) {
        this._cookingTime = value;
    }
    public get prepTime(): number {
        return this._prepTime;
    }
    public set prepTime(value: number) {
        this._prepTime = value;
    }
    public get diets(): string[] {
        return this._diets;
    }
    public set diets(value: string[]) {
        this._diets = value;
    }
    public get cuisine(): string {
        return this._cuisine;
    }
    public set cuisine(value: string) {
        this._cuisine = value;
    }
    public get authorId(): number {
        return this._authorId;
    }
    public set authorId(value: number) {
        this._authorId = value;
    }
    public get method(): string[] {
        return this._method;
    }
    public set method(value: string[]) {
        this._method = value;
    }
    public get ingredients(): Ingredient[] {
        return this._ingredients;
    }
    public set ingredients(value: Ingredient[]) {
        this._ingredients = value;
    }
    public get name(): string {
        return this._name;
    }
    public set name(value: string) {
        this._name = value;
    }

    public convertToDTO(): RecipeDTO {
        const recipeDTO: RecipeDTO = {
            name: this.name,
            ingredients: this.ingredients,
            method: this.method,
            prepTime: this.prepTime,
            cookingTime: this.cookingTime,
            authorId: this.authorId,
            cuisine: this.cuisine,
            diets: this.diets
        }
        return recipeDTO;
    }
}

export interface RecipeDTO {
  id?: number,
  name: string,
  ingredients : IngredientDTO[],
  method : string[],
  prepTime: number,
  cookingTime: number,
  authorId: number,
  cuisine :  string ,
  diets : string[],
  createdDate ?:  Date,
  editedDate ?:  Date 
}