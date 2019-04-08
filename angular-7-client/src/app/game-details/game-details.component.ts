import { Game } from '../game';
import { Component, OnInit, Input } from '@angular/core';
import { GameService } from '../game.service';
import { GameListComponent } from '../game-list/game-list.component';

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.css']
})
export class GameDetailsComponent implements OnInit {

  @Input() game: Game;

  constructor(private gameService: GameService, private listComponent: GameListComponent) { }

  ngOnInit() {
  }

}
