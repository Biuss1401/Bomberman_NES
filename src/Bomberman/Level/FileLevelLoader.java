package Bomberman.Level;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import Bomberman.Board;
import Bomberman.Game;
import Bomberman.entities.LayeredEntity;
import Bomberman.entities.character.Bomber;
import Bomberman.entities.character.enemy.Balloon;
import Bomberman.entities.character.enemy.Doll;
import Bomberman.entities.character.enemy.Kondoria;
import Bomberman.entities.character.enemy.Minvo;
import Bomberman.entities.character.enemy.Oneal;
import Bomberman.entities.tiles.Grass;
import Bomberman.entities.tiles.Portal;
import Bomberman.entities.tiles.Wall;
import Bomberman.entities.tiles.Destroyable.Brick;
import Bomberman.entities.tiles.item.BombItem;
import Bomberman.entities.tiles.item.FlameItem;
import Bomberman.entities.tiles.item.LiveItem;
import Bomberman.entities.tiles.item.ReverseDirectionItem;
import Bomberman.entities.tiles.item.SpeedItem;
import Bomberman.exceptions.LoadLevelException;
import Bomberman.graphics.Screen;
import Bomberman.graphics.Sprite;

public class FileLevelLoader extends LevelLoader
{

    /**
     * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
     * từ ma trận bản đồ trong tệp cấu hình
     */
    public static char[][] _map;


    public FileLevelLoader(Board board, int level) throws LoadLevelException
    {
        super(board, level);
    }

    @Override
    public void loadLevel(int level) throws LoadLevelException
    {
        try
        {

            // chuyển đổi level từ int sang string
            URL absPath = FileLevelLoader.class.getResource("/levels/Level" + Integer.toString(level) + ".txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(absPath.openStream()));

            String data = in.readLine(); // đọc  hàng đầu tiền
            /**
             * dùng substring để tách  data dòng đầu
             * ví dụ  nó có dạng :1 13 31
             * 1 là level
             *  13 31 là hàng cột
             */
            _level = Integer.parseInt(data.substring(0, 1));    // tách string đọc  level
            _height = Integer.parseInt(data.substring(2, 4));// tách string ra đọc hàng
            _width = Integer.parseInt(data.substring(5, 7));    // tách string đọc cột

            // mình chỉ đọc đủ  số hàng rồi đóng file

            _map = new char[_height][_width];
            for (int i = 0; i < _height; i++)
            {
                data = in.readLine();

                for (int j = 0; j < _width; j++)
                {
                    _map[i][j] = data.charAt(j);
                }
                //this.boardGame.add(data);
            }
            in.close();

        } catch (IOException e)
        {
            throw new LoadLevelException("Error loading level " + level, e);
        }

    }

    @Override
    public void createEntities()
    {
        // TODO: tạo các Entity của màn chơi
        // TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

        // TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
        // TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình
        // thêm Wall

        for (int y = 0; y < _height; y++)
        {
            for (int x = 0; x < _width; x++)
            {

                int pos = x + y * _width;
                char printSprite = _map[y][x];
                // load hình ảnh
                switch (printSprite)
                {
                    // thêm tường
                    case '#':
                        _board.addEntity(pos, new Wall(x, y, Sprite.wall));
                        break;
                    // thêm cái brick . gạch có thể phá
                    case '*':
                        _board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new Brick(x, y, Sprite.brick)));
                        break;

                    //   nhân vật bomber
                    case 'p':
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        _board.addCharacter(new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        //vị chí mặc định ô đầu tiên
                        Screen.setOffset(0, 0);
                        break;
                    //Item
                    // đảo ngược hướng di chuyển
                    case 'r':
                        _board.addEntity(pos
                                , new LayeredEntity(x, y, new Grass(x, y, Sprite.grass)
                                        , new ReverseDirectionItem(x, y, Sprite.powerup_flamepass),
                                        new Brick(x, y, Sprite.brick)));
                        break;


                    // flame của bom
                    case 'f':
                        _board.addEntity(pos
                                , new LayeredEntity(x, y, new Grass(x, y, Sprite.grass)
                                        , new FlameItem(x, y, Sprite.powerup_flames),
                                        new Brick(x, y, Sprite.brick)));
                        break;

                    // so luong bom
                    case 'b':
                        _board.addEntity(pos,
                                new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new BombItem(x, y, Sprite.powerup_bombs), new Brick(x, y, Sprite.brick)));
                        break;
                    // tốc độ
                    case 's':
                        _board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new SpeedItem(x, y, Sprite.powerup_speed), new Brick(x, y, Sprite.brick)));
                        break;

                    // live
                    case 'l':
                        _board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new LiveItem(x, y, Sprite.powerup_detonator), new Brick(x, y, Sprite.brick)));
                        break;


                    // cổng portal
                    case 'x':
                        _board.addEntity(pos
                                , new LayeredEntity(x, y, new Grass(x, y, Sprite.grass)
                                        , new Portal(x, y, _board, Sprite.portal), new Brick(x, y, Sprite.brick)));
                        break;
                    // bos ngu
                    case '1':
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        _board.addCharacter(new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        break;
                    // tạo boss ngu 2
                    case '2':
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        _board.addCharacter(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        break;
//                                      tao Doll
                    case '3':
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        _board.addCharacter(new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        break;
                    //Mivo
                    case '4':
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        _board.addCharacter(new Minvo(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        break;

                    // kondoria
                    case '5':
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        _board.addCharacter(new Kondoria(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        break;

                    // cỏ
                    default:
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        break;
                }
            }


        }

    }


}