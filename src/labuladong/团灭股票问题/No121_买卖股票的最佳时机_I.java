package labuladong.团灭股票问题;

/**
 * Created by xieli on 2021/1/9.
 */
public class No121_买卖股票的最佳时机_I {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                // i - 1 = -1, 不合法
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                // 一定要跳过本次循环
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        return dp[n - 1][0];
    }

}
