public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList();
        Arrays.sort(nums);
        int n = nums.length;
        int[] count = new int[n];
        int[] pre = new int[n];
        Arrays.fill(count, 1);
        int max = 1, mi = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && count[j] + 1 > count[i]) {
                    count[i] = count[j] + 1;
                    pre[i] = j;
                    if (count[i] > max) {
                        max = count[i];
                        mi = i;
                    }
                }
            }
        }
        LinkedList<Integer> res = new LinkedList();
        while (max-- > 0) {
            res.addFirst(nums[mi]);
            mi = pre[mi];
        }
        return res;
    }
}
