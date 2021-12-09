import { OktaAuth } from '@okta/okta-auth-js';
import { RatingService } from './../../../services/rating.service';
import { Component, Input, OnInit } from '@angular/core';
import { FavouritesService } from 'src/app/services/favourites.service';
import { UserService } from 'src/app/services/user.service';
import { RecipeDTO } from 'src/app/models/recipe.model';

@Component({
  selector: 'app-rating-carousel-generic',
  templateUrl: './rating-carousel-generic.component.html',
  styleUrls: ['./rating-carousel-generic.component.css']
})
export class RatingCarouselGenericComponent implements OnInit {

  @Input()
  userList: boolean = false;

  @Input()
  userId!: number;
  
  @Input()
  left: boolean = true;

  @Input()
  ourOrYour!: string;

 constructor(private ratingService: RatingService,
    private userService: UserService) { }

  @Input()
  recipeList!:RecipeDTO[];

  page: number = 0;

  ngOnInit(): void {
    this.loadRecipes();
  }

  getTop10Recipes():void {
    this.ratingService.getTop10Recipes().subscribe(result => {
      this.recipeList = result;
    })
  }

  getUsersTop10Recipes():void {
    this.ratingService.getTop10RecipesForUser(this.userId).subscribe(result => {
      this.recipeList = result;
    })
  }

  loadRecipes():void {
    if(this.userList){
      this.getUsersTop10Recipes();
    } else {
      this.getTop10Recipes();
    }
  }



}
