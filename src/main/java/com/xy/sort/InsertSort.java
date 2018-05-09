package com.xy.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] a = {6,1,7,2,5,9,3,8};
        int temp;
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j>0&& a[j-1]>a[j]; j--) {
                temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
