package com.pqr.search;
import java.util.*;

/**
 * @file: weekly274Q3.java
 * @time: 2022/1/2 11:07
 * @Author by Pking
 */
public class weekly274Q3 {
    public static void main(String[] args) {

        weekly274Q3Demo w1 = new weekly274Q3Demo();
        int mass = 10;
        int[] ass = new int[]{3,9,19,5,21};
        boolean res = w1.asteroidsDestroyed(mass, ass);
        System.out.println(res);
    }
}


class weekly274Q3Demo {
    public boolean asteroidsDestroyed(int mass, int[] asa) {
        Arrays.sort(asa);
        int n = asa.length;
        int sum = n;
        long ma = mass;
        List<Integer> as = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            as.add(asa[i]);
        }

        while(sum > 0) {
            int l = 0;
            int r = sum - 1;
            while(l < r) {
                int mid = l + (r - l + 1) / 2;
                if(as.get(mid) <= ma) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            System.out.println(l);
            if(as.get(l) > ma) {
                return false;
            }
            sum--;
            ma += as.get(l);
            as.remove(l);
        }

        return true;
    }
}