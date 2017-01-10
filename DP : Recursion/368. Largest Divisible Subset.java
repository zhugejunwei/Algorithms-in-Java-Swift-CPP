public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList();
        if (n == 0) return res;
        Arrays.sort(nums);
        int[] count = new int[n];
        int[] pre = new int[n];
        Arrays.fill(count, 1);
        
        int max = 1, mi = 0; // maxCount, and it's index
        
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && count[i] < count[j] + 1) {
                    count[i] = count[j] + 1;
                    pre[i] = j;
                    
                    if (count[i] > max) {
                        max = count[i]; // maxCount
                        mi = i; // index
                    }
                }
            }
        }
        while (max > 0) {
            res.add(0, nums[mi]);
            mi = pre[mi];
            max--;
        }
        return res;
    }
}
