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

  loading:boolean = true;
  hasFavourites: boolean = true;

 constructor(private ratingService: RatingService,
    private userService: UserService) { }

  @Input()
  recipeList!:RecipeDTO[];

  page: number = 0;

  async ngOnInit(): Promise<void> {
    await this.loadRecipes();
  }

  async getTop10Recipes():Promise<void> {
    this.recipeList = await this.ratingService.getTop10Recipes();
  }

  async getUsersTop10Recipes():Promise<void> {
    this.recipeList = await this.ratingService.getTop10RecipesForUser(this.userId)
  }

  async loadRecipes():Promise<void> {
    if(this.userList){
      try {
        await this.getUsersTop10Recipes();
        this.hasFavourites = true;
        this.loading = false;
      } catch (error){
        this.hasFavourites = false;
      }
      
    } else {
      await this.getTop10Recipes();
      this.loading = false;
    }
  }



}
