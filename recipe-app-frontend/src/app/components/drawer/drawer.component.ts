import { Router } from '@angular/router';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { OktaAuthStateService } from '@okta/okta-angular';

@Component({
  selector: 'app-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['./drawer.component.css']
})
export class DrawerComponent implements OnInit {

  @Output() closeDrawer: EventEmitter<string> = new EventEmitter();
  

  constructor(private router: Router, 
    public authService: OktaAuthStateService) { }

  ngOnInit(): void {
  }

  loadRecipePage():void{
    this.router.navigate(['recipes']);
    this.closeSideMenu();
  }

  closeSideMenu():void{
    this.closeDrawer.emit("Close");
  }

  loadProfile():void{
    this.router.navigate(['profile']);
    this.closeSideMenu();
  }

  loadAddRecipe():void{
    this.router.navigate(['/addrecipe']);
    this.closeSideMenu();
  }



}
