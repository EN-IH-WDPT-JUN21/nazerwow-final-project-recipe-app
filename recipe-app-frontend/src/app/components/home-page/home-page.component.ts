import { OktaAuthStateService } from '@okta/okta-angular';
import { UserDTO } from './../../models/user-model';
import { UserService } from 'src/app/services/user.service';
import { OktaAuth } from '@okta/okta-auth-js';
import { FavouritesCarouselComponent } from './favourites-carousel/favourites-carousel.component';
import { Component, OnInit, ViewChild } from '@angular/core';


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  user!:UserDTO;

  loggedIn: boolean = false;


  constructor(private oktaAuth: OktaAuth, 
    private userService:UserService) { }


  ngOnInit(): void {
    this.getUserIfLoggedIn();
  }

  getUserIfLoggedIn():void {
    if(this.oktaAuth.getAccessToken() != null) {
      this.loggedIn = true;
      this.getUserByEmail();
    } else {
      this.loggedIn = false;
    }
  }

  async getLoggedInEmail(): Promise<string> {
    let loggedInEmail: string = String((await this.oktaAuth.getUser()).preferred_username);
    return loggedInEmail;
  }

  async getUserByEmail(): Promise<void> {
    this.userService.getUserByEmail(await this.getLoggedInEmail()).subscribe(result => {
      this.user = result;
    })
  }

  

}
