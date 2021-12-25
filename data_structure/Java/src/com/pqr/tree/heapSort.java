package com.pqr.tree;

import java.util.Arrays;

/**
 * @file: heapSort.java
 * @time: 2021/4/27 5:48 PM
 * @Author by Pking
 * <p>
 * <p>
 * 思路：
 * 1).将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
 * 2).将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
 * 3).重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤， 直到整个序列有序。
 */
public class heapSort {
    public static void main(String[] args) {
        //升序排列 --> 大顶堆
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    //堆排序
    public static void heapSort(int[] arr) {
        int temp = 0;
        System.out.println("堆排序");
        //1. 无序序列构建成一个堆，此时我们从最后一个非叶子结点开始，左 --> 右， 上 --> 下调整
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        //2.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
        //3.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换 步骤，直到整个序列有序。
        for(int j = arr.length - 1; j > 0; j--){
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //此时当前堆最大元素被放到序列最后
            //然后继续 堆化 获得最大元素
            heapify(arr, 0, j);
        }
    }

    //数组堆化 --> 变成大顶堆
    //功能: 以i对应的非叶子节点对应的树调整为大顶堆
    //eg: i = 1
    //arr = [4,6,8,5,9] --> heaplifyArr = [4,9,8,5,6]
    //    4          -->          4
    //  6    8                 9     8
    //5    9                5     6
    //i = 0;
    //    4          -->          9
    //  6    8                 6     8   [9,6,8,5,4]
    //5    9                5     4
    // i: 非叶子节点对应的索引
    // length: 堆化参与的元素(逐渐减少)
    public static void heapify(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {// 左子节点 < 右子节点
                k++; //k指向右节点
            }
            if (arr[k] > temp) { //子 > 父
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        //当 for 循环结束后，我们已经将以 i 为父结点的树的最大值，放在了 最顶(局部)
        arr[i] = temp;
    }

}
