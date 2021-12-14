package Bomberman.entities.character.enemy;

import Bomberman.Board;
import Bomberman.Game;
import Bomberman.entities.character.enemy.AI.AIRandom;
import Bomberman.graphics.Sprite;


public class Minvo extends Enemy
{

    public Minvo(int x, int y, Board board)
    {
        super(x, y, board, Sprite.minvo_dead, Game.getBomberSpeed() * 0.1, 800);

        _sprite = Sprite.minvo_right1;

        _ai = new AIRandom();
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
                    _sprite = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, _animate, 60);
                else
                    _sprite = Sprite.minvo_left1;
                break;
            case 2:
            case 3:
                if (_moving)
                    _sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, _animate, 60);
                else
                    _sprite = Sprite.minvo_left1;
                break;
        }
    }
}
