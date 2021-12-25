package com.pqr.backTracking;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @file: backTrackingDemo.java
 * @time: 2021/5/16 7:40 PM
 * @Author by Pking
 */
public class backTrackingDemo {
    private static int X; //棋盘的列
    private static int Y; //棋盘的行

    private static boolean isVisited[];//标记各个位置是否被访问过
    private static boolean finished; //标记是否成功


    public static void main(String[] args) {
        X = 8;
        Y = 8;

        int row = 1;
        int col = 1; //马的初始位置
        int[][] board = new int[X][Y]; //创建棋盘
        isVisited = new boolean[X * Y];

        travel(board, row - 1, col - 1, 1);

        for (int[] rows : board) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }


    }

    /**
     * 完成骑士周游问题的算法
     *
     * @param board 棋盘
     * @param row   马儿当前的位置的行 从 0 开始
     * @param col   马儿当前的位置的列 从 0 开始
     * @param step  是第几步 ,初始位置就是第 1 步
     */
    public static void travel(int[][] board, int row, int col, int step) {
        board[row][col] = step;
        // row = 4; X = 8; col = 4; --> 36; --> 代表第4行 第5列
        isVisited[row * X + col] = true;
        //下一个位置的集合
        ArrayList<Point> pointArrayList = next(new Point(col, row)); // (x,y) --> 第y行，第x列 col = x; row = y;

        //对下一步可以走的位置，进行排序
        sort(pointArrayList);

        while (!pointArrayList.isEmpty()) {
            Point pNext = pointArrayList.remove(0); //取出下一个可以走的位置
            if (!isVisited[pNext.y * X + pNext.x]) {
                travel(board, pNext.y, pNext.x, step + 1);
            }
        }

        //判断是否完成任务，根据step判断
        //说明: step < X * Y 成立的情况有两种
        //1. 棋盘到目前位置,仍然没有走完
        //2. 棋盘处于一个回溯过程
        if (step < X * Y && !finished) {
            board[row][col] = 0; //形成回溯
            isVisited[row * X + col] = false;
        } else {
            finished = true;
        }

    }


    //根据当前位置，计算马还能走哪些位置，并放入到一个集合中(ArrayList), 最多有8个位置
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> pList = new ArrayList<>();

        Point p1 = new Point(); // p1 可能走到的新位置；   curPoint --> 当前位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            pList.add(new Point(p1));
        }

        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            pList.add(new Point(p1));
        }

        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            pList.add(new Point(p1));
        }

        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            pList.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            pList.add(new Point(p1)); }

        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            pList.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            pList.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            pList.add(new Point(p1));
        }
        return pList;
    }
    //优化
    //根据当前这个一步的所有的下一步的选择位置，进行非递减排序, 减少回溯的次数
    public static void sort(ArrayList<Point> pNextArrayList){
        pNextArrayList.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //o1的下一步所有位置个数
                int count1 = next(o1).size();
                //o2
                int count2 = next(o2).size();
                if(count1 < count2){
                    return -1;
                }else if(count1 == count2){
                    return 0;
                }else{
                    return 1;
                }
            }
        });
    }
}


