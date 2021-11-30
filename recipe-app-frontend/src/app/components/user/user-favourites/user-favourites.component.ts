import { FavouritesService } from './../../../services/favourites.service';
import { Component, Input, OnInit } from '@angular/core';
import { RecipeDTO } from 'src/app/models/recipe.model';

@Component({
  selector: 'app-user-favourites',
  templateUrl: './user-favourites.component.html',
  styleUrls: ['./user-favourites.component.css']
})
export class UserFavouritesComponent implements OnInit {

  @Input()
  userId!: number;

  recipeList!: RecipeDTO[];

  panelOpenState: boolean = false;

  constructor(private favouritesService: FavouritesService) { }

  ngOnInit(): void {
    this.getFavouriteRecipes();
  }

  getFavouriteRecipes(): void {
    this.favouritesService.getFavouriteRecipesByUserId(this.userId).subscribe(result => {
      this.recipeList = result;
    },
    error => {
      console.log("User currently has no favourites");
    })
  }

}
