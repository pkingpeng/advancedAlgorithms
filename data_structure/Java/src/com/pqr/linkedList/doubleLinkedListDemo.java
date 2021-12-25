package com.pqr.linkedList;

/**
 * @file: com.pqr.linkedList.doubleLinkedListDemo.java
 * @time: 2021/4/13 5:04 PM
 * @Author by Pking
 */
public class doubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        HeroNode2 heroNode1 = new HeroNode2(1, "songjiang", "jishiyu");
        HeroNode2 heroNode2 = new HeroNode2(2, "lujunyi", "yuqilin");
        HeroNode2 heroNode3 = new HeroNode2(3, "wuyong", "zhiduoxing");
        HeroNode2 heroNode4 = new HeroNode2(4, "linchong", "baozitou");

        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode4);

        doubleLinkedList.show();

        //修改
        HeroNode2 newHeroNode = new HeroNode2(4,"gongsunsheng", "ruyunlong");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改过后");
        doubleLinkedList.show();

        //删除
        doubleLinkedList.remove(2);
        System.out.println("删除后");
        doubleLinkedList.show();

    }
}


//定义heroNode2，定义一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    //构造器
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

//创建一个双向链表的类
class DoubleLinkedList {
    //初始化一个头节点
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }


    //添加节点
    //(1) 先找到双向链表的最后这个节点
    //(2) temp.next = newHeroNode
    //(3) newHeroNode.pre = temp;
    //思路：找到链表的最后节点，将最后节点的next指向新的节点
    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }

        //找到最后
        temp.next = heroNode;
        heroNode.pre = temp;
        //形成一个双向链表
    }

    //添加节点根据编号顺序
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
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
            temp.next = heroNode;
            heroNode.pre = temp;
        }
    }

    //修改节点信息，根据no编号来修改,思路跟单向链表一致
    //根据no修改
    // (1)先找到该节点，通过遍历，
    // (2) temp.name = newHeroNode.name ; temp.nickname= newHeroNode.nickname
    public void update(HeroNode2 newHeronode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //遍历找到需要修改的节点
        HeroNode2 temp = head.next;
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
    //(1) 因为是双向链表，因此，我们可以实现自我删除某个节点
    //(2) 直接找到要删除的这个节点，比如temp
    //(3)  temp.pre.next = temp.next
    //(4) temp.next.pre = temp.pre;
    //被删除的节点，将不会有其它引用指向，会被垃圾回收机制回收
    public void remove(int no) {

        //判断当前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {//到了链表的最后
                break;
            }
            if (temp.no == no) {//找到了待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {//找到了节点
            temp.pre.next = temp.next;
            if (temp.next != null) {//避免删除的是最后一个节点
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的节点%d为找到", no);
        }
    }

    //遍历
    //show链表
    public void show() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //head不能动，需要一个辅助变量temp
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);//重写过，直接打印就行了
            temp = temp.next;
        }
    }

}
