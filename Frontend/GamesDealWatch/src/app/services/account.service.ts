import { Injectable } from '@angular/core';
import {Account} from "../model/account";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {OAuthService} from "angular-oauth2-oidc";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private backendUrl : string = "http://localhost:8090/accounts/user/create";

  constructor(private httpClient: HttpClient,
              private oauthService: OAuthService) { }

  async createAccountInBackend() : Promise<string> {

    const response = await this.oauthService.loadUserProfile();
    let data : string = JSON.stringify(response);

    const parsedObject = JSON.parse(data);
    let email: string = parsedObject.info.email;
    let username: string = parsedObject.info.preferred_username;
    let name: string = parsedObject.info.name;

    const headers = new HttpHeaders(
      {'Content-Type': 'application/json',
               'Authorization': `Bearer ${this.oauthService.getAccessToken()}`}
    );

    this.httpClient.post(this.backendUrl,
      {name, username, email},
      { headers }).subscribe(
        response => {console.log(response)}
    );

    return email;
  }

  createAccountInAuthServer(account : Account): void {
    // let tokenUrl : string = "http://localhost:8085/realms/gdw/protocol/openid-connect/token";
    // const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });
    // this.httpClient.post(tokenUrl,
    //   {grant_type: "client_credentials",
    //     client_id: "gdw-create-client",
    //     client_secret: "9yuqWmCiRi1tw457N7tunsl7zVOefLJl"}
    // ).subscribe(
    //   response => {console.log(response)}
    // );
    console.log("Here");
    this.oauthService.loadUserProfile().then(response => {
      let data : string = JSON.stringify(response);
      const parsedObject = JSON.parse(data);
      const email = parsedObject.info.email;
      console.log('Email:', email);
    });
  }

}
