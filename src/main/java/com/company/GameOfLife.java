package com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("gameOfLife")
public class GameOfLife implements Game{
    @Autowired
    private GameField gameField;

    @Override
    public boolean isGameOver()
    {
        return gameField.gameOver();
    }

    @Override
    public void nextStep()
    {
        gameField.updateGameField();

    }

    @Override
    public String display()
    {
        return gameField.displayGameField();
    }

    @Override
    public void setAliveCells(int i, int j)
    {
        gameField.setValueCell(i,j,ValueCell.ALIVE);
    }
}
