package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MainControllerTest {

    @Autowired
    MainController mainController;

    @Test
    void health() {
        assertEquals( "HEALTH CHECK OK!", mainController.healthCheck());
    }

    @Test
    void version() {
        assertEquals( "The actual version is 1.0.0", mainController.version());
    }

}
