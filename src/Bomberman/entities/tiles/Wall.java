package Bomberman.entities.tiles;

import Bomberman.Game;
import Bomberman.entities.Entity;
import Bomberman.entities.character.Bomber;
import Bomberman.graphics.Sprite;

public class Wall extends Tile
{

    public Wall(int x, int y, Sprite sprite)
    {
        super(x, y, sprite);
    }

    @Override
    public boolean collide(Entity e)
    {
        return false;
    }

}
