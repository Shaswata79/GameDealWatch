import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Account} from "../model/account";
import {AccountService} from "./account.service";
import {OAuthService} from "angular-oauth2-oidc";

@Injectable({
  providedIn: 'root'
})
export class GamelistService {

  private backendUrl : string = 'http://localhost:8090/list/user/createList';

  constructor(private httpClient : HttpClient,
              private accountService : AccountService,
              private oauthService : OAuthService) { }

  async createNewList(listName : string) : Promise<void> {

    let userEmail : string = await this.accountService.createAccountInBackend();

    const headers = new HttpHeaders(
      {'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.oauthService.getAccessToken()}`}
    );

    console.log(listName, userEmail);

    this.httpClient.post(this.backendUrl,
      {listName, userEmail},
      { headers }).subscribe(
      response => {console.log(response)}
    );

  }

}
