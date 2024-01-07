import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import {AppComponent, initlizeOAuth} from './app.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatDividerModule} from "@angular/material/divider";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from '@angular/material/form-field';

import { ViewGamesComponent } from './view-games/view-games.component';
import { CreateGameListComponent } from './create-game-list/create-game-list.component';
import { UpdateGameListComponent } from './update-game-list/update-game-list.component';
import { LoginComponent } from './login/login.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {GameService} from "./services/game.service";
import {Routes, RouterModule} from "@angular/router";
import { LogoutComponent } from './logout/logout.component';
import {MatCardModule} from "@angular/material/card";
import {MatInputModule} from "@angular/material/input";
import {AccountService} from "./services/account.service";
import {OAuthService, provideOAuthClient} from "angular-oauth2-oidc";

// All possible routes and corresponding components. Accessible using sidenav
const routes: Routes = [
  {path: 'games', component: ViewGamesComponent},
  {path: 'login', component: LoginComponent},
  {path: 'updateGameList', component: UpdateGameListComponent},
  {path: 'createGameList', component: CreateGameListComponent},
  {path: 'logout', component: LogoutComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    ViewGamesComponent,
    CreateGameListComponent,
    UpdateGameListComponent,
    LoginComponent,
    LogoutComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatSidenavModule,
    BrowserAnimationsModule,
    MatDividerModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    HttpClient,
    GameService,
    AccountService,
    provideOAuthClient(),
    {
      provide: APP_INITIALIZER,
      useFactory: (oauthService: OAuthService) => {
        return () => {
          initlizeOAuth(oauthService);
        }
      },
      multi: true,
      deps: [OAuthService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
