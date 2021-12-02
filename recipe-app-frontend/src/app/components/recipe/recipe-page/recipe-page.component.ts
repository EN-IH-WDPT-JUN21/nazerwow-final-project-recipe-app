import { MatBadgeModule } from '@angular/material/badge';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from './../../../services/user.service';
import { UserDTO } from './../../../models/user-model';
import { RecipeService } from './../../../services/recipe.service';
import { RecipeDTO } from '../../../models/recipe.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AddRecipeFormComponent } from '../add-recipe-form/add-recipe-form.component';
import { Location } from '@angular/common'

@Component({
  selector: 'app-recipe-page',
  templateUrl: './recipe-page.component.html',
  styleUrls: ['./recipe-page.component.css']
})
export class RecipePageComponent implements OnInit {

  recipeDTO!:RecipeDTO;
  user!: UserDTO;

  constructor(private recipeService:RecipeService,
    private activateRoute:ActivatedRoute,
    private userService: UserService,
    private dialog: MatDialog,
    private location:Location) { }

  ngOnInit(): void {
    const recipeId:number = this.activateRoute.snapshot.params['recipeId'];
    this.getRecipe(recipeId);
  }

  getRecipe(id:number):void{
    this.recipeService.getRecipeById(id).subscribe(result => {
      this.recipeDTO = result;
      this.userService.getUserById(this.recipeDTO.authorId).subscribe(result => {
        this.user = result;
      })
    })
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

}
