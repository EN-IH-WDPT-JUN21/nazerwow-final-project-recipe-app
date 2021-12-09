import { AddRecipeFormComponent } from './components/recipe/add-recipe-form/add-recipe-form.component';
import { MyProfileComponent } from './components/user/my-profile/my-profile.component';
import { RecipeBrowserComponent } from './components/recipe/recipe-browser/recipe-browser.component';
import { RecipePageComponent } from './components/recipe/recipe-page/recipe-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserPageComponent } from './components/user/user-page/user-page.component';
import { OktaAuth } from '@okta/okta-auth-js';
import { OktaAuthGuard, OktaAuthModule, OktaCallbackComponent, OKTA_CONFIG } from '@okta/okta-angular';
import { HomePageComponent } from './components/home-page/home-page.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
// import { AuthInterceptor } from './shared/okta/auth.interceptor';

const oktaConfig = {
  issuer: 'https://dev-1472640.okta.com/oauth2/default',
  clientId: '0oa2xmwgl6FfE7mbI5d7',
  redirectUri: '/login/callback',
  scopes: ['openid', 'profile']
};

const oktaAuth = new OktaAuth(oktaConfig);

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {
    path: 'home',
    component: HomePageComponent
  },
  {
    path: 'login/callback',
    component: OktaCallbackComponent
  },
  {
    path: 'profile',
    component: MyProfileComponent,
    canActivate: [OktaAuthGuard]
  },
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
    path: 'addrecipe',
    component: AddRecipeFormComponent,
    canActivate: [OktaAuthGuard]
  }
];

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    OktaAuthModule,
    RouterModule.forRoot(routes)],
    providers: [
      { provide: OKTA_CONFIG, useValue: { oktaAuth } }      
    ],
    exports: [RouterModule]
})
export class AppRoutingModule { }
