import { RecipeService } from './../../../services/recipe.service';
import { RecipeDTO } from './../../../models/recipe.model';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  @Input()
  recipeList!:RecipeDTO[];

  @Input()
  filter!:string;

  page: number = 1;

  constructor() { }

  ngOnInit(): void {
    
  }

  handlePageChange(event: number): void {
    this.page = event;
  }

}
