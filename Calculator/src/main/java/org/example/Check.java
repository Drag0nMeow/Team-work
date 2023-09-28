package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Check {
    private ArrayList<String> returnList = new ArrayList<>();
    private ArrayList<String> txtList = new ArrayList<>();
    private ArrayList<String> answerList = new ArrayList<>();
    private ArrayList<String[]> answerFoList = new ArrayList<>();

    public ArrayList generation(int n, int r){
        Create create = new Create();
        for(int i = 0; i < n; ){
            String[] answerFormula = create.createFormula(r);
            if(answerFormula != null)
                if(!isRepeat(answerFormula)){
                    System.out.println((i + 1) + ":" +answerFormula[answerFormula.length - 1]);
                    i++;
                }
        }

        for (int i = 0; i < 2 * n; i++) {
            if (i < n)
                returnList.add(txtList.get(i));
            else
                returnList.add(answerList.get(i - n));
        }
        return returnList;
    }

    private boolean isRepeat(String[] answerFormula) {
        String formula = answerFormula[answerFormula.length-1];
        String[] RPN = new String[answerFormula.length-1];
        System.arraycopy(answerFormula, 0, RPN, 0, answerFormula.length-1);
        boolean isRepeat = false;

        for(String[] aF: answerFoList) {
            if (Arrays.equals(aF,RPN)){
                isRepeat = true;
            }else if (aF[aF.length-1].equals(RPN[RPN.length-1]) && aF.length == RPN.length){
                int j = 0;
                for (j = 0; j < RPN.length - 2; ){
                    boolean oR = aF[j + 2].equals("+") || aF[j + 2].equals("×");
                    boolean eR = aF[j].equals(RPN[j + 1]) && aF[j + 1].equals(RPN[j]) && aF[j + 2].equals(RPN[j + 2]);
                    boolean cR = aF[j].equals(RPN[j]) && aF[j + 1].equals(RPN[j + 1]) && aF[j + 2].equals(RPN[j + 2]);

                    if(eR && oR)
                        j = j + 3;
                    else if (cR)
                        j = j + 3;
                    else break;
                }
                if (j == RPN.length - 2){
                    isRepeat = true;
                    break;
                }
            }
        }

        if(!isRepeat) {
            this.txtList.add(formula);
            this.answerList.add(RPN[RPN.length - 1]);
            this.answerFoList.add(RPN);
        }
        return isRepeat;
    }
}
