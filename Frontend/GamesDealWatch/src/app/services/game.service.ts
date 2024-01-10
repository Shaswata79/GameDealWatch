import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Game} from "../model/game";
import {OAuthService} from "angular-oauth2-oidc";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private baseUrl : string = "http://localhost:8090/game/user/viewItems?storeName=Steam";

  constructor(private httpClient: HttpClient,
              private oauthService: OAuthService) { }

  getAllGames(): Observable<Game[]> {
    return this.httpClient.get<Game[]>(this.baseUrl,
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

}
