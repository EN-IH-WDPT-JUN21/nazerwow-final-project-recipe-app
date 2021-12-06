import { RecipeService } from '../../../services/recipe.service';
import { RecipeDTO } from 'src/app/models/recipe.model';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';

@Component({
  selector: 'app-recipe-search',
  templateUrl: './recipe-search.component.html',
  styleUrls: ['./recipe-search.component.css']
})
export class RecipeSearchComponent implements OnInit {

  @Input()
  recipeList!:RecipeDTO[];
  
  filteredRecipeList!: Observable<RecipeDTO[]>;

  recipeSearch: FormControl;
  recipeForm: FormGroup;

  recipeId!: number;

  @Output() recipeNameOutput: EventEmitter<string> = new EventEmitter();
  @Output() deleteOutput: EventEmitter<string> = new EventEmitter();

  constructor(private recipeService:RecipeService) {
    this.recipeSearch = new FormControl('');
    this.recipeForm = new FormGroup({
      recipeSearch: this.recipeSearch
    })
   }

  ngOnInit(): void {
    this.getAllRecipes();
    this.filteredRecipeList = this.recipeForm.valueChanges.pipe(
      startWith(''),
      map(value => this._filter()),
    )
  }

 
  getAllRecipes():void{
    if(this.recipeList == null)
    this.recipeService.getAllRecipes().subscribe(result => {
      this.recipeList = result;
      this.filteredRecipeList = this.recipeForm.valueChanges.pipe(
      startWith(''),
      map(value => this._filter()),
    )
    })
  }

  private _filter(): RecipeDTO[] {
    const filterValue:string = this.recipeSearch.value.toLowerCase().trim();
    console.log(filterValue)
    return this.recipeList
    .filter(recipe => recipe.name.toLowerCase().includes(filterValue) || recipe.cuisine.toLowerCase().includes(filterValue) || recipe.diets.filter((diet => diet.toLowerCase().includes(filterValue))).length > 0);
  }

  sendRecipeName(): void {
    this.recipeNameOutput.emit(this.recipeSearch.value.toLowerCase().trim());
  }

  delete():void {
    this.deleteOutput.emit(this.recipeSearch.value.toLowerCase().trim());
  }

}
