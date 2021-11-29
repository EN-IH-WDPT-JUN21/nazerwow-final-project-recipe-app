import { AbstractControl, Form, FormArray, FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Ingredient, IngredientDTO } from './../../../models/ingredient.model';
import { RecipeService } from './../../../services/recipe.service';
import { EnumService } from './../../../services/enum.service';
import { Component, Input, OnInit } from '@angular/core';
import { Recipe, RecipeDTO } from 'src/app/models/recipe.model';

@Component({
  selector: 'app-add-recipe-form',
  templateUrl: './add-recipe-form.component.html',
  styleUrls: ['./add-recipe-form.component.css']
})
export class AddRecipeFormComponent implements OnInit {

  dietList!: string[];
  cuisineList!: string[];
  measurementList!: string[];

  addForm: boolean = true;

  @Input()
  recipeDTO!: RecipeDTO;

  recipe!:Recipe;

  recipeForm!: FormGroup;
  name!: FormControl;
  ingredients! : FormArray;
  ingredient! : FormGroup;
  ingredientName!: FormControl;
  ingredientQuantity!: FormControl;
  ingredientMeasurement!: FormControl;
  method!: FormArray
  prepTime!: FormControl;
  cookingTime!: FormControl;
  authorId!: FormControl;
  cuisine!: FormControl;
  diets!: FormArray;

  stepCount: number = 1;



  constructor(private enumService: EnumService,
    private recipeService: RecipeService,
    private formBuilder: FormBuilder) {
       this.name = new FormControl('', [Validators.required]);
       this.ingredients = new FormArray([], [Validators.required]);
       this.ingredientName = new FormControl('', [Validators.required]);
       this.ingredientQuantity = new FormControl('', [Validators.required]);
       this.ingredientMeasurement = new FormControl('');
       this.method = new FormArray([], [Validators.required]);
       this.prepTime = new FormControl('', [Validators.required, Validators.min(0)]);
       this.cookingTime = new FormControl('', [Validators.required, Validators.min(0)]);
       this.cuisine = new FormControl('', [Validators.required]);
       this.diets = new FormArray([], [Validators.required]);
       
       this.ingredient = new FormGroup({
         name: this.ingredientName,
         quantity: this.ingredientQuantity,
         measurement: this.ingredientMeasurement
       })

       this.recipeForm = new FormGroup({
         name: this.name,
         ingredients: this.ingredients,
         method: this.method,
         prepTime: this.prepTime,
         cookingTime: this.cookingTime,
         cuisine: this.cuisine,
         diets: this.diets
       })
     }

  ngOnInit(): void {
    this.getDiets();
    this.getCuisines();
    this.getMeasurements();
  }

  ngAfterViewInit(): void {
    this.addOrEdit();
  }

  onSubmit(){
    // console.log(this.recipeDTO);
    // this.addNewRecipeToDatabase(this.createRecipeDTOFromForm());
    this.fillFormToEdit();
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

  addOrEdit(): void {
    if(this.recipeDTO != null){
      this.addForm = false;
      this.fillFormToEdit();
      console.log("did we get here")
    } else {
      this.addForm = true;
    }
  }

  fillFormToEdit():void {

    for(let diet in this.recipeDTO.diets){
      this.addDiet()
    }
    for(let step in this.recipeDTO.method){
      this.addStep();
    }
    this.recipeForm.patchValue(this.recipeDTO);
    this.patchFromArray();
  }

  patchFromArray(): void {
      let ctrl = <FormArray>this.recipeForm.controls.ingredients;
      this.recipeDTO.ingredients.forEach(ingredient => {
        ctrl.push( this.formBuilder.group({
          name: ingredient.name,
          quantity: ingredient.quantity,
          measurement: ingredient.measurement
        }))
      })
    }


  private getDiets(): void{
    this.enumService.getAllDiets().subscribe(diets => {
      this.dietList = diets;
    })
  }

  private getCuisines(): void {
    this.enumService.getAllCuisines().subscribe(cuisines => {
      this.cuisineList = cuisines;
    })
  }

  private getMeasurements():void {
    this.enumService.getAllMeasurements().subscribe(measurements => {
      this.measurementList = measurements;
    })
  }

  private addNewRecipeToDatabase(recipeDTO: RecipeDTO) {
    this.recipeService.addRecipe(recipeDTO).subscribe(result => {
      console.log(result);
    });
  }

  private createRecipeDTOFromForm() {
    this.recipe = new Recipe(
      this.name.value,
      this.ingredients.value,
      this.method.value,
      this.prepTime.value,
      this.cookingTime.value,
      1,
      this.cuisine.value,
      this.diets.value
    );
    const recipeDTO = this.recipe.convertToDTO();
    return recipeDTO;
  }

}
