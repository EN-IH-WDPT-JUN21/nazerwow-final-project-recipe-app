import { UserFormComponent } from './../user-form/user-form.component';
import { MatDialog } from '@angular/material/dialog';
import { UserOwnRecipeComponent } from './../user-own-recipe/user-own-recipe.component';
import { ActivatedRoute } from '@angular/router';
import { UserDTO } from './../../../models/user-model';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Location } from '@angular/common'

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  @Input()
  user!: UserDTO;

  constructor(private userService:UserService,
    private activatedRoute: ActivatedRoute,
    private location: Location
    ) { }

  ngOnInit(): void {
    this.getUserIfNoInputtedUser();
  }

  getUserIfNoInputtedUser():void {
    if(this.user == null){
      const userId:number = this.activatedRoute.snapshot.params['userId'];
      this.getUser(userId);
    }
  }

  getUser(id:number):void{
    this.userService.getUserById(id).subscribe(result => {
      this.user = result;
      },)
    }
    

    back(): void {
      this.location.back();
    }

    

}
