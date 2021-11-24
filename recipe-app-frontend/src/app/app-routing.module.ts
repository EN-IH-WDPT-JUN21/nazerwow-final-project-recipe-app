import { RecipeBrowserComponent } from './components/recipe/recipe-browser/recipe-browser.component';
import { RecipeListComponent } from './components/recipe/recipe-list/recipe-list.component';
import { RecipePageComponent } from './components/recipe/recipe-page/recipe-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'recipes/:recipeId',
    component: RecipePageComponent
  },
  {
    path: 'recipes',
    component: RecipeBrowserComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
