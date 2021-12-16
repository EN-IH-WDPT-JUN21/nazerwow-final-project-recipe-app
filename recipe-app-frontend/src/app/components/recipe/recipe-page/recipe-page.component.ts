import { MatSnackBar } from '@angular/material/snack-bar';
import { OktaAuth } from '@okta/okta-auth-js';
import { MatBadgeModule } from '@angular/material/badge';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from './../../../services/user.service';
import { UserDTO } from './../../../models/user-model';
import { RecipeService } from './../../../services/recipe.service';
import { RecipeDTO } from '../../../models/recipe.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AddRecipeFormComponent } from '../add-recipe-form/add-recipe-form.component';
import { Location } from '@angular/common'
import { ConfirmComponent } from '../../dialogs/confirm/confirm.component';

@Component({
  selector: 'app-recipe-page',
  templateUrl: './recipe-page.component.html',
  styleUrls: ['./recipe-page.component.css']
})
export class RecipePageComponent implements OnInit {

  recipeDTO!:RecipeDTO;
  user!: UserDTO;
  loadingRecipe: boolean = true;

  loggedInEmail!: string;

  userVerified: boolean = false;
  username!: string;
  confirmed: string = "no";

  constructor(private recipeService:RecipeService,
    private activateRoute:ActivatedRoute,
    private userService: UserService,
    private dialog: MatDialog,
    private location:Location,
    private oktaAuth: OktaAuth,
    private router:Router,
    private snackBar:MatSnackBar) { }

  async ngOnInit(): Promise<void> {
    const recipeId:number = this.activateRoute.snapshot.params['recipeId'];
    await this.getRecipe(recipeId);
  }

  async getRecipe(id:number):Promise<void>{
    try {
      this.recipeDTO = await this.recipeService.getRecipeById(id)
    }catch (error) {
      this.snackBar.open("Page Not Found - You Have Been Redirected", "close")
      this.router.navigate(['recipes']);
    }
    this.user = await this.userService.getUserById(this.recipeDTO.authorId);
    await this.verifyUser();
    this.loadingRecipe = false;
  }

  loadAddForm(): void {
    const dialogRef = this.dialog.open(AddRecipeFormComponent, { autoFocus: false, height: '80vh', width: '80vw', data: this.recipeDTO });
    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog result: %{result}');
      window.location.reload();
    })
  }

  back():void {
    this.location.back()
  }

  refreshPage():void {
    window.location.reload();
  }

  async verifyUser(){
    this.userVerified = await this.emailsMatch();
  }

  async emailsMatch(): Promise<boolean> {
    try {
      this.loggedInEmail = String((await this.oktaAuth.getUser()).preferred_username);
      if(this.user.email == this.loggedInEmail){
        return true;
      } else {
        return false;
      }
    } catch (error) {
      return false;
    }
  }

  delete(): void {
    if(this.confirmed == "yes"){
    this.recipeService.deleteRecipe(this.recipeDTO.id).subscribe(result => {
      console.log(result)
      this.snackBar.open("Deleted Successfully", "CLOSE")
    }, 
    error => {
      this.snackBar.open("An error occured: Please try again", "CLOSE")
    })
  }
  }

  confirm(): void {
    const dialogRef = this.dialog.open(ConfirmComponent, {
    });
    dialogRef.afterClosed().subscribe(result => {
      this.confirmed = result;
      this.delete();
    });
  }

}
