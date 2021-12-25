package com.pqr.DAC;

/**
 * @file: hanoiTower.java
 * @time: 2021/5/8 12:03 PM
 * @Author by Pking
 * <p>
 * 汉诺塔问题
 * 使用分治算法
 */
public class hanoiTower {
    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');

    }

    //汉诺塔
    //num个从a借助b 移动到c
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            //如果我们有 n >= 2 情况，我们总是可以看做是两个盘 1.最下边的盘 2. 上面的盘
            //2) 先把最上面的所有盘A->B，移动过程使用c
            hanoiTower(num - 1, a, c, b);
            //3) 把最下边的盘 A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //4) 把B塔的所有盘从B->C，移动过程使用a
            hanoiTower(num - 1, b, a, c);
        }
    }
}
