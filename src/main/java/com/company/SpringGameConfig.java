package com.company;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.company")
@PropertySource("classpath:gameSettings.properties")
public class SpringGameConfig {

    @Value("${gameSettings.width}")
    private int width;

    @Value("${gameSettings.height}")
    private int height;

    @Bean
    ValueCell [][] gameField()
    {
        return new ValueCell[height][width];
    }

    @Bean
    ValueCell [][] newGameField()
    {
        return new ValueCell[height][width];
    }

}
