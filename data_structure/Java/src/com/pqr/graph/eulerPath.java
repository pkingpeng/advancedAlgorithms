package com.pqr.graph;

import java.util.*;

/**
 * @file: com.pqr.graph.eulerPath.java
 * @time: 2022/1/1 16:36
 * @Author by Pking
 */
public class eulerPath {
    public static void main(String[] args) {
        eulerPathDemo ed = new eulerPathDemo();
        int n = 2;
        int k = 2;
        String res = ed.crackSafe(n, k);
        System.out.println(res);
    }
}

class eulerPathDemo {
    //dfs回溯遍历
    Set<String> vi = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    int total;
    public String crackSafe(int n, int k) {
        total = (int)(Math.pow(k, n)) + n - 1;
        for(int i = 1; i < n; i++) {
            sb.append(0);
        }
        dfs(n, k);
        return sb.toString();
    }

    void dfs(int n, int k) {
        //System.out.println(sb.toString());
        int len = sb.length();
        if(len == total) {
            return;
        }
        for(int i = 0; i < k; i++) {
            if(!vi.contains(sb.substring(len - n + 1) + i)) {
                sb.append(i);
                vi.add(sb.substring(len - n + 1));
                dfs(n, k);
                if(len == total) {
                    break;
                }
                vi.remove(sb.substring(len - n + 1));
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
