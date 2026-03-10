package com.penrose.leetcode.twopointers;

import java.util.ArrayList;
import java.util.List;

/**
 * 986. Interval List Intersections
 * <p>
 * You are given two lists of closed intervals, firstList and secondList,
 * where firstList[i] = [starti, endi] and secondList[j] = [startj, endj].
 * Each list of intervals is pairwise disjoint and in sorted order.
 * <p>
 * Return the intersection of these two interval lists.
 * <p>
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x
 * with a <= x <= b.
 * <p>
 * Constraints:
 * 0 <= firstList.length, secondList.length <= 1000
 * firstList.length + secondList.length >= 1
 * 0 <= starti < endi <= 10^9
 * endi < starti+1
 * 0 <= startj < endj <= 10^9
 * endj < startj+1
 */
public class L986IntervalListIntersections {

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        // TODO: implement your solution here
        List<int[]> res = new ArrayList<>();
        int i = 0;
        int j = 0;

        while(i < firstList.length && j < secondList.length){
            int a = firstList[i][0];
            int b = firstList[i][1];
            int c = secondList[j][0];
            int d = secondList[j][1];
            if(a <= d && c <= b){
                res.add(new int[]{Math.max(a,c),Math.min(b,d)});
            }
            if(b > d){
                j++;
            }else{
                i++;
            }

        }

        return res.toArray( new int[res.size()][] );
    }
}
