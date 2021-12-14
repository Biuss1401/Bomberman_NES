package Bomberman.entities.tiles.Destroyable;

import java.util.ArrayList;

import Bomberman.Game;
import Bomberman.entities.Entity;
import Bomberman.entities.bomb.Bomb;
import Bomberman.entities.bomb.Flame;
import Bomberman.entities.character.Bomber;
import Bomberman.graphics.Screen;
import Bomberman.graphics.Sprite;
import Bomberman.Level.Coordinates;

public class Brick extends DestroyableTile
{


    public static ArrayList<Integer> Xgachvo = new ArrayList();
    public static ArrayList<Integer> Ygachvo = new ArrayList();


    public Brick(int x, int y, Sprite sprite)
    {
        super(x, y, sprite);
    }

    public static void addXgachvo(int x)
    {
        Xgachvo.add(x);
    }

    public static void addYgachvo(int y)
    {
        Ygachvo.add(y);
    }


    @Override
    public void update()
    {
        super.update();
    }

    @Override
    public void render(Screen screen)
    {
        int x = Coordinates.tileToPixel(_x);
        int y = Coordinates.tileToPixel(_y);

        if (_destroyed)
        {
            _sprite = movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2);

            screen.renderEntityWithBelowSprite(x, y, this, _belowSprite);
        } else
            screen.renderEntity(x, y, this);
    }

    @Override
    public boolean collide(Entity e)
    {
        // nếu tính năng đảo ngược kích hoạt . thì bomber có thể đi  xuyên tường
        if (Game.REVERSE == true)
        {
            if (e instanceof Bomber)
            {
                return true;
            }
        }
        // khi ma hack
        if (e instanceof Bomb)
        {
            if (((Bomb) e).isExploded() == true)
            {
                destroy();
            }

        }
        if (e instanceof Flame)
        {

            addXgachvo(this.getXtile());
            addYgachvo(this.getYtile());
            destroy();
            return false; // cho qua khi no xong
        }
        // khoong cho qua
        return false;
    }

    public int getXtile()
    {
        return (int) this._x;
    }

    public int getYtile()
    {
        return (int) this._y;
    }

}