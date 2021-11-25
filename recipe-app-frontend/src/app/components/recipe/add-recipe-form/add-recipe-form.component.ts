import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Ingredient, IngredientDTO } from './../../../models/ingredient.model';
import { RecipeService } from './../../../services/recipe.service';
import { EnumService } from './../../../services/enum.service';
import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/models/recipe.model';

@Component({
  selector: 'app-add-recipe-form',
  templateUrl: './add-recipe-form.component.html',
  styleUrls: ['./add-recipe-form.component.css']
})
export class AddRecipeFormComponent implements OnInit {

  dietList!: string[];
  cuisineList!: string[];
  measurementList!: string[];
  recipe!:Recipe;

  recipeForm!: FormGroup;
  name!: FormControl;
  ingredients! : FormArray;
  ingredient! : FormGroup;
  ingredientName!: FormControl;
  ingredientQuantity!: FormControl;
  ingredientMeasurement!: FormControl;
  method!: FormArray;
  prepTime!: FormControl;
  cookingTime!: FormControl;
  authorId!: FormControl;
  cuisine!: FormControl;
  diets!: FormArray;

  stepCount: number = 1;



  constructor(private enumService: EnumService,
    private recipeService: RecipeService) {
       this.name = new FormControl('', [Validators.required]);
       this.ingredients = new FormArray([], [Validators.required]);
       this.ingredientName = new FormControl('', [Validators.required]);
       this.ingredientQuantity = new FormControl('', [Validators.required]);
       this.ingredientMeasurement = new FormControl('');
       this.method = new FormArray([], [Validators.required]);
       this.prepTime = new FormControl('', [Validators.required, Validators.min(0)]);
       this.cookingTime = new FormControl('', [Validators.required, Validators.min(0)]);
       this.authorId = new FormControl('', [Validators.required]);
       this.cuisine = new FormControl('', [Validators.required]);
       this.diets = new FormArray([], [Validators.required]);
       this.recipeForm = new FormGroup({
         name: this.name,
         ingredients: this.ingredients,
         method: this.method,
         prepTime: this.prepTime,
         cookingTime: this.cookingTime,
         authorId: this.authorId,
         cuisine: this.cuisine,
         diets: this.diets
       })
       this.ingredient = new FormGroup({
         name: this.ingredientName,
         quantity: this.ingredientQuantity,
         measurement: this.ingredientMeasurement
       })
     }

  ngOnInit(): void {
    this.getDiets();
    this.getCuisines();
    this.getMeasurements();
  }

  addIngredient(): void {
    this.ingredients.push(this.ingredient)
  }

  removeIngredient(index: number):void {
    this.ingredients.removeAt(index);
  }

  addDiet(): void {
    this.diets.push(new FormControl(''));
  }

  removeDiet(index: number):void {
    this.diets.removeAt(index);
  }

  addStep(): void {
    this.method.push(new FormControl(''));
    this.stepCount++;
  }

  removeStep(index: number):void {
    this.method.removeAt(index);
  }

  getDiets(): void{
    this.enumService.getAllDiets().subscribe(diets => {
      this.dietList = diets;
    })
  }

  getCuisines(): void {
    this.enumService.getAllCuisines().subscribe(cuisines => {
      this.cuisineList = cuisines;
    })
  }

  getMeasurements():void {
    this.enumService.getAllMeasurements().subscribe(measurements => {
      this.measurementList = measurements;
    })
  }

}