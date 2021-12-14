package Bomberman.gui;

import Bomberman.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.Frame;

/**
 * Swing Panel chứa cảnh game
 */
public class GamePanel extends JPanel
{

    private Game _game;

    public GamePanel(Frame frame)
    {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));

        _game = new Game((Bomberman.gui.Frame) frame);

        add(_game);

        _game.setVisible(true);

        setVisible(true);
        setFocusable(true);

    }

    public Game getGame()
    {
        return _game;
    }

    // thêm method change size
    public void changeSize()
    {
        setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
        revalidate();
        repaint();
    }


}