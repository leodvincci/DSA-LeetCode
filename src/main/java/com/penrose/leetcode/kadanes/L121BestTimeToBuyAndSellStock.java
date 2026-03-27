package com.penrose.leetcode.kadanes;

/**
 * 121. Best Time to Buy and Sell Stock
 * Pattern: Kadane's
 */
public class L121BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        // TODO: implement
        int left = 0;
        int right = 1;
        int max = 0;
        while (right < prices.length){
            if(prices[left] > prices[right]){
                left = right;
            }else{
                int dif = prices[right] - prices[left];
                max = Math.max(max,dif);
            }
            right++;
        }
        return max;
    }
}
