package com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component

public class MatrixGameField extends GameField{
    @Autowired
    @Qualifier("gameField")
    private ValueCell [][] gameField;

    @Autowired
    @Qualifier("newGameField")
    private ValueCell [][] newGameField;


    MatrixGameField(@Value("${gameSettings.width}") int width,@Value("${gameSettings.height}") int height)
    {
        super(width, height);
    }

    @Override
    public void setValueCell(int i, int j, ValueCell value)
    {
        if(i>height || j>width) return;
        gameField[i][j]=value;
    }

    @Override
    public int findAliveCells(int i, int j)
    {
        int amountAliveCells=0;

        int i00=i-1;
        int j00=j-1;

        if((i00>=0 && i00<=(height-1)) && (j00>=0 && j00<=(width-1)))
        {
            if(gameField[i00][j00]==ValueCell.ALIVE)
                amountAliveCells++;
        }

        int i01=i-1;
        int j01=j;

        if((i01>=0 && i01<=(height-1)) && (j01>=0 && j01<=(width-1)))
        {
            if(gameField[i01][j01]==ValueCell.ALIVE)
                amountAliveCells++;
        }

        int i02=i-1;
        int j02=j+1;

        if((i02>=0 && i02<=(height-1)) && (j02>=0 && j02<=(width-1)))
        {
            if(gameField[i02][j02]==ValueCell.ALIVE)
                amountAliveCells++;
        }

        int i10=i;
        int j10=j-1;

        if((i10>=0 && i10<=(height-1)) && (j10>=0 && j10<=(width-1)))
        {
            if(gameField[i10][j10]==ValueCell.ALIVE)
                amountAliveCells++;
        }

        int i12=i;
        int j12=j+1;

        if((i12>=0 && i12<=(height-1)) && (j12>=0 && j12<=(width-1)))
        {
            if(gameField[i12][j12]==ValueCell.ALIVE)
                amountAliveCells++;
        }

        int i20=i+1;
        int j20=j-1;

        if((i20>=0 && i20<=(height-1)) && (j20>=0 && j20<=(width-1)))
        {
            if(gameField[i20][j20]==ValueCell.ALIVE)
                amountAliveCells++;
        }

        int i21=i+1;
        int j21=j;

        if((i21>=0 && i21<=(height-1)) && (j21>=0 && j21<=(width-1)))
        {
            if(gameField[i21][j21]==ValueCell.ALIVE)
                amountAliveCells++;
        }

        int i22=i+1;
        int j22=j+1;

        if((i22>=0 && i22<=(height-1)) && (j22>=0 && j22<=(width-1))) {
            if (gameField[i22][j22] == ValueCell.ALIVE)
                amountAliveCells++;
        }

        return amountAliveCells;
    }

    @Override
    public String displayGameField()
    {
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<gameField.length; i++)
        {
            for (int j=0; j<gameField[i].length; j++)
            {
                if(gameField[i][j]==ValueCell.ALIVE)
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

    public void updateGameField()
    {
        //вычсисляем новое игоровое поле
        for (int i=0; i<gameField.length;i++)
        {
            for (int j=0;j<gameField[i].length;j++)
            {
                int amountAliveCells = findAliveCells(i, j);

                if(gameField[i][j]==ValueCell.ALIVE)
                {
                    //текущая клетка живая
                    if(amountAliveCells==2||amountAliveCells==3)
                    {
                        newGameField[i][j]=ValueCell.ALIVE;
                    }
                    else
                    {
                        newGameField[i][j]=ValueCell.DEAD;
                    }
                }
                else
                {
                    //текущая клетка мертвая
                    if(amountAliveCells==3)
                    {
                        newGameField[i][j]=ValueCell.ALIVE;
                    }
                    else
                    {
                        newGameField[i][j]=ValueCell.DEAD;
                    }
                }
            }
        }

        //тут мы должны проанализировать условия выхода из игры
        //если какое то из условий выполняется то должны вернуть false
        if(isGameOver()) {
            gameOver=true;
        }

        //новое игровое поле копируем в основное
        for(int i=0; i<gameField.length; i++)
        {
            gameField[i]= Arrays.copyOf(newGameField[i],newGameField.length);
        }

    }

    @Override
    protected boolean isGameOver()
    {
        return ((!isAliveCells()) || isStableConfiguration());
    }

    public boolean isAliveCells()
    {
        //проверяем что на поле не осталось ни одной живой клетки
        boolean isAliveCell=false;

        for(int i=0; i<newGameField.length; i++)
        {
            int res = Arrays.binarySearch(newGameField[i],ValueCell.ALIVE);
            if(res>0) {
                isAliveCell = true; //нашли живую клетку!
                return isAliveCell;
            }
        }

        return isAliveCell;
    }

    public boolean isStableConfiguration()
    {
        //складывается стабильная конфигурация
        boolean stableConfiguration = true;

        for(int i=0; i<newGameField.length; i++)
        {
            if(!Arrays.equals(newGameField[i],gameField[i]))
                stableConfiguration=false;
        }

       return stableConfiguration;
    }
}
