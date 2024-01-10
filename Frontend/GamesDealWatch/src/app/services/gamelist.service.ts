import {Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Account} from "../model/account";
import {AccountService} from "./account.service";
import {OAuthService} from "angular-oauth2-oidc";
import {map, Observable} from "rxjs";
import {Game} from "../model/game";
import {GameList} from "../model/game-list";
import {getXHRResponse} from "rxjs/internal/ajax/getXHRResponse";

@Injectable({
  providedIn: 'root'
})
export class GamelistService{

  private createListUrl : string = 'http://localhost:8090/list/user/createList';
  private getListUrl : string = 'http://localhost:8090/list/user/userList/';
  private addToListUrl : string = 'http://localhost:8090/list/user/add';

  constructor(private httpClient : HttpClient,
              private accountService : AccountService,
              private oauthService : OAuthService) { }

  async createNewList(listName : string) : Promise<void> {
    let userEmail = await this.accountService.createAccountInBackend();

    const headers = new HttpHeaders(
      {'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.oauthService.getAccessToken()}`}
    );

    console.log(listName, userEmail);

    this.httpClient.post(this.createListUrl,
      {listName, userEmail},
      { headers }).subscribe(
      response => {console.log(response)}
    );

  }

  getUserGameList(userEmail: string): Observable<GameList> {

    return this.httpClient.get<GameList>(this.getListUrl+userEmail,
      {headers: {'Content-Type': 'application/json',
          'Authorization': `Bearer ${this.oauthService.getAccessToken()}`}
      }
    ).pipe(
      map(response => {
        console.log(response);
        return response;
      })
    );

  }

  addToGameList(userEmail: string, id: number, threshold: number): Observable<GameList> {
    const headers = new HttpHeaders(
      {'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.oauthService.getAccessToken()}`}
    );

    return this.httpClient.put<GameList>(this.addToListUrl + `?email=${userEmail}&id=${id}&threshold=${threshold}`,
      {},
      { headers }
      ).pipe(
      map(response => {
        console.log(response);
        return response;
      })
    );

  }



}
