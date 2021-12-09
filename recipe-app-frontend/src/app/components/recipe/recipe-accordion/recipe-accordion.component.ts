import { Component, Input, OnInit } from '@angular/core';
import { RecipeDTO } from 'src/app/models/recipe.model';

@Component({
  selector: 'app-recipe-accordion',
  templateUrl: './recipe-accordion.component.html',
  styleUrls: ['./recipe-accordion.component.css']
})
export class RecipeAccordionComponent implements OnInit {

  @Input()
  recipeList!: RecipeDTO[];
  loading: boolean = true;

  panelOpenState: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

}
