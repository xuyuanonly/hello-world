package com.xy.other;

public class BinarySearch {
    public static void main(String[] args) {
        int a[]={0,2,6,8,9,44,77,88,90,91,94,97,99,100};
        int index = binarySearch(101,a,0,a.length-1);
        System.out.println(index);
    }

    private static int binarySearch(int target,int[] a,int start,int end) {
        if (start>end){
            return -1;
        }
        int mid = (start+end)/2;
        if(a[mid] == target){
            return mid;
        }else if (a[mid] > target){
            end = mid-1;     //todo -1 !!!!!!
            return binarySearch(target,a,start,end);
        }else{
            start = mid+1; //todo +1 !!!!!!!!
            return binarySearch(target,a,start,end);
        }
    }
}
