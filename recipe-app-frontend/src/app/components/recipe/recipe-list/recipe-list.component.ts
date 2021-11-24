import { RecipeService } from './../../../services/recipe.service';
import { RecipeDTO } from './../../../models/recipe.model';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  @Input()
  recipeList!:RecipeDTO[];

  @Input()
  filter!:string;

  constructor(private recipeService: RecipeService) { }

  ngOnInit(): void {
    this.getAllRecipes();
  }

  getAllRecipes():void{
    if(this.recipeList == null)
    this.recipeService.getAllRecipes().subscribe(result => {
      this.recipeList = result;
    })
  }


}
