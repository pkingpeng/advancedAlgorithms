package com.pqr.binarySearchWithNoRecursion;

/**
 * @file: binarySearchWithNoRecur.java
 * @time: 2021/5/7 11:32 PM
 * @Author by Pking
 */
public class binarySearchWithNoRecur {
    public static void main(String[] args) {
        //测试
        int[] arr = {1, 3, 8, 10, 67, 100};
        int index = binarySearch(arr, 8);
        System.out.println("index=" + index);

    }

    //二分查找非递归实现
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}



