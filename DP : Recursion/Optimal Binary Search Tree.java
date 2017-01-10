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
