//===================== coin changing "minimum" coins ===================== //
// dp
public static int minimumCoins(int[] coins, int total) {
    int n = coins.length;
    int[] count = new int[total + 1];
    int[] coin = new int[total + 1];
    count[0] = 0;
    
    for (int i = 1; i <= total; i++) {
        count[i] = Integer.MAX_VALUE - 1;
        coin[i] = -1;
    }
    
    for (int i = 0; i < n; i++) {
        for (int j = 1; j <= total; j++) {
            if (coins[i] > j) continue;
            if (count[j] > count[j - coins[i]] + 1) {
                count[j] = count[j - coins[i]] + 1;
                coin[j] = i;
            }
        }
    }
    return count[total];
}

// recursion + memorization

public static int minimumCoins(int[] coins, int total) {
    if (coins.length == 0 || total == 0) return 0;
    Map<Integer, Integer> map = new HashMap();
    return mini(coins, total, map);
}
private static int mini(int[] coins, int target, Map<Integer, Integer> map) {
    if (target == 0) return 0;
    
    if (map.containsKey(target))
        return map.get(target);
    
    int min = Integer.MAX_VALUE - 1;
    for (int i = 0; i < coins.length; i++) {
        if (coins[i] > target) continue;
        int cur = mini(coins, target - coins[i], map);
        
        if (cur < min) {
            min = cur;
        }
    }
    map.put(target, min += 1);
    return min;
}

// coin changing "number of ways" to add up to a target (no order)
public static int coinWays(int[] coins, int target) {
    int[] dp = new int[target + 1];
    dp[0] = 1;
    for (int coin : coins) {
        for (int i = 1; i <= target; i++) {
            if (i >= coin) {
                dp[i] += dp[i - coin];
            }
        }
    }
    return dp[target];
}


// the "number of possible combinations" that add up to a positive integer target. (order matters)
public int combinationSum4(int[] nums, int target) {
    if (nums == null || nums.length == 0) return 0;
    int n = nums.length;
    int[] dp = new int[target + 1];
    dp[0] = 1;
    for (int i = 1; i <= target; i++) {
        for (int j = 1; j <= n; j++) {
            if (nums[j - 1] <= i) {
                dp[i] += dp[i - nums[j - 1]];
            }
        }
    }
    return dp[target];
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
