import { Observable } from 'rxjs';
import { FavouriteDTO } from './../../../models/favourites-model';
import { FavouritesService } from './../../../services/favourites.service';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-favourites-toggle',
  templateUrl: './favourites-toggle.component.html',
  styleUrls: ['./favourites-toggle.component.css']
})
export class FavouritesToggleComponent implements OnInit {

  @Input()
  userId!: number;
  @Input()
  recipeId!: number;

  isFavourite: boolean = false;

  constructor(private favouritesService: FavouritesService) { }

  ngOnInit(): void {
    this.checkIfFavourited();
  }

  checkIfFavourited(): void {
    this.favouritesService.isRecipeFavourited(this.createFavouriteDTO()).subscribe(result => {
      this.isFavourite = result;
    });
  }

  createFavouriteDTO(): FavouriteDTO {
    const favouriteDTO: FavouriteDTO = {userId: this.userId, recipeId: this.recipeId}
    return favouriteDTO;
  }

  toggleFavourite(): void {
    if(this.isFavourite){
      this.favouritesService.removeFromFavourites(this.createFavouriteDTO()).subscribe(result => {
        console.log(result)
        this.isFavourite = false;
      })
    } else {
      this.favouritesService.addToFavourites(this.createFavouriteDTO()).subscribe(result => {
        console.log(result)
        this.isFavourite = true;
      })
    }
  }
}
