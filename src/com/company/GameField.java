package com.company;

public abstract class GameField {
    protected   int width;
    protected   int height;
    protected boolean gameOver=false;

    GameField(int width, int height)
    {
        this.width=width;
        this.height=height;
    }

    public boolean gameOver()
    {
        return gameOver;
    }

    public abstract void setValueCell(int i, int j, ValueCell value);
    public abstract int findAliveCells(int i, int j);
    public abstract String displayGameField();
    public abstract void updateGameField();
    public abstract boolean isAliveCells();
    public abstract boolean isStableConfiguration();
    protected abstract boolean isGameOver();
}
