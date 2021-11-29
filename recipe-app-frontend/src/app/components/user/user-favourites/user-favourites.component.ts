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

  favouriteRecipesList!: RecipeDTO[];

  panelOpenState: boolean = false;

  constructor(private favouritesService: FavouritesService) { }

  ngOnInit(): void {
  }

  getFavouriteRecipes(): void {
    this.favouritesService.getFavouriteRecipesByUserId(this.userId).subscribe(result => {
      this.favouriteRecipesList = result;
    })
  }

}
