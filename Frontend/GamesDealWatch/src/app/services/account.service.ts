import { Injectable } from '@angular/core';
import {Account} from "../model/account";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private backendUrl : string = "http://localhost:8082/accounts/user/create";

  constructor(private httpClient: HttpClient) { }

  createAccountInBackend(account : Account) : void {
    let name: string = account.name;
    let username: string = account.username;
    let email: string = account.email;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    this.httpClient.post(this.backendUrl,
      {name, username, email},
      { headers }).subscribe(
        response => {console.log(response)}
    );

  }

  createAccountInAuthServer(account : Account): void {

  }

}
