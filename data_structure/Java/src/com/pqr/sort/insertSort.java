package com.pqr.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @file: insertSort.java
 * @time: 2021/4/22 3:22 PM
 * @Author by Pking
 */
public class insertSort {
    public static void main(String[] args) {
        int[] arr = {17, 3, 25, 14, 20, 9};

        //排序前
        System.out.println("排序前的数组");
        System.out.println(Arrays.toString(arr));

        //测试冒泡排序
        insertSort(arr);

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
        insertSort(arr1);

        Date date2 = new Date();
        String dateString2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间" + dateString2);
    }


    //插入排序
    public static void insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertIndex = i - 1;
            insertVal = arr[i];
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //推出while循环时 insertIndex + 1的位置就是
            arr[insertIndex + 1] = insertVal;

        }
    }
}


