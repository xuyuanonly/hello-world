package com.xy.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] a = {6,1,7,2,5,9,3,8};
        int temp;
        int lowIndex;
        for (int i = 0; i < a.length; i++) {
            lowIndex = i;
            for (int j = i+1; j < a.length; j++) {
                if (a[j]<a[lowIndex]){
                    lowIndex = j;
                }
            }
            temp = a[i];
            a[i] = a[lowIndex];
            a[lowIndex] = temp;
        }
        System.out.println(Arrays.toString(a));
    }
}
