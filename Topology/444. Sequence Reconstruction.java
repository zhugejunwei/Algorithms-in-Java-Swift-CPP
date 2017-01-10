public class Solution {
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        if (seqs.length == 0 || seqs == null) return org.length == 0 || org == null;
        int n = org.length;
        int[] index = new int[n + 1];
        boolean[] pair = new boolean[n];
        for (int i = 0; i < n; i++) {
            index[org[i]] = i;
        }
        for (int[] s : seqs) {
            for (int j = 0; j < s.length; j++) {
                if (s[j] > n || s[j] < 0) return false;
                if (j > 0 && index[s[j - 1]] >= index[s[j]]) return false;
                if (j > 0 && index[s[j - 1]] + 1 == index[s[j]]) pair[index[s[j - 1]]] = true;
            }
        }
        for (int i = 0; i < n - 1; i++) {
            if (!pair[i]) return false;
        }
        return true;
    }
}

// Topological sort
public class Solution {
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        if (seqs.length == 0) return org.length == 0;
        int n = org.length;
        List<List<Integer>> graph = new ArrayList(n + 1);
        int[] inDegree = new int[n + 1];
        
        // initGraph
        for (int i = 0; i <= n; i++) graph.add(new ArrayList());
        for (int[] s : seqs) {
            if (s.length == 1 && (s[0] > n || s[0] <= 0)) return false;
            else {
                for (int i = 0; i < s.length - 1; i++) {
                    if (s[i] <= 0 || s[i] > n || s[i + 1] <= 0 || s[i + 1] > n) return false;
                    inDegree[s[i + 1]]++;
                    graph.get(s[i]).add(s[i + 1]);
                }
            }
        }
        
        // bfs
        Queue<Integer> q = new ArrayDeque();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) q.offer(i);
            if (q.size() > 1) return false;
        }
        int idx = 0;
        while (!q.isEmpty()) {
            if (q.size() > 1) return false;
            int cur = q.poll();
            if (idx >= org.length || org[idx++] != cur) return false;
            for (int to : graph.get(cur)) {
                inDegree[to]--;
                if (inDegree[to] == 0) q.offer(to);
            }
        }
        return idx == org.length;
    }
}
