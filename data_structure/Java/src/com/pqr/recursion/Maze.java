package com.pqr.recursion;

/**
 * @file: Maze.java
 * @time: 2021/4/21 4:55 PM
 * @Author by Pking
 */
public class Maze {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        //1 --> 墙;
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int j = 0; j < 8; j++) {
            map[j][0] = 1;
            map[j][6] = 1;
        }

        //挡板
        map[3][1] = 1;
        map[3][2] = 1;


        //输出地图
        System.out.println("地图:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //测试
        setWay(map, 1, 1);

        //输出新的地图, 小球走过并标识过的地图
        System.out.println("小球走过，并标识过的 地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }


    //递归回溯找路

    /**
     * map --> 地图
     * i j 开始寻找的位置
     * 找到 --> return true;
     * <p>
     * 思路：
     * 1.小球能到map[6][5]说明找到
     * 2.约定: map[i][j] = 0 --> 没走过;
     * 1 --> 墙，不能走;
     * 2 --> 走过，为通路
     * 3 --> 走过，但是走不通
     * 3.策略: 下 -> 右 -> 上 -> 左 走不通，则回溯
     **/
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//通路找到
            return true;
        } else {
            if (map[i][j] == 0) {//还没走过 -->按照策略
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) { //向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    //说明不通
                    map[i][j] = 3;
                    return false;
                }
            } else { //map[i][j] != 0: 则1, 2, 3
                return false;
            }
        }
    }
}

