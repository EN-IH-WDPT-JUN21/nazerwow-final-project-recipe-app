import { Component, Input, OnInit, Output } from '@angular/core';
import { RecipeDTO } from 'src/app/models/recipe.model';

@Component({
  selector: 'app-recipe-list-item',
  templateUrl: './recipe-list-item.component.html',
  styleUrls: ['./recipe-list-item.component.css']
})
export class RecipeListItemComponent implements OnInit {

  @Input()
  recipe!:RecipeDTO;

  constructor() { }

  ngOnInit(): void {
  }

}
