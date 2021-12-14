package Bomberman.entities;

import java.util.ArrayList;

import Bomberman.graphics.IRender;
import Bomberman.graphics.Screen;
import Bomberman.graphics.Sprite;
import Bomberman.Level.Coordinates;

/**
 * Lớp đại diện cho tất cả thực thể trong game (Bomber, Enemy, Wall, Brick,...)
 */
public abstract class Entity implements IRender
{

    protected double _x, _y;
    protected boolean _removed = false;
    protected Sprite _sprite;

    /**
     * Phương thức này được gọi liên tục trong vòng lặp game,
     * mục đích để xử lý sự kiện và cập nhật trạng thái Entity
     */
    @Override
    public abstract void update();

    /**
     * Phương thức này được gọi liên tục trong vòng lặp game,
     * mục đích để cập nhật hình ảnh của entity theo trạng thái
     */
    @Override
    public abstract void render(Screen screen);

    public void remove()
    {
        _removed = true;
    }

    public boolean isRemoved()
    {
        return _removed;
    }

    public Sprite getSprite()
    {
        return _sprite;
    }

    /**
     * Phương thức này được gọi để xử lý khi hai entity va chạm vào nhau
     *
     * @param e
     * @return
     */
    public abstract boolean collide(Entity e);

    public double getX()
    {
        return _x;
    }

    public double getY()
    {
        return _y;
    }

    // tọa độ trong hệ tọa độ ô/
    public int getXTile()
    {
        return Coordinates.pixelToTile(_x + _sprite.SIZE / 2);
    }

    public int getYTile()
    {
        return Coordinates.pixelToTile(_y - _sprite.SIZE / 2);
    }
}
