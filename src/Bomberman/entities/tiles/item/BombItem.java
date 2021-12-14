package Bomberman.entities.tiles.item;

import Bomberman.Game;
import Bomberman.entities.Entity;
import Bomberman.entities.character.Bomber;
import Bomberman.graphics.Sprite;

public class BombItem extends Item
{

    public BombItem(int x, int y, Sprite sprite)
    {
        super(x, y, sprite);
    }

    @Override
    public boolean collide(Entity e)
    {
        if (e instanceof Bomber)
        {
            ((Bomber) e).addPowerup(this);
            remove();
            return true;
        }
        return false;

    }

    @Override
    public void setValues()
    {
        this.setActive(true);
        // tăng số bom được đặt
        Game.addBombRate(1);


    }
}
