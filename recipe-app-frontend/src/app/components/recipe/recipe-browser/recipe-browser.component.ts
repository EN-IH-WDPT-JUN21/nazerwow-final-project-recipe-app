import { RecipeService } from './../../../services/recipe.service';
import { RecipeDTO } from 'src/app/models/recipe.model';
import { EnumService } from './../../../services/enum.service';
import { Component, OnInit } from '@angular/core';
import { MatChip } from '@angular/material/chips';
import { Router } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-recipe-browser',
  templateUrl: './recipe-browser.component.html',
  styleUrls: ['./recipe-browser.component.css']
})
export class RecipeBrowserComponent implements OnInit {

  cuisineList!:string[];
  dietList!:string[];
  measurementsList!:string[];

  recipeList!: RecipeDTO[];
  filterList: string[];
  filterActive: boolean = false;
  filteredRecipeList!: RecipeDTO[];

  constructor(private enumService:EnumService,
    private recipeService:RecipeService,
    private router:Router) {
    this.filterList = [],
    this.recipeList = []
   }

  ngOnInit(): void {
    this.getAllDiets();
    this.getAllMeasurements();
    this.getAllCuisines();
    this.getAllRecipes();
  }

  getAllCuisines():void{
    this.enumService.getAllCuisines().subscribe(result => {
      this.cuisineList = result
    })
  }

  getAllDiets():void{
    this.enumService.getAllDiets().subscribe(result => {
      this.dietList = result
    })
  }

  getAllMeasurements():void{
    this.enumService.getAllMeasurements().subscribe(result => {
      this.measurementsList = result
    })
  }

  toggleSelection(chip: MatChip) {
   chip.toggleSelected();
   this.alterFilterList(chip);
   this.toggleFilterActive();
}

  private alterFilterList(chip: MatChip) {
    if (chip.selected) {
      this.filterList.push(chip.value);
      this.filterRecipeList();
      console.log("here")
    } else {
      let index: number = this.filterList.indexOf(chip.value);
      console.log(index);
      this.filterList.splice(index, 1);
      console.log(this.filterList)
      this.filterRecipeList();
    }
  }

  private toggleFilterActive():void {
    if(this.filterList.length > 0){
      this.filterActive = true;
    } else {
      this.filterActive = false;
    }
  }

  getAllRecipes():void{
    this.recipeService.getAllRecipes().subscribe(result => {
      this.recipeList = result;
      this.filteredRecipeList = this.recipeList;
    })
  }

  private filterRecipeList(): void {
    this.filteredRecipeList = this.recipeList.filter((recipe => this.filterList.includes(recipe.cuisine) || recipe.diets.filter((diet => this.filterList.includes(diet))).length > 0));
    if(this.filterList.length == 0){
      this.filteredRecipeList = this.recipeList
    }
  }

  filterByName(recipeName: any){
    this.filteredRecipeList = this.recipeList.filter((recipe => recipe.name.toLowerCase().includes(recipeName)));
    if(recipeName == ''){
      this.filteredRecipeList = this.recipeList;
    }
  }

  reloadPage(): void {
    if(this.filterActive){
    window.location.reload();
  }
}
  
}
