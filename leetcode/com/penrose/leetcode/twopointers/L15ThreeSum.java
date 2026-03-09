package com.penrose.leetcode.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 3Sum
 *
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Constraints:
 *   3 <= nums.length <= 3000
 *   -10^5 <= nums[i] <= 10^5
 */
public class L15ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        // TODO: implement your solution here
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        System.out.println(Arrays.toString(nums));

        for(int i = 0; i <= nums.length - 3; i++){


            System.out.println("i: " + nums[i]);
            int left = i + 1;
            int right = nums.length - 1;
            int sum;


            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }

            if(nums[i] > 0 ){
                break;
            }



            while (left < right){
                sum = nums[i] + nums[left] + nums[right];
                System.out.println(List.of(nums[i],nums[left],nums[right]));
                if(sum == 0){
                    res.add(List.of(nums[i],nums[left],nums[right]));
                    while (left < right && nums[left] == nums[left+1]){
                        left++;
                    }
                    while (right > left && nums[right] == nums[right - 1]){
                        right--;
                    }
                    left++;
                    right--;
                }else if(sum < 0){
                    left++;
                }else {
                    right--;
                }
            }

        }
        System.out.println(res);
        return res;
    }
}
