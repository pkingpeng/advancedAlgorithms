package com.pqr.rabinkarp;
import java.util.*;

public class rabinKarp {
    public static void main(String[] args) {
        //leetcode 1044 and 686
    }
}

class rabinKarpDemo {
    static int P = 1313;
    static long[] p, h;

    //字符串哈希
    //rk算法
    static int strHash(String ss, String b) {
        int n = ss.length(), m = b.length();
        String str = ss + b;
        int len = str.length();
        int[] h = new int[len + 10], p = new int[len + 10];
        p[0] = 1;
        for (int i = 0; i < len; i++) {
            p[i + 1] = p[i] * P;
            h[i + 1] = h[i] * P + str.charAt(i);
        }
        int r = len, l = r - m + 1;
        int target = h[r] - h[l - 1] * p[r - l + 1]; // b 的哈希值
        for (int i = 1; i <= n; i++) {
            int j = i + m - 1;
            int cur = h[j] - h[i - 1] * p[j - i + 1]; // 子串哈希值
            if (cur == target) return i - 1;
        }
        return -1;
    }

    static String longestDupSubstring(String s) {
        int n = s.length();

        h = new long[n + 1];
        p = new long[n + 1];

        p[0] = 1;
        for(int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P;
            h[i] = h[i - 1] * P + s.charAt(i - 1);
        }

        String ans = "";
        int l = 0, r = n;
        while(l <= r) {
            int mid = l + r >> 1;
            String t = check(s, mid);
            if(t.length() != 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }

            ans = t.length() > ans.length() ? t:ans;
        }
        return ans;
    }

    static String check(String s, int len) {
        int n = s.length();

        Set<Long> set = new HashSet<>();

        for(int i = 1; i + len - 1 <= n; i++) {
            int j = i + len - 1;
            long cur = h[j] - h[i - 1] * p[j - i + 1];
            if(set.contains(cur)) return s.substring(i - 1, j);
            set.add(cur);
        }

        return "";
    }
}

