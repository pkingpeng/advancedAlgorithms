package com.pqr.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @file: quickSort.java
 * @time: 2021/4/23 12:19 PM
 * @Author by Pking
 */
public class quickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};

        //排序前
        System.out.println("排序前的数组");
        System.out.println(Arrays.toString(arr));

        //测试冒泡排序
        quickSort(arr, 0, arr.length - 1);


        //排序后
        System.out.println("排序后的数组");
        System.out.println(Arrays.toString(arr));

        //测试80000个随机的数组
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int) (Math.random() * 8000000);
        }


        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间" + dateString1);


        //测试冒泡排序
        quickSort(arr1, 0, arr.length - 1);

        Date date2 = new Date();
        String dateString2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间" + dateString2);

    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[left + (right - left) / 2];
        int temp = 0; //用于交换时使用
        //比pivot 小的放到左边， 大的放到右边
        while (l < r) {
            while (arr[l] < pivot) {//pivot左边一直找到 比 pivot 大或等 就退出
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }
            // l >= r时；表示pivot左右两的值已经符合
            if (l >= r) {
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

//            if(arr[l] == pivot){
//                r -= 1;
//            }
//
//            if(arr[r] == pivot){
//                l += 1;
//            }
        }

        if (l == r) {
            l += 1;
            r -= 1;
        }

        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }

        //右边
        if (right > l) {
            quickSort(arr, l, right);
        }

    }


}
