/**
 *  Created by Nikita Bondar 24.08.2016.
 *  Tic-tac-toe(5x5). (Крестики-нолики)
 */

import java.util.Random;
import java.util.Scanner;

public class game {
    
    static char field[][] = new char[5][5];
    
    static final char playerDot = 'X';
    static final char aiDot = 'O';
    static final char emptyDot = '*';
    
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    
    public static void main (String[] args) {
        initField();
        printField();
        
        while (true) {
            playerTurn();
            printField();
            
            if (checkWin(playerDot)) {
                System.out.println("Игрок победил.");
                break;
            }
            
            if (isFieldFull()) {
                System.out.println("Ничья.");
                break;
            }
            
            aiTurn();
            printField();
            
            if (checkWin(aiDot)) {
                System.out.println("Компьютер победил.");
                break;
            }
            
            if (isFieldFull()) {
                System.out.println("Ничья.");
                break;
            }
        }
    }
    
    //  Заполнение поля
    public static void initField () {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                field[i][j] = emptyDot;
            }
        }
    }
    
    //  Печать поля
    public static void printField () {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    //  Ставит значение на поле
    public static void setXO (int _x, int _y, char _xo) {
        field[_x][_y] = _xo;
    }
    
    //  Ход игрока
    public static void playerTurn () {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X(строка) Y(столбец) (1-5): ");
            x = sc.nextInt();
            y = sc.nextInt();
        } while(!isCellEmpty(x - 1, y - 1));
        System.out.println();
        setXO(x - 1, y - 1, playerDot);
    }
    
    //  Ход компьютера
    public static void aiTurn () {
        int x, y, k, s, countHor = 0, countVer = 0, countDiag = 0, countDiagS = 0, aiCountHor = 0, aiCountVer = 0, aiCountDiag = 0, aiCountDiagS = 0, flag = 0;
        
        //  Блок хода игроку
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                //  Горизонтали
                if ( field[i][j] == playerDot ) {
                    k = j;
                    if ( k < 4 ) {
                        while ( k < 5 && field[i][k] == playerDot ) {
                            countHor++;
                            k++;
                        }
                        if ( countHor >= 2 && isCellEmpty(i, k) ) {
                            setXO(i, k, aiDot);
                            flag = 1;
                        }
                    }
                    k = 0;
                }
                if (flag != 1 && countHor >= 2 && j > 0) {
                    s = j - 1;
                    if (isCellEmpty(i, s)) {
                        setXO(s, 4-s, aiDot);
                        flag = 1;
                    }
                }
                countHor = 0;
                if (flag == 1) {
                    break;
                }
                //  Вертикали
                if ( field[j][i] == playerDot ) {
                    k = j;
                    if ( k < 4 ) {
                        while ( k < 5 && field[k][i] == playerDot ) {
                            countVer++;
                            k++;
                        }
                        if ( countVer >= 2 && isCellEmpty(k, i) ) {
                            setXO(k, i, aiDot);
                            flag = 1;
                        }
                    }
                    k = 0;
                }
                if (flag != 1 && countVer >= 2 && j > 0) {
                    s = j - 1;
                    if (isCellEmpty(s, i)) {
                        setXO(s, i, aiDot);
                        flag = 1;
                    }
                }
                countVer = 0;
                if (flag == 1) {
                    break;
                }
                //  Первая диагональ
                if ( field[j][j] == playerDot ) {
                    k = j;
                    if ( k < 4 ) {
                        while ( k < 5 && field[k][k] == playerDot ) {
                            countDiag++;
                            k++;
                        }
                        if ( countDiag >= 2 && isCellEmpty(k, k) ) {
                            setXO(k, k, aiDot);
                            flag = 1;
                        }
                    }
                    k = 0;
                }
                if (flag != 1 && countDiag >= 2 && j > 0) {
                    s = j - 1;
                    if (isCellEmpty(s, s)) {
                        setXO(s, s, aiDot);
                        flag = 1;
                    }
                }
                countDiag = 0;
                if (flag == 1) {
                    break;
                }
                //  Вторая диагональ
                if ( field[j][4-j] == playerDot ) {
                    k = j;
                    if ( k < 4 ) {
                        while ( k < 5 && field[k][4-k] == playerDot ) {
                            countDiagS++;
                            k++;
                        }
                        if ( countDiagS >= 2 && isCellEmpty(k, 4-k) ) {
                            setXO(k, 4-k, aiDot);
                            flag = 1;
                        }
                    }
                    k = 0;
                }
                if (flag != 1 && countDiagS >= 2 && j > 0) {
                    s = j - 1;
                    if (isCellEmpty(s, 4-s)) {
                        setXO(s, 4-s, aiDot);
                        flag = 1;
                    }
                }
                countDiagS = 0;
                if (flag == 1) {
                    break;
                }
            }
            if (flag == 1) {
                break;
            }
        }
        
        if (flag == 0) {
            do {
                x = rand.nextInt(5);
                y = rand.nextInt(5);
            } while(!isCellEmpty(x, y));
            setXO(x, y, aiDot);
        }
    }
    
    //  Проверяет ячейку на пустоту
    public static boolean isCellEmpty (int _x, int _y) {
        if (_x < 0 || _y < 0 || _x > 4 || _y > 4) {
            return false;
        }
        if ( field[_x][_y] == '*' ) {
            return true;
        } else {
            return false;
        }
    }
    
    //  Проверка победы
    public static boolean checkWin (char _xo) {
        
        int countHor = 0, countVer = 0, countDiag = 0, countDiagS = 0, k;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                //  Горизонтали
                if ( field[i][j] == _xo ) {
                    k = j;
                    if ( k < 4 ) {
                        while ( k < 5 && field[i][k] == _xo ) {
                            countHor++;
                            k++;
                        }
                        if ( countHor == 4 ) {
                            return true;
                        }
                    }
                    k = 0;
                    countHor = 0;
                }
                //  Вертикали
                if ( field[j][i] == _xo ) {
                    k = j;
                    if ( k < 4 ) {
                        while ( k < 5 && field[k][i] == _xo ) {
                            countVer++;
                            k++;
                        }
                        if ( countVer == 4 ) {
                            return true;
                        }
                    }
                    k = 0;
                    countVer = 0;
                }
                //  Первая диагональ
                if ( field[j][j] == _xo ) {
                    k = j;
                    if ( k < 4 ) {
                        while ( k < 5 && field[k][k] == _xo ) {
                            countDiag++;
                            k++;
                        }
                        if ( countDiag == 4 ) {
                            return true;
                        }
                    }
                    k = 0;
                    countDiag = 0;
                }
                //  Вторая диагональ
                if ( field[j][4-j] == _xo ) {
                    k = j;
                    if ( k < 4 ) {
                        while ( k < 5 && field[k][4-k] == _xo ) {
                            countDiagS++;
                            k++;
                        }
                        if ( countDiagS == 4 ) {
                            return true;
                        }
                    }
                    k = 0;
                    countDiagS = 0;
                }
            }
        }
        
        return false;
    }
    
    //  Проверяет поле на заполненность
    public static boolean isFieldFull () {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ( field[i][j] == '*' ) {
                    return false;
                }
            }
        }
        return true;
    }
    
}




