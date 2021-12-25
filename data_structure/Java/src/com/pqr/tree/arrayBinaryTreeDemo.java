package com.pqr.tree;

/**
 * @file: arrayBinaryTree.java
 * @time: 2021/4/25 5:45 PM
 * @Author by Pking
 */
public class arrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};

        //        1
        //     2      3
        //   4   5  6   7
        // 8


        ArrayBinaryTree arraybinarytree = new ArrayBinaryTree(arr);
        System.out.println("前序遍历");
        arraybinarytree.preOrder();
        //pre --> 1 2 4 8 5 3 6 7

        System.out.println("中序遍历");
        arraybinarytree.nifixOrder();
        //nifix --> 8 4 2 5 1 6 3 7

        System.out.println("后序遍历");
        arraybinarytree.postOrder();
        //post --> 8 4 5 2 6 7 3 1
    }

}

//arrayBinaryTree 实现顺序存储二叉树

class ArrayBinaryTree {
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载
    public void preOrder() {
        this.preOrder(0);
    }

    public void nifixOrder() {
        this.nifixOrder(0);
    }

    public void postOrder(){
        this.postOrder(0);
    }

    //完成顺序存储二叉树 --> 前序遍历
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的谦虚遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左遍历
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }

        //向右递归
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //完成顺序存储二叉树 --> 中序遍历
    public void nifixOrder(int index) {
        if(arr == null || arr.length ==0){
            System.out.println("数组为空，无法遍历");
        }

        if((index * 2 + 1) < arr.length){
            nifixOrder(index * 2 + 1);
        }

        System.out.println(arr[index]);

        if((index * 2 + 2) < arr.length){
            nifixOrder(index * 2 + 2);
        }
    }

    //完成顺序存储二叉树 --> 后序遍历
    public void postOrder(int index) {
        if(arr == null || arr.length ==0){
            System.out.println("数组为空，无法遍历");
        }

        if((index * 2 + 1) < arr.length){
            postOrder(index * 2 + 1);
        }

        if((index * 2 + 2) < arr.length){
            postOrder(index * 2 + 2);
        }

        System.out.println(arr[index]);
    }
}
