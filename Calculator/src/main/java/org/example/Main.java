package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while(true){
            int n = 10;
            int r = 10;
            String submitPath = null;
            String answersPath = null;
            try {
                // 获取输入指令
                System.out.println("Please enter the command：");
                Scanner command = new Scanner(System.in);
                String arr[] = command.nextLine().split("\\s");

                //System.out.println(arr.length);//-n 5 -r 6
                if (arr.length > 1) {
                    for (int i = 0; i < arr.length; i = i + 2) {
                        switch (arr[i]) {
                            case "-n":
                                n = Integer.parseInt(arr[i + 1]);
                                if (n > 10000 || n < 1) {
                                    System.out.println("对不起，只允许输入1-10000的数字！");
                                    return; //结束运行
                                }
                                break;
                            case "-r":
                                r = Integer.parseInt(arr[i + 1]);
                                if (r < 1) {
                                    System.out.println("对不起，只允许大于等于1的自然数！");
                                    return; //结束运行
                                }
                                break;
                            case "-e":
                                submitPath = arr[i + 1];
                                if (submitPath == null) {
                                    System.out.println("对不起，没有输入相应文件路径，请重新输入");
                                    return; //结束运行
                                }
                                break;
                            case "-a":
                                answersPath = arr[i + 1];
                                if (answersPath == null) {
                                    System.out.println("对不起，没有输入相应文件路径，请重新输入");
                                    return; //结束运行
                                }
                                break;
                            default:
                                System.out.println("指令输入错误!");
                                break;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("您输入的指令有误，请重新输入");
            }

            /* **** 执行函数 **** */
            System.out.println("n: " + n + ", r: " + r);
            BuildFile makefile = new BuildFile();
            if (submitPath != null && answersPath != null)
                makefile.createGradeFile(submitPath,answersPath);
            else
                makefile.BuildFile(n, r);
        }
    }
}