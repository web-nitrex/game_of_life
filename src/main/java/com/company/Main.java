package com.company;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringGameConfig.class);
        Game gameOfLife = context.getBean("gameOfLife",GameOfLife.class);

        //запускаем планер
        gameOfLife.setAliveCells(4,4);
        gameOfLife.setAliveCells(4,5);
        gameOfLife.setAliveCells(4,6);
        gameOfLife.setAliveCells(3,6);
        gameOfLife.setAliveCells(2,5);

        while (true)
        {
            gameOfLife.nextStep();

            if(gameOfLife.isGameOver()){
                System.out.println("Game Over!");
                break;
            }

            System.out.println(gameOfLife.display());

            try {
                Thread.sleep(500);
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }

}
