public class Solution {
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] == 0) nums[i] = -1;
        Map<Integer, Integer> sumToIdx = new HashMap();
        sumToIdx.put(0, -1);
        int sum = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sumToIdx.containsKey(sum))
                max = Math.max(max, i - sumToIdx.get(sum));
            else sumToIdx.put(sum, i);
        }
        return max;
    }
}
