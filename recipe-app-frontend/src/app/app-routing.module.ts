import { UserDetailsComponent } from './components/user/user-details/user-details.component';
import { RecipeBrowserComponent } from './components/recipe/recipe-browser/recipe-browser.component';
import { RecipeListComponent } from './components/recipe/recipe-list/recipe-list.component';
import { RecipePageComponent } from './components/recipe/recipe-page/recipe-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserPageComponent } from './components/user/user-page/user-page.component';

const routes: Routes = [
  {
    path: 'recipes/:recipeId',
    component: RecipePageComponent
  },
  {
    path: 'users/:userId',
    component: UserPageComponent
  },
  {
    path: 'recipes',
    component: RecipeBrowserComponent
  },
  {
    path: '',
    component: RecipeBrowserComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
