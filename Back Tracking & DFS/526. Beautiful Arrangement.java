public class Solution {
    public int countArrangement(int N) {
        List<Integer>[] arr = new List[N + 1]; // idx : possible vals
        // construct all possible choices
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList();
            for (int j = 1; j <= N; j++) {
                if (i % j == 0 || j % i == 0) {
                    arr[i].add(j);
                }
            }
        }
        return helper(arr, 1, new boolean[N + 1]);
    }
    
    private int helper(List<Integer>[] arr, int i, boolean[] vis) {
        if (i == arr.length) return 1;
        int count = 0;
        for (int v : arr[i]) {
            if (vis[v]) continue;
            vis[v] = true;
            count += helper(arr, i + 1, vis);
            vis[v] = false;
        }
        return count;
    }
}

// much faster solution
// https://discuss.leetcode.com/topic/80027/beat-100-so-far-java-simple-solution/5

public class Solution {
    int[] map;
    public int countArrangement(int N) {
        map = new int[2 << N];
        int[][] arr = new int[N + 1][]; // idx : possible vals
        // construct all possible choices
        for (int i = 1; i <= N; i++) {
            List<Integer> tmp = new ArrayList();
            for (int j = 1; j <= N; j++) {
                if (i % j == 0 || j % i == 0) {
                    tmp.add(1 << j);
                }
            }
            arr[i] = new int[tmp.size()];
            for (int k = 0; k < arr[i].length; k++)
                arr[i][k] = tmp.get(k);
        }
        return helper(arr, N, 0);
    }
    private int helper(int[][] arr, int i, int vis) {
        if (map[vis] > 0) return map[vis] - 1;
        if (i == 1) return 1;
        int count = 0;
        for (int v : arr[i])
            if ((vis & v) == 0)
                count += helper(arr, i - 1, vis ^ v);
        map[vis] = count + 1;
        return count;
    }
}
