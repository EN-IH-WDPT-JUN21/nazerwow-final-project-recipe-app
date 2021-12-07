import { OktaAuth } from '@okta/okta-auth-js';
import { MatDialog } from '@angular/material/dialog';
import { UserDTO } from './../../../models/user-model';
import { UserService } from './../../../services/user.service';
import { Component, Input, OnInit } from '@angular/core';
import { UserFormComponent } from '../user-form/user-form.component';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  @Input()
  user!:UserDTO;

  loggedInEmail: string | undefined;

  userVerified: boolean = false;


  constructor(private dialog: MatDialog, private oktaAuth:OktaAuth) { }

  async ngOnInit(): Promise<void> {
    this.loggedInEmail = (await this.oktaAuth.getUser()).preferred_username;
    this.verifyUser();
  }

  loadUserForm(): void {
    const dialogRef = this.dialog.open(UserFormComponent, { autoFocus: false, height: '80vh', width: '80vw', data: this.user});
    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog result: %{result}');
      this.refreshPage();
    })
  }

  refreshPage():void {
    window.location.reload();
  }

  verifyUser() {
    if(this.user.email == this.loggedInEmail){
      this.userVerified = true;
    }
  }

}
