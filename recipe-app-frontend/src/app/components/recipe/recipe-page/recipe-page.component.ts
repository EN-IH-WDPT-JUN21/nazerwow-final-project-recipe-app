import { RecipeService } from './../../../services/recipe.service';
import { RecipeDTO } from '../../../models/recipe.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-recipe-page',
  templateUrl: './recipe-page.component.html',
  styleUrls: ['./recipe-page.component.css']
})
export class RecipePageComponent implements OnInit {

  recipeDTO!:RecipeDTO;

  constructor(private recipeService:RecipeService) { }

  ngOnInit(): void {
    this.getRecipeTest();
  }

  getRecipeTest():void{
    this.recipeService.getRecipeById(1).subscribe(result => {
      this.recipeDTO = result
    })
  }

}
