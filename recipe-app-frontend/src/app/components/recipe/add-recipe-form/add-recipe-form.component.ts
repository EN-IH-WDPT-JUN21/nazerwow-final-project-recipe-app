import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import { OktaAuth } from '@okta/okta-auth-js';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AbstractControl, Form, FormArray, FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Ingredient, IngredientDTO } from './../../../models/ingredient.model';
import { RecipeService } from './../../../services/recipe.service';
import { EnumService } from './../../../services/enum.service';
import { Component, Inject, Input, OnInit } from '@angular/core';
import { Recipe, RecipeDTO } from 'src/app/models/recipe.model';
import { UserDTO } from 'src/app/models/user-model';

@Component({
  selector: 'app-add-recipe-form',
  templateUrl: './add-recipe-form.component.html',
  styleUrls: ['./add-recipe-form.component.css']
})
export class AddRecipeFormComponent implements OnInit {

  user!:UserDTO;
  title: string = "Add a new recipe"
  dietList!: string[];
  cuisineList!: string[];
  measurementList!: string[];
  authorsId!: number;

  addForm: boolean = true;
  addFormButton: string = "Add";
  formDisabled: boolean = false;

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
  imageUrl!: FormControl;

  stepCount: number = 1;



  constructor(private enumService: EnumService,
    private recipeService: RecipeService,
    private formBuilder: FormBuilder,
    private oktaAuth: OktaAuth,
    private userService: UserService,
    @Inject(MAT_DIALOG_DATA) public data: RecipeDTO,
    private snackBar:MatSnackBar
    ) {
       this.name = new FormControl('', [Validators.required]);
       this.ingredients = new FormArray([], [Validators.required]);
       this.ingredientName = new FormControl('', [Validators.required]);
       this.ingredientQuantity = new FormControl('', [Validators.required]);
       this.ingredientMeasurement = new FormControl('');
       this.method = new FormArray([], [Validators.required]);
       this.prepTime = new FormControl('', [Validators.required, Validators.min(0)]);
       this.cookingTime = new FormControl('', [Validators.required, Validators.min(0)]);
       this.cuisine = new FormControl('', [Validators.required]);
       this.imageUrl = new FormControl('', [Validators.required]);
       this.diets = new FormArray([]);
       
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
         diets: this.diets,
         imageUrl: this.imageUrl
       })
     }

  ngOnInit(): void {
    this.getDiets();
    this.getCuisines();
    this.getMeasurements();
    this.recipeDTO = this.data;
    this.getUserByEmail();
  }

  ngAfterViewInit(): void {
    this.addOrEdit();
  }

  onSubmit(){
    if(this.addForm){
      this.addNewRecipeToDatabase();
    } else {
      this.editRecipeInDatabase();
    }
  }

  // Method to disable form after submission

  disableForm(): void {
    this.formDisabled = true;
    this.recipeForm.disable();
    this.ingredient.disable();
  }


  // Methods To set up Form Arrays and Their Controls 
  addIngredient(): void {
    this.ingredients.push(new FormGroup({ name: new FormControl(''), quantity: new FormControl(''), measurement: new FormControl('')} ))
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

  // Logic for when form is loaded to display either Edit or Add information 

  addOrEdit(): void {
    if(this.recipeDTO.id != null){
      this.addForm = false;
      this.addFormButton = "Confirm Edited"
      this.title = "Edit your recipe"
      this.fillFormToEdit();
    } else {
      this.addForm = true;
      this.addFormButton = "Add"
      this.title = "Enter your new recipe details...";
    }
    console.log(this.addForm)
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

    // Methods To load data for cuisine list, diet list and measurements 


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

  // Methods to sent Post / Put requests to back end to either Add new Recipe or Edit existing 

  private async addNewRecipeToDatabase() {
    const newRecipe: RecipeDTO = this.createRecipeDTOFromForm();
    this.recipeService.addRecipe(newRecipe).subscribe(result => {
      console.log(result)
      this.disableForm();
      this.snackBar.open("Success: Recipe Added")
    } ,
    error => {
      this.snackBar.open("Failed to update details, please try again")
    });
  }

  private editRecipeInDatabase() {
    this.authorsId = this.recipeDTO.authorId;  
    const editedRecipe: RecipeDTO = this.createRecipeDTOFromForm();
    this.recipeService.editRecipe(editedRecipe).subscribe(result => {
      console.log(result)
      this.disableForm();
            this.snackBar.open("Success: Recipe Edited")
    },
    error => {
      this.snackBar.open("Failed to update details, please try again")
    });
  }

  private createRecipeDTOFromForm() {
    let id: any;
    if(this.recipeDTO == null){
      id = 0;
    } else {
      id = this.recipeDTO.id
    }
    return {
      id: id,
      name: this.name.value,
      ingredients : this.ingredients.value,
      method : this.method.value,
      prepTime: this.prepTime.value,
      cookingTime: this.cookingTime.value,
      authorId: this.authorsId,
      cuisine :  this.cuisine.value ,
      diets : this.diets.value,
      imageUrl: this.imageUrl.value
    };
  }

  // Gets logged in users details

  async getLoggedInEmail(): Promise<string> {
    let loggedInEmail: string = String((await this.oktaAuth.getUser()).preferred_username);
    return loggedInEmail;
  }

  async getUserByEmail(): Promise<void> {
    this.userService.getUserByEmail(await this.getLoggedInEmail()).subscribe(result => {
      this.user = result;
      this.authorsId = this.user.id;
    })
  }

  

}
