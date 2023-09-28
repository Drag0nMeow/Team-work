package org.example;

import java.util.Random;

public class Create {
    public int GCD(int x,int y){
        while (true){
            if(x % y == 0)
                return y;
            int temp = y;
            y = x % y;
            x = temp;
        }
    }

    public String[] createFormula(int r){
        Random random = new Random();
        String[] operator = {"＋","－","×","÷","＝"};

        String[] totalOperator = new String[1 + random.nextInt(3)];
        String[] totalFraction = new String[totalOperator.length + 1];
        String formula = new String();

        Boolean isFraction = false;

        //随机生成操作数
        for (int i = 0; i < totalFraction.length; i++){
            //随机确定生成整数or分数
            int numType = random.nextInt(2);

            if(numType == 0) {//生成分数时
                int denominator = 1 + random.nextInt(r);
                int molecule = random.nextInt(denominator);
                int integer = random.nextInt(r + 1);
                //化简分数
                if (molecule!=0) {
                    int GCD1 = GCD(denominator, molecule);
                    denominator /= GCD1;
                    molecule /= GCD1;
                }
                //输出最简分数
                if (molecule > 0 && integer == 0) {
                    totalFraction[i] = molecule + "/" + denominator;
                    isFraction = true;
                }
                else if (molecule == 0)
                    totalFraction[i] = String.valueOf(integer);
                else {
                    totalFraction[i] = integer + "'" + molecule + "/" + denominator;
                    isFraction = true;
                }
            }else {//生成整数时
                int integer = random.nextInt(r+1);
                totalFraction[i] = String.valueOf(integer);
            }
        }

        //随机生成运算符
        for (int i = 0; i < totalOperator.length; i++){
            if(isFraction)
                totalOperator[i] = operator[random.nextInt(2)];
            else
                totalOperator[i] = operator[random.nextInt(4)];
        }
        //括号
        int bracket = totalFraction.length;
        if(totalFraction.length != 2)
            bracket = random.nextInt(totalFraction.length);
        //合成算式
        for (int i = 0;i < totalFraction.length; i++) {
            if (i == bracket && bracket<totalOperator.length)
                formula = formula + "(" + totalFraction[i] + totalOperator[i] ;
            else if (i == totalFraction.length - 1 && i == bracket + 1 && bracket<totalOperator.length)
                formula = formula + totalFraction[i] + ")" + "=";
            else if (bracket<totalOperator.length && i == bracket+1)
                formula = formula + totalFraction[i] + ")" + totalOperator[i];
            else if (i == totalFraction.length - 1)
                formula = formula + totalFraction[i] + "=";
            else
                formula = formula + totalFraction[i] + totalOperator[i];
        }
        //检查
        Compute compute = new Compute();
        String[] answerFormula = compute.typeCheck(formula, 3 * totalOperator.length + 3);

        if(answerFormula != null)
            return answerFormula;
        return null;
    }
}