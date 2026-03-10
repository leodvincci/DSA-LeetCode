package com.penrose.leetcode.warmup;

/**
 * Warmup: Count Interval Overlaps
 *
 * You are given two lists of closed intervals, firstList and secondList.
 * Each list is sorted by start time and pairwise disjoint (no overlaps within the same list).
 *
 * Return the NUMBER of intersections between the two lists.
 * (You don't need to return the actual intervals — just the count.)
 *
 * Example 1:
 *   firstList  = [[0,3], [5,8]]
 *   secondList = [[1,4], [6,7]]
 *   Output: 2
 *   Explanation:
 *     [0,3] and [1,4] overlap → that's 1
 *     [5,8] and [6,7] overlap → that's 2
 *
 * Example 2:
 *   firstList  = [[1,5]]
 *   secondList = [[2,3], [4,6]]
 *   Output: 2
 *   Explanation:
 *     [1,5] and [2,3] overlap → that's 1
 *     [1,5] and [4,6] overlap → that's 2
 *     (Notice [1,5] produced TWO overlaps — don't advance past it too early!)
 *
 * Example 3:
 *   firstList  = [[1,2], [5,6]]
 *   secondList = [[3,4], [7,8]]
 *   Output: 0
 *   Explanation: No intervals overlap at all.
 *
 * Example 4:
 *   firstList  = [[1,3]]
 *   secondList = [[3,5]]
 *   Output: 1
 *   Explanation: They touch at point 3. Closed intervals include endpoints.
 *
 * Constraints:
 *   0 <= firstList.length, secondList.length <= 100
 *   Intervals are sorted and non-overlapping within each list.
 */
public class CountIntervalOverlaps {

    public int countOverlaps(int[][] firstList, int[][] secondList) {
        // TODO: implement your solution here
        int count = 0;
        int i = 0;
        int j = 0;

        while (i < firstList.length && j < secondList.length){
            int a = firstList[i][0];
            int d = secondList[j][1];

            int c = secondList[j][0];
            int b = firstList[i][1];

            if(a <= d && c <= b){
                System.out.println(Math.max(a,c) + " " + Math.min(b,d));
                count++;
            }

            if(b > d){
                j++;
            }else{
                i++;
            }

        }

        System.out.println("COUNT: " + count);
        return count;
    }
}
