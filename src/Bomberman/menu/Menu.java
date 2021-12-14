package Bomberman.menu;

import javax.swing.JMenuBar;

import Bomberman.gui.Frame;


public class Menu extends JMenuBar
{
    public Menu(Frame frame)
    {
        add(new Game(frame));
        add(new Options(frame));

    }

}