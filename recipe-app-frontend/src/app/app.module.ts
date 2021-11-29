import { AngularMaterialModule } from './angular-material.module';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { RecipePageComponent } from './components/recipe/recipe-page/recipe-page.component';
import { RecipeListComponent } from './components/recipe/recipe-list/recipe-list.component';
import {MatTabsModule} from '@angular/material/tabs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { HttpClientModule } from '@angular/common/http';
import { RecipeCardComponent } from './components/recipe/recipe-page/recipe-card/recipe-card.component';
import { RecipeInstructionsComponent } from './components/recipe/recipe-page/recipe-instructions/recipe-instructions.component';
import { RecipeListItemComponent } from './components/recipe/recipe-list/recipe-list-item/recipe-list-item.component';
import { RecipeBrowserComponent } from './components/recipe/recipe-browser/recipe-browser.component';
import { RecipeSearchComponent } from './components/recipe/recipe-search/recipe-search.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddRecipeFormComponent } from './components/recipe/add-recipe-form/add-recipe-form.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UserPageComponent } from './components/user/user-page/user-page.component';
import { UserDetailsComponent } from './components/user/user-details/user-details.component';
import { UserFavouritesComponent } from './components/user/user-favourites/user-favourites.component';
import { UserOwnRecipeComponent } from './components/user/user-own-recipe/user-own-recipe.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    RecipePageComponent,
    RecipeListComponent,
    RecipeCardComponent,
    RecipeInstructionsComponent,
    RecipeListItemComponent,
    RecipeBrowserComponent,
    RecipeSearchComponent,
    AddRecipeFormComponent,
    UserPageComponent,
    UserDetailsComponent,
    UserFavouritesComponent,
    UserOwnRecipeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatTabsModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
