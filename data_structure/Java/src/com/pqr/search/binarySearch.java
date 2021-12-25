package com.pqr.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @file: binarySearch.java
 * @time: 2021/4/24 8:58 PM
 * @Author by Pking
 */

//二分查找 数组必须有序
//若无序，则需要先排序
public class binarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        int findVal = 1000;
        int resIndex = binarySearch(arr, 0, arr.length - 1, findVal);
        System.out.println("resIndex:" + resIndex);

        ArrayList<Integer> resIndexList = binarySearchFindAll(arr, 0, arr.length -1, findVal);
        System.out.println("resIndexList:" + resIndexList);
    }

    public static int binarySearch(int arr[], int left, int right, int findVal) {
        int mid = left + (right - left) / 2;
        int midVal = arr[mid];

        if (left > right) {
            return -1; //找不到直接返回-1
        }

        if (findVal > midVal) {
            //从小到大，像右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }


    //如果找到所有符合要求的值的下标
    //例如：{1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，有多个相同的数值时，如何将所有的数值 都查找到，比如这里的 1000.
    //思路： 找到mid不返回; 分别像min索引值的左边和右边扫描，所有满足要求的索引加入到集合
    public static ArrayList binarySearchFindAll(int arr[], int left, int right, int findVal) {
        int mid = left + (right - left) / 2;
        int midVal = arr[mid];

        if (left > right) {
            return new ArrayList<Integer>(); //找不到直接返回-1
        }

        if (findVal > midVal) {
            //从小到大，像右递归
            return binarySearchFindAll(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearchFindAll(arr, left, mid - 1, findVal);
        } else {
            ArrayList<Integer> resIndexList = new ArrayList<Integer>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {//左边扫描结束
                    break;
                }
                //temp放入
                resIndexList.add(temp);
                temp -= 1;
            }
            resIndexList.add(mid);

            //右边扫描
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {//左边扫描结束
                    break;
                }
                //temp放入
                resIndexList.add(temp);
                temp += 1;
            }
            return resIndexList;
        }
    }
}

