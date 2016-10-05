package com.bondar;

/**
 * Created by Nikita Bondar 5.10.2016.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;

public class Main extends JFrame {

    final String TITLE = "Крестики - нолики (3х3)";
    final int START_LOCATION_X = 600;
    final int START_LOCATION_Y = 300;
    final int WINDOW_SIZE = 400;
    final int WINDOW_DX = 7;
    final int WINDOW_DY = 55;
    final int FIELD_SIZE = 3;
    final int CELL_SIZE = WINDOW_SIZE / FIELD_SIZE;
    Canvas canvas;
    Random rand = new Random();

    public static void main(String[] args) {
	    new Main().game();
    }

    public void game() {
        setTitle(TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(START_LOCATION_X, START_LOCATION_Y, WINDOW_SIZE + WINDOW_DX, WINDOW_SIZE + WINDOW_DY);
        canvas = new Canvas();
        canvas.setBackground(Color.WHITE);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX() / CELL_SIZE;
                int y = e.getY() / CELL_SIZE;
                if (isCellEmpty(x, y) && !canvas.gameOver) {
                    canvas.field[x][y] = canvas.PLAYER_DOT;
                    canvas.repaint();
                    if (checkWin(canvas.PLAYER_DOT)) {
                        canvas.playerWin = true;
                        canvas.gameOver = true;
                        canvas.repaint();
                    } else if (isFieldFull()) {
                        canvas.draft = true;
                        canvas.gameOver = true;
                        canvas.repaint();
                    } else {
                        aiTurn();
                        canvas.repaint();
                        if (checkWin(canvas.AI_DOT)) {
                            canvas.aiWin = true;
                            canvas.gameOver = true;
                            canvas.repaint();
                        } else if (isFieldFull()) {
                            canvas.draft = true;
                            canvas.gameOver = true;
                            canvas.repaint();
                        }
                    }
                }
            }
        });

        JPanel bp = new JPanel();
        bp.setLayout(new GridLayout());

        JButton start = new JButton("Новая игра");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initField();
                canvas.playerWin = false;
                canvas.aiWin = false;
                canvas.draft = false;
                canvas.repaint();
            }
        });

        JButton exit = new JButton("Выход");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        bp.add(start);
        bp.add(exit);

        add(BorderLayout.CENTER, canvas);
        add(BorderLayout.SOUTH, bp);

        setVisible(true);
        initField();
    }

    public boolean isCellEmpty(int x, int y) {
        if (canvas.field[x][y] == canvas.EMPTY_DOT) {
            return true;
        }
        return false;
    }

    public boolean checkWin(char _xo) {
        int countHor = 0, countVer = 0, countDiag = 0, countDiagS = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //  Горизонтали
                if ( canvas.field[i][j] == _xo ) {
                    countHor++;
                }
                //  Вертикали
                if ( canvas.field[j][i] == _xo ) {
                    countVer++;
                }
                //  Первая диагональ
                if ( canvas.field[j][j] == _xo ) {
                    countDiag++;
                }
                //  Вторая диагональ
                if ( canvas.field[j][2-j] == _xo ) {
                    countDiagS++;
                }
                if (countHor == 3) {
                    return true;
                }
                if (countVer == 3) {
                    return true;
                }
                if (countDiag == 3) {
                    return true;
                }
                if (countDiagS == 3) {
                    return true;
                }
            }
            countHor = 0;
            countVer = 0;
            countDiag = 0;
            countDiagS = 0;
        }

        return false;
    }

    public boolean isFieldFull() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if ( canvas.field[i][j] == '*' ) {
                    return false;
                }
            }
        }
        return true;
    }

    public void aiTurn() {
        int x, y, countHor = 0, countVer = 0, countDiag = 0, countDiagS = 0, aiCountHor = 0, aiCountVer = 0, aiCountDiag = 0, aiCountDiagS = 0, flag = 0;
        //  Первый ход компьютера (по выигрышной стратегии)
        if (isCellEmpty(1, 1)) {
            canvas.field[1][1] = canvas.AI_DOT;
            flag = 1;
        }
        //  Проверка на возможную победу
        if (flag == 0) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    //  Проверка горизонталей
                    if ( canvas.field[i][j] == canvas.PLAYER_DOT ) {
                        countHor++;
                    }
                    if ( canvas.field[i][j] == canvas.AI_DOT ) {
                        aiCountHor++;
                    }
                    if ( aiCountHor == 2 && countHor == 0 ) {
                        for (int k = 0; k < 3; k++) {
                            if (isCellEmpty(i, k)) {
                                canvas.field[i][k] = canvas.AI_DOT;
                                flag = 1;
                                break;
                            }
                        }
                    }
                    if (flag == 1) {
                        break;
                    }
                    //  Проверка вертикалей
                    if ( canvas.field[j][i] == canvas.PLAYER_DOT ) {
                        countVer++;
                    }
                    if ( canvas.field[j][i] == canvas.AI_DOT ) {
                        aiCountVer++;
                    }
                    if ( aiCountVer == 2 && countVer == 0 ) {
                        for (int k = 0; k < 3; k++) {
                            if (isCellEmpty(k, i)) {
                                canvas.field[k][i] = canvas.AI_DOT;
                                flag = 1;
                                break;
                            }
                        }
                    }
                    if (flag == 1) {
                        break;
                    }
                    //  Проверка диагонали
                    /*  Первая диагональ  */
                    if ( canvas.field[j][j] == canvas.PLAYER_DOT ) {
                        countDiag++;
                    }
                    if ( canvas.field[j][j] == canvas.AI_DOT ) {
                        aiCountDiag++;
                    }
                    if ( aiCountDiag == 2 && countDiag == 0 ) {
                        for (int k = 0; k < 3; k++) {
                            if (isCellEmpty(k, k)) {
                                canvas.field[k][k] = canvas.AI_DOT;
                                flag = 1;
                                break;
                            }
                        }
                    }
                    if (flag == 1) {
                        break;
                    }
                    /*  Вторая диагональ  */
                    if ( canvas.field[j][2-j] == canvas.PLAYER_DOT ) {
                        countDiagS++;
                    }
                    if ( canvas.field[j][2-j] == canvas.AI_DOT ) {
                        aiCountDiagS++;
                    }
                    if ( aiCountDiagS == 2 && countDiagS == 0 ) {
                        for (int k = 0; k < 3; k++) {
                            if (isCellEmpty(k, 2 - k)) {
                                canvas.field[k][2 - k] = canvas.AI_DOT;
                                flag = 1;
                                break;
                            }
                        }
                    }
                    if (flag == 1) {
                        break;
                    }
                }
                aiCountHor = 0;
                aiCountVer = 0;
                aiCountDiag = 0;
                aiCountDiagS = 0;
                countHor = 0;
                countVer = 0;
                countDiag = 0;
                countDiagS = 0;
                if (flag == 1) {
                    break;
                }
            }
        }

        //  Защита от игрока
        if (flag == 0) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    //  Блок горизонталей
                    if ( canvas.field[i][j] == canvas.PLAYER_DOT ) {
                        countHor++;
                    }
                    if ( canvas.field[i][j] == canvas.AI_DOT ) {
                        aiCountHor++;
                    }
                    if ( countHor == 2 && aiCountHor == 0) {
                        for (int k = 0; k < 3; k++) {
                            if (isCellEmpty(i, k)) {
                                canvas.field[i][k] = canvas.AI_DOT;
                                flag = 1;
                                break;
                            }
                        }
                    }
                    if (flag == 1) {
                        break;
                    }
                    //  Блок вертикалей
                    if ( canvas.field[j][i] == canvas.PLAYER_DOT ) {
                        countVer++;
                    }
                    if ( canvas.field[j][i] == canvas.AI_DOT ) {
                        aiCountVer++;
                    }
                    if ( countVer == 2 && aiCountVer == 0 ) {
                        for (int k = 0; k < 3; k++) {
                            if (isCellEmpty(k, i)) {
                                canvas.field[k][i] = canvas.AI_DOT;
                                flag = 1;
                                break;
                            }
                        }
                    }
                    if (flag == 1) {
                        break;
                    }
                    //  Блок диагоналей
                    /*  Первая диагональ  */
                    if ( canvas.field[j][j] == canvas.PLAYER_DOT ) {
                        countDiag++;
                    }
                    if ( canvas.field[j][j] == canvas.AI_DOT ) {
                        aiCountDiag++;
                    }
                    if ( countDiag == 2 && aiCountDiag == 0 ) {
                        for (int k = 0; k < 3; k++) {
                            if (isCellEmpty(k, k)) {
                                canvas.field[k][k] = canvas.AI_DOT;
                                flag = 1;
                                break;
                            }
                        }
                    }
                    if (flag == 1) {
                        break;
                    }
                    /*  Вторая диагональ  */
                    if ( canvas.field[j][2-j] == canvas.PLAYER_DOT ) {
                        countDiagS++;
                    }
                    if ( canvas.field[j][2-j] == canvas.AI_DOT ) {
                        aiCountDiagS++;
                    }
                    if ( countDiagS == 2 && aiCountDiagS == 0 ) {
                        for (int k = 0; k < 3; k++) {
                            if (isCellEmpty(k, 2 - k)) {
                                canvas.field[k][2 - k] = canvas.AI_DOT;
                                flag = 1;
                                break;
                            }
                        }
                    }
                    if (flag == 1) {
                        break;
                    }
                }
                countHor = 0;
                countVer = 0;
                countDiag = 0;
                countDiagS = 0;
                aiCountHor = 0;
                aiCountVer = 0;
                aiCountDiag = 0;
                aiCountDiagS = 0;
                if (flag == 1) {
                    break;
                }
            }
        }

        //  Если защищать нечего, то ходим по выигрышной стратегии (на этот момент у игрока было два хода)
        if (flag == 0) {
            if (isCellEmpty(0, 0)) {
                if ((isCellEmpty(0, 1) && !isCellEmpty(1, 0)) || (isCellEmpty(1, 0) && !isCellEmpty(0, 1))) {
                    canvas.field[0][0] = canvas.AI_DOT;
                    flag = 1;
                }
            }
        }
        if (flag == 0) {
            if (isCellEmpty(0, 2)) {
                if ((isCellEmpty(0, 1) && !isCellEmpty(1, 2)) || (isCellEmpty(1, 2) && !isCellEmpty(0, 1))) {
                    canvas.field[0][2] = canvas.AI_DOT;
                    flag = 1;
                }
            }
        }
        //  Не уверен, что до этого момента доходит :(
        if (flag == 0) {
            if (isCellEmpty(2, 2)) {
                if ((isCellEmpty(2, 1) && !isCellEmpty(1, 2)) || (isCellEmpty(1, 2) && !isCellEmpty(2, 1))) {
                    canvas.field[2][2] = canvas.AI_DOT;
                    flag = 1;
                }
            }
        }
        if (flag == 0) {
            if (isCellEmpty(2, 0)) {
                if ((isCellEmpty(1, 0) && !isCellEmpty(2, 1)) || (isCellEmpty(2, 1) && !isCellEmpty(1, 0))) {
                    canvas.field[2][0] = canvas.AI_DOT;
                    flag = 1;
                }
            }
        }

        if (flag == 0) {
            do {
                x = rand.nextInt(3);
                y = rand.nextInt(3);
            } while(!isCellEmpty(x, y));
            canvas.field[x][y] = canvas.AI_DOT;
        }
    }

    public void initField() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                canvas.field[i][j] = canvas.EMPTY_DOT;
            }
        }
        canvas.gameOver = false;
    }

}
