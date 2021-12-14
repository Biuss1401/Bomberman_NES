package Bomberman.entities.character.enemy;

import Bomberman.Board;
import Bomberman.Game;
import Bomberman.entities.character.enemy.AI.AILow;
import Bomberman.graphics.Sprite;

public class Balloon extends Enemy
{


    public Balloon(int x, int y, Board board)
    {
        super(x, y, board, Sprite.balloom_dead, Game.getBomberSpeed() / 2, 100);
        _sprite = Sprite.balloom_left1;
        _ai = new AILow(_board.getBomber(), this, board);
        _direction = _ai.calculateDirection();
    }

    @Override
    protected void chooseSprite()
    {
        switch (_direction)
        {
            case 0:
            case 1:
                _sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, _animate, 20);
                break;
            case 2:
            case 3:
                _sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, _animate, 20);
                break;
        }
    }
}