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

// dp
public class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] res = new int[target + 1];
        res[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int n : nums) {
                if (n > i) continue;
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
/* 终于想明白了：这个和coins 组成一个target的区别是那个顺序是无关的，而这个顺序是有关的。

那么如何理解这个顺序呢？
 
 其实就是枚举，枚举任何一个比 target 小的数作为最后一个数，这就形成了一棵树，
 
 
         2-0
       /
     1 - 1 - 0
   ／
 1 － 2 - 1 - 0
   \
    3 - 0              => 一共有7个
 
     1-1-0
   /
 2  --- 2 - 0
 
 3 - 1 - 0
 
