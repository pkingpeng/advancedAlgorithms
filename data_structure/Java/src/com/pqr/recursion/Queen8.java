package com.pqr.recursion;

/**
 * @file: Queen8.java
 * @time: 2021/4/21 9:34 PM
 * @Author by Pking
 */
public class Queen8 {
    int max = 8;
    int[] arr = new int[max]; // arr[8] = {0 , 4, 7, 5, 2, 6, 1, 3}
    static int count = 0;

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println("一共有" + count + "种解法");
    }

    //放置第n个皇后
    private void check(int n) {
        if (n == max) { //8个皇后已经放置好
            print();
            return;
        }

        for (int i = 0; i < max; i++) {
            arr[n] = i;
            if (judgePosition(n)) {//true --> 不冲突
                check(n + 1);
            }
        }
    }


    //查看放置的第n个皇后，检测该皇后是否与之前摆放的冲突
    private boolean judgePosition(int n) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) { //如果 同一列 或者 同一斜线
                return false;
            }
        }
        return true;
    }

    //输出皇后摆放的位置
    private void print() {
        count += 1;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
