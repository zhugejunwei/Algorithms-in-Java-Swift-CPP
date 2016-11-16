// DP
public class Solution {
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int[] res = new int[target + 1];
        res[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int n : nums) {
                if (n > i) break;
                else res[i] += res[i-n];
            }
        }
        return res[target];
    }
}


// Recursion + Memorization
public class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    public int combinationSum4(int[] nums, int target) {
        int count = 0;
        if (target < 0) return 0;
        if (target == 0) return 1;
        if (map.containsKey(target)) return map.get(target);
        for (int n : nums) {
            count += combinationSum4(nums, target - n);
        }
        map.put(target, count);
        return count;
    }
}
