import { UserService } from './../../../../services/user.service';
import { UserDTO } from './../../../../models/user-model';
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

  @Input()
  authorId!: number;

  user!:UserDTO;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getAuthor();
  }

  ngAfterViewInit(): void {

  }

  getAuthor():void {
    this.userService.getUserById(this.authorId).subscribe(result => {
      this.user = result;
    })
  }
}

