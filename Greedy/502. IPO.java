public class Solution {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        if (Profits == null || Profits.length == 0) return 0;
        int n = Profits.length;
        PriorityQueue<Tuple> q = new PriorityQueue();
        for (int i = 0; i < n; i++) {
            q.add(new Tuple(Profits[i], Capital[i]));
        }
        while (!q.isEmpty() && k-- > 0) {
            Queue<Tuple> tmp = new ArrayDeque();
            while (!q.isEmpty() && q.peek().cap > W) tmp.add(q.poll());
            if (q.isEmpty()) return W;
            Tuple cur = q.poll();
            W += cur.pro;
            while (!tmp.isEmpty()) q.add(tmp.poll());
        }
        return W;
    }
    
    class Tuple implements Comparable<Tuple> {
        int pro, cap;
        public Tuple(int pro, int cap) {
            this.pro = pro;
            this.cap = cap;
        }
        
        public int compareTo(Tuple that) {
            return that.pro - this.pro;
        }
    }
}
