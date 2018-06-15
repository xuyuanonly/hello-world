package com.xy.sort;

import java.util.Arrays;

/**
 * Description:https://github.com/iTimeTraveler/SortAlgorithms
 * Created by Hugo.xu on 2018/6/15.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] a = {6,1,7,2,5,9,9,3,8};
        quickSort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }

    public static void quickSort(int[] arr,int low ,int high){
        if (arr.length <= 0) return;
        if (low >= high) return;
        int left = low;
        int right = high;
        int temp = arr[left];
        while (left < right){
            while(left <right && arr[right] >= temp){
                right --;
            }
            arr[left] = arr[right];
            while (left <right && arr[left] <= temp){
                left ++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;
        quickSort(arr,low,left-1);
        quickSort(arr,left+1,high);
    }
}

