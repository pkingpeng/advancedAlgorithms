package com.pqr.kruskal;

import java.util.Arrays;

/**
 * @file: kruskalDemo.java
 * @time: 2021/5/10 10:00 PM
 * @Author by Pking
 */
public class kruskalDemo {

    /**
     * 根据前面介绍的克鲁斯卡尔算法的基本思想和做法，我们能够了解到，克鲁斯卡尔算法重点需要解决的以下两个问 题:
     * 问题一 对图的所有边按照权值大小进行排序。 --> 很好解决，采用排序算法进行排序即可。
     * 问题二 将边添加到最小生成树中时，怎么样判断是否形成了回路。 --> 处理方式是:记录顶点在"最小生成树"中的终点，顶点的终点是"在最小生成树中与它连通的最大顶点"。
     * 然后每次需要将一条边添加到最小生存树时，判断该边的两个顶点的终点是否重合，重合的话则会构成回路。
     * <p>
     * <p>
     * 如 何 判 断 是 否 构 成 回 路
     * 1) 就是将所有顶点按照从小到大的顺序排列好之后;某个顶点的终点就是"与它连通的最大顶
     * 2) 我们加入的边的两个顶点不能都指向同一 个终点，否则将构成回路
     **/

    private int edgeNum; //边的个数 
    private char[] vertexs; //顶点
    private int[][] matrix; //邻接矩阵
    private static final int INF = Integer.MAX_VALUE; //表示定点不连通


    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};

        kruskalDemo kruskalDemo = new kruskalDemo(vertexs, matrix);

        kruskalDemo.print();

        kruskalDemo.kruskal();

        //测试排序
//        EData[] edges = kruskalDemo.getEdges();
//        System.out.println("排序前" + Arrays.toString(kruskalDemo.getEdges()));
//        kruskalDemo.sortEdges(edges);
//        System.out.println("排序后" + Arrays.toString(edges));

    }

    //constructor
    public kruskalDemo(char[] vertexs, int[][] matrix) {
        int vLen = vertexs.length; //顶点个数

        //初始化顶点
        this.vertexs = new char[vLen];
        for (int i = 0; i < vLen; i++) {
            this.vertexs[i] = vertexs[i];
        }

        //初始化边
        this.matrix = new int[vLen][vLen];
        for (int i = 0; i < vLen; i++) {
            for (int j = 0; j < vLen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        //统计不连通的边数目
        for (int i = 0; i < vLen; i++) {
            for (int j = i + 1; j < vLen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    //算法
    public void kruskal() {
        int index = 0; // 表示最后结果数组的索引
        int[] ends = new int[edgeNum]; //用于保存"已有最小生成树"中的每个顶点在该最小生成树中的终点

        //结果数组，保存最小生成数
        EData[] results = new EData[edgeNum];

        //获取图中所有边的集合, 一共12边
        EData[] edges = getEdges();
        System.out.println("图的边的集合=" + Arrays.toString(edges) + "共" + edges.length);

        // 1)按照边的权值大小进行排序
        sortEdges(edges);

        // 2)边加入mst中，并判断是否构成回路
        // 没有构成则加入，否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取第i条边的第一个顶点
            int p1 = getPosition(edges[i].start);
            //获取第二个顶点
            int p2 = getPosition(edges[i].end);

            //p1在已有mst对应的终点
            int m = getEnds(ends, p1);

            int n = getEnds(ends, p2);

            //是否构成回路
            if (m != n) {
                ends[m] = n; //设置p1的终点是p2
                results[index++] = edges[i]; //增加一条边
            }
        }

        //打印"MST" 放在, 输出results
        System.out.println("MST= " + Arrays.toString(results));
    }


    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵:\n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%10d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }


    //对边排序
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }


    //根据顶点的值返回下标 eg:  'C' --> 2
    //找不到返回-1
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }

        return -1;
    }

    //获取图中的边，放到EData[]，后面需要遍历该数组
    //eg: EData[] 形式 [['A','B', 12], ['B','F',7], .....]
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 功能: 获取下标为 i 的顶点的终点(), 用于后面判断两个顶点的终点是否相同
     *
     * @param ends : 数组就是记录了各个顶点对应的终点是哪个,ends 数组是在遍历过程中，逐步形成
     * @param i    : 表示传入的顶点对应的下标
     *
     * @return 返回的就是 下标为 i 的这个顶点对应的终点的下标
     */
    private int getEnds(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }

        return i;
    }
}

//表示一条边
class EData {
    char start; //边的起点
    char end; //边的终点
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "{" +
                "" + start +
                "-->" + end +
                ", w = " + weight +
                '}';
    }
}
