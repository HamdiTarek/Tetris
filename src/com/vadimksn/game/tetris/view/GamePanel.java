package com.vadimksn.game.tetris.view;


import com.vadimksn.game.tetris.controller.GameController;
import com.vadimksn.game.tetris.controller.PaintController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GamePanel extends JPanel implements ActionListener {
    public static final int ROW_COUNT = 20;
    public static final int COLUMNS_COUNT = 10;
    public static final int BORDER_SIZE = 5;
    public static final int TILE_SIZE = 25;
    public static final int PANEL_WIDTH = TILE_SIZE * COLUMNS_COUNT + BORDER_SIZE * 2;
    public static final int PANEL_HEIGHT = TILE_SIZE * ROW_COUNT + BORDER_SIZE * 2;
    private static final Color BASE_DARK_COLOR = BaseColors.BASE_DARK_COLOR.getColor();
    private static final Color BASE_FONT_COLOR = BaseColors.BASE_FONT_COLOR.getColor();
    private static final Color BASE_LIGHT_COLOR = BaseColors.BASE_LIGHT_COLOR.getColor();
    private static final Color BASE_LIGHT_COLOR2 = BaseColors.BASE_LIGHT_COLOR2.getColor();
    private static final GamePanel INSTANCE = new GamePanel();
    private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 12);
    private GameController gameController = GameController.getINSTANCE();
    private Timer timer;
    private JTextField textField = new JTextField(10);

    private GamePanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BASE_DARK_COLOR);
        timer = new Timer(gameController.getGameSpeed(), this);
//        timer = new Timer(0, this);
        timer.start();

    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(BORDER_SIZE, BORDER_SIZE);

        if (gameController.isGameRunning()) {
            g.setColor(BASE_LIGHT_COLOR2);
            for (int x = 0; x < COLUMNS_COUNT; x++) {
                for (int y = 0; y < ROW_COUNT; y++) {
                    if (y > 0) g.drawLine(0, y * TILE_SIZE, TILE_SIZE * COLUMNS_COUNT, y * TILE_SIZE);
                    if (x > 0) g.drawLine(x * TILE_SIZE, 0, x * TILE_SIZE, TILE_SIZE * ROW_COUNT);
                }
            }

            PaintController.getINSTANCE().paintGameMas(gameController.getGameMas(), g);
            PaintController.getINSTANCE().paintShape(gameController.getCurrentShape(), g);

        } else if (!gameController.isGameOver() && !gameController.isGameRunning()) {
            PaintController.getINSTANCE().paintStartView(g);


//            g.setColor(BASE_FONT_COLOR);
//            Font font = new Font("Tahoma", Font.BOLD, 85);
//            g.setFont(font);
//            g.drawString("GAME", 2, TILE_SIZE * 5);
//            g.drawString("OVER", 5, TILE_SIZE * 8);
//            textField.setBounds(TILE_SIZE*3,TILE_SIZE*10,TILE_SIZE*5,TILE_SIZE*2);
//            textField.setBackground(BASE_DARK_COLOR);
//            textField.setForeground(BASE_LIGHT_COLOR);
//            textField.setFont(SMALL_FONT);
////            textField.set
//            add(textField);

        }
        g.setColor(BASE_LIGHT_COLOR);
        g.drawRoundRect(0, 0, TILE_SIZE * COLUMNS_COUNT, TILE_SIZE * ROW_COUNT, 10, 10);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        gameController.start();
        repaint();
    }

    public static int getRowCount() {
        return ROW_COUNT;
    }

    public static int getColumnsCount() {
        return COLUMNS_COUNT;
    }

    public static int getBorderSize() {
        return BORDER_SIZE;
    }

    public static int getTileSize() {
        return TILE_SIZE;
    }

    public static int getPanelWidth() {
        return PANEL_WIDTH;
    }

    public static int getPanelHeight() {
        return PANEL_HEIGHT;
    }

    public static Color getBaseDarkColor() {
        return BASE_DARK_COLOR;
    }

    public static Color getBaseLightColor() {
        return BASE_LIGHT_COLOR;
    }

    public static Color getBaseLightColor2() {
        return BASE_LIGHT_COLOR2;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public static GamePanel getINSTANCE() {
        return INSTANCE;
    }
}
