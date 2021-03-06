import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { EnumPipe } from './custom-pipes/enum-pipe';
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
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RecipeCardComponent } from './components/recipe/recipe-page/recipe-card/recipe-card.component';
import { RecipeInstructionsComponent } from './components/recipe/recipe-page/recipe-instructions/recipe-instructions.component';
import { RecipeListItemComponent } from './components/recipe/recipe-list/recipe-list-item/recipe-list-item.component';
import { RecipeBrowserComponent } from './components/recipe/recipe-browser/recipe-browser.component';
import { RecipeSearchComponent } from './components/recipe/recipe-search/recipe-search.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddRecipeFormComponent } from './components/recipe/add-recipe-form/add-recipe-form.component';
import { NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { UserPageComponent } from './components/user/user-page/user-page.component';
import { UserDetailsComponent } from './components/user/user-details/user-details.component';
import { UserFavouritesComponent } from './components/user/user-favourites/user-favourites.component';
import { UserOwnRecipeComponent } from './components/user/user-own-recipe/user-own-recipe.component';
import { UserFormComponent } from './components/user/user-form/user-form.component';
import { DrawerComponent } from './components/drawer/drawer.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { HomePageComponent } from './components/home-page/home-page.component';
import { HomeSearchComponent } from './components/home-page/home-search/home-search.component';
import { FavouritesCarouselComponent } from './components/home-page/favourites-carousel/favourites-carousel.component';
import { MyProfileComponent } from './components/user/my-profile/my-profile.component';
import { AuthInterceptor } from './shared/okta/auth.interceptor';
import { FavouritesToggleComponent } from './components/recipe/favourites-toggle/favourites-toggle.component';
import { StarRatingComponent } from './components/rating/star-rating/star-rating.component';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RecipeAccordionComponent } from './components/recipe/recipe-accordion/recipe-accordion.component';
import { RatingCarouselGenericComponent } from './components/home-page/rating-carousel-generic/rating-carousel-generic.component';
import { ReviewListComponent } from './components/review/review-list/review-list.component';
import { ReviewListItemComponent } from './components/review/review-list-item/review-list-item.component';
import { ReviewFormComponent } from './components/review/review-form/review-form.component';
import { ConfirmComponent } from './components/dialogs/confirm/confirm.component';



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
    UserOwnRecipeComponent,
    UserFormComponent,
    DrawerComponent,
    HomePageComponent,
    HomeSearchComponent,
    FavouritesCarouselComponent,
    EnumPipe,
    MyProfileComponent,
    FavouritesToggleComponent,
    StarRatingComponent,
    RecipeAccordionComponent,
    RatingCarouselGenericComponent,
    ReviewListComponent,
    ReviewListItemComponent,
    ReviewFormComponent,
    ConfirmComponent,
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
    NgbModule,
    NgxPaginationModule,
    MDBBootstrapModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: MAT_DIALOG_DATA, useValue: {} },
    { provide: MatDialogRef, useValue: {} }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
