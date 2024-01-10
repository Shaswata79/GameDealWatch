import {Component, OnInit} from '@angular/core';
import {Game} from "../model/game";
import {GameService} from "../services/game.service";
import {GamelistService} from "../services/gamelist.service";
import {GameList} from "../model/game-list";
import {ListItem} from "../model/list-item";
import {AccountService} from "../services/account.service";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-update-game-list',
  templateUrl: './update-game-list.component.html',
  styleUrl: './update-game-list.component.css'
})
export class UpdateGameListComponent implements OnInit{

  games: Game[] = [];           //all games
  gameListName: string = '';
  userGames: ListItem[] = [];   //user's games
  gameListAddForm: FormGroup = new FormGroup({});

  constructor(private gameService: GameService,
              private gameListService: GamelistService,
              private accountService: AccountService,
              private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.getAllGames();
    this.getUserGameList();

    this.gameListAddForm = this.formBuilder.group({
      id: 0,
      threshold: 0
    });
    console.log(this.games);
  }

  private getAllGames() {
    this.gameService.getAllGames().subscribe(
      data => {
        this.games = data;
      }
    )
  }

  private async getUserGameList() {
    let userEmail = await this.accountService.createAccountInBackend();

    this.gameListService.getUserGameList(userEmail).subscribe(
      data => {
        this.gameListName = data.listName;
        this.userGames = data.items;
      }
    )
  }

  async addGame(){
    let userEmail = await this.accountService.createAccountInBackend();

    this.gameListService.addToGameList(
          userEmail,
          this.gameListAddForm.value.id,
          this.gameListAddForm.value.threshold)
      .subscribe(
      data => {
        this.gameListName = data.listName;
        this.userGames = data.items;
      }
    )

  }

}
