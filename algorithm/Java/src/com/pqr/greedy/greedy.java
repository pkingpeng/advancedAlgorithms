package com.pqr.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @file: greedy.java
 * @time: 2021/5/8 6:50 PM
 * @Author by Pking
 */
public class greedy {
    public static void main(String[] args) {
        //创建电台
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();

        //各个电台放入
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("北京");
        hashSet2.add("广州");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("大连");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        hashSet5.add("成都");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //allAreas存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //ArrayList 存放已选择的broadcast集合
        ArrayList<String> selects = new ArrayList<String>();

        //定义tempSet，在遍历过程中存放电台覆盖的地区和当前还没覆盖地区的交集
        HashSet<String> tempSet = new HashSet<String>();

        //定义给 maxKey ， 保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的 key
        String maxKey = null;
        while (allAreas.size() != 0) {
            maxKey = null;

            //遍历 broadcasts, 取出对应 key
            for (String key : broadcasts.keySet()) {

                tempSet.clear();

                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);

                tempSet.retainAll(allAreas); //tempSet 和 allAreas 集合的交集, 交集会赋给 tempSet

                //如果当前这个集合包含的未覆盖地区的数量，比 maxKey 指向的集合地区还多
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }

            if (maxKey != null) {
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }


        }

        System.out.println(selects);

    }
}
