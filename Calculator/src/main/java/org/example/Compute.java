package org.example;

import java.util.Stack;
import java.util.HashMap;

public class Compute {
    public String[] typeCheck(String formula,int length){
        Stack<String> stackNumber = new Stack<>();
        Stack<String> stackOperator = new Stack<>();
        String[] RPN = new String[length];//逆波兰表达式
        //创建哈希表用于存放运算符优先级
        HashMap<String, Integer> hashmap = new HashMap<>();
        hashmap.put("(", 0);
        hashmap.put("＋", 1);
        hashmap.put("－", 1);
        hashmap.put("×", 2);
        hashmap.put("÷", 2);

        for (int i=0,j=0; i < formula.length();) {
            StringBuilder digit = new StringBuilder();

            char c = formula.charAt(i);
            while (Character.isDigit(c)||c=='/'||c=='\'') {
                digit.append(c);
                i++;
                c = formula.charAt(i);
            }

            if (digit.length() == 0){ //当前digit里面已经无数字，即当前处理符号
                switch (c) {
                    //如果是“(”转化为字符串压入字符栈
                    case '(': {
                        stackOperator.push(String.valueOf(c));
                        break;
                    }
                    // )
                    case ')': {
                        String operator = stackOperator.pop();
                        while (!stackOperator.isEmpty() && !operator.equals("(")) {
                            String a = stackNumber.pop();
                            String b = stackNumber.pop();

                            RPN[j++] = a;
                            RPN[j++] = b;
                            RPN[j++] = operator;

                            String ansString = Compute(b, a, operator);

                            if(ansString == null)
                                return  null;

                            stackNumber.push(ansString);
                            operator = stackOperator.pop();
                        }
                        break;
                    }
                    //遇到了 =，则计算最终结果
                    case '=': {
                        String operator;
                        while (!stackOperator.isEmpty()) {
                            operator = stackOperator.pop();
                            String a = stackNumber.pop();
                            String b = stackNumber.pop();

                            RPN[j++] = a;
                            RPN[j++] = b;
                            RPN[j++] = operator;

                            String ansString = Compute(b, a, operator);
                            if(ansString == null) return null;

                            stackNumber.push(ansString);
                        }
                        break;
                    }
                    default: {
                        String operator;
                        while (!stackOperator.isEmpty()) {
                            operator = stackOperator.pop();
                            if (hashmap.get(operator) >= hashmap.get(String.valueOf(c))) { //比较优先级
                                String a = stackNumber.pop();
                                String b = stackNumber.pop();

                                RPN[j++] = a;
                                RPN[j++] = b;
                                RPN[j++] = operator;
                                //计算
                                String ansString =Compute(b, a, operator);
                                if(ansString == null) return  null;

                                stackNumber.push(ansString);
                            }
                            else {
                                stackOperator.push(operator);
                                break;
                            }

                        }
                        stackOperator.push(String.valueOf(c));
                        break;
                    }
                }
            }
            else {
                stackNumber.push(digit.toString());
                continue;
            }
            i++;
        }
        RPN[length-3] = "=";
        RPN[length-2] = stackNumber.peek();
        RPN[length-1] = formula;
        return RPN;
    }

    private String sim (int integer,int molecule,int denominator) {
        String answerFormula;
        int GCD1 = 1;

        //求最大公约数
        Create create = new Create();
        GCD1 = create.GCD(denominator,molecule);

        //化简
        denominator /= GCD1;
        molecule /= GCD1;

        if (molecule > 0 && integer == 0) {
            answerFormula = String.valueOf(molecule) + '/' + String.valueOf(denominator);
        } else if (molecule == 0)
            answerFormula = String.valueOf(integer);
        else {
            answerFormula = String.valueOf(integer) + "'" + String.valueOf(molecule) + '/' + String.valueOf(denominator);
        }

        //返回最简结果
        return answerFormula;
    }

    private String Compute(String m,String n,String operator) {
        String answerFormula = null;
        char operator1 = operator.charAt(0);
        int[] indexFraction = {m.indexOf('\''), m.indexOf('/'), n.indexOf('\''), n.indexOf('/')};

        if (indexFraction[1] > 0 || indexFraction[3] > 0) {
            int[] molecule = new int[3];
            int[] denominator = new int[3];
            int[] integer = new int[3];

            if (indexFraction[1] > 0) {
                for (int i = 0; i < m.length(); i++) {
                    if (i < indexFraction[0]) {
                        integer[0] = Integer.parseInt(integer[0] + String.valueOf(m.charAt(i) - '0'));
                    } else if (i > indexFraction[0] && i < indexFraction[1]) {
                        molecule[0] = Integer.parseInt(molecule[0] + String.valueOf(m.charAt(i) - '0'));
                    } else if (i > indexFraction[1]) {
                        denominator[0] = Integer.parseInt(denominator[0] + String.valueOf(m.charAt(i) - '0'));
                    }
                }
            } else {
                integer[0] = Integer.parseInt(m);
                denominator[0] = 1;
                molecule[0] = 0;
            }
            if (indexFraction[3] > 0) {
                for (int i = 0; i < n.length(); i++) {
                    if (i < indexFraction[2]) {
                        integer[1] = Integer.parseInt(integer[1] + String.valueOf(n.charAt(i) - '0'));
                    } else if (i > indexFraction[2] && i < indexFraction[3]) {
                        molecule[1] = Integer.parseInt(molecule[1] + String.valueOf(n.charAt(i) - '0'));
                    } else if (i > indexFraction[3]) {
                        denominator[1] = denominator[1] + n.charAt(i) - '0';
                    }
                }
            } else {
                integer[1] = Integer.parseInt(n);
                denominator[1] = 1;
                molecule[1] = 0;
            }
            //分数运算
            switch (operator1) {
                case '＋': {
                    denominator[2] = denominator[0] * denominator[1];
                    molecule[2] = integer[0] * denominator[2]
                            + molecule[0] * denominator[1]
                            + integer[1] * denominator[2]
                            + molecule[1] * denominator[0];
                    break;
                }
                case '－': {
                    denominator[2] = denominator[0] * denominator[1];
                    molecule[2] = integer[0] * denominator[2]
                            + molecule[0] * denominator[1]
                            - integer[1] * denominator[2]
                            - molecule[1] * denominator[0];
                    break;
                }
                default:
                    return null;
            }
            if (molecule[2] >= denominator[2] && molecule[2]>0) {
                integer[2] = molecule[2] / denominator[2];
                molecule[2] = Math.abs(molecule[2] % denominator[2]);
            } else if (molecule[2]<0)
                return null;
            //化简
            if (molecule[2] != 0)
                answerFormula = sim(integer[2],molecule[2],denominator[2]);
            else
                answerFormula = String.valueOf(integer[2]);
        } else { //处理整数运算
            int a = Integer.parseInt(m);
            int b = Integer.parseInt(n);

            switch (operator1) {
                case '＋': {
                    answerFormula = String.valueOf(a + b);
                    break;
                }
                case '－': {
                    if (a - b >= 0)
                        answerFormula = String.valueOf(a - b);
                    else
                        return null;
                    break;
                }
                case '×': {
                    answerFormula = String.valueOf(a * b);
                    break;
                }
                case '÷': {
                    if (b == 0)
                        return null;
                    else if (a % b != 0) {
                        answerFormula = a % b + "/" + b;
                        if (a / b > 0)
                            answerFormula = a / b + "'" + answerFormula;
                    } else
                        answerFormula = String.valueOf(a / b);
                    break;
                }
            }
        }
        return answerFormula;
    }
}