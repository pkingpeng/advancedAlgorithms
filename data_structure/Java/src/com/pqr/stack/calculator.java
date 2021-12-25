package com.pqr.stack;

/**
 * @file: calculator.java
 * @time: 2021/4/16 5:23 PM
 * @Author by Pking
 */


//使用栈完成表达式的计算 思路
//1. 通过一个 index  值（索引），来遍历我们的表达式
//2. 如果我们发现是一个数字, 判断是几位数，然后入栈
//3. 如果发现扫描到是一个符号,  就分如下情况
//  3.1 如果发现当前的符号栈为 空，就直接入栈
//  3.2 如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符， 就需要从数栈中pop出两个数,在从符号栈中pop出一个符号，进行运算, 将得到结果，入数栈，然后将当前的操作符入符号栈，
//         如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
//4. 当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.
//5. 最后在数栈只有一个数字，就是表达式的结果

public class calculator {
    public static void main(String[] args) {
        String expression = "222+31*6-1";

        Stack numStack = new Stack(10);
        Stack operStack = new Stack(10);

        int index = 0; //用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //每次扫描得到的char保存到ch
        String keepNum = ""; //用来处理多位数

        while (true) {
            //取字符30系显卡是不是没办法装黑苹果
            ch = expression.substring(index, index + 1).charAt(0);

            if (operStack.isOper(ch)) {//if it is oper char
                if (!operStack.isEmpty()) {
                    //judge the priority
                    //if pr <= oper which in stack --> pop 2 num and 1 oper; then cal and push cur oper
                    //else: push in operstack
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);

                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else { //empty --> push
                    operStack.push(ch);
                }
            } else {// if it is num --> judge index + 1:
                // if num --> keep index += 1; else oper --> push
                keepNum += ch;

                //if ch is the last of expression --> push directly
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //if next index is oper --> push
                        //keepNum = "123" --> 123
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        while (true) {//only same priority oper in operStack; then cal every 2 num and 1 oper; fi get result
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        int res2 = numStack.pop();
        System.out.printf("%s=%d", expression, res2);
    }
}

//创建数栈和运算符栈
class Stack {
    private int maxSize; //栈大小
    private int[] stack; //数组模拟栈
    private int top = -1; //表示栈顶，初始化为-1

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //先判断是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            System.out.println("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈：需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d \n", i, stack[i]);
        }
    }

    //返回栈顶的元素，但不出栈
    public int peek() {
        return stack[top];
    }


    //返回运算符的优先级 --> 自定义: 优先级使用数字表示 数字越大 --> 优先级高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }

    }

    //judge if it is a opertion char
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}

