import { RatingService } from './../../../../services/rating.service';
import { UserService } from './../../../../services/user.service';
import { UserDTO } from './../../../../models/user-model';
import { Component, Input, OnInit, Output } from '@angular/core';
import { RecipeDTO } from 'src/app/models/recipe.model';

@Component({
  selector: 'app-recipe-list-item',
  templateUrl: './recipe-list-item.component.html',
  styleUrls: ['./recipe-list-item.component.css']
})
export class RecipeListItemComponent implements OnInit {

  @Input()
  recipe!:RecipeDTO;

  rating: number = 0;
  loading: boolean = true;

  @Input()
  authorId!: number;

  user!:UserDTO;

  constructor(private userService: UserService, 
    private ratingService: RatingService) { }

  ngOnInit(): void {
    this.loadData();
  }

  ngAfterViewInit(): void {

  }

  async loadData():Promise<void> {
    await this.getAuthor();
    await this.getRating();
  }

  async getAuthor():Promise<void> {
   this.user = await  this.userService.getUserById(this.authorId)
      this.loading = false;
  }

  async getRating():Promise<void> {
      this.rating = await this.ratingService.getAverageRatingForRecipe(this.recipe.id).catch(error => {})
  }
}

