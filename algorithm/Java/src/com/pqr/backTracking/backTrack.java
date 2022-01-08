package com.pqr.backTracking;

import java.util.*;
/**
 * @file: backTrack.java
 * @time: 2021/12/30 21:08
 * @Author by Pking
 */
public class backTrack {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] candidates = new int[]{10,1,2,7,6,1,5,2};
        int target = 8;
        List<List<Integer>> res = new ArrayList<>();
        res = s.combinationSum2(candidates, target);
        for(int i = 0; i < res.size(); i++) {
            for(int j = 0; j < res.get(i).size(); j++) {
                System.out.print(res.get(i).get(j) + "\t");
            }
            System.out.println();
        }
    }
}


class Solution {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    Deque<Integer> curPath = new LinkedList<Integer>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(0, 0, target, candidates);
        return res;
    }

    public void dfs(int curPathSum, int begin, int target, int[] candidates) {
        int len = candidates.length;
        if(curPathSum > target){
            return;
        }


        if(curPathSum == target) {
            res.add(new ArrayList<Integer>(curPath));
            return;
        }

        for(int i = begin; i < len; i++) {
            //clip branch to del the repeat
            if(i > begin && candidates[i] == candidates[i - 1]) { //just preserve the first one in the same level
                continue;
            }

            curPath.offerLast(candidates[i]);
            curPathSum += candidates[i];
            dfs(curPathSum, i + 1, target, candidates);  //the nexe one of i will avoid the repeatness of using same one
            curPath.pollLast();
            curPathSum -= candidates[i];
        }
    }
}