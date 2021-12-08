import { UserDTO } from './../../../models/user-model';
import { UserService } from 'src/app/services/user.service';
import { OktaAuth } from '@okta/okta-auth-js';
import { RatingService } from './../../../services/rating.service';
import { RecipeDTO } from 'src/app/models/recipe.model';
import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { RatingDTO } from 'src/app/models/rating-model';

@Component({
  selector: 'app-star-rating',
  templateUrl: './star-rating.component.html',
  styleUrls: ['./star-rating.component.css']
})
export class StarRatingComponent implements OnInit {

  @Input()
  recipeId!: number;

  loggedInUser!: UserDTO;

  rating!: number;

  @Output() ratingOutput : EventEmitter<number> = new EventEmitter();

  @Input()
  isReadOnly: boolean = true;
  
  @Input()
  usersRating!: number;

  constructor(private ratingService: RatingService, 
    private oktaAuth: OktaAuth, 
    private userService:UserService) { }

  ngOnInit(): void {
  }

  ngAfterContentInit(): void {
    this.getRating();
  }

  getRating():void {
      this.ratingService.getAverageRatingForRecipe(this.recipeId).subscribe(result => {
        this.rating = result;
        if(this.usersRating != null){
          this.rating = this.usersRating;
        }
      })
  }

  async submitRating() {
    if(this.rating != 0 || this.rating != null){

      this.userService.getUserByEmail(await this.getLoggedInEmail()).subscribe(result => {
        this.loggedInUser = result;
        let ratingDTO: RatingDTO = {
          rating: this.rating,
          recipeId: this.recipeId,
          userId: this.loggedInUser.id
        }
        this.ratingService.rateRecipe(ratingDTO).subscribe(result => {
          console.log(result);
        })
      })
    }
  }
  
  private async getLoggedInEmail(): Promise<string> {
    return String((await this.oktaAuth.getUser()).preferred_username);
  }

}

