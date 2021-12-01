import { MatDialog } from '@angular/material/dialog';
import { Route } from '@angular/compiler/src/core';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { UserFormComponent } from '../user/user-form/user-form.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  value:String = "Clear me"

  @Output() toggleMenu: EventEmitter<string> = new EventEmitter();

  constructor(private router: Router,
    private dialog: MatDialog) { }

  ngOnInit(): void {
  }

  loadRecipes():void{
    console.log("working")
    this.router.navigateByUrl("/recipes/")
  }

  menuClicked():void{
    this.toggleMenu.emit("clicked");
  }

  loadSignUpForm(): void {
    const dialogRef = this.dialog.open(UserFormComponent, { autoFocus: false, height: '80vh', width: '80vw'});
    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog result: %{result}');
      window.location.reload();
    })
  }

}
