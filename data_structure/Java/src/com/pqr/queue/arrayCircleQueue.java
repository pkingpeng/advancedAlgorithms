package com.pqr.queue;

import java.util.Scanner;

/**
 * @file: arrayCircleQueue.java
 * @time: 2021/1/18 11:16 PM
 * @Author by Pking
 * <p>
 * <p>
 * rear == front --> 空
 * (rear  + 1) % maxSize == front -->满
 * 队列中有效的数据的个数   (rear + maxSize - front) % maxSize
 **/
public class arrayCircleQueue {
    public static void main(String[] args) {
        //测试
        arrayToCicleQueue arrayToCicleQueue = new arrayToCicleQueue(3);
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
                    arrayToCicleQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数字：");
                    int value = scanner.nextInt();
                    arrayToCicleQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayToCicleQueue.getQueue();
                        System.out.printf("取出的数据是：%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayToCicleQueue.headQueue();
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


class arrayToCicleQueue {
    private int maxSize; // 数组最大容量
    private int front; //队列头：front 就指向队列的第一个元素
    private int rear; //队列尾：rear 指向队列的最后一个元素的后一个位置
    private int[] arr;

    //创建队列的构造器
    public arrayToCicleQueue(int arrMaxSize) {
        maxSize = arrMaxSize + 1;
        arr = new int[maxSize]; //有一个多余空间，队列有效数据个数为maxSize
        //front = 0; // ****注意是0****
        //rear = 0; //默认是0不写也可以

    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        arr[rear] = n;
        rear = (rear + 1) % maxSize; //防止越界
    }

    //出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        int tmp;
        tmp = arr[front];
        front = (front + 1) % maxSize;
        return tmp;
    }

    //展示队列所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i < front + validNumberSize(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //展示有效数据个人
    public int validNumberSize() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }
}