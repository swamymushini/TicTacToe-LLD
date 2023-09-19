package com.lld.TicTacToe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lld.TicTacToe.service.GameServiceImpl;

@Component
public class GameInitializer implements CommandLineRunner {

    private final GameServiceImpl gameServiceImpl;

    @Autowired
    public GameInitializer(GameServiceImpl gameServiceImpl) {
        this.gameServiceImpl = gameServiceImpl;
    }

    @Override
    public void run(String... args) {
        try {
            gameServiceImpl.intiateGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
