package com.pqr.singleLinkedList;

import java.util.Stack;

/**
 * @file: testStack.java
 * @time: 2021/2/11 2:14 PM
 * @Author by Pking
 */
// 栈的使用
public class testStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack();
        stack.add("1");
        stack.add("2");
        stack.add("3");

        //出栈
        while(stack.size() > 0){
            System.out.println(stack.pop());
        }


    }
}
