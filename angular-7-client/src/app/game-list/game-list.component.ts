import { GameDetailsComponent } from './../game-details/game-details.component';
import { Observable } from "rxjs";
import { GameService } from "../game.service";
import { Game } from "../game";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: "app-game-list",
  templateUrl: "./game-list.component.html",
  styleUrls: ["./game-list.component.css"]
})
export class GameListComponent implements OnInit {
  game: Observable<Game[]>;

  constructor(private gameService: GameService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.game = this.gameService.getGameList();
  }

  deleteGame(id: number) {
    this.gameService.deleteGame(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }
}
