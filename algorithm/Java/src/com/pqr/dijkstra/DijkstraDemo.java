
package com.pqr.dijkstra;

import java.util.Arrays;

/**
 * @file: Dijkstra.java
 * @time: 2021/5/13 12:35 PM
 * @Author by Pking
 */
public class DijkstraDemo {
    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertexs.length][vertexs.length];
        final int N = 65535; // 表示不可连接

        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        Graph graph = new Graph(vertexs, matrix);
        graph.showGraph();

        graph.dijkstra(6);
        graph.showDijResult();

    }
}


class VisitedVertex {
    // 记录各个顶点是否访问过 1 表示访问过,0 未访问,会动态更新
    public int[] already_arr;
    // 每个下标对应的值为前一个顶点下标, 会动态更新
    public int[] pre_visited;
    // 记录出发顶点到其他所有顶点的距离,比如 G 为出发顶点，就会记录 G 到其它顶点的距离，会动态更新，求的最短距离就会存放到dis
    public int[] dis;

    //length --> 顶点个数
    //index --> 出发顶点的索引
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];

        //初始化dis： 设置出发顶点访问距离为0，设置出发顶点已经访问，其他顶点距离为65535
        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1;
        this.dis[index] = 0;

    }

    //判断 index是否被访问过
    public boolean in(int index) {
        return already_arr[index] == 1;
    }


    //更新出发顶点到index的距离
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    //更新顶点的前驱节点
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    //返回出发顶点到index顶点的距离
    public int getDis(int index) {
        return dis[index];
    }

    //继续选择并返回新的访问顶点， 比如这里的 G 完后，就是 A 点作为新的访问顶点(注意不是出发顶点)
    public int updateIndex() {
        int min = 65535;
        int index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {//未被访问，且距离最小
                min = dis[i];
                index = i;
            }
        }

        //更新index被访问
        already_arr[index] = 1;
        return index;
    }

    //显示结果 already_arr; pre_visited; dis
    public void show() {
        System.out.println("===============");
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();

        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertexs[count] + "(" + i + ")");
            }else{
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();
    }
}


//构造图
class Graph {
    private char[] vertexs; //顶点数组
    private int[][] matrix; //邻接矩阵
    private VisitedVertex vv; //已经访问节点的集合

    //构造器
    public Graph(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;
    }

    //显示图
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    //显示结果
    public void showDijResult() {
        vv.show();
    }


    //dijkstra

    /**
     * @param index: 出发顶点对应的下标
     */
    public void dijkstra(int index) {
        vv = new VisitedVertex(vertexs.length, index);
        update(index); //更新index（G） 到周围顶点的距离和pre顶点

        for (int j = 1; j < vertexs.length; j++) {
            index = vv.updateIndex(); //选择并返回新的访问节点 eg: G 之后是 A  --> index = 0 --> 'A'
            update(index); //再更新 index 顶点到周围顶点的距离和前驱顶点
        }


    }

    //更新index下标顶点的到周围顶点的距离 和 周围顶点的前驱节点
    private void update(int index) {
        int len = 0;
        //遍历邻接举证matrix 更新 dis; already_arr, vv
        for (int j = 0; j < matrix[index].length; j++) {
            //len --> 已经统计的出发顶点到index的距离 + index到j顶点的距离
            len = vv.getDis(index) + matrix[index][j];

            if (!vv.in(j) && len < vv.getDis(j)) {//j没被访问，且距离更小，则需要更新
                vv.updateDis(j, len);   //更新出发顶点到j的目前最短距离
                vv.updatePre(j, index); //更新j前驱为index

            }
        }
    }
}


