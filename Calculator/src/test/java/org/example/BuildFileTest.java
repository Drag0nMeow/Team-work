package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildFileTest {
    @Test
    void BuildFileTest(){//创建题目文件及答案测试
        new BuildFile().BuildFile(10,10);
    }
    @Test
    void createGradeFileTest(){//创建分数文件测试
        new BuildFile().BuildFile(10,10);
        new BuildFile().createGradeFile("..\\Calculator\\Answer.txt","..\\Calculator\\Exercises.txt");
        new BuildFile().BuildFile(5,5);
        new BuildFile().createGradeFile("..\\Calculator\\Answer.txt","..\\Calculator\\Exercises.txt");
    }
}