import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatDividerModule} from "@angular/material/divider";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { ViewGamesComponent } from './view-games/view-games.component';
import { CreateGameListComponent } from './create-game-list/create-game-list.component';
import { UpdateGameListComponent } from './update-game-list/update-game-list.component';
import { LoginComponent } from './login/login.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import {HttpClientModule} from "@angular/common/http";
import {GameService} from "./services/game.service";
import {Routes, RouterModule} from "@angular/router";
import { LogoutComponent } from './logout/logout.component';

// All possible routes and corresponding components. Accessible using sidenav
const routes: Routes = [
  {path: 'games', component: ViewGamesComponent},
  {path: 'signup', component: CreateAccountComponent},
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
    CreateAccountComponent,
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
    MatDividerModule
  ],
  providers: [GameService],
  bootstrap: [AppComponent]
})
export class AppModule { }
