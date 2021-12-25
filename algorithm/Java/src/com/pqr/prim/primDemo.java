package com.pqr.prim;

import java.util.Arrays;

/**
 * @file: prim.java
 * @time: 2021/5/10 5:25 PM
 * @Author by Pking
 */

public class primDemo {
    public static void main(String[] args) {
        //测试图的创建
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertex = data.length;
        //二维数组来描述 邻接矩阵的关系
        //10000表示不连通
        int[][] weight = new int[][]{
                //A    B   C    D     E      F     G
                {10000, 5, 7, 10000, 10000, 10000, 2}, //A
                {5, 10000, 10000, 9, 10000, 10000, 3}, //B
                {7, 10000, 10000, 10000, 8, 10000, 10000}, //C
                {10000, 9, 10000, 10000, 10000, 4, 10000}, //D
                {10000, 10000, 8, 10000, 10000, 5, 4}, //E
                {10000, 10000, 10000, 4, 5, 10000, 6}, //F
                {2, 3, 10000, 10000, 4, 6, 10000},};//G

        MGraph mGraph = new MGraph(vertex);
        MinTree minTree = new MinTree();
        minTree.createGrapth(mGraph, vertex, data, weight);

        //输出图
        minTree.showGraph(mGraph);

        //测试普利姆算法
        minTree.prim(mGraph, 1);


    }

}

class MinTree {

    //创建图的邻接矩阵

    /**
     * @param mGraph 图对象
     * @param vertex 图对应的顶点个数
     * @param data   图的各个顶点的值
     * @param weight 图的邻接矩阵
     **/

    public void createGrapth(MGraph mGraph, int vertex, char data[], int[][] weight) {
        for (int i = 0; i < vertex; i++) {
            mGraph.data[i] = data[i];
            for (int j = 0; j < vertex; j++) {
                mGraph.weight[i][j] = weight[i][j];
            }
        }

    }

    //显示图的邻接矩阵
    public void showGraph(MGraph mGraph) {
        for (int[] link : mGraph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }


    //prim算法 --> MST
    public void prim(MGraph mGraph, int v) {
        //标记节点是否访问过
        int[] visited = new int[mGraph.vertex];

        //从A开始，标记为已访问
        visited[v] = 1;

        int h1 = -1;
        int h2 = -1; //h1 h2 记录两个顶点下标
        int minWeight = 10000;
        for (int k = 1; k < mGraph.vertex; k++) {//因为有 graph.verxs 顶点，普利姆算法结束后，有 graph.verxs-1 边


            for (int i = 0; i < mGraph.vertex; i++) { //访问过
                for (int j = 0; j < mGraph.vertex; j++) { //遍历没访问过
                    if (visited[i] == 1 && visited[j] == 0 && mGraph.weight[i][j] < minWeight) {
                        minWeight = mGraph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            System.out.println("边<" + mGraph.data[h1] + "," + mGraph.data[h2] + "> 权值" + minWeight);

            visited[h2] = 1; //h1为已访问过，h2为没访问过，因此需要标记为访问过
            minWeight = 10000; //需要重置
        }
    }
}


class MGraph {
    int vertex; //节点个数
    char[] data; //存放节点数据
    int[][] weight; //邻接矩阵

    public MGraph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }


}
