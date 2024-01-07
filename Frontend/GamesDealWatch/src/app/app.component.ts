import {Component, ViewChild} from '@angular/core';
import {MatSidenav} from "@angular/material/sidenav";
import {BreakpointObserver} from "@angular/cdk/layout";
import {AfterViewInit} from "@angular/core";
import {AuthConfig, OAuthService} from "angular-oauth2-oidc";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent implements AfterViewInit{
  title = 'GamesDealWatch';

  @ViewChild(MatSidenav)
  sidenav!: MatSidenav;

  constructor(private observer: BreakpointObserver) {
  }

  ngAfterViewInit(){

    // if width of window is less than 1200px sidenav mode is switched to 'over', otherwise it stays 'side'
    // if mode is 'over' can toggle sidenav using button
    this.observer.observe(['(max-width: 1200px)']).subscribe(res => {
      if (res.matches) {
        this.sidenav.mode = 'over';
        this.sidenav.close();
      }else {
        this.sidenav.mode = 'side';
        this.sidenav.open();
      }
    });

  }
}

export const authCodeFlowConfig: AuthConfig = {
  issuer: 'http://localhost:8085/realms/gdw',
  tokenEndpoint: 'http://localhost:8085/realms/gdw/protocol/openid-connect/token',
  redirectUri: window.location.origin,
  clientId: 'angular-frontend',
  responseType: 'code',
  scope: 'openid profile',
}

export function initlizeOAuth(oauthService : OAuthService) : Promise<void> {
  return new Promise(resolve => {
    oauthService.configure(authCodeFlowConfig);
    oauthService.setupAutomaticSilentRefresh();
    oauthService.loadDiscoveryDocumentAndLogin().then(() => resolve());
  });
}




