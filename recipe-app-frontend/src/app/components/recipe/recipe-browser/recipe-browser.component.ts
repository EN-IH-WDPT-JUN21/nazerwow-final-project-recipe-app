import { RecipeInstructionsComponent } from './../recipe-page/recipe-instructions/recipe-instructions.component';
import { AddRecipeFormComponent } from './../add-recipe-form/add-recipe-form.component';
import { RecipeSearchComponent } from './../recipe-search/recipe-search.component';
import { RecipeService } from './../../../services/recipe.service';
import { RecipeDTO } from 'src/app/models/recipe.model';
import { EnumService } from './../../../services/enum.service';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatChip } from '@angular/material/chips';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-recipe-browser',
  templateUrl: './recipe-browser.component.html',
  styleUrls: ['./recipe-browser.component.css']
})
export class RecipeBrowserComponent implements OnInit {

  cuisineList!:string[];
  dietList!:string[];
  measurementsList!:string[];

  @Input()
  showList: boolean = true
  @Input()
  showAddButton: boolean = true

  recipeList!: RecipeDTO[];
  filterList: string[];
  filterActive: boolean = false;
  filteredRecipeList!: RecipeDTO[];

  @ViewChild(RecipeSearchComponent) recipeSearchComponent!: RecipeSearchComponent;

  constructor(private enumService:EnumService,
    private recipeService:RecipeService,
    private router:Router,
    private dialog:MatDialog) {
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
    } else {
      let index: number = this.filterList.indexOf(chip.value);
      this.filterList.splice(index, 1);
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
      this.populateFilteredList();
    })
  }

  public populateFilteredList():void {
    this.filteredRecipeList = this.recipeList;
  }

  private filterRecipeList(): void {
    this.filteredRecipeList = this.recipeList.filter((recipe => this.filterList.includes(recipe.cuisine) || recipe.diets.filter((diet => this.filterList.includes(diet))).length > 0));
    if(this.filterList.length == 0){
      this.filteredRecipeList = this.recipeList
    }
  }

  filterByName(recipeName: any){
    this.recipeSearchComponent.filteredRecipeList.subscribe(recipes => {
      this.filteredRecipeList = recipes
    })
  }

  reloadPage(): void {
    if(this.filterActive){
    window.location.reload();
  }
}

  reloadListAndFilter():void{
    this.populateFilteredList();
    this.filterRecipeList();
  }

  loadAddForm(): void {
    const dialogRef = this.dialog.open(AddRecipeFormComponent, { autoFocus: false, height: '80vh', width: '80vw' });
    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog result: %{result}');
    })
  }
  
}
