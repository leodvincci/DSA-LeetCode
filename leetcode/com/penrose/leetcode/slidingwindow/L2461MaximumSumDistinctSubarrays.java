package com.penrose.leetcode.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 2461. Maximum Sum of Distinct Subarrays With Length K
 *
 * You are given an integer array nums and an integer k. Find the maximum subarray sum
 * of all the subarrays of nums that meet the following conditions:
 *
 *   - The length of the subarray is k, and
 *   - All the elements of the subarray are distinct.
 *
 * Return the maximum subarray sum of all the subarrays that meet the conditions.
 * If no subarray meets the conditions, return 0.
 *
 * Constraints:
 *   1 <= k <= nums.length <= 10^5
 *   1 <= nums[i] <= 10^5
 */
public class L2461MaximumSumDistinctSubarrays {

    public long maximumSubarraySum(int[] nums, int k) {
        // TODO: implement your solution here
        int maxValue = 0;
        int sum = 0;
        int left = 0;
        int right = k - 1;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < k; i++){
            System.out.println(nums[i]);
            sum+=nums[i];
            System.out.println("Sum: " + sum);
            if(map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i]) + 1);
            }else{
                map.put(nums[i], 1);
            }
        }
        if(map.size() == k){
            maxValue = sum;
        }

        while(right < nums.length - 1){
            sum-= nums[left];
            map.put(nums[left], map.get(nums[left]) - 1);
            if(map.get(nums[left]) <= 0){
                map.remove(nums[left]);
            }
            left++;

            right++;
            sum+=nums[right];
            System.out.println(nums[right]);
            map.put(nums[right], map.getOrDefault(nums[right]+1,1));

            if(map.size() == k){
                maxValue = Math.max(sum,maxValue);
            }
            System.out.println("Map Size: " + map.size());
            System.out.println(map);
            System.out.println("Left: " + left +" RIGHT: " + right);
            System.out.println("SUM: " + sum);

        }

        return maxValue;
    }
}
