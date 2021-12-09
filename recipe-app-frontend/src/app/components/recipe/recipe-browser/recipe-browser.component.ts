import { EnumPipe } from './../../../custom-pipes/enum-pipe';
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
import { OktaAuthStateService } from '@okta/okta-angular';

@Component({
  selector: 'app-recipe-browser',
  templateUrl: './recipe-browser.component.html',
  styleUrls: ['./recipe-browser.component.css']
})
export class RecipeBrowserComponent implements OnInit {

  cuisineList!:string[];
  dietList!:string[];
  measurementsList!:string[];
  searchValue!: string;
  recipeFormTitle: string = "Add a new recipe"
  loading: boolean = true;
  enumPipe: EnumPipe = new EnumPipe();

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
    private dialog:MatDialog,
    public authService: OktaAuthStateService) {
    this.filterList = [],
    this.recipeList = []
   }

  async ngOnInit(): Promise<void> {
    await this.loadData();

  }

  // Get enums for the chips and form

  async loadData():Promise<void> {
    this.getAllDiets();
    this.getAllMeasurements();
    this.getAllCuisines();
    await this.getAllRecipes();
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

  // Populates the recipe lists
  async getAllRecipes():Promise<void>{
    this.recipeService.getAllRecipes().subscribe(result => {
      this.recipeList = result;
      this.loading = false;
      this.populateFilteredList();
    })
  }
  
  public populateFilteredList():void {
    this.filteredRecipeList = this.recipeList;
  }

  // Chip handling

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

  // Filter using chips & search bar is not null 

  private filterRecipeList(): void {
    if(this.filterList.length == 0){
      this.filteredRecipeList = this.recipeList
    }else {
      this.filteredRecipeList = this.recipeList.filter((recipe => this.filterList.includes(recipe.cuisine) || recipe.diets.filter((diet => this.filterList.includes(diet))).length > 0));
    }
    if(this.searchValue != null){
      this.filterByText(this.searchValue)
    }
  }

  // Filter using the search bar 
  filterByText(recipeName: any){
    this.searchValue = recipeName;
    const filterValue:string = recipeName
    this.filteredRecipeList = this.filteredRecipeList
    .filter(recipe => recipe.name.toLowerCase().includes(filterValue) ||  this.enumPipe.transform(recipe.cuisine).toLowerCase().includes(filterValue) || recipe.diets.filter((diet => this.enumPipe.transform(diet).toLowerCase().includes(filterValue))).length > 0);
  }

  // When user deletes text in the search bar this updates to ensure search is correct
  updateSearchValue(recipeName: any){
    this.searchValue = recipeName;
    this.filterRecipeList()
    if(this.filterList.length == 0 && this.searchValue == ""){
      this.filteredRecipeList = this.recipeList;
    }
  }

  // Reloads page when all is clicked 
  reloadPage(): void {
    if(this.filterActive || this.searchValue != null){
    window.location.reload();
  }
}

  // Loads the add recipe form! 
  loadAddForm(): void {
    const dialogRef = this.dialog.open(AddRecipeFormComponent, { autoFocus: false, maxHeight: '80vh', maxWidth: '80vw'});
    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog result: %{result}');
    })
  }
  
}
