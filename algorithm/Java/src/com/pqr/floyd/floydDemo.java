package com.pqr.floyd;

import java.util.Arrays;

/**
 * @file: floydDemo.java
 * @time: 2021/5/15 7:38 PM
 * @Author by Pking
 * <p>
 * 算法简单
 * 但是三层for循环
 * O（N ^ 3）
 * 时间复杂度很高
 */
public class floydDemo {
    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        int[][] matrix = new int[vertexs.length][vertexs.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        Graph graph = new Graph(vertexs.length, matrix, vertexs);
        graph.floyd();
        graph.show();

    }
}

class Graph {
    private char[] vertexs; //存放顶点的数组

    private int[][] dis; //保存，从各个顶点出发到其它顶点的距离，最后的结果，也是保留在该数组
    private int[][] pre; //保存到达目标顶点的前驱顶点

    public Graph(int length, int[][] matrix, char[] vertexs) {
        this.vertexs = vertexs;
        this.dis = matrix;
        this.pre = new int[length][length];

        //初始化pre，存放的是前驱节点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public void show() {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        for (int k = 0; k < dis.length; k++) {
            //输出pre的一行
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertexs[pre[k][i]] + " ");
            }
            System.out.println();

            //输出dis的一行
            for (int i = 0; i < dis.length; i++) {
                System.out.print("(" + vertexs[k] + "到" + vertexs[i] + " 最短路径:" + dis[k][i] + ") ");
            }
            System.out.println("\n");
        }
    }

    //floyd
    //三层循环
    //中间顶点   [A, B, C, D, E, F, G]  -> k
    //出发顶点   [A, B, C, D, E, F, G]  -> i
    //终点       [A, B, C, D, E, F, G]  -> j
    public void floyd() {
        int len = 0;
        for (int k = 0; k < dis.length; k++) {//中间顶点遍历 --> 第一层
            for (int i = 0; i < dis.length; i++) {//从 i 顶点出发 --> 第二层
                for (int j = 0; j < dis.length; j++) {//经过k 到达j --> 第三层
                    //i -> k + k -> j ：从i节点出发经过k到达j顶点的距离
                    len = dis[i][k] + dis[k][j];
                    if(len < dis[i][j]){
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j]; //更新前驱节点···
                    }
                }
            }

        }
    }

}
