package Bomberman.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import Bomberman.gui.*;
import Bomberman.input.IOClass;

public class Game extends JMenu
{


    public Frame frame;

    public Game(Frame frame)
    {
        super("Game");
        this.frame = frame;

        /*
         * New Game
         */
        JMenuItem newgame = new JMenuItem("New Game");
        newgame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newgame.addActionListener((ActionListener) new MenuActionListener(frame));
        add(newgame);


    }

    class MenuActionListener implements ActionListener
    {
        public Frame _frame;

        public MenuActionListener(Frame frame)
        {
            _frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {

            if (e.getActionCommand().equals("New Game"))
            {
                _frame.newGame();
            }

        }
    }

}
