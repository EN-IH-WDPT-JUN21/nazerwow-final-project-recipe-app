import { UserDTO } from './../../../models/user-model';
import { UserService } from 'src/app/services/user.service';
import { RecipeDTO } from 'src/app/models/recipe.model';
import { FavouritesService } from './../../../services/favourites.service';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { NgbCarousel, NgbSlideEvent, NgbSlideEventSource } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-favourites-carousel',
  templateUrl: './favourites-carousel.component.html',
  styleUrls: ['./favourites-carousel.component.css']
})
export class FavouritesCarouselComponent implements OnInit {

  constructor(private favouritesService: FavouritesService,
    private userService: UserService) { }

  @Input()
  recipeList!:RecipeDTO[];

  page: number = 0;

  ngOnInit(): void {
    this.getTop10Favourites();
  }

  getTop10Favourites():void {
    this.favouritesService.getTop10FavouritedRecipes().subscribe(result => {
      this.recipeList = result;
    })
  }


} 
