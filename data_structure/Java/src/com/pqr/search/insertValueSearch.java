package com.pqr.search;

import java.util.Arrays;

/**
 * @file: insertValueSearch.java
 * @time: 2021/4/24 9:20 PM
 * @Author by Pking
 */
public class insertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 1; i < 100; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));

        int findVal = 1;
        int resIndex = insertValueSearch(arr, 0, arr.length - 1, findVal);
        System.out.println("resIndex: " + resIndex);

    }

    //int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left])
    //插值查找算法
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        //mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
