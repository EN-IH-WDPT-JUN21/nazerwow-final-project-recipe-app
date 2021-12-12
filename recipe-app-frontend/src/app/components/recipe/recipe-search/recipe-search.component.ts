import { EnumPipe } from './../../../custom-pipes/enum-pipe';
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
  @Input()
  external: boolean = false;

  loading:boolean = true;
  
  filteredRecipeList!: Observable<RecipeDTO[]>;

  recipeSearch: FormControl;
  recipeForm: FormGroup;

  enumPipe : EnumPipe = new EnumPipe();

  recipeId!: number;

  @Output() recipeNameOutput: EventEmitter<string> = new EventEmitter();
  @Output() clickedRecipeNameOutput: EventEmitter<string> = new EventEmitter();
  @Output() deleteOutput: EventEmitter<string> = new EventEmitter();
  @Output() recipeIdOutput: EventEmitter<string> = new EventEmitter();

  constructor(private recipeService:RecipeService) {
    this.recipeSearch = new FormControl('');
    this.recipeForm = new FormGroup({
      recipeSearch: this.recipeSearch
    })
   }

  async ngOnInit(): Promise<void> {
    await this.getAllRecipes();
    this.filteredRecipeList = this.recipeForm.valueChanges.pipe(
      startWith(''),
      map(value => this._filter()),
    )
    this.loading = false;
  }

 
  async getAllRecipes():Promise<void>{
    if(this.recipeList == null)
    this.recipeList = await this.recipeService.getAllRecipes();
      this.filteredRecipeList = this.recipeForm.valueChanges.pipe(
      startWith(''),
      map(value => this._filter())
    )
  }

  private _filter(): RecipeDTO[] {
    const filterValue:string = this.recipeSearch.value.toLowerCase().trim();
    return this.recipeList
    .filter(recipe => recipe.name.toLowerCase().includes(filterValue) || this.enumPipe.transform(recipe.cuisine).toLowerCase().includes(filterValue) || recipe.diets.filter((diet => this.enumPipe.transform(diet).toLowerCase().includes(filterValue))).length > 0);
  }

  sendRecipeName(): void {
    this.recipeNameOutput.emit(this.recipeSearch.value.toLowerCase().trim());
  }

  sendClickedRecipeName(): void {
    this.clickedRecipeNameOutput.emit(this.recipeSearch.value.toLowerCase().trim());
  }

  delete():void {
    this.deleteOutput.emit(this.recipeSearch.value.toLowerCase().trim());
  }

  sendRecipeId(): void {
    console.log(this.recipeSearch.value.id)
    this.recipeIdOutput.emit(this.recipeSearch.value.id)
  }

  displayFn(recipe: RecipeDTO): string {
    return recipe && recipe.name ? recipe.name : '';
  }

}
