package org.example;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;

public class BuildFile {
    public void BuildFile(int n, int r){
        Check temp = new Check();
        ArrayList returnList = temp.generation(n, r);
        ArrayList<String> txtList = new ArrayList<>();
        ArrayList<String> ansList = new ArrayList<>();

        for (int i = 0; i < 2 * n; i++){
            if (i < n)
                txtList.add(returnList.get(i).toString());
            else
                ansList.add(returnList.get(i).toString());
        }

        createExeFile(txtList);
        createAnsFile(ansList);
    }
    //生成输出Exercise.txt
    private void createExeFile(ArrayList txtList) {
        try {
            File exeTxT = new File("../Calculator/Exercises.txt");

            if (exeTxT.exists()) {
                exeTxT.delete();
            }
            if (exeTxT.createNewFile()){
                //System.out.println("创建Exercises.txt:");
                FileOutputStream txtFile = new FileOutputStream(exeTxT);
                PrintStream q = new PrintStream(exeTxT);
                q.println("题目：");

                for(int i=0;i<txtList.size();i++){
                    //System.out.print(">");
                    q.println(i+1 + ". " +txtList.get(i));
                }

                txtFile.close();
                q.close();
                System.out.println("Exercises.txt 创建成功！");
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    //生成输出Answer.txt
    private void createAnsFile(ArrayList ansList){
        try{
            File ansTXT = new File("../Calculator/Answer.txt");
            //文件已存在时删除文件
            if (ansTXT.exists()) {
                ansTXT.delete();
            }

            if(ansTXT.createNewFile()){
                //System.out.print("创建Answer.txt:");
                FileOutputStream ansFile = new FileOutputStream(ansTXT);
                PrintStream a = new PrintStream(ansTXT);
                a.println("答案：");

                for(int i=0;i<ansList.size();i++){//正常运行
                    //for(int i=0;i<ansList.size()+1;i++){//测试代码覆盖率
                    //System.out.print(">");
                    a.println(i+1 + ". " +ansList.get(i));
                }
                ansFile.close();
                a.close();
                System.out.println("Answer.txt 创建成功！");
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void createGradeFile(String submitPath, String answersPath) {
        try {
            //获取指定文件的内容
            ArrayList<String> submitList = obtainAnswer(submitPath);
            ArrayList<String> answersList = obtainAnswer(answersPath);

            //评判成绩
            ArrayList<String> trueQuesNum = new ArrayList<>();
            ArrayList<String> falseQuesNum = new ArrayList<>();

            for (int i = 0; i < submitList.size(); i++) {
                if (submitList.get(i).equals(answersList.get(i)))
                    trueQuesNum.add(String.valueOf(i+1));
                else
                    falseQuesNum.add(String.valueOf(i+1));
            }

            //生成输出Grade.txt
            File gradeTXT = new File("../Calculator/Grade.txt");

            //文件已存在时删除文件
            if (gradeTXT.exists()) {
                gradeTXT.delete();
            }

            if (gradeTXT.createNewFile()) {
                //System.out.print("创建Grade.txt:");
                FileOutputStream gradeFile = new FileOutputStream(gradeTXT);
                PrintStream p = new PrintStream(gradeTXT);
                p.println("成绩：\n");

                p.print("Correct:");
                output(p, trueQuesNum);
                p.print("Wrong:");
                output(p, falseQuesNum);

                gradeFile.close();
                p.close();
                System.out.println("Grade.txt 创建成功！");
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void output(PrintStream p,ArrayList quesNum) {
        p.print(quesNum.size() +"(");
        for(int i=0;i<quesNum.size();i++){//正常运行
            System.out.print(">");
            if (i<quesNum.size()-1)
                p.print(" " + quesNum.get(i) + "，");
            else
                p.print(" " + quesNum.get(i));
        }
        p.println(" )\n");
    }

    private ArrayList<String> obtainAnswer(String path) throws IOException {
        ArrayList<String> answerList = new ArrayList<>();
        BufferedReader answerFile = new BufferedReader(new FileReader(path));
        String answerLine = null;

        while ((answerLine = answerFile.readLine()) != null) {
            answerLine = answerLine.replace(" ", "");
            //int index = answerLine.indexOf('=') > answerLine.indexOf('.') ? answerLine.indexOf('=') : answerLine.indexOf('.');
            if (answerLine.indexOf('.') >= 0) {//index >= 0
                if (answerLine.length() > 2)
                    answerList.add(answerLine);
            }
        }
        return answerList;
    }


}
