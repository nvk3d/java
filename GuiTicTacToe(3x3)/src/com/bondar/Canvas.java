package com.bondar;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by nvk3d on 05.10.16.
 */

public class Canvas extends JPanel {

    final int FIELD_SIZE = 3;
    final int WINDOW_SIZE = 400;
    final int CELL_SIZE = WINDOW_SIZE / FIELD_SIZE;
    char[][] field = new char[FIELD_SIZE][FIELD_SIZE];
    final char PLAYER_DOT = 'X';
    final char AI_DOT = 'O';
    final char EMPTY_DOT = '*';
    boolean playerWin = false;
    boolean aiWin = false;
    boolean draft = false;
    boolean gameOver;
    final String PLAYER_WIN = "ТЫ ПОБЕДИЛ!";
    final String AI_WIN = "КОМПЬЮТЕР ПОБЕДИЛ!";
    final String DRAFT = "НИЧЬЯ!";
    final int WINDOW_DX = 7;
    final int WINDOW_DY = 55;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(int i = 0; i < FIELD_SIZE - 1; i++) {
            g.drawLine(0, (i + 1) * CELL_SIZE, WINDOW_SIZE, (i + 1) * CELL_SIZE);
            g.drawLine((i + 1) * CELL_SIZE, 0, (i + 1) * CELL_SIZE, WINDOW_SIZE);
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        for (int y = 0; y < FIELD_SIZE; y++) {
            for (int x = 0; x < FIELD_SIZE; x++) {
                if (field[x][y] == PLAYER_DOT) {
                    g.setColor(Color.green);
                    g2.draw(new Line2D.Float(x * CELL_SIZE + CELL_SIZE / 4, y * CELL_SIZE + CELL_SIZE / 4, (x + 1) * CELL_SIZE - CELL_SIZE / 4, (y + 1) * CELL_SIZE - CELL_SIZE / 4));
                    g2.draw(new Line2D.Float(x*CELL_SIZE+CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4, (x+1)*CELL_SIZE-CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4));
                }
                if (field[x][y] == AI_DOT) {
                    g.setColor(Color.ORANGE);
                    g2.draw(new Ellipse2D.Float(x * CELL_SIZE + CELL_SIZE / 4, y * CELL_SIZE + CELL_SIZE / 4, CELL_SIZE / 2, CELL_SIZE / 2));
                }
            }
        }
        if (gameOver && playerWin) {
            FontMetrics fm = g.getFontMetrics();
            g.setColor(Color.MAGENTA);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
            g.drawString(PLAYER_WIN, (WINDOW_SIZE - fm.stringWidth(PLAYER_WIN)) / 2 - WINDOW_DX * 10, WINDOW_SIZE / 2);
        }
        if (gameOver && aiWin) {
            FontMetrics fm = g.getFontMetrics();
            g.setColor(Color.MAGENTA);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
            g.drawString(AI_WIN, (WINDOW_SIZE - fm.stringWidth(AI_WIN)) / 2 - WINDOW_DX * 15, WINDOW_SIZE / 2);
        }
        if (gameOver && draft) {
            FontMetrics fm = g.getFontMetrics();
            g.setColor(Color.MAGENTA);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
            g.drawString(DRAFT, (WINDOW_SIZE - fm.stringWidth(DRAFT)) / 2 - WINDOW_DX * 4, WINDOW_SIZE / 2);
        }
    }
}
