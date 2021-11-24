import { RecipeService } from './../../../services/recipe.service';
import { RecipeDTO } from '../../../models/recipe.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-recipe-page',
  templateUrl: './recipe-page.component.html',
  styleUrls: ['./recipe-page.component.css']
})
export class RecipePageComponent implements OnInit {

  recipeDTO!:RecipeDTO;

  constructor(private recipeService:RecipeService,
    private activateRoute:ActivatedRoute) { }

  ngOnInit(): void {
    const recipeId:number = this.activateRoute.snapshot.params['recipeId'];
    this.getRecipe(recipeId);
  }

  getRecipe(id:number):void{
    this.recipeService.getRecipeById(id).subscribe(result => {
      this.recipeDTO = result
    })
  }

}
