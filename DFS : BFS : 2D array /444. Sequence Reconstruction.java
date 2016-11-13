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
