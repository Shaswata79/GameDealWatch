import {Component, OnInit} from '@angular/core';
import {GameService} from "../services/game.service";
import {Game} from "../model/game";

@Component({
  selector: 'app-view-games',
  templateUrl: './view-games.component.html',
  styleUrl: './view-games.component.css'
})
export class ViewGamesComponent implements OnInit{

  games: Game[] = [];
  constructor(private gameService: GameService) {}

  ngOnInit(): void {
    this.listGames();
  }

  private listGames() {
    this.gameService.getAllGames().subscribe(
      data => {
        this.games = data;
      }
    )
  }

}
