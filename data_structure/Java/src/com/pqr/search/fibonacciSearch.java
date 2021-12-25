package com.pqr.search;

import java.util.Arrays;

/**
 * @file: fibonacciSearch.java
 * @time: 2021/4/24 10:23 PM
 * @Author by Pking
 */
public class fibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        System.out.println(fibSearch(arr, 1234));

    }

    //因为后面我们 mid=low+F(k-1)-1，需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
    //非递归获得斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //斐波那契查找
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0; //斐波那契分割数值下标
        int mid = 0;//存放mid值
        int f[] = fib();

        //获取分割下标
        while (high > f[k] - 1) {
            k++;
        }

        //举例:
        //temp = {1,8, 10, 89, 1000, 1234, 0, 0} => {1,8, 10, 89, 1000, 1234, 1234, 1234,}
        int[] temp = Arrays.copyOf(a, f[k]);  //不足的部分会用 0 填充
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        //查找
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                //左边查找
                high = mid - 1;
                k--;
            } else if (key > temp[mid]) {
                //右边
                low = mid + 1;
                //为什么是 k -=2
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //3. 因为后面我们有 f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4] //4. 即在 f[k-2] 的前面进行查找 k -=2
                //5. 即下次循环 mid=f[k-1-2]-1
                k -= 2;
            }else{
                if(mid <= high){
                    return mid;
                }else{
                    return high;
                }
            }
        }
        return -1;
    }
}
