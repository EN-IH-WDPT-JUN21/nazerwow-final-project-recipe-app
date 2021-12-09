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

  paused = false;
  unpauseOnArrow = false;
  pauseOnIndicator = false;
  pauseOnHover = true;
  pauseOnFocus = true;

  @ViewChild('carousel', { static: true })
  carousel!: NgbCarousel;

  togglePaused() {
    if (this.paused) {
      this.carousel.cycle();
    } else {
      this.carousel.pause();
    }
    this.paused = !this.paused;
  }

  onSlide(slideEvent: NgbSlideEvent) {
    if (this.unpauseOnArrow && slideEvent.paused &&
      (slideEvent.source === NgbSlideEventSource.ARROW_LEFT || slideEvent.source === NgbSlideEventSource.ARROW_RIGHT)) {
      this.togglePaused();
    }
    if (this.pauseOnIndicator && !slideEvent.paused && slideEvent.source === NgbSlideEventSource.INDICATOR) {
      this.togglePaused();
    }
  }




} 
