package com.pqr.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @file: Graph.java
 * @time: 2021/5/7 5:22 PM
 * @Author by Pking
 */
public class Graph {
    private ArrayList<String> vertexList; //存储顶点
    private int[][] edges; //存储邻接矩阵
    private int numOfEdges; //边的数目
    private boolean[] isVisited; //记录节点是否被访问过

    public static void main(String[] args) {
        //测试图的创建
        int n = 8; //节点 5个
//        String vertexValues[] = {"A", "B", "C", "D", "E"};
        String vertexValues[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Graph graph = new Graph(n);
        //添加节点
        for (String vertex : vertexValues) {
            graph.insertVertex(vertex);
        }

        //添加边
        //A-B A-C B-C B-D B-E
//        graph.insertEdges(0, 1, 1);
//        graph.insertEdges(0, 2, 1);
//        graph.insertEdges(1, 2, 1);
//        graph.insertEdges(1, 3, 1);
//        graph.insertEdges(1, 4, 1);



        //更新边的关系
        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);
        graph.insertEdges(3, 7, 1);
        graph.insertEdges(4, 7, 1);
        graph.insertEdges(2, 5, 1);
        graph.insertEdges(2, 6, 1);
        graph.insertEdges(5, 6, 1);


        //show
        graph.showGraphMatrix();

        //dfs
        System.out.println("dfs");
        graph.dfs();
        System.out.println();

        //bfs
        System.out.println("bfs");
        graph.bfs();
    }

    //constructer
    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
    }


    //得到第一个邻接节点下标W
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }

        return -1;
    }

    //根据前一个邻接节点的下标获得下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * DFS
     **/

    //dfs
    private void dfs(boolean[] isVisited, int i) {
        //打印未访问的节点
        System.out.print(getValueByIndex(i) + "->");

        // 1) 访问初始结点v，并标记结点v为已访问。
        isVisited[i] = true;

        int w = getFirstNeighbor(i); // 2) 查找结点v的第一个邻接结点w
        while (w != -1) {//说明w节点存在
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }

            //如果w已经被访问
            w = getNextNeighbor(i, w);

        }
    }

    //对dfs重载，遍历所有节点，并进行dfs
    public void dfs() {
        isVisited = new boolean[8];
        //遍历所有节点dfs
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }


    /**
     * BFS
     **/

    //bfs
    private void bfs(boolean[] isVisited, int i) {
        int u; //队列头节点的下标
        int w; //邻接节点下标
        //队列记录节点访问顺序
        LinkedList queue = new LinkedList();

        System.out.print(getValueByIndex(i) + "=>");

        isVisited[i] = true;
        //节点加入队列
        queue.add(i);

        while (!queue.isEmpty()) {
            //取出头
            u = (Integer)queue.removeFirst();
            //查找第一个邻接节点
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    isVisited[w] = true;
                    //入队
                    queue.add(w);

                }

                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs() {
        isVisited = new boolean[8];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }


    /**
     * 图的常用方法
     **/

    //显示图对应的矩阵
    public void showGraphMatrix() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //插入顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //返回节点个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //返回边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点i对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1 v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //添加边
    //v1 v2 表示点的下标即使第几个顶点
    //"A"-"B" "A"->0 "B"->1
    public void insertEdges(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}