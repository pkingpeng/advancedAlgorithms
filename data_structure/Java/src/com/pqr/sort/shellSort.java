package com.pqr.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @file: shellSort.java
 * @time: 2021/4/22 4:18 PM
 * @Author by Pking
 */
public class shellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        //排序前
        System.out.println("排序前的数组");
        System.out.println(Arrays.toString(arr));

        //测试排序
        shellSort(arr);

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
        shellSortByShift(arr1);

        Date date2 = new Date();
        String dateString2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间" + dateString2);
    }


    public static void shellSort(int[] arr) {

        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) { //gap组， 对每组遍历
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {//交换 大的到后面，小的到前面
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    //对交换式的希尔排序进行优化->移位法
    public static void shellSortByShift(int[] arr) {

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) { // 从第gap个元素开始，逐个对所在的组进行直接插入排序
                int j = i;
                int temp = arr[j];
                if(arr[j] < arr[j -gap]){
                    while(j - gap >= 0 && temp < arr[j - gap]){
                        //插入排序 移动
                        arr[j] = arr[j -gap];
                        j -= gap;
                    }
                    //退出循环，找到temp插入的位置
                    arr[j] = temp;
                }
            }
        }
    }

}
