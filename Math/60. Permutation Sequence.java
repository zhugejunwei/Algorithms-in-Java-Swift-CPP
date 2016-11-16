public class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList();
        int[] factorial = new int[n+1];
        int f = 1;
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            f *= i;
            factorial[i] = f;
            nums.add(i);
        }
        return dfs(new StringBuilder(), nums, factorial, n, k-1);
    }
    private String dfs(StringBuilder sb, List<Integer> nums, int[] f, int n, int k) {
        if (nums.size() == 0) return sb.toString();
        int i = k/f[n-1];
        sb.append(nums.get(i));
        nums.remove(i);
        return dfs(sb, nums, f, n-1, k - i * f[n-1]);
    }
}
