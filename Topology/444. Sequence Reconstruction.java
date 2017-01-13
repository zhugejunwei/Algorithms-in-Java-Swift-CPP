public class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        if (seqs == null || seqs.size() == 0) return org == null || org.length == 0;
        int n = org.length;
        int[] idx = new int[n + 1];
        boolean[] pair = new boolean[n];
        Set<Integer> vis = new HashSet();
        
        for (int i = 0; i < n; i++) {
            idx[org[i]] = i;
        }
        
        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size(); i++) {
                vis.add(seq.get(i));
                if (seq.get(i) <= 0 || seq.get(i) > n) return false;
                if (i > 0 && idx[seq.get(i - 1)] >= idx[seq.get(i)]) return false;
                if (i > 0 && idx[seq.get(i - 1)] + 1 == idx[seq.get(i)]) pair[idx[seq.get(i - 1)]] = true;
            }
        }
        
        if (vis.size() != n) return false;
        
        for (int i = 0; i < n - 1; i++) {
            if (!pair[i]) return false;
        }
        return true;
    }
}

// Topological sort
public class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        if (org == null || org.length == 0) return seqs == null || seqs.size() == 0;
        if (seqs == null || seqs.size() == 0) return org == null || org.length == 0;
        int n = org.length;
        Set<Integer> vis = new HashSet();
        List<List<Integer>> graph = new ArrayList(n + 1);
        int[] inDegree = new int[n + 1];
        // init
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList());
        }
        
        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size(); i++) {
                vis.add(seq.get(i));
                if (i + 1 < seq.size()) {
                    if (seq.get(i) > n || seq.get(i) <= 0 || seq.get(i + 1) > n || seq.get(i + 1) <= 0) return false;
                    graph.get(seq.get(i)).add(seq.get(i + 1));
                    inDegree[seq.get(i + 1)]++;
                }
            }
        }
        
        // start point
        if (inDegree[org[0]] != 0 || vis.size() != n) return false;
        
        Queue<Integer> q = new ArrayDeque();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) q.offer(i);
        }
        
        // traverse graph
        int idx = 0;
        while (!q.isEmpty()) {
            if (q.size() != 1) return false;
            int from = q.poll();
            if (idx >= n || org[idx++] != from) return false;
            for (int to : graph.get(from)) { // all "to" nodes
                inDegree[to]--;
                if (inDegree[to] == 0) q.offer(to);
            }
        }
        
        return idx == n;
    }
}
