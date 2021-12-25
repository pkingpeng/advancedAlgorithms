package com.pqr.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @file: puppleSort.java
 * @time: 2021/4/22 2:39 PM
 * @Author by Pking
 */
public class bubbleSort {
    public static void main(String[] args) {

        int arr[] = {3, 9, -1, 10, -2};

        //排序前
        System.out.println("排序前的数组");
        System.out.println(Arrays.toString(arr));

        //测试冒泡排序
        bubbleSort(arr);

        //排序后
        System.out.println("排序后的数组");
        System.out.println(Arrays.toString(arr));


        //测试冒泡排序的速度 --> O(n ^ 2);
        int[] arr1 = new int[80000];
        for(int i = 0; i < 80000; i++){
            arr1[i] = (int)(Math.random() * 8000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间" + dateString1);


        //测试冒泡排序
        bubbleSort(arr1);

        Date date2 = new Date();
        String dateString2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间" + dateString2);



    }

    //封装冒泡排序法
    public static void bubbleSort(int[] arr){
        int temp = 0;
        boolean flag = false;
        for(int i = 0; i < arr.length - 1; i++){
            flag = false;
            for(int j = 0; j < arr.length - 1 - i; j++){
                if(arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }
    }
}
