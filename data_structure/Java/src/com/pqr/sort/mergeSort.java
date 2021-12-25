package com.pqr.sort;

import java.util.Arrays;

/**
 * @file: mergeSort.java
 * @time: 2021/4/24 5:22 PM
 * @Author by Pking
 */
public class mergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length]; //归并排序需要额外空间temp 
        //归并排序空间复杂度为线性 O(N)
        mergeSort(arr, 0, arr.length - 1, temp);

        System.out.println(Arrays.toString(arr));
    }

    //分+合
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            //左递归分
            mergeSort(arr, left, mid, temp);

            //右分
            mergeSort(arr, mid + 1, right, temp);

            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    //合并方法 --> 合并两个有序数组 成为一个有序数组
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int ls = left; //左边有序序列的 初始索引
        int rs = mid + 1; //右边序列的 初始索引
        int tempIndex = 0; //中转数组的索引

        while (ls <= mid && rs <= right) {
            if (arr[ls] <= arr[rs]) {
                temp[tempIndex] = arr[ls];
                tempIndex += 1;
                ls += 1;
            } else {
                temp[tempIndex] = arr[rs];
                tempIndex += 1;
                rs += 1;
            }
        }
        //将剩余数组的数据放入temp
        while (ls <= mid) {
            temp[tempIndex] = arr[ls];
            tempIndex++;
            ls++;
        }


        while (rs <= right) {
            temp[tempIndex] = arr[rs];
            tempIndex++;
            rs++;
        }

        //将临时数组的数据放回原来数组应该存在的位置
        tempIndex = 0;
        int arrLeft = left;
        while (arrLeft <= right) {
            arr[arrLeft] = temp[tempIndex];
            arrLeft++;
            tempIndex++;
    }
}
