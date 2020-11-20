package com.company;

import java.util.Arrays;

public class Main {

    private static final int WIDTH=12;
    private static final int HEIGHT=12;
    private static int [][] gameField;
    private static int [][] newGameField;

    static {
        gameField = new int[HEIGHT][WIDTH];
        newGameField = new int[HEIGHT][WIDTH];
    }

    public static void main(String[] args){

        //запускаем планер
        setAliveCells(4,4);
        setAliveCells(4,5);
        setAliveCells(4,6);
        setAliveCells(3,6);
        setAliveCells(2,5);


        while(true)
        {
            boolean nextStep = updateGameField();

            if(!nextStep)
            {
                System.out.println("Game Over!");
                break;//game over
            }

            System.out.println(displayGameField());

            try {
                Thread.sleep(500);
            } catch(InterruptedException ex) {}
        }
    }

    private static String displayGameField()
    {
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<gameField.length; i++)
        {
            for (int j=0; j<gameField[i].length; j++)
            {
                if(gameField[i][j]==1)
                {
                    sb.append('*');
                }
                else
                {
                    sb.append(' ');
                }

                sb.append(' ');
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    private static void setAliveCells(int i, int j)
    {
        if(i>HEIGHT || j>WIDTH) return;
        gameField[i][j]=1;
    }

    private static int findAliveCells(int i, int j)
    {
        int amountAliveCells=0;

        int i00=i-1;
        int j00=j-1;

        if((i00>=0 && i00<=(HEIGHT-1)) && (j00>=0 && j00<=(WIDTH-1)))
        {
            if(gameField[i00][j00]==1)
                amountAliveCells++;
        }

        int i01=i-1;
        int j01=j;

        if((i01>=0 && i01<=(HEIGHT-1)) && (j01>=0 && j01<=(WIDTH-1)))
        {
            if(gameField[i01][j01]==1)
                amountAliveCells++;
        }

        int i02=i-1;
        int j02=j+1;

        if((i02>=0 && i02<=(HEIGHT-1)) && (j02>=0 && j02<=(WIDTH-1)))
        {
            if(gameField[i02][j02]==1)
                amountAliveCells++;
        }

        int i10=i;
        int j10=j-1;

        if((i10>=0 && i10<=(HEIGHT-1)) && (j10>=0 && j10<=(WIDTH-1)))
        {
            if(gameField[i10][j10]==1)
                amountAliveCells++;
        }

        int i12=i;
        int j12=j+1;

        if((i12>=0 && i12<=(HEIGHT-1)) && (j12>=0 && j12<=(WIDTH-1)))
        {
            if(gameField[i12][j12]==1)
                amountAliveCells++;
        }

        int i20=i+1;
        int j20=j-1;

        if((i20>=0 && i20<=(HEIGHT-1)) && (j20>=0 && j20<=(WIDTH-1)))
        {
            if(gameField[i20][j20]==1)
                amountAliveCells++;
        }

        int i21=i+1;
        int j21=j;

        if((i21>=0 && i21<=(HEIGHT-1)) && (j21>=0 && j21<=(WIDTH-1)))
        {
            if(gameField[i21][j21]==1)
                amountAliveCells++;
        }

        int i22=i+1;
        int j22=j+1;

        if((i22>=0 && i22<=(HEIGHT-1)) && (j22>=0 && j22<=(WIDTH-1))) {
            if (gameField[i22][j22] == 1)
                amountAliveCells++;
        }

        return amountAliveCells;
    }

    private static boolean isGameOver()
    {
        //проверяем что на поле не осталось ни одной живой клетки
        boolean aliveNotFound=true;

        for(int i=0; i<newGameField.length; i++)
        {
            int res = Arrays.binarySearch(newGameField[i],1);
            if(res>0)
                aliveNotFound=false; //нашли живую клетку!
        }

        if(aliveNotFound==true)
            return true; //завершаем игру

        //складываеься стабильная конфигурация
        boolean stableConfiguration = true;

        for(int i=0; i<newGameField.length; i++)
        {
            if(!Arrays.equals(newGameField[i],gameField[i]))
                stableConfiguration=false;
        }

        if(stableConfiguration==true)
            return true;//завершаем игруS

        return false;
    }

    private static boolean updateGameField()
    {
        //вычсисляем новое игоровое поле
        for (int i=0; i<gameField.length;i++)
        {
            for (int j=0;j<gameField[i].length;j++)
            {
                int amounAliveCells = findAliveCells(i, j);

                if(gameField[i][j]==1)
                {
                    //текущая клетка живая
                    if(amounAliveCells==2||amounAliveCells==3)
                    {
                        newGameField[i][j]=1;
                    }
                    else
                    {
                        newGameField[i][j]=0;
                    }
                }
                else
                {
                    //текущая клетка мертвая
                    if(amounAliveCells==3)
                    {
                        newGameField[i][j]=1;
                    }
                    else
                    {
                        newGameField[i][j]=0;
                    }
                }
            }
        }

        //тут мы должны проанализировать условия выхода из игры
        //если какое то из условий выполняется то должны вернуть false
        if(isGameOver())
            return false;

        //новое игровое поле копируем в основное
        for(int i=0; i<gameField.length; i++)
        {
            gameField[i]=Arrays.copyOf(newGameField[i],newGameField.length);
        }

        return true;
    }


}
