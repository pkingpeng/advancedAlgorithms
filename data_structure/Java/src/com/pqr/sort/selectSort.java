package com.pqr.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @file: selectSort.java
 * @time: 2021/4/22 3:02 PM
 * @Author by Pking
 */
public class selectSort {
    public static void main(String[] args) {
        int[] arr = {8, 3, 2, 1, 7, 4, 6, 5};

        //排序前
        System.out.println("排序前的数组");
        System.out.println(Arrays.toString(arr));

        //测试插入排序
        selectSort(arr);

        //排序后
        System.out.println("排序后的数组");
        System.out.println(Arrays.toString(arr));

        //测试80000个随机的数组
        int[] arr1 = new int[80000];
        for(int i = 0; i < 80000; i++){
            arr1[i] = (int)(Math.random() * 8000000);
        }


        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间" + dateString1);


        //测试冒泡排序
        selectSort(arr1);

        Date date2 = new Date();
        String dateString2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间" + dateString2);

    }

    //选择排序
    public static void selectSort(int[] arr) {
        // O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[minIndex]; //假设最小的索引是第一个
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
