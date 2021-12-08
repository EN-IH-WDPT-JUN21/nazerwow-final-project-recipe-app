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
  loading: boolean = true;

  recipeList!: RecipeDTO[];

  panelOpenState: boolean = false;

  constructor(private recipeService: RecipeService) {
   }

  ngOnInit(): void {
    this.getUsersOwnRecipes();
  }


  async getUsersOwnRecipes(): Promise<void> {
    this.recipeList = await this.recipeService.getRecipesByUserId(this.userId)
    // .subscribe(result => {
      // this.recipeList = result;
      this.loading = false;
    // },
    //  error => {
    //   console.log("User currently has not created any recipes");
    //   this.loading = false
    // })
  }
}
