package com.company;

public class GameOfLife implements Game{
    private GameField gameField = new MatrixGameField(12,12);

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
