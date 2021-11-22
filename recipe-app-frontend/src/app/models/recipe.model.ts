import { FormsModule } from "@angular/forms";
import { Ingredient, IngredientDTO } from "./ingredient.model";

export class Recipe {

    constructor(
        private _id: number,
        private _name: String,
        private _ingredients: Ingredient[],
        private _method: string[],
        private _authorId: number,
        private _cuisine: string,
        private _diets: string[],
        private _createdDate: Date,
        private _editedDate: Date
    ){}

    public get editedDate(): Date {
        return this._editedDate;
    }
    public set editedDate(value: Date) {
        this._editedDate = value;
    }
    public get createdDate(): Date {
        return this._createdDate;
    }
    public set createdDate(value: Date) {
        this._createdDate = value;
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
    public get name(): String {
        return this._name;
    }
    public set name(value: String) {
        this._name = value;
    }
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

}

export interface RecipeDTO {
  id: number,
  name: string,
  ingredients : IngredientDTO[],
    method : string[],
   authorId : number,
   cuisine :  string ,
   diets : string[],
   createdDate :  Date,
   editedDate :  Date 
}