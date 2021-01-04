package com.company;

public interface Game {
    void nextStep();
    String display();
    void setAliveCells(int i, int j);
    boolean isGameOver();
}
