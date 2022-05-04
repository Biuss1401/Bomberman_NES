package Bomberman.entities.character.enemy.AI;

import java.util.ArrayList;

import Bomberman.Board;
import Bomberman.Game;
import Bomberman.entities.character.Bomber;
import Bomberman.entities.character.enemy.Enemy;

/**
 * tìm đến bomber dựa trên khoảng cách
 */
public class AIMedium extends AI
{
    Bomber _bomber;
    Enemy _e;
    Board _board;
    int radius = Game.getBombRadius();

    public AIMedium(Bomber _bomber, Enemy _e, Board _board)
    {
        this._bomber = _bomber;
        this._e = _e;
        this._board = _board;
    }

    /**
     * tính toán tọa độ bomber so với enemy
     *
     * @return hướng đi
     */

    public int calculateColDirection()
    {
        if (_bomber.getXTile() < _e.getXTile())
        {
            return 3;
        }
        else if (_bomber.getXTile() > _e.getXTile())
        {
            return 1;
        }

        return -1;
    }

    public int calculateRowDirection()
    {
        if (_bomber.getYTile() < _e.getYTile())
        {
            return 0;
        }
        else if (_bomber.getYTile() > _e.getYTile())
        {
            return 2;
        }
        return -1;
    }

    /**
     * x1 x2 x3
     * y1   3  2  1
     * y2   4  e  0
     * y3   5  6  7
     *
     * @param Xb toa do x bom
     * @param Yb toa do y bom
     * @return vị chí của bọm trong khu vực dò của enenmy
     * @return -1 nếu không thuộc phạm vi dò
     */

    public int detectBombinRanger(int Xb, int Yb)
    {

        int Xe = this._e.getXTile();
        int Ye = this._e.getYTile();
        // ngang
        if (Yb == Ye)
        {
            //  bom o ben phai
            if ((Xb - Xe) > 0 && (Xb - Xe) <= radius)
            {
                return 0; // phai
            }
            // bom o ben trai
            if ((Xe - Xb) > 0 && (Xe - Xb) <= radius)
            {
                return 4;// trai
            }
        }
        // doc
        if (Xb == Xe)
        {

            //  bom oi duoi
            if ((Yb - Ye) > 0 && (Yb - Ye) <= radius)
            {
                return 6; // duoi
            }
            //  bom o ben tren
            if ((Ye - Yb) > 0 && (Ye - Yb) <= radius)
            {
                return 2;//tren
            }
        }


        // goc ben tren
        if ((Ye - Yb > 0) && (Ye - Yb <= radius))
        {
            // bom o phai tren
            if ((Xb - Xe) > 0 && (Xb - Xe) <= radius)
            {
                return 1;//phair tren
            }
            // bom o trai tren
            if ((Xe - Xb) > 0 && (Xe - Xb) <= radius)
            {
                return 3; // trai tren
            }
        }
        //goc ben duoi
        if ((Yb - Ye > 0) && (Yb - Ye <= radius))
        {
            // xet goc duoi phai
            if ((Xb - Xe) > 0 && (Xb - Xe) <= radius)
            {
                return 7;//duoi phai
            }
            // duoi trai
            if ((Xe - Xb) > 0 && (Xe - Xb) <= radius)
            {
                return 5; // trai duoi
            }
        }
        return -1;
    }


    @Override
    public int calculateDirection()
    {
        int Xe = this._e.getXTile();
        int Ye = this._e.getYTile();

        // top left
               /*
                    tl      tm      tr

                     l      m       r

                     bl     bm      br


               */
        // canGo  -1 tương ứng với cango[4]
        //0     1      2    3   4 (-1)
        boolean[] canGo = {true, true, true, true, true};

        ArrayList<Integer> way = new ArrayList<Integer>();

        int thread = 0;

        // duyet toan bo list bom cua bang
        for (int i = 0; i < this._board.getBombs().size(); i++)
        {
            // phat hiện bom
            int Xb = this._board.getBombs().get(i).getXTile();
            int Yb = this._board.getBombs().get(i).getYTile();

            // xét những quả bom trong miền sét
            if (this.detectBombinRanger(Xb, Yb) != -1)
            {
                thread++;
                // tùy trường hợp thì mình sẽ sét cách  hướng KHÔNG THỂ ĐI
                //  chú ý 4 thay cho -1;
                switch (this.detectBombinRanger(Xb, Yb))
                {
                    case 0:
                        if (Xb - Xe == this.radius)
                        {
                            canGo[4] = canGo[1] = false;
                        } else
                        {
                            canGo[4] = canGo[1] = canGo[3] = false;
                        }

                        break;
                    case 1:
                        canGo[1] = canGo[0] = false;
                        break;
                    case 2:
                        if (Ye - Yb == this.radius)
                        {
                            canGo[4] = canGo[0] = false;
                        } else
                        {
                            canGo[4] = canGo[0] = canGo[2] = false;

                        }
                        break;
                    case 3:
                        canGo[3] = canGo[0] = false;
                        break;
                    case 4:
                        if (Xe - Xb == this.radius)
                        {
                            canGo[4] = canGo[3] = false;
                        } else
                        {
                            canGo[4] = canGo[1] = canGo[3] = false;
                        }
                        break;
                    case 5:
                        canGo[2] = canGo[3] = false;
                        break;
                    case 6:
                        if (Yb - Ye == this.radius)
                        {
                            canGo[4] = canGo[2] = false;
                        } else
                        {
                            canGo[4] = canGo[0] = canGo[2] = false;
                        }
                        break;
                    case 7:
                        canGo[1] = canGo[2] = false;
                        break;
                }
            }

        }
        for (int k = 0; k < canGo.length; k++)
        {
            if (canGo[k] == true)
            {
                if (k == 4)
                {
                    way.add(-1); // chuyển 4 là -1
                } else
                {
                    way.add(k);
                }

            }
        }
        // nếu ko có nguy  hiểm
        if (thread == 0)
        {
            //  return -1;

            int vertical = random.nextInt(2);
            if (vertical == 1)
            {
                int v = calculateRowDirection();
                if (v != -1)
                {
                    return v;
                }
                else
                {
                    return calculateColDirection();
                }

            } else
            {
                int h = calculateColDirection();

                if (h != -1)
                {
                    return h;
                }
                else
                {
                    return calculateRowDirection();
                }
            }
        }

        // trường hợp không có đường đi hợp lý
        // thì sẽ cho ramdom
        if (way.size() == 0)
        {
            return random.nextInt(4);
        }
        // tồn tại đường duy nhất
        if (way.size() == 1)
        {
            return way.get(0);
        }
        return way.get(random.nextInt(way.size()));
    }
}
