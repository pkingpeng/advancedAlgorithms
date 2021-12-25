package com.pqr.huffmanTree;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @file: huffmanTree.java
 * @time: 2021/4/28 6:04 PM
 * @Author by Pking
 */
public class huffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        //huffmanTree
        //       67·
        //   29     38
        //        15   23
        //       7 8 10 13
        //          4 6
        //         1 3

        Node root = createHuffmanTree(arr);

        preOrder(root);


    }

    //前序遍历的方法
    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("是空树，不能遍历");
        }
    }

    public static Node createHuffmanTree(int[] arr){
        List<Node> nodes = new ArrayList<Node>();
        for(int value: arr){
            nodes.add(new Node(value));
        }

        while(nodes.size() > 1){
            //排序 从小到大
            Collections.sort(nodes);

            //取出跟节点最小的两颗二叉树
            //1. 取出权值最小的节点
            Node leftNode = nodes.get(0);
            //2. 第二小
            Node rightNode = nodes.get(1);

            //3. 构建一个新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //4. 从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5.parent加入nodes中，重复操作
            nodes.add(parent);
        }

        return nodes.get(0); //返回生成的霍夫曼树的root节点

    }


}


//创建节点类
// 为了让 Node 对象持续排序 Collections 集合排序
// 让 Node 实现 Comparable 接口
class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    //写一个前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }


        if(this.right != null){
            this.right.preOrder();
        }
    }

    public Node(int value){
        this.value = value;
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(@NotNull Node o) {
        return  this.value - o.value;
        //从小到大
    }


}
