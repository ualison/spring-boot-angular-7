import { Game } from './game';

describe('Game', () => {
  it('deveria criar uma instancia', () => {
    expect(new Game()).toBeTruthy();
  });
});
