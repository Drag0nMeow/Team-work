package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckTest {
    @Test
    void generationTest(){//生成给定参数的算术表达式
        new Check().generation(10,10);
        new Check().generation(5,10);
        new Check().generation(10,5);
    }
}