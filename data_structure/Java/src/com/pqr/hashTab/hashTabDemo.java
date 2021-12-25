package com.pqr.hashTab;

import java.util.Scanner;

/**
 * @file: hashTabDemo.java
 * @time: 2021/4/24 11:31 PM
 * @Author by Pking
 */
public class hashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        hashTab hashTab = new hashTab(7);

        //menu
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入 id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入查找的id:");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//创建hashtable用于管理多条链表
class hashTab {
    private EmpLinkedList[] empLinkedLists;
    private int size; //链表条数

    //constructer
    public hashTab(int size) {
        //初始化
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];

        //分别初始化每个链表
        for(int i = 0; i < size; i++){
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        int empLinkedListNo = hashFunction(emp.id);
        //emp --> 对应链表
        empLinkedLists[empLinkedListNo].add(emp);
    }

    //输入id 查找雇员
    public void findEmpById(int id){
        //使用hashfuction 确定在哪条链表
        int empLinkedListNo = hashFunction(id);
        Emp emp = empLinkedLists[empLinkedListNo].findEmpById(id);
        if(emp != null){//找到
            System.out.printf("在第%d 条链表中找到 雇员 id = %d\n", (empLinkedListNo + 1), id);
        }else{
            System.out.println("哈希表中没有找到该雇员");
        }
    }

    //遍历所有链表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list(i);
        }
    }

    //散列函数 --> 使用简单取模法
    public int hashFunction(int id) {
        return id % size;
    }
}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next; //默认为空

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建链表，会存放数据
class EmpLinkedList {
    //头指针指向第一个Emp 即 head --> first Emp
    private Emp head;

    //添加雇员
    //suppose
    //id自增长，即add到链表的最后即可
    public void add(Emp emp) {
        //如果是添加第一个emp
        if (head == null) {
            head = emp;
            return;
        }

        //不是第一个，使用辅助指针帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {//到了链表最后
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    //根据id查找emp
    public Emp findEmpById(int id){
        if(head == null){
            System.out.println("链表为空");
            return null;
        }

        Emp curEmp = head;
        while(true){
            if(curEmp.id == id){//找到
                break;
            }
            if(curEmp.next == null){//遍历当前链表没有找到
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    //遍历链表雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.println("第 "+(no+1)+" 链表为空");
            return;
        }
        System.out.print("第 "+(no+1)+" 链表的信息为");
        Emp curEmp = head;
        while (true) {
            System.out.printf(" --> id:%d name = %s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {//最后一个节点
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }
}
