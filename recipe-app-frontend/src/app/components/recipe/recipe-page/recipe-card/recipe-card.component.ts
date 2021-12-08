import { OktaAuthStateService } from '@okta/okta-angular';
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
    private oktaAuth:OktaAuth,
    public authService: OktaAuthStateService) { }

  async ngOnInit(): Promise<void> {
    await this.getUsersRating();
  }

   async getUsersRating() {
    this.userService.getUserByEmail(await this.getLoggedInEmail()).subscribe(result => {
      this.loggedInUser = result;
      let ratingDTO: RatingDTO = {
        rating: 0,
        recipeId: this.recipe.id,
        userId: this.loggedInUser.id
      }
      console.log(ratingDTO)
      this.ratingService.getUsersRating(ratingDTO).subscribe(result => {
        this.usersRating = result;
        console.log(result)
      })
    })
  }
  
  private async getLoggedInEmail(): Promise<string> {
    return String((await this.oktaAuth.getUser()).preferred_username);
  }
  
}
