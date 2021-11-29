import { ActivatedRoute } from '@angular/router';
import { UserDTO } from './../../../models/user-model';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Location } from '@angular/common'

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  user!: UserDTO;

  constructor(private userService:UserService,
    private activatedRoute: ActivatedRoute,
    private location: Location
    ) { }

  ngOnInit(): void {
    const userId:number = this.activatedRoute.snapshot.params['userId'];
    this.getUser(userId);
  }

  getUser(id:number):void{
    this.userService.getUserById(id).subscribe(result => {
      this.user = result;
      })
    }

    back(): void {
      this.location.back();
    }

}
