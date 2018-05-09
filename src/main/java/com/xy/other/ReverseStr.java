package com.xy.other;

public class ReverseStr {
    public static void main(String[] args) {
        String reversed = reverse("Hello world!");
        System.out.println(reversed);
    }

    private static String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length()-1; i >=0 ; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
