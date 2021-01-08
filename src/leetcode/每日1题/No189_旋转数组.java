package leetcode.每日1题;

/**
 * Created by xieli on 2021/1/8.
 */
public class No189_旋转数组 {

    /**
     * 使用额外的数组, 时间复杂度 O(n), 空间复杂度 O(n)
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; i++) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }

    /**
     * 数组翻转, 时间复杂度 O(n), 空间复杂度 O(1)
     *
     * @param nums
     * @param k
     */
    public void rotate_02(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 环状替换, 时间复杂度 O(n), 空间复杂度 O(1)
     * @param nums
     * @param k
     */
    public void rotate_03(int[] nums, int k) {
        int len = nums.length;
        k %= len;
        // 记录交换位置的次数, n 个同学一共需要换 n 次
        int count = 0;

        for (int start = 0; count < len; start++) {
            // 从 0 位置开始换位子
            int cur = start;
            int pre = nums[cur];
            do {
                int next = (cur + k) % len;
                // 来到角落
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                cur = next;
                count++;
            } while (start != cur);     // 循环暂停，回到起始位置，角落无人
        }
    }

}
