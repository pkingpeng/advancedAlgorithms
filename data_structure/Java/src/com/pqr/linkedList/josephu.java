package com.pqr.linkedList;

/**
 * @file: josephu.java
 * @time: 2021/4/13 10:31 PM
 * @Author by Pking
 */


/**
 * Josephu 问题为:设编号为 1，2，... n 的 n 个人围坐一圈，约定编号为 k(1<=k<=n)的人从 1 开始报数，数 到 m 的那个人出列，
 * 它的下一位又从 1 开始报数，数到 m 的那个人又出列，依次类推，直到所有人出列为止，由 此产生一个出队编号的序列。
 * <p>
 * 构建一个单向的环形链表思路
 * 1. 先创建第一个节点, 让 first 指向该节点，并形成环形
 * 2. 后面当我们每创建一个新的节点，就把该节点，加入到已有的环形链表中即可.
 * <p>
 * 遍历环形链表
 * 1. 先让一个辅助指针(变量) curBoy，指向first节点
 * 2. 然后通过一个while循环遍历 该环形链表即可 curBoy.next  == first 结束
 **/
public class josephu {
    public static void main(String[] args) {
        //测试构建环形链表
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        //测试小孩出圈是否正确
        circleSingleLinkedList.countBoy(1, 2, 5);
        //出圈的顺序 2->4->1->5->3
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点
    private Boy first = new Boy(-1);

    //添加boy节点形成环形链表
    public void addBoy(int nums) {//nums代表一共多少个节点对应n个人
        if (nums < 2) {
            System.out.println("人数不正确");
            return;
        }
        Boy curBoy = null;//辅助指针
        //使用for循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //第一个小孩，需要特殊处理
            if (i == 1) {
                first = boy;
                first.setNext(first); //成环
                curBoy = first;
            }
            curBoy.setNext(boy);
            boy.setNext(first);
            curBoy = boy;
        }
    }

    //遍历环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }

        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩编号%d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {//遍历完毕
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //根据用户输入生成出圈的顺序
    //n = 5 , 即有5个人
    //k = 1, 从第一个人开始报数
    //m = 2, 数2下fff
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("输入数据不规范");
            return;
        }

        //1.  需求创建一个辅助指针(变量) helper , 事先应该指向环形链表的最后这个节点.
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {//说明helper此时指向最后节点
                break;
            }
            helper = helper.getNext();
        }

        //补充： 小孩报数前，先让 first 和  helper 移动 k - 1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        //2.  当小孩报数时，让first 和 helper 指针同时 的移动  m  - 1 次
        while (true) {
            if (helper == first) {//说明圈中只有一个节点
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }

            //3. 这时first指向要出圈的节点，完成出圈操作
            //first = first .next
            //helper.next = first
            System.out.printf("小孩%d出圈\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }

        System.out.printf("最后一个小孩%d出圈\n", first.getNo());

    }
}


//创建boy表示一个节点
class Boy {
    private int no;
    private Boy next; //默认为null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
