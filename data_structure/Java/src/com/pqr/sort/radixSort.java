package com.pqr.sort;

import java.util.Arrays;

/**
 * @file: radixSort.java
 * @time: 2021/4/24 5:56 PM
 * @Author by Pking
 */
public class radixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};

        radixSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    //基数排序
    public static void radixSort(int[] arr) {

        //定义二维数组，表示10个桶，每个桶都是一个一维数组
        int[][] bucket = new int[10][arr.length];

        //定义一维数组记录各个桶里面数据个数
        int[] bucketEleCount = new int[10];

        //得到arr里面最大的数的位数
        int maxDigits = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxDigits) {
                maxDigits = arr[i];
            }
        }

        int maxDigitsCount = (maxDigits + "").length(); //用字符处理获得数字的位数

        //开始 逐位 操作
        for (int i = 0, n = 1; i < maxDigitsCount; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int digitOfEle = arr[j] / n % 10;//数组里面 元素对应位的值
                //放入桶中
                bucket[digitOfEle][bucketEleCount[digitOfEle]] = arr[j];
                bucketEleCount[digitOfEle]++;
            }
            //按照桶顺序，将元素放入原来数组
            int index = 0;
            for (int k = 0; k < 10; k++) {
                //判断 有数据 才需要放回原来数组
                if (bucketEleCount[k] != 0) {
                    for (int l = 0; l < bucketEleCount[k]; l++) {
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                //注意：需要清零，方便下次操作
                bucketEleCount[k] = 0;
            }
        }
    }
}
