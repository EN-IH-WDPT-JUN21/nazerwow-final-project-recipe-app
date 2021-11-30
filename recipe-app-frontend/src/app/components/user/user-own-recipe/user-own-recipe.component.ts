import { RecipeDTO } from './../../../models/recipe.model';
import { RecipeService } from './../../../services/recipe.service';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-own-recipe',
  templateUrl: './user-own-recipe.component.html',
  styleUrls: ['./user-own-recipe.component.css']
})
export class UserOwnRecipeComponent implements OnInit {

  @Input()
  userId!: number;

  recipeList!: RecipeDTO[];

  panelOpenState: boolean = false;

  constructor(private recipeService: RecipeService) {
   }

  ngOnInit(): void {
    this.getUsersOwnRecipes();
  }


  getUsersOwnRecipes(): void {
    this.recipeService.getRecipesByUserId(this.userId).subscribe(result => {
      this.recipeList = result;
    })
  }
}
