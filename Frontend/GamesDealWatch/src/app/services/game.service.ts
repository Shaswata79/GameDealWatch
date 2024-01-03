import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Game} from "../model/game";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private baseUrl = "http://localhost:8081/game/user/viewItems?storeName=Steam";

  constructor(private httpClient: HttpClient) { }

  getAllGames(): Observable<Game[]> {
    return this.httpClient.get<Game[]>(this.baseUrl).pipe(
      map(response => {
        return response;
      })
    );
  }

}
