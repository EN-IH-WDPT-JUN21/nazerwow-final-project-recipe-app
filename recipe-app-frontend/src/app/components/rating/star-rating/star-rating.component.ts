import { RatingService } from './../../../services/rating.service';
import { RecipeDTO } from 'src/app/models/recipe.model';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-star-rating',
  templateUrl: './star-rating.component.html',
  styleUrls: ['./star-rating.component.css']
})
export class StarRatingComponent implements OnInit {

  @Input()
  recipeId!: number;

  rating: number = 0;

  @Input()
  isReadOnly: boolean = true;

  constructor(private ratingService: RatingService) { }

  ngOnInit(): void {
    this.getRating();
  }

  getRating():void {
    if(this.recipeId != null){ 
    this.ratingService.getAverageRatingForRecipe(this.recipeId).subscribe(result => {
      this.rating = result;
    })
  }
  }

}
