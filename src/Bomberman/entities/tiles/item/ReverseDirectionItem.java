package Bomberman.entities.tiles.item;

import Bomberman.Game;
import Bomberman.entities.Entity;
import Bomberman.entities.character.Bomber;
import Bomberman.graphics.Sprite;

public class ReverseDirectionItem extends Item
{

    public ReverseDirectionItem(int x, int y, Sprite sprite)
    {
        super(x, y, sprite);
    }


    @Override
    public boolean collide(Entity e)
    {
        if (e instanceof Bomber)
        { // kiểm tra entity có phải là bomberman ko
            // add thêm  sức mạnh của flame
            ((Bomber) e).addPowerup(this);
            remove();
            return true;
        }

        return false;
    }

    @Override
    public void setValues()
    {

        Game.setReverser();
    }

}

