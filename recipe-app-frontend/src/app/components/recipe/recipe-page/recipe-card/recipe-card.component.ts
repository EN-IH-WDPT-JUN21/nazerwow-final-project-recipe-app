import { OktaAuth } from '@okta/okta-auth-js';
import { RatingService } from './../../../../services/rating.service';
import { UserService } from './../../../../services/user.service';
import { UserDTO } from './../../../../models/user-model';
import { RecipeDTO } from '../../../../models/recipe.model';
import { Component, Input, OnInit } from '@angular/core';
import { RatingDTO } from 'src/app/models/rating-model';

@Component({
  selector: 'app-recipe-card',
  templateUrl: './recipe-card.component.html',
  styleUrls: ['./recipe-card.component.css']
})
export class RecipeCardComponent implements OnInit {

  @Input()
  recipe!:RecipeDTO;
  
  @Input()
  user!:UserDTO

  loggedInUser!: UserDTO
  usersRating!: number;

  constructor(private userService:UserService, 
    private ratingService: RatingService,
    private oktaAuth:OktaAuth) { }

  ngOnInit(): void {
    this.getUsersRating();
  }

   async getUsersRating() {
    this.userService.getUserByEmail(await this.getLoggedInEmail()).subscribe(result => {
      this.loggedInUser = result;
      let ratingDTO: RatingDTO = {
        userId: this.loggedInUser.id,
        recipeId: this.recipe.id
      }
      this.ratingService.getUsersRating(ratingDTO).subscribe(result => {
        this.usersRating = result;
      })
    })
  }
  
  private async getLoggedInEmail(): Promise<string> {
    return String((await this.oktaAuth.getUser()).preferred_username);
  }
  
}
