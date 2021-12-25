package com.pqr.stack;

import java.util.LinkedList;

/**
 * @file: linkedListStackDemo.java
 * @time: 2021/4/16 3:38 PM
 * @Author by Pking
 * <p>
 * 使用尾插法来实现
 * 需要遍历
 * 时间复杂度比较高
 * <p>
 * 优化：头插法
 */
public class linkedListStackDemo {
    public static void main(String[] args) {
        //测试
        LinkedListStack stack = new LinkedListStack();
        stack.push(new Node(1));
        stack.push(new Node(2));
        stack.push(new Node(3));
        stack.push(new Node(4));

        //入栈后打印
        System.out.println("入栈后打印");
        stack.list();

        //出栈
        System.out.println("出栈");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        System.out.println("剩余");
        stack.list();

    }
}

//链表的节点
class Node {

    private int no;
    private Node next;

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }


    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", next=" + next +
                '}';
    }
}

class LinkedListStack {
    private Node head; //头节点：防止丢失链表
    private Node top; //表示栈顶

    public LinkedListStack() {
        head = new Node(-1);
        top = head;
    }

    //判断栈为空
    public boolean isEmpty() {
        return head.getNext() == null;
    }

    //遍历
    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }

        Node temp = head;
        while (temp.getNext() != null) {
            System.out.println(temp.getNext());
            temp = temp.getNext();
        }
    }

    //入栈操作
    public void push(Node node) {
        top.setNext(node);
        top = node;
    }

    //出栈 尾插法 -->出栈需要遍历
    public Node pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }

        Node value = top; //top就是栈顶，直接返回
        Node temp = head;
        while (true) {//找到top的前一个节点
            if (temp.getNext() == top) {//temp就是top的前一个节点
                break;
            }
            temp = temp.getNext();
        }
        top = temp;
        //top = temp; 栈顶前移
        top.setNext(null);
        return value;
    }

}
