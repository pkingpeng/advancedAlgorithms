package com.pqr.queue;

import java.util.Scanner;

/**
 * @file: arrayQueue.java
 * @time: 2021/1/17 5:41 PM
 * @Author by Pking
 */
public class arrayQueue {
    public static void main(String[] args) {
        //测试
        arrayToQueue arrayToQueue = new arrayToQueue(3);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):队列中取出数据");
            System.out.println("h(head):查看队列的头数据");
            key = scanner.next().charAt(0); //接收一个字符
            switch (key) {
                case 's':
                    arrayToQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数字：");
                    int value = scanner.nextInt();
                    arrayToQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayToQueue.getQueue();
                        System.out.printf("取出的数据是：%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayToQueue.headQueue();
                        System.out.printf("队列头数据是: %d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }

        }
        System.out.println("程序退出");
    }
}


//使用数组来模拟队列
//实现一个arrayToQueue类

class arrayToQueue {
    private int maxSize; // 数组最大容量
    private int front; //队列头
    private int rear; //队列尾
    private int[] arr;

    //创建队列的构造器
    public arrayToQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; // ****注意是-1****
        rear = -1;

    }

    //判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //队列添加数据
    public void addQueue(int n) {
        //判断是否满
        if (isFull()) {
            System.out.println("队列已满，无法添加");
            return;
        }
        //添加 --> rear后移
        arr[++rear] = n;
    }

    //出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        front++; //front后移
        return arr[front];
    }

    //展示队列所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d \n", i, arr[i]);
        }
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[front + 1];
    }

}