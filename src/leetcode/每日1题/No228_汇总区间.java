package leetcode.每日1题;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieli on 2021/1/10.
 */
public class No228_汇总区间 {

    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        // i 指向第 1 个区间的起始位置
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            // 到达数组边界 || 不满足连续递增
            if (j + 1 == nums.length || nums[j] + 1 != nums[j + 1]) {
                // 将 [i,j] 写入结果列表
                StringBuilder sb = new StringBuilder();
                sb.append(nums[i]);
                if (i != j) {
                    sb.append("->").append(nums[j]);
                }
                res.add(sb.toString());
                i = j + 1;
            }
        }
        return res;
    }

}
