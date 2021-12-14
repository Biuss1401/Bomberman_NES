package Bomberman.entities.character.enemy;

import Bomberman.Board;
import Bomberman.Game;
import Bomberman.entities.character.enemy.AI.AIMedium;
import Bomberman.graphics.Sprite;


public class Kondoria extends Enemy
{

    public Kondoria(int x, int y, Board board)
    {
        super(x, y, board, Sprite.kondoria_dead, Game.getBomberSpeed(), 1000);

        _sprite = Sprite.kondoria_right1;

        _ai = new AIMedium(_board.getBomber(), this, board); //TODO: implement AIHigh
        _direction = _ai.calculateDirection();
    }

    /*
    |--------------------------------------------------------------------------
    | Mob Sprite
    |--------------------------------------------------------------------------
     */
    @Override
    protected void chooseSprite()
    {
        switch (_direction)
        {
            case 0:
            case 1:
                if (_moving)
                    _sprite = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, _animate, 20);
                else
                    _sprite = Sprite.kondoria_left1;
                break;
            case 2:
            case 3:
                if (_moving)
                    _sprite = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, _animate, 20);
                else
                    _sprite = Sprite.kondoria_left1;
                break;
        }
    }
}
