import { OktaAuthStateService } from '@okta/okta-angular';
import { UserDTO } from './../../../models/user-model';
import { OktaAuth } from '@okta/okta-auth-js';
import { UserService } from 'src/app/services/user.service';
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
  recipeId!: number;

  user!: UserDTO;

  isFavourite: boolean = false;
  loading: boolean = true;

  displayText: string = "Add to favourites"

  constructor(private favouritesService: FavouritesService, 
    private userService: UserService, 
    private oktaAuth: OktaAuth, 
    public authService: OktaAuthStateService) { }

  ngOnInit(): void {
    this.loadUserAndCheckIfFavourite();
  }

  async loadUserAndCheckIfFavourite(): Promise<void> {
  this.userService.getUserByEmail(await this.getLoggedInEmail()).subscribe(result => {
    this.user = result;
    this.checkIfFavourited();
  })
  }

  private async getLoggedInEmail(): Promise<string> {
    return String((await this.oktaAuth.getUser()).preferred_username);
  }

  checkIfFavourited(): void {
    this.favouritesService.isRecipeFavourited(this.createFavouriteDTO()).subscribe(result => {
      this.isFavourite = result;
      console.log(this.isFavourite)
      this.toggleDisplayText();
      this.loading = false;
    });
  }

  createFavouriteDTO(): FavouriteDTO {
    const favouriteDTO: FavouriteDTO = {userId: this.user.id, recipeId: this.recipeId}
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
    this.toggleDisplayText();
  }

  private toggleDisplayText():void{
    if(this.isFavourite){
      this.displayText = "Remove from favourites"
    } else {
      this.displayText = "Add to favourites"
    }
  }
}
