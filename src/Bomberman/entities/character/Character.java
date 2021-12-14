package Bomberman.entities.character;

import Bomberman.Board;
import Bomberman.Game;
import Bomberman.entities.AnimatedEntity;
import Bomberman.graphics.Screen;

/**
 * Includes Bomber and Enemy
 */
public abstract class Character extends AnimatedEntity
{

    protected Board _board;
    protected int _direction = -1;
    protected boolean _alive = true;
    protected boolean _moving = false;
    public int _timeAfter = 40;

    public Character(int x, int y, Board board)
    {
        _x = x;
        _y = y;
        _board = board;
    }

    @Override
    public abstract void update();

    @Override
    public abstract void render(Screen screen);

    /**
     * Find the way
     */
    protected abstract void calculateMove();

    protected abstract void move(double xa, double ya);

    /**
     * Called when an enemy was killed
     */
    public abstract void kill();

    /**
     * Handling killed Animation
     */
    protected abstract void afterKill();

    /**
     * Check the way of entity
     *
     * @param x
     * @param y
     * @return
     */
    protected abstract boolean canMove(double x, double y);

    protected double getXMessage()
    {
        return (_x * Game.SCALE) + (_sprite.SIZE / 2 * Game.SCALE);
    }

    protected double getYMessage()
    {
        return (_y * Game.SCALE) - (_sprite.SIZE / 2 * Game.SCALE);
    }

}
