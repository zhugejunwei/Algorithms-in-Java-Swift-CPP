public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> q = new PriorityQueue<>();
        int bound = n < k ? n : k;
        for (int j = 0; j < bound; j++) q.add(new Tuple(0, j, matrix[0][j]));
        for (int i = 0; i < k - 1; i++) {
            Tuple cur = q.poll();
            if (cur.x == n - 1) continue;
            // 逐行搜索
            q.offer(new Tuple(cur.x + 1, cur.y, matrix[cur.x + 1][cur.y]));
        }
        return q.poll().val;
    }
}

class Tuple implements Comparable<Tuple> {
    int x, y, val;
    public Tuple(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
    @Override
    public int compareTo (Tuple that) {
        return this.val - that.val;
    }
}
