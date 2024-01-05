import { Component } from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css'
})

export class LogoutComponent {

  constructor(private oauthService: OAuthService) {
  }

  logout() {
    this.oauthService.logOut();
  }

}
