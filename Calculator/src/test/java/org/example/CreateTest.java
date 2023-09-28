package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateTest {
    @Test
    void GCDTest(){//测试两数之间最小公约数
        //12和8的最小公约数为4
        assertEquals(4,new Create().GCD(12,8));
        //1和1的最小公约数为1
        assertEquals(1,new Create().GCD(1,1));
        //5和7的最小公约数为1
        assertEquals(1,new Create().GCD(5,7));
    }
    @Test
    void createFormulaTest(){//生成一个算式表达式
        //整数,分母,分子均不大于5的算式
        String[] S1 = new String[]{};
        do{
        S1 = new Create().createFormula(5);}
        while (S1==null);
        System.out.println(S1[S1.length-1]);
        //整数,分母,分子均不大于10的算式
        String[] S2 = new String[]{};
        do{
            S2 = new Create().createFormula(10);}
        while (S2==null);
        System.out.println(S2[S2.length-1]);
        //整数,分母,分子均不大于20的算式
        String[] S3 = new String[]{};
        do{
            S3 = new Create().createFormula(20);}
        while (S3==null);
        System.out.println(S3[S3.length-1]);
        //生成不符合要求的算式舍去
        String[] S4 = new String[]{};
        do{
            S4 = new Create().createFormula(20);}
        while (S4!=null);
        System.out.println("算式不符要求,返回值为null");
    }
}
