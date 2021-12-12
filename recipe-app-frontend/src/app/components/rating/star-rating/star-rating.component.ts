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
  loading: boolean = true;
  loggedInUser!: UserDTO;

  @Input()
  rating: number = 0;

  @Output() ratingOutput : EventEmitter<number> = new EventEmitter();

  @Input()
  isReadOnly: boolean = true;
  

  constructor(private ratingService: RatingService, 
    private oktaAuth: OktaAuth, 
    private userService:UserService) { }

  async ngOnInit(): Promise<void> {
    await this.getRating()
    this.loading = false;
  }

  async getRating():Promise<void> {
    if(this.isReadOnly){
        this.rating = await this.ratingService.getAverageRatingForRecipe(this.recipeId).catch( error => { });
        this.loading = false;
  }
}

  async submitRating(): Promise<void> {
    if(this.rating != 0 || this.rating != null){
      this.loggedInUser = await this.userService.getUserByEmail(await this.getLoggedInEmail())
        let ratingDTO: RatingDTO = {
          rating: this.rating,
          recipeId: this.recipeId,
          userId: this.loggedInUser.id
        }
        this.ratingService.rateRecipe(ratingDTO).subscribe(result => {
        })
    }
  }
  
  private async getLoggedInEmail(): Promise<string> {
    return String((await this.oktaAuth.getUser()).preferred_username);
  }

}

