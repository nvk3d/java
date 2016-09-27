/**
 *  Created by Nikita Bondar 24.08.2016.
 *  Tic-tac-toe(3x3). (Крестики-нолики)
 *  Доработал искусственный интеллект, действует по тактике, может не только блокировать ходы,
 *  но и выигрывать.
 */

import java.util.Random;
import java.util.Scanner;

public class game {

    static char field[][] = new char[3][3];
    
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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = emptyDot;
            }
        }
    }
    
    //  Печать поля
    public static void printField () {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
            System.out.println("Введите координаты в формате X(строка) Y(столбец) (1-3): ");
            x = sc.nextInt();
            y = sc.nextInt();
        } while(!isCellEmpty(x - 1, y - 1));
        System.out.println();
        setXO(x - 1, y - 1, playerDot);
    }
    
    //  Ход компьютера
    public static void aiTurn () {
        int x, y, countHor = 0, countVer = 0, countDiag = 0, countDiagS = 0, aiCountHor = 0, aiCountVer = 0, aiCountDiag = 0, aiCountDiagS = 0, flag = 0;
        //  Первый ход компьютера (по выигрышной стратегии)
        if (isCellEmpty(1, 1)) {
            setXO(1, 1, aiDot);
            flag = 1;
        }
        //  Проверка на возможную победу
        if (flag == 0) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    //  Проверка горизонталей
                    if ( field[i][j] == playerDot ) {
                        countHor++;
                    }
                    if ( field[i][j] == aiDot ) {
                        aiCountHor++;
                    }
                    if ( aiCountHor == 2 && countHor == 0 ) {
                        for (int k = 0; k < 3; k++) {
                            if (isCellEmpty(i, k)) {
                                setXO(i, k, aiDot);
                                flag = 1;
                                break;
                            }
                        }
                    }
                    if (flag == 1) {
                        break;
                    }
                    //  Проверка вертикалей
                    if ( field[j][i] == playerDot ) {
                        countVer++;
                    }
                    if ( field[j][i] == aiDot ) {
                        aiCountVer++;
                    }
                    if ( aiCountVer == 2 && countVer == 0 ) {
                        for (int k = 0; k < 3; k++) {
                            if (isCellEmpty(k, i)) {
                                setXO(k, i, aiDot);
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
                    if ( field[j][j] == playerDot ) {
                        countDiag++;
                    }
                    if ( field[j][j] == aiDot ) {
                        aiCountDiag++;
                    }
                    if ( aiCountDiag == 2 && countDiag == 0 ) {
                        for (int k = 0; k < 3; k++) {
                            if (isCellEmpty(k, k)) {
                                setXO(k, k, aiDot);
                                flag = 1;
                                break;
                            }
                        }
                    }
                    if (flag == 1) {
                        break;
                    }
                    /*  Вторая диагональ  */
                    if ( field[j][2-j] == playerDot ) {
                        countDiagS++;
                    }
                    if ( field[j][2-j] == aiDot ) {
                        aiCountDiagS++;
                    }
                    if ( aiCountDiagS == 2 && countDiagS == 0 ) {
                        for (int k = 0; k < 3; k++) {
                            if (isCellEmpty(k, 2 - k)) {
                                setXO(k, 2 - k, aiDot);
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
                    if ( field[i][j] == playerDot ) {
                        countHor++;
                    }
                    if ( field[i][j] == aiDot ) {
                        aiCountHor++;
                    }
                    if ( countHor == 2 && aiCountHor == 0) {
                        for (int k = 0; k < 3; k++) {
                            if (isCellEmpty(i, k)) {
                                setXO(i, k, aiDot);
                                flag = 1;
                                break;
                            }
                        }
                    }
                    if (flag == 1) {
                        break;
                    }
                    //  Блок вертикалей
                    if ( field[j][i] == playerDot ) {
                        countVer++;
                    }
                    if ( field[j][i] == aiDot ) {
                        aiCountVer++;
                    }
                    if ( countVer == 2 && aiCountVer == 0 ) {
                        for (int k = 0; k < 3; k++) {
                            if (isCellEmpty(k, i)) {
                                setXO(k, i, aiDot);
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
                    if ( field[j][j] == playerDot ) {
                        countDiag++;
                    }
                    if ( field[j][j] == aiDot ) {
                        aiCountDiag++;
                    }
                    if ( countDiag == 2 && aiCountDiag == 0 ) {
                        for (int k = 0; k < 3; k++) {
                            if (isCellEmpty(k, k)) {
                                setXO(k, k, aiDot);
                                flag = 1;
                                break;
                            }
                        }
                    }
                    if (flag == 1) {
                        break;
                    }
                    /*  Вторая диагональ  */
                    if ( field[j][2-j] == playerDot ) {
                        countDiagS++;
                    }
                    if ( field[j][2-j] == aiDot ) {
                        aiCountDiagS++;
                    }
                    if ( countDiagS == 2 && aiCountDiagS == 0 ) {
                        for (int k = 0; k < 3; k++) {
                            if (isCellEmpty(k, 2 - k)) {
                                setXO(k, 2 - k, aiDot);
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
                    setXO(0, 0, aiDot);
                    flag = 1;
                }
            }
        }
        if (flag == 0) {
            if (isCellEmpty(0, 2)) {
                if ((isCellEmpty(0, 1) && !isCellEmpty(1, 2)) || (isCellEmpty(1, 2) && !isCellEmpty(0, 1))) {
                    setXO(0, 2, aiDot);
                    flag = 1;
                }
            }
        }
        //  Не уверен, что до этого момента доходит :(
        if (flag == 0) {
            if (isCellEmpty(2, 2)) {
                if ((isCellEmpty(2, 1) && !isCellEmpty(1, 2)) || (isCellEmpty(1, 2) && !isCellEmpty(2, 1))) {
                    setXO(2, 2, aiDot);
                    flag = 1;
                }
            }
        }
        if (flag == 0) {
            if (isCellEmpty(2, 0)) {
                if ((isCellEmpty(1, 0) && !isCellEmpty(2, 1)) || (isCellEmpty(2, 1) && !isCellEmpty(1, 0))) {
                    setXO(2, 0, aiDot);
                    flag = 1;
                }
            }
        }
        
        if (flag == 0) {
            do {
                x = rand.nextInt(3);
                y = rand.nextInt(3);
            } while(!isCellEmpty(x, y));
            setXO(x, y, aiDot);
        }
    }
    
    //  Проверяет ячейку на пустоту
    public static boolean isCellEmpty (int _x, int _y) {
        if (_x < 0 || _y < 0 || _x > 2 || _y > 2) {
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
        
        int countHor = 0, countVer = 0, countDiag = 0, countDiagS = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //  Горизонтали
                if ( field[i][j] == _xo ) {
                    countHor++;
                }
                //  Вертикали
                if ( field[j][i] == _xo ) {
                    countVer++;
                }
                //  Первая диагональ
                if ( field[j][j] == _xo ) {
                    countDiag++;
                }
                //  Вторая диагональ
                if ( field[j][2-j] == _xo ) {
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
    
    //  Проверяет поле на заполненность
    public static boolean isFieldFull () {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ( field[i][j] == '*' ) {
                    return false;
                }
            }
        }
        return true;
    }

}






