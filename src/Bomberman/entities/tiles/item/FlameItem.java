package Bomberman.entities.tiles.item;

import Bomberman.Game;
import Bomberman.entities.Entity;
import Bomberman.entities.character.Bomber;
import Bomberman.graphics.Sprite;

public class FlameItem extends Item
{

    public FlameItem(int x, int y, Sprite sprite)
    {
        super(x, y, sprite);
    }

    @Override
    public boolean collide(Entity e)
    {
        // xử lý Bomber ăn Item
        //  xử lý Bomber ăn Item  cộng flam
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

        this.setActive(true);
        // thăng bán kính bom
        // Game.addBombRate(1);
        Game.addBombRadius(1);
    }

}
