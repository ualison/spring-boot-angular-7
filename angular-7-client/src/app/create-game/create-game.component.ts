import { GameService } from '../game.service';
import { Game } from '../game';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-game',
  templateUrl: './create-game.component.html',
  styleUrls: ['./create-game.component.css']
})
export class CreateGameComponent implements OnInit {

  game: Game = new Game();
  submitted = false;

  constructor(private gameService: GameService) { }

  ngOnInit() {
  }

  newGame(): void {
    this.submitted = false;
    this.game = new Game();
  }

  save() {
    this.gameService.createGame(this.game)
      .subscribe(data => console.log(data), error => console.log(error));
    this.game = new Game();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }
}
