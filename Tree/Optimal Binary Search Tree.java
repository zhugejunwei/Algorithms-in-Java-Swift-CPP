// DP solution:
public int optimalSearchTree(int[] keys, int[] freq, int n) {
    int[][] cost = new int[n][n];
    
    // every single node, the freq cost is its own freq.
    for (int i = 0; i < n; i++) {
        cost[i][i] = freq[i];
    }
    
    for (int len = 2; len <= n; len++) { // calculate from the smallest length of trees
        for (int i = 0; i < n - len + 1 ;i++) { // the left side of the tree range
            int j = i + len - 1; // the right side of the tree range
            int min = Integer.MAX_VALUE;
            for (int k = i; k <= j; k++) {
                min = Math.min(min, (k > i ? cost[i][k - 1] : 0) +
                               (k < j ? cost[k + 1][j] : 0) + sum(freq, i, j));
            }
            cost[i][j] = min;
        }
    }
    return cost[0][n - 1];
}
private int sum(int[] freq, int l, int r) {
    int sum = 0;
    for (int i = l; i <= r; i++) {
        sum += freq[i];
    }
    return sum;
}

// recursion + memorization
public class Main
{
    static int[][] memo;
    
    public static int optimalSearchTree(int keys[], int freq[]) {
        int n = keys.length;
        memo = new int[n][n];
        return optCost(freq, 0, n - 1);
    }
    
    private static int optCost(int[] freq, int l , int r) {
        if (l > r) return 0;
        if (l == r) return freq[l];
        if (memo[l][r] != 0) return memo[l][r];
        int levelSum = sum(freq, l, r);
        int min = Integer.MAX_VALUE;
        for (int k = l; k <= r; k++) {
            int cost = optCost(freq, l, k - 1) + optCost(freq, k + 1, r);
            if (cost < min) min = cost;
        }
        memo[l][r] = min + levelSum;
        return min + levelSum;
    }
    
    private static int sum(int[] freq, int i, int j) {
        int s = 0;
        for (int k = i; k <= j; k++) s += freq[k];
        return s;
    }
    
    public static void main (String[] args) throws java.lang.Exception
    {
        int keys[] = {10, 12, 20};
        int freq[] = {34, 8, 50};
        int res = optimalSearchTree(keys, freq);
        System.out.println(res);
    }
    
}
