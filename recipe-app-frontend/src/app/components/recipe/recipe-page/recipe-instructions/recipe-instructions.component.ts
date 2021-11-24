import { RecipeDTO } from '../../../../models/recipe.model';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-recipe-instructions',
  templateUrl: './recipe-instructions.component.html',
  styleUrls: ['./recipe-instructions.component.css']
})
export class RecipeInstructionsComponent implements OnInit {

  @Input()
  recipe!:RecipeDTO;

  constructor() { }

  ngOnInit(): void {
  }

}
