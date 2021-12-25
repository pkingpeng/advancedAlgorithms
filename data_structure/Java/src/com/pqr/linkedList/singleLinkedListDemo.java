package com.pqr.linkedList;

import java.util.Stack;

/**
 * @file: singleLinkedList.java
 * @time: 2021/1/26 10:27 PM
 * @Author by Pking
 */
public class singleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "songjiang", "jishiyu");
        HeroNode heroNode2 = new HeroNode(2, "lujunyi", "yuqilin");
        HeroNode heroNode3 = new HeroNode(3, "wuyong", "zhiduoxing");
        HeroNode heroNode4 = new HeroNode(4, "linchong", "baozitou");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

//        singleLinkedList.add(heroNode1);
//        singleLinkedList.add(heroNode2);
//        singleLinkedList.add(heroNode3);
//        singleLinkedList.add(heroNode4);

        //加入按照编号
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode3);

        //3.测试单链表的反转
        System.out.println("原来链表");
        singleLinkedList.show();
        System.out.println("反转链表");
        reverseSingleList(singleLinkedList.getHead());
        singleLinkedList.show();
        System.out.println();

        //修改节点的代码
        //显示
        singleLinkedList.show();

        HeroNode newHeronode = new HeroNode(2, "pqr", "pking");
        singleLinkedList.update(newHeronode);

        System.out.println("修改后");
        //修改后显示
        singleLinkedList.show();


//        //删除节点
//        singleLinkedList.remove(1);
//        System.out.println("删除后");
//        singleLinkedList.show();
//        singleLinkedList.remove(2);
//        System.out.println();
//        singleLinkedList.show();
//        singleLinkedList.remove(3);
//        singleLinkedList.remove(4);
//        System.out.println();
//        singleLinkedList.show();

        //测试笔试题1
        System.out.println("笔试题1");
        System.out.println(getLength(singleLinkedList.getHead()));

        //测试笔试题2
        System.out.println("笔试题2");
        HeroNode res = findLastKNode(singleLinkedList.getHead(), 2);
        System.out.println("倒数第二个是" + res);

        //测试逆序打印
        System.out.println("逆序打印");
        reversePrintLinkedList(singleLinkedList.getHead());
    }


    //笔试题

    /**
     * 1.获取单链表的节点个数
     **/
    public static int getLength(HeroNode head) {
        if (head.next == null) {//带头节点的空链表
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    /**
     * 2.查找单链表中的倒数第k个节点
     * 思路：
     * 1.接收head节点，并接收一个index，表示倒数第index
     * 2.先把链表从头到尾遍历，得到链表的总长度length
     * 3.得到length后，我们从链表的第一个开始遍历,(length - index)个，就可以得到
     **/
    public static HeroNode findLastKNode(HeroNode head, int k) {
        if (head.next == null) {
            return null;
        }
        //找到length
        int size = getLength(head);
        //第二次遍历到 size - k
        if (k > size || k <= 0) {
            return null;
        }

        HeroNode cur = head.next;
        for (int i = 0; i < size - k; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 3.单链表的翻转
     * 思路：
     * 1.定义一个节点reverseHead --> 反转头
     * 2.头到尾遍历原来的链表，每遍历一个节点，放到reverseHead的最前端
     * 3.原来的head.next = reverseHead.next
     **/
    public static void reverseSingleList(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }

        HeroNode cur = head.next;
        HeroNode next = null; //定义 当前节点的下个节点
        HeroNode reverseHead = new HeroNode(0, "", "");

        //遍历
        while (cur != null) {
            next = cur.next;

            cur.next = reverseHead.next;//将一个链表的节点添加到另一个链表里面的方法
            reverseHead.next = cur;

            cur = next; //cur后移
        }

        head.next = reverseHead.next;

    }

    /**
     * 4.从尾到头打印单链表
     * 方式1： 先将单链表进行反转操作，然后再遍历即可，这样的做的问题是会破坏原来的单链表的结构，不建议
     * 方式2：可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果.
     **/
    public static void reversePrintLinkedList(HeroNode head) {
        if (head.next == null) {
            return;
        }

        //创建栈，各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //遍历链表
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 5.合并两个有序链表，合并以后仍然有序
     * 遍历链表head1，head2有序放到新链表head
     * 最后将未遍历完的链表放到head
     **/
    public static HeroNode mergeLinkedList(HeroNode head1, HeroNode head2) {
        HeroNode head = new HeroNode(0, "", "");
        HeroNode cur = head;
        while (head1 != null & head2 != null) {
            if (head1.no < head2.no) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }

        if (head1 != null) {
            cur.next = head1;
        }
        if (head2 != null) {
            cur.next = head2;
        }
        return head.next;
    }
}

//定义heroNode，定义一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方法，重新toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", next=" + next +
                '}';
    }
}

//定义一个singleLinkedList
class SingleLinkedList {
    //初始化一个头节点，一般最好不要动，避免找不到链表的头部
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }
    //addNode 第一种方法在添加英雄时，直接添加到链表的尾部

    //思路：找到链表的最后节点，将最后节点的next指向新的节点
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置(如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false; //标志添加的编号是否存在
        while (true) {
            if (temp.next == null) {//temp在链表最后
                break;
            }
            if (temp.next.no > heroNode.no) {
                break; //heroNode 应该在temp后面
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {//编号存在，不能添加
            System.out.printf("插入的英雄编号no.%d已经存在\n", heroNode.no);
        } else {
            //插入到temp后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点信息，根据no编号来修改
    //根据no修改
    // (1)先找到该节点，通过遍历，
    // (2) temp.name = newHeroNode.name ; temp.nickname= newHeroNode.nickname
    public void update(HeroNode newHeronode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //遍历找到需要修改的节点
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {//到了链表最后的， 表示遍历结束
                break;
            }
            if (temp.no == newHeronode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //判断是否找到修改的节点
        if (flag) {
            temp.name = newHeronode.name;
            temp.nickname = newHeronode.nickname;
        } else { //没找到需要修改的节点
            System.out.printf("没有找到需要修改的节点%d，不能修改\n", newHeronode.no);
        }
    }

    //删除链表中的节点
    //1. 我们先找到 需要删除的这个节点的前一个节点 temp
    //2. temp.next = temp.next.next
    //3. 被删除的节点，将不会有其它引用指向，会被垃圾回收机制回收
    public void remove(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {//到了链表的最后
                break;
            }
            if (temp.next.no == no) {//找到了找到了待删除节点的前一个节点
                flag = true;
                break;
            }
        }

        if (flag) {//找到了节点
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的节点%d为找到", no);
        }
    }


    //show链表
    public void show() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //head不能动，需要一个辅助变量temp
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);//重写过，直接打印就行了
            temp = temp.next;
        }
    }
}