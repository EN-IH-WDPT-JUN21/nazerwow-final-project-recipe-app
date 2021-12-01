import { UserService } from './../../../../services/user.service';
import { UserDTO } from './../../../../models/user-model';
import { RecipeDTO } from '../../../../models/recipe.model';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-recipe-card',
  templateUrl: './recipe-card.component.html',
  styleUrls: ['./recipe-card.component.css']
})
export class RecipeCardComponent implements OnInit {

  @Input()
  recipe!:RecipeDTO;
  
  @Input()
  user!:UserDTO

  constructor(private userService:UserService) { }

  ngOnInit(): void {

  }
  
}
