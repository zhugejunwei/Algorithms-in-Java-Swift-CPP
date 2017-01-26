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


/// ----------------- recursion cpp --------------------

// A recursive function to calculate cost of optimal binary search tree
int optCost(int freq[], int i, int j)
{
    if (j < i)
        return 0;
    if (j == i)
        return freq[i];
    
    int fsum = sum(freq, i, j);
    
    int min = INT_MAX;
    
    // One by one consider all elements as root and recursively find cost
    // of the BST, compare the cost with min and update min if needed
    for (int r = i; r <= j; ++r)
    {
        int cost = optCost(freq, i, r-1) + optCost(freq, r+1, j);
        if (cost < min)
            min = cost;
    }
    return min + fsum;
}

// The main function that calculates minimum cost of a Binary Search Tree.
// It mainly uses optCost() to find the optimal cost.
int optimalSearchTree(int keys[], int freq[], int n)
{
    // Here array keys[] is assumed to be sorted in increasing order.
    // If keys[] is not sorted, then add code to sort keys, and rearrange
    // freq[] accordingly.
    return optCost(freq, 0, n-1);
}

// A utility function to get sum of array elements freq[i] to freq[j]
int sum(int freq[], int i, int j)
{
    int s = 0;
    for (int k = i; k <=j; k++)
        s += freq[k];
    return s;
}
