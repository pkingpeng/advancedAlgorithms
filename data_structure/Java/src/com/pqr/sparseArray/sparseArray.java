package com.pqr.sparseArray;

import org.jetbrains.annotations.NotNull;

/**
 * @file: sparseArray.java
 * @time: 2021/1/17 4:42 PM
 * @Author by Pking
 */



public class sparseArray {
    //二维数组 --> 稀疏数组
    /**
     二维数组 转 稀疏数组的思路
     1. 遍历  原始的二维数组，得到有效数据的个数 sum
     2. 根据sum 就可以创建 稀疏数组 sparseArr   int[sum + 1] [3]
     3. 将二维数组的有效数据数据存入到 稀疏数组
     * */
    public static int[][] toSparseArray(int[][] chessArr1){
        //1.遍历
        int sum = 0;
        for(int i = 0; i < chessArr1.length; i++){
            for(int j = 0; j < chessArr1[0].length; j++){
                if(chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        System.out.println("sum=" + sum);
        //2.创建sparseArray
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = chessArr1.length;
        sparseArray[0][1] = chessArr1[0].length;
        sparseArray[0][2] = sum;
        int count = 0;
        for(int i = 0; i < chessArr1.length; i++){
            for(int j = 0; j < chessArr1[0].length; j++){
                if(chessArr1[i][j] != 0){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println();
        System.out.println("转换的稀疏数组为:");
        for(int i = 0; i < sparseArray.length; i++){
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }
        System.out.println();
        return sparseArray;
    }

    /**
     * 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的  chessArr2 = int [11][11]
     * 2. 在读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可
     * **/
    public static int[] @NotNull [] toArray(int[][] sparseArray){
        //1.定义二维数组chessArr2
        int[][] chessArr2 = new int[sparseArray[0][0]][sparseArray[0][1]];

        //2.赋值
        for(int i = 1; i < sparseArray.length; i++){
            chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }


        return chessArr2;
    }

    public static void main(String[] args) {
        //创建一个原始的二位数组
        //0 --> 没有棋子 1 --> black 2 --> white
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始数组
        System.out.println("原始的棋盘二维数组");
        for(int[] row: chessArr1){
            for(int data: row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        int[][] sparseArray = toSparseArray(chessArr1);
        int[][] chessArr2 = toArray(sparseArray);

        //输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复的二维数组为:");
        for(int[] row: chessArr2){
            for(int data: row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
