package Bomberman.entities.tiles;

import Bomberman.Board;
import Bomberman.entities.Entity;
import Bomberman.entities.character.Bomber;
import Bomberman.graphics.Sprite;

public class Portal extends Tile
{
    // theem bang
    public Board _board;

    // đã sửa lại contructor
    public Portal(int x, int y, Board board, Sprite sprite)
    {
        super(x, y, sprite);
        _board = board;
    }

    /**
     * @param e thực thể,
     * @return true nếu thực thể là bomber và đã giết hết enemy
     */

    @Override
    public boolean collide(Entity e)
    {

        if (e instanceof Bomber)
        {
            // kiểm tra bomber  giết hết quái chưa?
//
            if (_board.detectNoEnemies() == false)
            {
                return false;
            }
            // giết hết quái rồi thì có thể qua màn
            if (e.getXTile() == getX() && e.getYTile() == getY())
            {
                if (_board.detectNoEnemies())
                    _board.nextLevel();
            }

        }
        return true;
    }

}